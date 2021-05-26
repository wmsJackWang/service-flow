package com.service.flow.web.config;

import com.service.flow.index.component.BaseComponentClassLoader;
import com.service.flow.index.dto.DTOClassLoader;
import com.service.flow.support.FlowDefintitionFactory;
import com.service.flow.web.context.ComponentContext;
import com.service.flow.web.context.DtoContext;
import com.service.flow.web.context.FlowContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author zhangcb
 * @ClassName FlowConfiguration.java
 * @Description TODO 这个配置类初始化一些框架对象，分别是FlowContext、DtoContext、ComponentContext。它们都会容器对象，存放着flow对象、输入输出对象、组件对象
 * @createTime 2020年06月21日 15:38:00
 * 
 * @deprecated 问题：1. 扩展性不够，扫描的包限定死了，新项目开发的类还要创建这样一摸一样的路径下才能被加载到。
 * 
 */
@Configuration
public class FlowConfiguration {

    private String scanName = "com.service.flow";

    /*
     * @description 解析yaml文件，将文件中描述的流程对象全部加载到内存中。
     */
    @Bean
    public FlowContext flowContext(){
        FlowDefintitionFactory flowDefintitionFactory = new FlowDefintitionFactory();//加载所有的flow
        flowDefintitionFactory.initDefintionFactory();
        FlowContext flowContext = new FlowContext();//用于保存所有的flow对象
        flowContext.setFlowDefintitionMap(flowDefintitionFactory.flowDefintitionMap);
        return flowContext;
    }

    /*
     * @description 输入输出对象。加载所有被@Dto注解修饰的类
     */
    @Bean
    public DtoContext dtoContext(){
        DTOClassLoader dtoClassLoader = new DTOClassLoader();
        dtoClassLoader.load(scanName);
        DtoContext dtoContext = new DtoContext();
        dtoContext.setDtoDefinitionMap(dtoClassLoader.getDtoDefinitionMap());
        return dtoContext;
    }

    /*
     * @description 加载scanName包下所有被注解@Flow修饰的类
     */
    @Bean
    public ComponentContext componentContext(){
        BaseComponentClassLoader baseComponentClassLoader = new BaseComponentClassLoader();
        baseComponentClassLoader.load(scanName);
        ComponentContext componentContext = new ComponentContext();
        componentContext.setComponentDefinitionMap(baseComponentClassLoader.getComponentDefinitionMap());
        return componentContext;
    }

}
