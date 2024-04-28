package com.hbsi.jdbc.teacher.service;

import com.hbsi.jdbc.student.entity.StudentEntity;
import com.hbsi.jdbc.teacher.dao.TeacherDao;
import com.hbsi.jdbc.teacher.dao.TeacherDaoImpl;
import com.hbsi.jdbc.teacher.entity.TeacherEntity;

import java.util.List;

public class TeacherServiceImpl implements TeacherService{

    private TeacherDao dao = new TeacherDaoImpl();

    private String sql;
    @Override
    public TeacherEntity queryById(String id) {
        this.sql = "select * from teacher where t_id=?";
        return dao.queryById(sql, id);
    }

    @Override
    public List<TeacherEntity> queryBy(TeacherEntity teacher) {
        this.sql = "select * from teacher where t_id=? and t_name=?";
        return dao.queryBy(sql, teacher);
    }
}
