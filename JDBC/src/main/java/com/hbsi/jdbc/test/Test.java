package com.hbsi.jdbc.test;

import com.hbsi.jdbc.student.conntroller.StudentConntroller;
import com.hbsi.jdbc.student.entity.StudentEntity;
import com.hbsi.jdbc.teacher.conntroller.TeacherConntroller;
import com.hbsi.jdbc.teacher.entity.TeacherEntity;

import java.lang.reflect.InvocationTargetException;

public class Test {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
//        StudentConntroller conntroller = new StudentConntroller();
//        StudentEntity student = new StudentEntity(null, "张三", "2000-01-01", "男");
//        conntroller.add(student);
//        conntroller.delete("10");
//        conntroller.queryOne("04");
//        StudentEntity student = new StudentEntity("10", "李四", "2000-01-01", "男");
//        student = new StudentEntity("11", "王五", "2001-01-01", null);
//        conntroller.update(student);
//        conntroller.query(null);
//        student = new StudentEntity(null, null, null, "男");
//        student = new StudentEntity();
//        student.setS_sex("男");
//        conntroller.query(student);
        TeacherConntroller conntroller = new TeacherConntroller();
        conntroller.queryById("01");
        conntroller.queryBy(new TeacherEntity("01", "张三"));
    }
}
