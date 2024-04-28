package com.hbsi.jdbc.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BeanHandler {

    public static <T> T mapToBean(Class<T> tClass, Map<String, Object> map) throws InvocationTargetException, IllegalAccessException, InstantiationException {
//        Class<?> aClass = t.getClass();
        //创建一个实例对象，用于储存map中的数据
        T t = tClass.newInstance();
        Method[] declaredMethods = tClass.getDeclaredMethods();

        String methodName = null;
        String key = null;
        Set<String> keys = map.keySet();;
        Iterator<String> it = null;
        for (int i = 0; i < declaredMethods.length; i++) {
            methodName = declaredMethods[i].getName();
            if (!methodName.startsWith("set")) continue;
            it = keys.iterator();
            while (it.hasNext()) {
                key = it.next();
                if (methodName.toLowerCase().equals("set" + key)){
                    declaredMethods[i].invoke(t, map.get(key));
                    break;
                }
            }
            map.remove(key);
        }
        return t;
    }
}
