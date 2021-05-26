package com.service.flow.support.yml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.service.flow.model.FlowDefintion;
import com.service.flow.model.Node;
import com.service.flow.support.FlowDefintionRegistry;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangcb
 * @ClassName YmlFlowDefintionRegistry.java
 * @Description 基于YML格式注册流程模型
 * @createTime 2020年06月21日 15:38:00
 */
public class YmlFlowDefintionRegistry implements FlowDefintionRegistry {

    private final String CLASSPATH_FLOW="flow/*.flow.yml";

    @Override
    public Map<String, FlowDefintion> registry() throws Exception{
        return registryModel();
    }

    /**
     * 注册流程模型
     * @return
     * @throws Exception
     * @description 家在所有flow路径下的*.flow.yml文件，并初始化所有的流程。
     */
    public Map<String, FlowDefintion> registryModel() throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(CLASSPATH_FLOW);
        Map<String, FlowDefintion> flowMap = new HashMap<>();
        Arrays.stream(resources).forEach(resource->{
            YamlMapFactoryBean yamlMapFactoryBean = new YamlMapFactoryBean();//使用spring的yml解析工具类。
            yamlMapFactoryBean.setResources(resource);
            yamlMapFactoryBean.afterPropertiesSet();
            Map<String, Object> object = yamlMapFactoryBean.getObject();//得到加载后的流程配置对象。其中涉及到了yml的属性加载和yml的数组属性加载
            YmlFlow flow = JSONObject.parseObject(JSON.toJSONString(object), YmlFlow.class);//将map对象使用json工具转化为流程属性对象。
            flowMap.put(flow.getId(),buildFlowDefintition(flow));//根据流程的属性对象，初始化流程定义对象，并加入到流程容器中去。
        });
        return flowMap;
    }

    private FlowDefintion buildFlowDefintition(YmlFlow flow){
        FlowDefintion flowDefintition = new FlowDefintion();
        flowDefintition.setId(flow.getId());
        flowDefintition.setName(flow.getName());
        flowDefintition.setDesc(flow.getDesc());
        flowDefintition.setInput(flow.getInput());
        flowDefintition.setOutput(flow.getOutput());
        flowDefintition.setTemp(flow.getTemp());
        flowDefintition.setStartNode(flow.getStartNode());
        Map<String, Node> nodeMap = new HashMap<>();
        flow.getNodes().forEach(tempNode -> {
            Node node = tempNode.getNode();
            nodeMap.put(node.getId(),node);
        });
        flowDefintition.setNodeMap(nodeMap);
        return flowDefintition;
    }
}
