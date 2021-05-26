package com.service.flow.web.api;

import com.service.flow.constant.FlowConstants;
import com.service.flow.index.component.ComponentDefinition;
import com.service.flow.index.dto.DTODefinition;
import com.service.flow.model.FlowDefintion;
import com.service.flow.model.Node;
import com.service.flow.parseing.FlowParser;
import com.service.flow.parseing.component.NodeParserEnum;
import com.service.flow.web.context.ComponentContext;
import com.service.flow.web.context.DtoContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangcb
 * @ClassName AnnotationFlowHandler.java
 * @Description TODO
 * @createTime 2020年06月30日 07:13:00
 */
@Component
public class AnnotationFlowHandler extends AbstractFlowHandler{

    @Autowired
    private FlowHandler flowHandler;

    @Autowired
    private DtoContext dtoContext;

    @Autowired
    private ComponentContext componentContext;

    @Override
    public FlowDefintion flowDefintion(String flowId) {
        FlowDefintion flowDefintion = flowHandler.flowDefintion(flowId);//加载需要执行的flow对象
        String input = flowDefintion.getInput();//获取流程的输入
        flowDefintion.setInput(dtoClassName(input));//获取input的className
        String output = flowDefintion.getOutput();
        flowDefintion.setOutput(dtoClassName(output));//获取output的className
        String temp = flowDefintion.getTemp();
        flowDefintion.setTemp(dtoClassName(temp));//获取临时变量的类名称
        Map<String, Node> nodeMap = flowDefintion.getNodeMap();
        nodeClassName(nodeMap);
        return flowDefintion;
    }

    private String dtoClassName(String dto){
        Map<String, DTODefinition> dtoDefinitionMap = dtoContext.getDtoDefinitionMap();
        DTODefinition dtoDefinition = dtoDefinitionMap.get(dto);
        if(null != dtoDefinition){
            dto = dtoDefinition.getClassName();
        }
        return dto;
    }

    /*
     * @description 将flow中的所有节点信息都设置到节点对象中去。
     */
    private void nodeClassName(Map<String, Node> nodeMap){
        Map<String, Node> nodeMapNew = new HashMap<>();
        Map<String, ComponentDefinition> componentDefinitionMap = componentContext.getComponentDefinitionMap();
        nodeMap.forEach((nodeId,node)->{
            String input = node.getInput();
            node.setInput(dtoClassName(input));
            if(NodeParserEnum.method.name().equals(node.getType())){
                String component = node.getComponent();
                ComponentDefinition componentDefinition = componentDefinitionMap.get(component);
                String compon = componentDefinition.getClassName() + FlowConstants.COLON + componentDefinition.getMethodName();
                node.setComponent(compon);
            }
        });
    }
}
