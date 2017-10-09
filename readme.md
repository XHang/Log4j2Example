# log4j2在web环境下的应用  
1. 必须添加额外的依赖 log4j2-web  
2. log4j2关闭钩子在web应用中将被禁用  

## 指定log4j配置文件
1. 可以通过在web.inf中配置一个log4jConfiguration的上下文参数，指定配置文件，比如说在log4jConfiguration里包含logging.xml，将在web根目录查找对应名称的文件  
eg:    

			<context-param>
						<param-name >log4jConfiguration</param-name>
						<param-value>file:///E:\log4j2.xml</param-value>
			</context-param>
      

2. 如果没有配置log4jConfiguration，将搜索web-inf里面以log4j2开头的文件。  
	如果找到多个文件，且存在log4j2-name开头的文件(name是web应用程序的名称)，则使用该配置文件，否则使用第一个找到的文件  

3. 就算你不配配置文件的位置，以下的配置你还是省不了的  

	<!--最后配置一个监听器，监听应web容器启动时加载配置文件-->
   <listener>  
    		<listener-class>org.apache.logging.log4j.web.Log4jServletContextListener</listener-class>  
	</listener> 
	<filter>
        	<filter-name>log4jServletFilter</filter-name>
        	<filter-class>org.apache.logging.log4j.web.Log4jServletFilter</filter-class>
	</filter>
	<filter-mapping>
	    		<filter-name>log4jServletFilter</filter-name>
	    		<url-pattern>/*</url-pattern>
	</filter-mapping>

附：过滤器的作用系：  
	1. 负责web应用程序启动完毕时清除loggerContext;  
    2. 在处理请求之前设置loggercontext,处理请求后将其清除  
    3. 在web应用程序关闭时设置loggercontext。     
## 小贴士
1. 如果在log4j2.xml里面只写保存日志文件的文件名的话，默认是保存在tomcat安装目录的bin目录下。  
其他server服务器未测试。  
2. 在server3.0的web应用环境中  
  用配置监听器，会自动启动。如果你想禁用自动启动的话，就在web.xml里面设置以下内容  
  
	  			<context-param>
	    				<param-name>isLog4jAutoInitializationDisabled</param-name>
	    				<param-value>true</param-value>
				</context-param>
	
3. Tomcat7会忽略log4j*.jar的jar文件，从而使日志记录失效。  
	如果你不幸遇到日志不生效的情况，尝试更改catalina.properties，在jarsToSkip属性中删除“log4j * .jar.

		




	
	