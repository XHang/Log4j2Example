# log4j2笔记
## 常用知识点
1. 日志级别：TRACE，DEBUG，INFO，WARN，ERROR和FATAL    
2. 如果不为log4j设置配置文件，则使用最基本的配置：只显示错误信息并打印到控制台  
	这是由于控制台目的地被附加了根记录器，并且限定了输出等级只能是ERROR  
	另注：最基本的配置还包括输出样式被规定为：`%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n`  
	付：最基本的配置如果要写出来，他是酱紫的  
	
	<?xml version="1.0" encoding="UTF-8"?>
	<Configuration status="WARN">
	  <Appenders>
	    <Console name="Console" target="SYSTEM_OUT">
	      <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
	    </Console>
	  </Appenders>
	  <Loggers>
	    <Root level="error">
	      <AppenderRef ref="Console"/>
	    </Root>
	  </Loggers>
	</Configuration>
ps:如果把WARN改为trace 运行之后，你将得到一大批日志  
	因为Configuration的status其实是记录log4j自身运行的日志的。同样遵循等级制度，低等级的不会打印出来  
	但是有一点不爽，它默认打印到控制台，怎么把它保存到日志文件呢？  
	简单，在Configuration标签添加一个属性dest指定保存文件路径，即可。  
	如：`<Configuration status="TRACE"  dest="hahaha.log"> `
	 
ps:看来还是有层级的，如果把根记录器的等级设为info，低等级的TRACE，DEBUG不会显示  

PS:<Configuration>属性上加上monitorInterval属性可以动态加载配置文件。  
如：<Configuration monitorInterval="30">是每隔30s加载一次配置文件（实际上是这次记录日志和上次记录日志时间如果相差30s则重新加载一次配置文件）  
注意：有些配置是无法自动加载的，比如说插件的配置	

3：LogManager首先会找到LoggerContext加载，然后Logger从LogManager获得 继承    
4：获取根记录器：    

	Logger logger = LogManager.getLogger（LogManager.ROOT_LOGGER_NAME）;
	Logger logger = LogManager.getRootLogger（）;
5:相加性  
	如果你再根记录器上搞了一个记录器，并设置了目的地。  
	那么在记录时，该记录器除了会将记录根据自身配置保存到某个地方外，还会引用父记录器的目的地，重新记录一遍  
	某些情况确实好用吧。。。你又想保存到文件里面，又想把它打印出来。但有时也挺烦的，这时候就要在记录器标签的additivity属性为false。Example！  
`<Logger name="com.foo.Bar" level="trace" additivity="false">`

6.  log4j2配置结构  

	<?xml version="1.0" encoding="UTF-8"?>
				<Configuration>
	 				 <Properties>
	    					<Property name="name1">value</property>
	   				 		<Property name="name2" value="value2"/>
	  				</Properties>
			  <filter  ... />
	 		 <Appenders>
	    			<appender ... >
	      			<filter  ... />
	    			</appender>
	    				...
	 		 </Appenders>
	  		<Loggers>
	    			<Logger name="name1">
	     			 <filter  ... />
	   		</Logger>
	   			 ...
	    		<Root level="level">
	     			 <AppenderRef ref="name"/>
	    		</Root>
	  		</Loggers>
	</Configuration>
		
## 流跟踪
含义：  
记录方法的参数和返回值以及抛出的错误对象  



## 插件
1. Chainsaw 插件
含义：是一个日志GUI化的一个插件，如果你对浩瀚无比的日志文件感到头痛的话，可以试试用这个  
注：这个插件只能搜集FileAppender的日志
设置步骤：  
1.  添加JmDns library库文件到你的class path路径下
2. 在Configuration标签上加上一个属性：`advertiser="multicastdns"`
3. 在 appender标签（目的地标签）上加上一个属性：`advertise="true"	`
4. 要在文件目的地标签上加上适当的url
`<File name="File1" fileName="output.log" bufferedIO="false" advertiseURI="file://path/to/output.log" advertise="true">`
附上完整配置：

		<?xml version="1.0" encoding="UTF-8"?>
			<Configuration advertiser="multicastdns">
			<Appenders>
  						<File name="File1" fileName="output.log" bufferedIO="false" advertiseURI="file://path/to/output.log" advertise="true"> </File>
			</Appenders>
		</Configuration>
			
