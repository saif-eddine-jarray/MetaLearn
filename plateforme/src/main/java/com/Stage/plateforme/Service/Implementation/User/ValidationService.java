package com.Stage.plateforme.Service.Implementation.User;


import java.util.Random;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.Stage.plateforme.Entity.User.Validation;
import com.Stage.plateforme.Repository.User.ValidationRepository;
import com.Stage.plateforme.Service.Interface.User.I_Validation;


@Service
public class ValidationService implements I_Validation {

    private final JavaMailSender mailSender;
    private final ValidationRepository validationRepository;

    
    public ValidationService(JavaMailSender mailSender, ValidationRepository validationRepository) {
        this.mailSender = mailSender;
        this.validationRepository = validationRepository;
    }

    public static String code() {
        Random rnd = new Random();
        int number = rnd.nextInt(9999);
        return String.format("%04d", number);
    }
    
    @Override
    public void sendEmail(String to, String subject, String body) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("saifjar23@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        this.mailSender.send(simpleMailMessage);
    }

    public void addNewCode(Validation validation) {
        validationRepository.save(validation);
    }

    
    public Validation Verify(String code, Long id) {
        Validation validation=validationRepository.findUserEmailAndCode(code,id);
        return validation;
    }

    public void updateCode(Validation e, String code) {
        Validation validation=validationRepository.findUserById(e.getId());
        if (validation==null){
            e.setCode(code);
            validationRepository.save(e);
        }else{
        validation.setCode(code);
        validationRepository.save(validation);
        }
   }
}
