package com.FlowTracing.Example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;


/**
 * 该示例程序为演示流追踪的程序
 * 值得注意的是，官网提供的示例程序中有几个对象是过时的。。
 * 本程序换为建议提供的方法
 * @author DELL
 *
 */
public class FlowTracingExample {
		
	    private Logger logger =null;
	    /**
	     * 通过配置文件加载出记录器
	     * @throws Exception
	     */
	    public FlowTracingExample() throws Exception{
	    	String config=System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"FlowTrackLog4j.xml";
			InputStream in=new FileInputStream(config);
			ConfigurationSource source=new ConfigurationSource(new FileInputStream(config));
			Configurator.initialize(null, source);
			logger= LogManager.getLogger(FlowTracingExample.class.getName());
	    }
	    private String[] messages = new String[] {
	        "Hello, World",
	        "Goodbye Cruel World",
	        "You had me at hello"
	    };
	    private Random rand = new Random(1);
	 
	    public void setMessages(String[] messages) {
	        logger.traceEntry(messages.toString());
	        this.messages = messages;
	        logger.traceExit();
	    }
	 
	    public String[] getMessages() {
	        logger.traceEntry();
	        return logger.traceExit(messages);
	    }
	 //09:59:53.231 TRACE com.FlowTracing.Example.FlowTracingExample 56 retrieveMessage - Enter
	    public String retrieveMessage() {
	        logger.traceEntry();
	 
	        String testMsg = getMessage(getKey());
	 
	        return logger. traceExit(testMsg);
	        //09:59:53.234 TRACE com.FlowTracing.Example.FlowTracingExample 60 retrieveMessage - Exit with(Hello, World)
	    }
	 
	    public void exampleException() {
	        logger.traceEntry();
	        try {
	            String msg = messages[messages.length];
	            logger.error("An exception should have been thrown");
	        } catch (Exception ex) {
	            logger.catching(ex);
	        }
	        logger.traceExit();
	    }
	 
	    public String getMessage(int key) {
	        logger.entry(key);
	 
	        String value = messages[key];
	 
	        return logger.traceExit(value);
	    }
	 
	    private int getKey() {
	        logger.traceEntry();
	        int key = rand.nextInt(messages.length);
	        return logger.traceExit(key);
	    }
}

