package com.Bank.Branch.Utils;

import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.ParameterizedType;

public class Reflection {

    public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch(ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static<T> String  getClassName (T obj ){
        return obj.getClass().getSimpleName();
    }

    public static<T> String getClassName(Class<T> name){
        return name.getSimpleName();
    }
}
