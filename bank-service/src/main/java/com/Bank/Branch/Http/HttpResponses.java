package com.Bank.Branch.Http;

import com.Bank.Branch.Utils.Reflection;

public class HttpResponses {

    public static final String NOT_FOUND = "Sorry! There is no entity matching the given Id of ${id}";
    public static final String NO_RECORD_FOUND = "Sorry! No record find for the given request";
    public static final String BAD_REQUEST = "You've sent me a bad request! error occurred while processing entity";
    public static final String DUPLICATION_NOT_ALLOWED = "Sorry! I can not save multiple entities with same field attribute";
    public static final String INTERNAL_ERROR = "Ops! we encountered a problem while processing entity.";

    public static final String UPDATED = "Updated successfully!";
    public static final String DELETED = "Deleted successfully!";

    public static final String ENTITY = "Entity";


    public static <T> String getHttpResponse(T obj, String message){
        return message.replace("entity", Reflection.getClassName(obj));
    }

    public static <T, K> String notFoundResponse(T obj, K k){
        return NOT_FOUND.replace("entity", Reflection.getClassName(obj)).replace("${id}", String.valueOf(k));
    }

    public static <T> String notFoundResponse(Class<T> t){
        return NOT_FOUND.replace("entity", Reflection.getClassName(t));
    }

    public static <T> String duplicateResponse(Class<T> t, String duplicate){
        return DUPLICATION_NOT_ALLOWED.replace("entities", Reflection.getClassName(t)).replace("filed", duplicate);
    }

    public static <T> String duplicateResponse(T obj, String duplicate){
        return DUPLICATION_NOT_ALLOWED.replace("entities", Reflection.getClassName(obj)).replace("filed", duplicate);
    }

    public static <T> String errorWhileProcessing(T obj){
        return INTERNAL_ERROR.replace("entity", Reflection.getClassName(obj));
    }

    public static <T> String errorWhileProcessing(Class<T> c){
        return INTERNAL_ERROR.replace("entity", Reflection.getClassName(c));
    }

    public static <T> String badRequest(T obj){
        return BAD_REQUEST.replace("entity", Reflection.getClassName(obj));
    }

    public static String internalServerError(){
        return INTERNAL_ERROR;
    }

    public static <T> String badRequestResponse(T obj){
        return BAD_REQUEST;
    }
}
