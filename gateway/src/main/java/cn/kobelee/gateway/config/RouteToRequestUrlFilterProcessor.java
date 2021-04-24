package cn.kobelee.gateway.config;

import cn.kobelee.gateway.filter.global.RouteToRequestUrlFilterWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cloud.gateway.filter.RouteToRequestUrlFilter;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author Yuxiang Li
 * @date 12/15/2020
 */
@Component
@Slf4j
public class RouteToRequestUrlFilterProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException {
        if(bean instanceof RouteToRequestUrlFilter){
            return new RouteToRequestUrlFilterWrapper();
        }else{
            return bean;
        }

    }
}
