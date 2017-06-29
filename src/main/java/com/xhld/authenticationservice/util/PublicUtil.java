package com.xhld.authenticationservice.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author wgc
 * 
 */
public class PublicUtil {



  /**
   * 系统参数存储组件
   * */
  public static Map<String, String> ParamMap = new HashMap<String, String>();

  /**
   * 设置跨域http返回头
   * 
   * @param res
   */
  public static void setResponse(HttpServletResponse res) {
    res.addHeader("content-type", "application:json;charset=utf8");// Json数据格式 采用utf-8字符集
    res.addHeader("Access-Control-Allow-Origin", "*");// 解决跨域问题
    res.addHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");// Method设置为Post
    res.addHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");// x-requested-with为XMLHttpRequest为ajax请求
  }


  /**
   * 复制对象NULL 判断
   * */
  public static String[] getNullPropertyNames(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet<String>();
    for (java.beans.PropertyDescriptor pd : pds) {
      Object srcValue = src.getPropertyValue(pd.getName());
      if (srcValue == null) emptyNames.add(pd.getName());
    }
    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }
}
