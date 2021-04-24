package cn.kobelee.gateway.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * Description:
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 12/23/2020
 */
public class ContextHolder {
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context){
        applicationContext = context;
    }
    public static <T> T getBean(Class<T> beanType) throws BeansException{
        return applicationContext.getBean(beanType);
    }
}
