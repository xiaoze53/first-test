package com.hbsi.jdbc.student.entity;

import java.util.Objects;

public class StudentEntity {

    private String s_id;
    private String s_name;
    private String s_birth;
    private String s_sex;

    public StudentEntity() {
    }

    public StudentEntity(String s_id, String s_name, String s_birth, String s_sex) {
        this.s_id = s_id;
        this.s_name = s_name;
        this.s_birth = s_birth;
        this.s_sex = s_sex;
    }

    public String getS_id() {
        return s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public String getS_birth() {
        return s_birth;
    }

    public String getS_sex() {
        return s_sex;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public void setS_birth(String s_birth) {
        this.s_birth = s_birth;
    }

    public void setS_sex(String s_sex) {
        this.s_sex = s_sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return Objects.equals(s_id, that.s_id) && Objects.equals(s_name, that.s_name) && Objects.equals(s_birth, that.s_birth) && Objects.equals(s_sex, that.s_sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s_id, s_name, s_birth, s_sex);
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "s_id='" + s_id + '\'' +
                ", s_name='" + s_name + '\'' +
                ", s_birth='" + s_birth + '\'' +
                ", s_sex='" + s_sex + '\'' +
                '}';
    }
}
