package com.service.flow.sample.common.jdk1;

import java.util.Map;

import com.service.flow.api.DTO;
import com.service.flow.model.BaseInput;

@DTO
public class Jdk1Input extends BaseInput{
	
	private Map<String ,Object> params ;
	
	
	public Map<String, Object> getParams() {
		return params;
	}
	
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}
