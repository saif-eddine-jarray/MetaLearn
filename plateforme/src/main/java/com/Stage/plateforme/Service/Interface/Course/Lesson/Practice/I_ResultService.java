package com.Stage.plateforme.Service.Interface.Course.Lesson.Practice;

import com.Stage.plateforme.Entity.Course.Lesson.Practice.Student_Practice_Result;

public interface I_ResultService {
    Student_Practice_Result createResult(Student_Practice_Result result, Long session_id, Long question_id);
    Student_Practice_Result getResultById(Long id);
}
