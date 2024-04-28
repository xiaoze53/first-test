package com.hbsi.jdbc.teacher.dao;
import com.hbsi.jdbc.student.entity.StudentEntity;
import com.hbsi.jdbc.teacher.entity.TeacherEntity;

import java.util.List;

public interface TeacherDao {
    public TeacherEntity queryById(String sql, String id);
    public List<TeacherEntity> queryBy(String sql, TeacherEntity teacher);
}
