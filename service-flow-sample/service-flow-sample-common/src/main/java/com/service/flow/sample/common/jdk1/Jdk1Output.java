package com.service.flow.sample.common.jdk1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.alibaba.fastjson.JSON;
import com.service.flow.api.DTO;
import com.service.flow.model.BaseOutput;

@DTO
public class Jdk1Output extends BaseOutput{
	
	private List<HashMap<String, String>> resultMsgs ;
	
	private String isReady;
	
	public List<HashMap<String, String>> getResultMsgs() {
		return resultMsgs;
	}
	
	
	public void addResultMsg(String key,String msg) {
		
		synchronized (this) {
			isReady = Optional.ofNullable(isReady).orElseGet(()->create());
			
			resultMsgs.add((HashMap<String, String>) new HashMap<>().put(key, msg));
		}
	}


	private  String create() {
		// TODO Auto-generated method stub
		
		resultMsgs = new ArrayList<HashMap<String,String>>();
		return new String("ready");
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JSON.toJSONString(resultMsgs);
	}

}
