package com.service.flow.support;

import com.service.flow.model.FlowDefintion;

import java.util.Map;

/**
 * @author zhangcb
 * @ClassName FlowDefintionRegistry.java
 * @Description 流程模型加载接口 ， 流程注册器
 * @createTime 2020年06月21日 15:38:00
 */
public interface FlowDefintionRegistry {
    /**
     * flow注册接口
     * @return
     * @throws Exception
     */
    Map<String, FlowDefintion> registry() throws Exception;
}
