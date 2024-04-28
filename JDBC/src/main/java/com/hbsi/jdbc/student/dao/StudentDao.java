package com.hbsi.jdbc.student.dao;

import java.util.List;
import java.util.Map;

public interface StudentDao {

    public String getLastID();

    public int addStudent(String sql, List<Object> list);

    public int deleteStudent(String sql, Object id);

    public int updateStudent(String sql, Object[] objs, Object[] types);

    public Map<String, Object> queryOne(String sql, String id);

    public List<Map<String, Object>> queryByStudent(String sql, Object[] objs, Object[] types);

    public List<Map<String, Object>> queryAllStudent(String sql);

}
