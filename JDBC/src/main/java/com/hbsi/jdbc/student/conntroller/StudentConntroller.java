package com.hbsi.jdbc.student.conntroller;

import com.hbsi.jdbc.student.entity.StudentEntity;
import com.hbsi.jdbc.student.service.StudentService;
import com.hbsi.jdbc.student.service.StudentServiceImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class StudentConntroller {

    private StudentService service = new StudentServiceImpl();
    public void add(StudentEntity student) throws InvocationTargetException, IllegalAccessException {
        if (student == null)
            return;
        if (service.addStudent(student))
            System.out.println("插入成功");
        else
            System.out.println("插入失败");
    }

    public void delete(Object id){
        if (id == null) return;
        if (service.deleteStduent(id))
            System.out.println("删除成功");
        else
            System.out.println("删除失败");
    }

    public void queryOne(String id) {
        if (id == null) return;
        StudentEntity student = service.queryOne(id);
        System.out.println(student);
    }

    public void update(StudentEntity student) {
        if (student == null) return;
        if (service.update(student))
            System.out.println("修改成功");
        else
            System.out.println("修改失败");
    }

    public void query(StudentEntity student) {
        List<StudentEntity> list = student == null ? service.queryAllStudent() : service.queryByStudent(student);
        System.out.println(list);
    }

}
