package com.Bank.Branch.Interfaces;

import java.util.List;
import java.util.Map;

public interface IHttpMethods {
     <T> T get(String url, Class<T> responseType ,Object param);
     <T> T get(String url, Class<T> responseType, Map<String,String> params);
     <T> List<T> getList(String url, Object param);
     <T> List<T> getList(String url, Map<String,String> params);

}
