package com.hbsi.jdbc.student.dao;

import com.hbsi.jdbc.framework.dao.DaoImpl;
import com.hbsi.jdbc.student.entity.StudentEntity;

import java.sql.Types;
import java.util.List;
import java.util.Map;

public class StudentDaoImpl extends DaoImpl<StudentEntity> implements StudentDao {
    @Override
    public String getLastID() {
        List<Map<String, Object>> id_from_student = this.queryAll("select s_id from student");
        return id_from_student.get(id_from_student.size() - 1).get("s_id").toString();
    }

    @Override
    public int addStudent(String sql, List<Object> list) {

        return this.saveOrUpdate(sql, list.toArray());
    }

    @Override
    public int deleteStudent(String sql, Object id) {
        Object[] objs = {id};

        return this.saveOrUpdate(sql, objs);
    }

    @Override
    public int updateStudent(String sql, Object[] objs, Object[] types) {
        int[] ts = new int[types.length];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = (int)types[i];
        }
        return this.saveOrUpdate(sql, objs, ts);
    }

    @Override
    public Map<String, Object> queryOne(String sql, String id) {
        Object[] objs = {id};
        int[] types = {Types.VARCHAR};
        return this.queryBy(sql, objs, types).get(0);
    }

    @Override
    public List<Map<String, Object>> queryByStudent(String sql, Object[] objs, Object[] types) {
        int[] ts = new int[types.length];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = (int)types[i];
        }
        return this.queryBy(sql, objs, ts);
    }

    @Override
    public List<Map<String, Object>> queryAllStudent(String sql) {
        return this.queryAll(sql);
    }
}
