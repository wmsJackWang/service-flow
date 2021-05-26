package com.service.flow.util;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author zhangcb
 * @ClassName SpleUtils.java
 * @Description SpringEl表达式验证工具类
 * @createTime 2020年06月21日 15:38:00
 */
public class SpleUtils {

    public static Object eval(String expr, Object contextValue) {
        StandardEvaluationContext conetxt = new StandardEvaluationContext(contextValue);
        ExpressionParser parser = new SpelExpressionParser();
        return parser.parseExpression(expr).getValue(conetxt);
    }

    public static void evalSet(String expr, Object contextValue, Object value) {
        StandardEvaluationContext conetxt = new StandardEvaluationContext(contextValue);
        ExpressionParser parser = new SpelExpressionParser();
        parser.parseExpression(expr).setValue(conetxt, value);
    }
    
    public static void main(String[] args) {
    	Calculation calculation= new Calculation();
    	StandardEvaluationContext context=new StandardEvaluationContext(calculation);
    		
    	ExpressionParser parser = new SpelExpressionParser();
    	parser.parseExpression("number").setValue(context, "5");
    		
    	System.out.println(calculation.cube());
	}
}



class Calculation {
	private int number;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int cube(){
		return number*number*number;
	}
	} 