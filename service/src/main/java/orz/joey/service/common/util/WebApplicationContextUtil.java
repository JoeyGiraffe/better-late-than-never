package orz.joey.service.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @Description: 获取Bean的工具类
 * @Author: JoeyGiraffe
 * @Date: 2019-09-12 15:39:25
 **/
@Component
public class WebApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    /**
     * Set the ApplicationContext that this object runs in.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WebApplicationContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name){
        assertApplicationContextNotNull();
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz){
        assertApplicationContextNotNull();
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz){
        assertApplicationContextNotNull();
        return getApplicationContext().getBean(name, clazz);
    }

    private static void assertApplicationContextNotNull() {
        Assert.notNull(getApplicationContext(), "ApplicationContext not initialized");
    }
}
