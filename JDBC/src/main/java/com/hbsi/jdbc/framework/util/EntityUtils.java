package com.hbsi.jdbc.framework.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class EntityUtils {

    public static <T> List<T> rsToEntity(Class<T> tClass, ResultSet resultSet) throws InstantiationException, IllegalAccessException, SQLException, InvocationTargetException {
        List<T> list = new ArrayList<>();
        //创建实体的实例对象
        T t = tClass.newInstance();

        ResultSetMetaData metaData = resultSet.getMetaData();
        //获取rs的列数
        int columnCount = metaData.getColumnCount();
        String columnName = null;

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                columnName = metaData.getColumnName(i);
                switch (metaData.getColumnType(i)) {
                    case Types.DATE:
                        getClassSetMethod(tClass, "set" + columnName).invoke(t, DateUtil.sqlDateToUtilDate(resultSet.getDate(i)));
                        break;
                    case Types.TIME:
                        getClassSetMethod(tClass, "set" + columnName).invoke(t, DateUtil.sqlDateToUtilDate(resultSet.getTime(i)));
                        break;
                    case Types.TIMESTAMP:
                        getClassSetMethod(tClass, "set" + columnName).invoke(t, DateUtil.sqlDateToUtilDate(resultSet.getTimestamp(i)));
                        break;
                    case Types.BIT:
                        getClassSetMethod(tClass, "set" + columnName).invoke(t, resultSet.getBoolean(i));
                        break;
                    case Types.VARCHAR:
                        getClassSetMethod(tClass, "set" +columnName).invoke(t, resultSet.getString(i));
                        break;
                    default:
                        getClassSetMethod(tClass, "set" + columnName).invoke(t, resultSet.getObject(i));
                        break;
                }
            }
            list.add(t);
        }
        return list;
    }

    public static <T> Method getClassSetMethod(Class<T> tClass, String setMethodName){
        Method[] declaredMethods = tClass.getDeclaredMethods();
        Method method = null;
        for (Method m : declaredMethods) {
            if (!m.getName().toLowerCase().equals(setMethodName)) continue;
            method = m;
            break;
        }
        return method;
    }
}
