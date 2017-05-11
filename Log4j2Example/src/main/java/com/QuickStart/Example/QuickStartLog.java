package com.QuickStart.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.Map; 
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.MapMessage;
import org.apache.logging.log4j.spi.LoggerContext;

import com.log4j2.Messages.MapMessages;


/**
 * logFj2示例程序
 * @author DELL
 *
 */
public class QuickStartLog {
	public static void main(String[] args) {  
		messagesExample();
		shouSysProperties();
	}
	public static  void example(){
		//根据记录器名字取得该记录器，如果这句代码执行两遍获取的都是同一个对象，也就是传说中的单例模式
				//另注：如果不带参数，则获取的是类的全限定类名作为Logger名称
				Logger logger=   LogManager.getLogger();
				System.out.println(logger.getName());
				logger.info("哥，666666");
				//这个当且仅当9记录器为info级别时才会发生字符串构造，省内存
				logger.error("对象的具体属性{},{}",new Date().toString(),new Date().toLocaleString());
	}
	public static void formattingParametersExample(){
		Logger logger = LogManager.getFormatterLogger("Foo");
		 
		logger.debug("Logging in user %s with birthday %s", "张三", new Date());
		logger.debug("Logging in user %1$s with birthday %2$tm %2$te,%2$tY",  "张三", new Date());
		logger.debug("Integer.MAX_VALUE = %,d", Integer.MAX_VALUE);
		logger.debug("Long.MAX_VALUE = %,d", Long.MAX_VALUE);
	}
	/**
	 * 演示log4j2复杂数据类型的输出 
	 */
	public static void messagesExample(){
		Logger logger=   LogManager.getLogger();
		Map<String,String> map=new HashMap<String,String>();
		map.put("UserName", "张三");
		map.put("Age", "11");
		map.put("ff", "11");
		map.put("dd", "11");
		map.put("gg", "11");
		logger.info(new MapMessage(map));
	}
	public  static void shouSysProperties(){
		Properties pro=System.getProperties();
		for(Map.Entry<Object,Object>  element:pro.entrySet()){
			System.out.println(element.getKey()+":"+element.getValue());
		}
	}
}
