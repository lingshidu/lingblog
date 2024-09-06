package cn.ling.annotation;

import org.springframework.aop.framework.AopContext;

/**
 * 封装self()方法便于获取自身接口代理类
 */
public interface AopProxy <T>{
    /**
     * self()方法
     * @return default
     */
    @SuppressWarnings("unchecked")
    default T self() {
        return (T) AopContext.currentProxy();
    }
}
