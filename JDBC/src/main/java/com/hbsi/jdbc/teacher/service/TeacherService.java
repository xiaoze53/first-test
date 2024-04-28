package com.hbsi.jdbc.teacher.service;

import com.hbsi.jdbc.student.entity.StudentEntity;
import com.hbsi.jdbc.teacher.entity.TeacherEntity;

import java.util.List;

public interface TeacherService {

    public TeacherEntity queryById(String id);

    public List<TeacherEntity> queryBy(TeacherEntity teacher);
}
