package com.service.flow.support;

import com.service.flow.exception.FlowException;
import com.service.flow.model.FlowDefintion;
import com.service.flow.support.yml.YmlFlowDefintionRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangcb
 * @ClassName FlowDefintition.java
 * @Description 流程模型加载工厂
 * @createTime 2020年06月21日 15:38:00
 */
public class FlowDefintitionFactory {

    public  static List<FlowDefintionRegistry> flowDefintionRegistries = new ArrayList<>();

    public static Map<String, FlowDefintion> flowDefintitionMap = new HashMap<>();

    static {
        flowDefintionRegistries.add(new YmlFlowDefintionRegistry());
    }

    /**
     * 初始化流程加载
     * @deprecated 初始化流程容器中的所有流程属性对象，并生成流程定义对象。
     */
    public void initDefintionFactory() {
        flowDefintionRegistries.forEach(flowDefintionRegistry -> {
            try {
            	//初始化
                flowDefintitionMap.putAll(flowDefintionRegistry.registry());
            } catch (Exception e) {
                throw new FlowException("Flow loading exception",e);
            }
        });
    }
}
