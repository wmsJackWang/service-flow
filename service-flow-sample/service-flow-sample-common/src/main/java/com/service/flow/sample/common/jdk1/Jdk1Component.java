package com.service.flow.sample.common.jdk1;

import java.util.Optional;

import org.apache.tomcat.util.buf.StringUtils;

import com.service.flow.api.Flow;
import com.service.flow.sample.common.test1.Test1Input;
import com.service.flow.sample.common.test1.Test1Output;

public class Jdk1Component {

    @Flow(name = "paramsCheck_name")
    public Jdk1Output chechName(Jdk1Input input){
        Jdk1Output output = new Jdk1Output();
        if(!Optional.ofNullable(input.getParams().get("userName")).isPresent()) {
        	output.addResultMsg("code","1");
        	output.addResultMsg("paramsCheck_name", "userName is null");
        	return output;
        }
        

    	output.addResultMsg("code","0");
    	output.addResultMsg("paramsCheck_name", "userName checked success");
        return output;
    }
    
    @Flow(name = "paramsCheck_age")
    public Jdk1Output checkAge(Jdk1Input input){
        Jdk1Output output = new Jdk1Output();
        if(!Optional.ofNullable(input.getParams().get("age")).isPresent()) {
        	output.addResultMsg("code","1");
        	output.addResultMsg("paramsCheck_age", "age is null");
        	return output;
        }
        
        if(Integer.valueOf(String.valueOf(input.getParams().get("age")))<18) {
        	output.addResultMsg("code","1");
        	output.addResultMsg("paramsCheck_age", "age is not beyond 18");
        	return output;
        	
        }
        

    	output.addResultMsg("code","0");
    	output.addResultMsg("paramsCheck_name", "userName checksuccess");
        return output;
    }
}