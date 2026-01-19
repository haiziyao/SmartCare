package com.hzy.custom.config;

import com.hzy.custom.convert.StringToBaseTypeConveter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
    public class WebMvcConfiguration implements WebMvcConfigurer {
//    @Autowired
//    private StringToItemTypeConvertor stringToItemTypeConvertor;

    @Autowired
    private StringToBaseTypeConveter stringToBaseTypeConveter;
    @Override
    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(this.stringToItemTypeConvertor);
        registry.addConverterFactory(stringToBaseTypeConveter);
    }

}