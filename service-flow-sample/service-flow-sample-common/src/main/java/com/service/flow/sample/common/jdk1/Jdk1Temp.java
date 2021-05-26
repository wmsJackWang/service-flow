package com.service.flow.sample.common.jdk1;

import java.util.Map;

import com.service.flow.model.BaseTemp;

public class Jdk1Temp extends BaseTemp{
	private Map<String ,Object> params ;
	
	
	public Map<String, Object> getParams() {
		return params;
	}
	
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}
