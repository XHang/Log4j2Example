package com.log4j2WebExample.Servlct;


/**
 * 该类主要作为一个web应用程序，让log4j2在web应用程序的环境下工作
 */
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class ServlctExample extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Logger logger=LogManager.getLogger();
    public ServlctExample() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("log输出！！！");
		logger.info(new Date());
		logger.info("接受到的参数是："+request.getParameter("parameter"));
		System.out.println("当前日志记录器名字为"+logger.getName());
		System.out.println("非日志输出！！！");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
