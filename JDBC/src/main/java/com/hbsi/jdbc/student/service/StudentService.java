package com.hbsi.jdbc.student.service;

import com.hbsi.jdbc.student.entity.StudentEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface StudentService {
    public boolean addStudent(StudentEntity student) throws InvocationTargetException, IllegalAccessException;

    public boolean deleteStduent(Object id);

    public StudentEntity queryOne(String id);

    public boolean update(StudentEntity student);

    public List<StudentEntity> queryByStudent(StudentEntity student);

    public List<StudentEntity> queryAllStudent();
}
