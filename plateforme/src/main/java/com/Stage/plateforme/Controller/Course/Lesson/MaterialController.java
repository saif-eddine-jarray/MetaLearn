package com.Stage.plateforme.Controller.Course.Lesson;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Stage.plateforme.Entity.Course.Lesson.Material;
import com.Stage.plateforme.Service.Implementation.Course.Lesson.MaterialService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("material")
@CrossOrigin
public class MaterialController {
    private final MaterialService materialService;
    @PostMapping("/save/id={id}")
    public ResponseEntity<Material> createMaterial(@RequestBody Material material, @PathVariable Long id) {
        return ResponseEntity.ok(materialService.createMatreial(material, id));
    }
}
