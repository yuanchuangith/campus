package com.example.server.util;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * There is no royal road to learning.
 * Description:提交实体对象中的null赋值
 */
public  class CopyObjTool {
    /**
     * 将目标源中不为空的字段过滤，将数据库中查出的数据源复制到提交的目标源中
     *
     * @param source 用id从数据库中查出来的数据源
     * @param target 提交的实体，目标源
     */
    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNoNullProperties(target));
    }

    /*
     *   新对象的哪些属性不被复制
     */
    private static String[] getNoNullProperties(Object target) {
        BeanWrapper srcBean = new BeanWrapperImpl(target);
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        Set<String> noEmptyName = new HashSet<>();
        for (PropertyDescriptor p : pds) {
            //Object value:对象属性的value值
            Object value = srcBean.getPropertyValue(p.getName());
            //System.out.println("value ---> " + value);
            if (value != null && !value.toString().equalsIgnoreCase("")){
                //System.out.println("value ----------");
                noEmptyName.add(p.getName());
            }
        }
        String[] result = new String[noEmptyName.size()];
        return noEmptyName.toArray(result);
    }

    public static void copyTo(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

}