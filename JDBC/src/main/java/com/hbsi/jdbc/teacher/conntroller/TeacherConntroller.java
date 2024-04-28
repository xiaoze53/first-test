package com.hbsi.jdbc.teacher.conntroller;

import com.hbsi.jdbc.teacher.entity.TeacherEntity;
import com.hbsi.jdbc.teacher.service.TeacherService;
import com.hbsi.jdbc.teacher.service.TeacherServiceImpl;

public class TeacherConntroller {

    private TeacherService service = new TeacherServiceImpl();

    public void queryById(String id) {
        if (id == null) return;
        System.out.println(service.queryById(id));
    }

    public void queryBy(TeacherEntity teacher) {
        System.out.println(service.queryBy(teacher));
    }
}
