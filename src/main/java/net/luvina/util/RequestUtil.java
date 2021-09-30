package net.luvina.util;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class RequestUtil {
    public static <T> T toModel(Class<T> tClass, HttpServletRequest request) {
        try {
            T t = tClass.newInstance();
            BeanUtils.populate(t, request.getParameterMap());
            return t;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("RequestUtil:toModel " + e.getMessage());
        }
        return null;
    }
}
