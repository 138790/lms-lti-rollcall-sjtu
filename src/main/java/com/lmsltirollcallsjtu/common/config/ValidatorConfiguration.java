package com.lmsltirollcallsjtu.common.config;

import org.springframework.context.annotation.Configuration;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
public class ValidatorConfiguration {

    /**
     * @author wzj
     * @createdDate 2019.8.22
     * @description 配置hibernate Validator为快速失败返回模式
     * @param
     * @result Validator
     * */
    @Bean
    public Validator validator(){

        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "true" )   //设为快速失败返回模式，即只要遇到一个校验失败便立即返回，设为false则为普通模式
                .buildValidatorFactory();

        Validator validator = validatorFactory.getValidator();

        return validator;
    }

    /**
     * @author wzj
     * @createdDate 2019.8.22
     * @description 使用校验bean的方式，没有办法校验RequestParam的内容，一般在处理Get请求(或参数比较少)的时候，需要使用MethodValidationPostProcessor的Bean。
     * 这里对MethodValidationPostProcessor进行设置Validator（因为MethodValidationPostProcessor默认不是用的Validator进行验证，Validator的配置不起作用）
     * 使用@Valid注解，对RequestParam对应的参数进行注解，是无效的，需要使用@Validated注解来使得验证生效
     * @param
     * @result MethodValidationPostProcessor
     * */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {

        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        postProcessor.setValidator(validator());
        return postProcessor;
    }
}
