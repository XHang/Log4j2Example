package com.QuickStart.Example;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerContext;

/**
 * logFj2示例程序
 * @author DELL
 *
 */
public class QuickStartLog {
	public static void main(String[] args) {
		//根据记录器名字取得该记录器，如果这句代码执行两遍获取的都是同一个对象，也就是传说中的单例模式
		//另注：如果不带参数，则获取的是类的全限定类名作为Logger名称
		Logger logger=   LogManager.getLogger();
		System.out.println(logger.getName());
		logger.info("哥，666666");
		//这个当且仅当9记录器为info级别时才会发生字符串构造，省内存
		logger.error("对象的具体属性{},{}",new Date().toString(),new Date().toLocaleString());
		logger.traceExit();        
	}
}
