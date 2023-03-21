package cc.sample.batch.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

import cc.sample.batch.common.helper.ApplicationContextHelper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BeanUtils {
    public static <T> T getBean(String beanName, Class<T> classType) {
        ApplicationContext applicationContext = ApplicationContextHelper.getApplicationContext();
        return applicationContext.getBean(beanName, classType);
    }

    public static PlatformTransactionManager getTransactionManagerBean(String key) {
        return getBean(String.format("%sTransactionManager", key), PlatformTransactionManager.class);
    }
}