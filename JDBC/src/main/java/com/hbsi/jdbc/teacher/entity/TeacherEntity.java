package com.hbsi.jdbc.teacher.entity;

public class TeacherEntity {

    private String t_id;
    private String t_name;

    public TeacherEntity() {
    }

    public TeacherEntity(String t_id, String t_name) {
        this.t_id = t_id;
        this.t_name = t_name;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "t_id='" + t_id + '\'' +
                ", t_name='" + t_name + '\'' +
                '}';
    }
}
