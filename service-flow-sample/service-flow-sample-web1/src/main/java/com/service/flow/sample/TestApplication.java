package com.service.flow.sample;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.service.flow.api.IFlowHandler;
import com.service.flow.model.BaseOutput;
import com.service.flow.model.BaseTemp;
import com.service.flow.sample.common.jdk1.Jdk1Input;
import com.service.flow.sample.common.jdk1.Jdk1Output;
import com.service.flow.sample.common.test1.Test1Input;
import com.service.flow.sample.common.test2.Test2Input;
import com.service.flow.sample.common.test3.Test3Input;
import com.service.flow.sample.common.test4.RefundRequest;
import com.service.flow.web.api.AnnotationFlowHandler;
import com.service.flow.web.api.FlowHandler;

/**
 * @author zhangcb
 * @ClassName TestApplication.java
 * @Description TODO
 * @createTime 2020年06月19日 08:00:00
 */
@SpringBootApplication(scanBasePackages = {"com.service.flow"})
public class TestApplication {
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TestApplication.class, args);
//        test1(applicationContext);
        //test2(applicationContext);
        //test3(applicationContext);
        //test4(applicationContext);
//        test5(applicationContext);
        //test6(applicationContext);
//        testRefund(applicationContext);
        testJdk1(applicationContext);
    }

    private static void testJdk1(ConfigurableApplicationContext applicationContext) {
		// TODO Auto-generated method stub
    	HashMap<String, Object> requestParams = new HashMap<String, Object>();
    	requestParams.put("userName", "jackdking");
    	requestParams.put("age", 18);
    	Jdk1Input input = new Jdk1Input();
    	input.setParams(requestParams);
    	IFlowHandler flowHandler = applicationContext.getBean(AnnotationFlowHandler.class);
        BaseOutput out = flowHandler.execute("jdk1", input);
        System.out.println(out.toString());
		
	}

	private static void testRefund(ConfigurableApplicationContext applicationContext) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setAccount("1111");
        refundRequest.setName("zhangsan");
        refundRequest.setMoney("20");
        IFlowHandler flowHandler = applicationContext.getBean(AnnotationFlowHandler.class);
        flowHandler.execute("refund", refundRequest);
    }

    private static void test6(ConfigurableApplicationContext applicationContext) {
        Test3Input input = new Test3Input();
        input.setCount(0);
        IFlowHandler flowHandler = applicationContext.getBean(AnnotationFlowHandler.class);
        flowHandler.execute("test6", input);
    }

    private static void test5(ConfigurableApplicationContext applicationContext) {
        Test3Input input = new Test3Input();
        input.setCount(0);
        IFlowHandler flowHandler = applicationContext.getBean(FlowHandler.class);
        flowHandler.execute("test5", input);
    }

    private static void test4(ConfigurableApplicationContext applicationContext) {
        Test3Input input = new Test3Input();
        input.setCount(0);
        IFlowHandler flowHandler = applicationContext.getBean(FlowHandler.class);
        flowHandler.execute("test4", input);
    }

    private static void test3(ConfigurableApplicationContext applicationContext) {
        Test3Input input = new Test3Input();
        input.setCount(0);
        IFlowHandler flowHandler = applicationContext.getBean(FlowHandler.class);
        flowHandler.execute("test3", input);
    }


    public static void test1(ApplicationContext applicationContext){
        Test1Input input = new Test1Input();
        input.setCount(0);
        IFlowHandler flowHandler = applicationContext.getBean(FlowHandler.class);
        flowHandler.execute("test1", input);
    }

    private static void test2(ApplicationContext applicationContext) {
        Test2Input input = new Test2Input();
        input.setCount(0);
        IFlowHandler flowHandler = applicationContext.getBean(FlowHandler.class);
        flowHandler.execute("test2", input);
    }

}
