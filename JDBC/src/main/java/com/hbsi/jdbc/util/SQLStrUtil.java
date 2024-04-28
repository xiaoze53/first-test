package com.hbsi.jdbc.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Types;
import java.util.List;

public class SQLStrUtil {

    /**
     * @param list 拼接对应的参数值，如果不需要可以以null来代替
     * @param <T>
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <T> String insertSQLStr(T t, List<Object> list) throws InvocationTargetException, IllegalAccessException {
        StringBuffer insertStr = new StringBuffer("(");
        StringBuffer valuesStr = new StringBuffer(" values(");
        Class<?> aClass = t.getClass();
        //获取自身所有成员方法
        Method[] declaredMethods = aClass.getDeclaredMethods();

        Field[] declaredFields = aClass.getDeclaredFields();
        /*
        方法筛选
        只需要get+field的方法即可
         */
        for (int i = 0; i < declaredMethods.length; i++) {
            String name = declaredMethods[i].getName();
            if (!name.startsWith("get"))
                continue;
            for (int j = 0; j < declaredFields.length; j++) {
                String name1 = declaredFields[j].getName();
                if (!name.toLowerCase().equals("get" + name1))
                    continue;
                Object o = declaredMethods[i].invoke(t);
                if (o != null) {
                    insertStr.append(name1 + ",");
                    valuesStr.append("?,");
                    if (list != null)
                        list.add(o);
                }
            }
        }

        //删除最后一个valuesStr的逗号
        valuesStr.deleteCharAt(valuesStr.length() - 1);
        insertStr.deleteCharAt(insertStr.length() - 1);
        //添加括号做闭合处理
        insertStr.append(")");
        valuesStr.append(")");
        return insertStr.append(valuesStr).toString();
    }

    public static <T> String updateSQLStr(T t, List<Object> list, List<Object> types) throws InvocationTargetException, IllegalAccessException {
        StringBuffer updateStr = new StringBuffer(" set");
        StringBuffer whereStr = new StringBuffer(" where");
        //获取类对象
        Class<?> aClass = t.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        Field[] declaredFields = aClass.getDeclaredFields();
        boolean[] flags = new boolean[declaredFields.length];
        String methodName = null;
        String fieldName = null;
        Object id = null;
        String appendStr = null;
        StringBuffer sb = null;
        boolean f = false;
        for (int i = 0; i < declaredMethods.length; i++) {
            methodName = declaredMethods[i].getName();
            if (!methodName.startsWith("get")) continue;
            for (int j = 0; j < declaredFields.length; j++) {
                if (flags[j]) continue;
                fieldName = declaredFields[j].getName();
                if (methodName.toLowerCase().equals("get" + fieldName)) {
                    Object o = declaredMethods[i].invoke(t);
                    if (o == null) continue;
                    //标记已经使用过的属性对象
                    flags[j] = true;
                    f = methodName.toLowerCase().contains("id");
                    appendStr = " " + fieldName + "=?,";
                    sb = f ? whereStr : updateStr;
                    sb.append(appendStr);


                    if (f) id = o;
                    else list.add(o);
                    String className = o.getClass().getName();
                    switch (className.substring(className.lastIndexOf(".") + 1).toLowerCase()) {
                        case "string":
                            types.add(Types.VARCHAR);
                            break;
                        case "date":
                            types.add(Types.DATE);
                            break;
                        case "boolean":
                            types.add(Types.BIT);
                            break;
                        default:
                            types.add(Types.DISTINCT);
                            break;
                    }

                }

            }
        }
        updateStr.deleteCharAt(updateStr.length() - 1);
        updateStr.append(whereStr);
        updateStr.deleteCharAt(updateStr.length() - 1);
        list.add(id);
        return updateStr.toString();
    }

    /**
     *
     * @param t 要查询的条件
     * @param list 将条件按顺序存放的集合
     * @param types 将条件类型按顺序存放的集合
     * @return
     * @param <T>
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <T> String selectSQLStr(T t, List<Object> list, List<Object> types) throws InvocationTargetException, IllegalAccessException {
        if (t == null) return null;

        Class<?> aClass = t.getClass();
        //获取完整对象名
        String allClassName = aClass.getName();
        //获取直接对象名
        String className = allClassName.substring(allClassName.lastIndexOf(".") + 1, allClassName.length() - 6).toLowerCase();

        StringBuffer selectStr = new StringBuffer("select * from " + className + " where 1=1");

        Method[] declaredMethods = aClass.getDeclaredMethods();
        Field[] declaredFields = aClass.getDeclaredFields();
        String methodName = null;
        String fieldName = null;
        for (int i = 0; i < declaredMethods.length; i++) {
            methodName = declaredMethods[i].getName();
            if (!methodName.startsWith("get")) continue;
            for (int j = 0; j < declaredFields.length; j++) {
                fieldName = declaredFields[j].getName();
                if (methodName.toLowerCase().equals("get" + fieldName)){
                    Object invoke = declaredMethods[i].invoke(t);
                    if (invoke != null) {
                        selectStr.append(" and " + fieldName + "=?");
                        list.add(invoke);

                        String fieldType = invoke.getClass().getName();
                        switch (fieldType.substring(fieldType.lastIndexOf(".") + 1).toLowerCase()) {
                            case "string":
                                types.add(Types.VARCHAR);
                                break;
                            case "date":
                                types.add(Types.DATE);
                                break;
                            case "boolean":
                                types.add(Types.BIT);
                                break;
                            default:
                                types.add(Types.DISTINCT);
                                break;
                        }
                    }
                }
            }
        }
        return selectStr.toString();
    }

}