另注：
Configuration元素有一个属性：packages。值为逗号分隔的包名称，可以用它来搜索插件。  
但是由于每个类加载器只加载一次插件，所以动态加载配置文件这个改了是生效不了的  


## 配置文件元素简介
### Configuration
1. shutdownHook：指示jvm关闭时log4j是否也要狗带，默认启用，也就是自动狗带，当然你可以设置该属性为禁用
	没尝试过。
2. shutdownTimeout：指示jvm关闭时目的地任务（写日志信息的任务）和后台任务存活几秒后关闭。
	默认值为0，但是并不是使用目的地任务都听这个配置的话  
	这只是一个提示，非保证，注意啊，把这值搞的太低可能会增加未写入日志的风险！（不过本来就是0.。。。。。）  
3. strict：指示这个xml配置使用严格模式。Json配置不支持。
4. verbose：加载插件时启用调试信息
	
## 过滤器
放置位置：和appendersh,loggers 元素放在同一等级上  
作用：logger 元素中，作用是过滤一些特定的日志信息  
			放置在lappender 中，作用是拒绝或者接受打印指定级别的记录事件  
	
## 	标记	
作用：记录器可以使用不同的Markers来区分不同的记录
						
10:日志输出乱码咋办，在目的地配置设置编码，如：									
	<PatternLayout charset="GBK" pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
	
## log架构介绍

获取一个Logger 时，LogManager会找到对应的LoggerContext对象，然后获取Logger    
创建一个Logger 时，它必须和LoggerConfig关联，LoggerConfig包含了：和Logger相同的名称，父包的名称，根LoggerConfig  
注：LoggerConfig对象是由配置文件的Logger来创建的  


## log等级继承		
	
含义：一个logger引用相应的LoggerConfig，LoggerConfig又可以引用上级，从而实现相同的效果  
如果log的配置文件没有配置根LoggerConfig，将使用默认级别来处理日志（错误，打印）  
情况1：如果只是配置根记录器并且配置其日志级别和目的地  
				其他记录器不配级别，配或者不配目的地配置。
				那么其他记录器会引用根记录器的日志级别。这就是日志记录器的引用  
情况2：除了com.QuickStart.Example.QuickStartLog记录器，其他记录器都配置了等级和目的地。那么你说x记录器会引用哪个记录器的等级呢？  
答案是会引用com这个名称的记录器配置。  
一般来说这种情况：没有配置LoggerConfig记录器会引用它的父级记录器的LoggerConfig  
它的父级怎么计算？取记录器名的起始位置后的字母去匹配其他记录器名。匹配的上，就是父级记录器，匹配不上，默认父级记录器就是根记录器    
如果没有为某个名字配置记录器，那么默认也就是一个只有带这个名字的记录器配置。  
	
总结一下，一个记录器如何跟父级记录器匹配？  
1. 如果一个记录器的名字为xx.yy.zz那么首先会去匹配点之前的记录器。即以xx为名字去找对应名字的记录器。  
2. 如果一个记录器的名字是xyz,理论上说，它应该和配置上的x或者xy匹配，（xy可用的话，会去匹配xy）	。然而经过我测试。xyz这个记录器匹配不了xy ，只能匹配根记录器  
	xml代码如下  
	
		<Loggers>
  				<Logger name="xyz"  >
  		</Logger>
  				<Logger name="xy"   level="info" >
  				<AppenderRef ref="Console"/>
  		</Logger>
    			<Root level="info">
      			<AppenderRef ref="Console"/>
    	</Root>
  </Loggers>	
  
 RT，当程序获取到xyz记录器并输出日志时，不会引用xy记录器。哪怕是x记录器也没有引用（我做过试验了）
  原因。。。以后再说

## log格式化输出
含义：日志输出的格式
配置位置：目的地
举例子：

	  	<Console name="Console" target="SYSTEM_OUT">	
	      <PatternLayout charset="GBK" pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
	    </Console>
 解释：  
   1.  PatternLayout就是配置布局输出的地方了，charset是输出的字符编码，这你应该知道了吧
   2.pattern就是输出格式，这种格式是通过格式符来确定的，一个格式符是类似于“%r”这样的东西。
   另注几个格式符解释  
   
    ％r  表示程序自启动以来经过的毫秒数，握草，还蛮6的，可以根据这个来看那个方法耗时最长！
    ％t  表示发出日志请求的是那个线程
    ％c 请求记录器的名称
    ％p 日志级别  有的配置会写成：％-5p  经测算没有任何区别，权且记录一下。

