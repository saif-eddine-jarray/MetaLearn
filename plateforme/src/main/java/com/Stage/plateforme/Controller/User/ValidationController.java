package com.Stage.plateforme.Controller.User;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Stage.plateforme.Entity.User.Validation;
import com.Stage.plateforme.Service.Implementation.User.UserService;
import com.Stage.plateforme.Service.Implementation.User.ValidationService;
import com.Stage.plateforme.Service.Interface.User.I_Validation;

@RestController
@RequestMapping("login")
@CrossOrigin
public class ValidationController {
    
    private final I_Validation emailSender;
    private ValidationService validationService;
    private UserService userService;
    private String code;

    @Autowired
    public ValidationController(I_Validation emailSender,ValidationService validationService, UserService userService) {
        this.emailSender = emailSender;
        this.validationService = validationService;
        this.userService = userService;
    }
    
    private String code() {
        Random rnd = new Random();
        int number = rnd.nextInt(9999);
        return String.format("%04d", number);
    }

    @PostMapping("/validation")
    public ResponseEntity<String> sendEmail(@RequestBody Validation id) {
        code=code();
        validationService.updateCode(id, code);
        this.emailSender.sendEmail(userService.getEmail(id.getId()), "Plateform_Sign_in",
        "Welcome to our Plateform. Here's your code : "+code);
        return ResponseEntity.ok("success");
    }
    
    @PostMapping("/signin")
    public ResponseEntity<String> verifyCode(@RequestParam String code,@RequestParam Long id) {
        Validation validation= validationService.Verify(code,id);
        if(validation!=null){
            userService.enableUser(id);
            return ResponseEntity.ok("true");
        }else{
            return ResponseEntity.ok("false");
        }
    }
}
