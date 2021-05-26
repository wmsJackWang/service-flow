package com.service.flow.model;

import com.service.flow.util.BeanUtils;

public class BaseTransfer {
	
	
	public  void copyPropertiesTo(Object target) {
		
		BeanUtils.copyProperties(this,target);
	}

}