## log的奇技淫巧
`logger.error("对象的具体属性{},{}",new Date().toString(),new Date().toLocaleString());`
 类似于字符串拼接,这种方式可以在记录器的等级符合的情况下才会构造字符串，避免浪费资源
    
## log中的lambda表达式
这种表达式可以在不显式检查	日志级别的情况下延迟日志记录  
代码：`logger.trace("Some long-running operation returned {}", () -> expensiveOperation());`  
 解释：如果未启用TRACE级别，则不会评估lambda表达式	  	
## Event Logging


    19：Log4j    Messages 。。这节主要讲的是输出信息的形式。
    		成然，log4j2提供了一些常用数据类型的输出，例如，字符串，例如数字，可是有时，你想输出一个Map怎么办？
    		你直接输出只能调用map对象的toString()方法。
    		其实，记录器对象提供了一个方法允许接受Message子类的对象，然后调用该子类对象的getFormattedMessage来对信息进行格式化
    		这样的话，你只要写一个子类，去实现Message，通过构造函数传进一个对象，并在getFormattedMessage方法对其格式化即可。
    		不过，其实吧，你不去实现这个类也可以达到格式化的目的。只要定义一个方法，格式化对象，返回格式化后的字符串。让记录器记录这个字符串就行了。
    		用接口的话你可以直接传对象，不用接口的话你还需要调用方法输出字符串。。
    除了实现Message接口外，log4j2还提供了一些直接可以用的Message的子类，让你直接new它传对象，就可以格式化输出了
    	FormattedMessage：用于处理格式化字符串，会动态确定消息是属于MessageFormat还是String.format。如果是，分别调用MessageFormatMessage和StringFormattedMessage
    	对其进行信息格式化。如果都不是，调用ParameterizedMessage进行信息格式化
    	LocalizedMessage：用于兼容log4j第一代。。。。。
    	LoggerNameAwareMessage：这个带有setLoggerName方法.感觉没什用，就是一个接口，充其量也就是记录记录器的名字。
    	MapMessage：这个实现了FormattedMessage接口，允许传入一个XML的格式说明符，可以格式化成XML.
    	否则Map将被格式化成key1=value1 key2=value2...所以说，直接使用MapMessage来格式化就行了，不用再定义一个什么鬼的Message子类
    	其他的我就不说了，个人感觉不怎么懂，而且看起来也毫无卵用
    20：Thread Context  感觉有点高大上，什么鱼标记，什么，什么上下文映射。
    	私以为，学到这份上，我已经掌握日志的大部分应用了。像这种鱼标记一般是高分布式的。现在接触不了
    	所以呢？这个就先占个位置，将来有用到，再补充
    21：log4j2用于Scala 语言。。。这个不在我研究范围内，略。
    22：通过变量来替换掉xml里面的变量。
    	就是把xml你觉得可以变的配置用变量替代，方便修改。
    	怎么做？
    	能亮代码就别瞎比比
    	<Properties>
				<Property  name="pattern">哈哈  %r [%t] %-5p %c - %m%n</Property>
		</Properties>
		如上，想用动态属性时就酱紫
		 <PatternLayout charset="utf-8" pattern="${pattern}"/>
		 完美替代
		 另外，log4j2的配置文件自带了两个变量，你不用设置就能用
		 一个是${log4j:configLocation}  引用的是log4j2输出配置文件的绝对路径，带文件名的，默认就是tarage的classs文件
		 一个是${log4j:configParentLocation} 引用的是log4j2的配置文件的输出路径的绝对路径
		 虽然这个路径是不带文件名的，但是这个路径是输出路径，不是你放log4j2的配置文件的那个文件夹。
		 默认这个情况下，这个文件夹是编译生成的targe文件夹里面的class。
		 ${date:YYYY}  这个也是不用设置也能用，引用的是当前的时间，带格式化的。
		 $(env:系统环境变量名}  这个引用的就是系统环境变量。啥，什么叫系统环境变量？就是安装jdk时设置的JAVA_HOME。那就是环境变量
		 $(sys:属性名}   引用的就是系统属性，通过System.getProperties();可以获取当前系统的所有属性。
		 $(java:key}  key的情况有下列几种
		 		version ==》Java version 1.7.0_67
		 		runtime==》Java(TM) SE Runtime Environment (build 1.7.0_67-b01) from Oracle Corporation
				vm==》Java HotSpot(TM) 64-Bit Server VM (build 24.65-b04, mixed mode)
				os==》Windows 7 6.1 Service Pack 1, architecture: amd64-64
				locale==》default locale: en_US, platform encoding: Cp1252（硬件信息）
				hw===》processors: 4, architecture: amd64-64, instruction sets: amd64（硬件信息）
				

				
				
				
				
		 		
		 
    23:过滤器，之前好像讲过了，但是讲的只是利用信息等级来进行信息的过滤。其实还有一种，就是利用第9点所说的标记来进行信息过滤
    大概配置是酱紫的：
    <filters>
        <MarkerFilter marker="FLOW" onMatch="ACCEPT" onMismatch="NEUTRAL"/>
        <MarkerFilter marker="EXCEPTION" onMatch="ACCEPT" onMismatch="DENY"/>
     </filters>
     24：目的地配置中的Routing 目的地配置
     25:脚本标签
     log4j2支持在部分组件中使用JSR 223脚本语言。
     比如说对于JavaScript，Groovy ，Beanshell这些脚本语言，都是直接支持的，并且只要引入该语言的jar就行了
     支持脚本的组件使用<script>, <scriptFile>, or <scriptRef>标签来完成脚本的配置。
     <script>元素包含脚本的名称，脚本的语言和脚本文本
     <scriptFile>元素包含脚本的名称，位置，语言，字符集，以及是否监视脚本文件以动态加载
     <scriptRef>元素处在<script>元素中，用于定义脚本的名称，名称是用来存储脚本和脚本引擎。
     脚本名称非必须，但有助于调试时找脚本位置。
     脚本语言必须在<script>中定义好。
     如果在<scriptFile>没有定义脚本语言，则会通过文件的后缀名判断是什么脚本语言。
     如果请求了文件监视，则只会当在Configuration元素中配置了非零的monitorInterval属性才会启用
     某些脚本文件支持把执行完毕的返回值返回到调用者，也就是java代码上。但是javaScript不支持这么做
     因为，它不允许使用返回语句，除非是在函数内，折中的做法是，返回在脚本中执行的最后一个语句的值。
     var result=1;
      result++；
      result;
      调用者将得到result的值。
      示例小程序
      <Scripts>
			    <Script name="selector" language="javascript"><![CDATA[
			            var result;
			            if (logEvent.getLoggerName().equals("JavascriptNoLocation")) {
			                result = "NoLocation";
			            } else if (logEvent.getMarker() != null && logEvent.getMarker().isInstanceOf("FLOW")) {
			                result = "Flow";
			            }
			            result;
			            ]]>
			      </Script>
    <ScriptFile name="groovy.filter" path="scripts/filter.groovy"/></Scripts>
    由于所需jar包找不到，所以本示例程序暂停
    
    26：log4j2配置引入其他配置文件。
    		如果你觉得一个log4j2的配置文件很繁多，可以把一些配置提出来，写在单独的一个xml文件，配置文件只要引入xml文件即可。
    		eg：
    		这是一个log4j-xinclude-appenders.xml文件
    		<?xml version="1.0" encoding="UTF-8"?>
			<appenders>
			  <Console name="STDOUT">
			    <PatternLayout pattern="%m%n" />
			  </Console>
			  <File name="File" fileName="${filename}" bufferedIO="true" immediateFlush="true">
			    <PatternLayout>
			      <pattern>%d %p %C{1.} [%t] %m%n</pattern>
			    </PatternLayout>
			  </File>
			</appenders>
      		引入的log配置文件
      		<?xml version="1.0" encoding="UTF-8"?>
			<configuration xmlns:xi="http://www.w3.org/2001/XInclude"
			               status="warn" name="XIncludeDemo">
			  <properties>
			    <property name="filename">xinclude-demo.log</property>
			  </properties>
			  <ThresholdFilter level="debug"/>
			  <xi:include href="log4j-xinclude-appenders.xml" />
			</configuration>
      		感觉简单的可以，就不写示例程序了。
      		
##  LOG日志的BUG
1. 如果你用这样的语句`log.error(msg, exception);`来记录Throws的话 ,在windows平台上，看到的堆栈信息都是不换行的，贼难看。事实上，在linux上看就很和谐了  
    