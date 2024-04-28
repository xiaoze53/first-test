package com.hbsi.jdbc.student.service;

import com.hbsi.jdbc.student.dao.StudentDao;
import com.hbsi.jdbc.student.dao.StudentDaoImpl;
import com.hbsi.jdbc.student.entity.StudentEntity;
import com.hbsi.jdbc.util.BeanHandler;
import com.hbsi.jdbc.util.SQLStrUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Array;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService {
    private StudentDao dao = new StudentDaoImpl();
    private String sql;

    @Override
    public boolean addStudent(StudentEntity student) {
        this.sql = "insert into student";
        List<Object> list = new ArrayList<>();


        int id = Integer.parseInt(dao.getLastID()) + 1;
        if (id > 9)
            student.setS_id(id + "");
        else
            student.setS_id("0" + id);
        //通过对象获取class对象
        Class<? extends StudentEntity> aClass = student.getClass();
        //获取所有的成员方法（包含父类的方法）
//        Method[] methods = aClass.getMethods();

        try {
            sql += SQLStrUtil.insertSQLStr(student, list);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return dao.addStudent(sql, list) == 1 ? true : false;
    }

    @Override
    public boolean deleteStduent(Object id) {
        sql = "delete from student where s_id = ?";

        return dao.deleteStudent(sql, id) == 1 ? true : false;
    }

    @Override
    public StudentEntity queryOne(String id) {
        this.sql = "select * from student where s_id=?";
        Map<String, Object> map = dao.queryOne(sql, id);
        try {
            return BeanHandler.mapToBean(StudentEntity.class, map);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(StudentEntity student) {
        //update tableName set columnName1=?, columnName2=?... where s_id=?
        this.sql = "update student";
        List<Object> list = new ArrayList<>();
        List<Object> types = new ArrayList<>();
        try {
            this.sql += SQLStrUtil.updateSQLStr(student, list, types);
            return dao.updateStudent(sql, list.toArray(), types.toArray()) == 1 ? true : false;
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StudentEntity> queryByStudent(StudentEntity student) {
        ArrayList<Object> list = new ArrayList<>();
        ArrayList<Object> types = new ArrayList<>();
        ArrayList<StudentEntity> entities = new ArrayList<>();
        try {
            this.sql = SQLStrUtil.selectSQLStr(student, list, types);
            List<Map<String, Object>> maps = this.dao.queryByStudent(sql, list.toArray(), types.toArray());
            for (Map<String, Object> map : maps) {
                entities.add(BeanHandler.mapToBean(StudentEntity.class, map));
            }
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
        return entities;
    }

    @Override
    public List<StudentEntity> queryAllStudent() {
        this.sql = "select * from student";
        List<Map<String, Object>> maps = dao.queryAllStudent(sql);
        ArrayList<StudentEntity> studentEntities = new ArrayList<>();

        try {
            for (Map<String, Object> map : maps) {
                studentEntities.add(BeanHandler.mapToBean(StudentEntity.class, map));
            }
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }

        return studentEntities;
    }

}
