package com.hbsi.jdbc.teacher.dao;

import com.hbsi.jdbc.framework.dao.Dao;
import com.hbsi.jdbc.framework.dao.DaoImpl;
import com.hbsi.jdbc.student.entity.StudentEntity;
import com.hbsi.jdbc.teacher.entity.TeacherEntity;

import java.util.List;

public class TeacherDaoImpl implements TeacherDao{
    private Dao<TeacherEntity> dao = new DaoImpl<>();


    @Override
    public TeacherEntity queryById(String sql, String id) {

        return dao.queryById(sql, id, TeacherEntity.class);
    }

    @Override
    public List<TeacherEntity> queryBy(String sql, TeacherEntity teacher) {
        return dao.queryBy(sql, teacher);
    }
}
