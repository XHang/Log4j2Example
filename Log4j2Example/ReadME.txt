日志级别：TRACE，DEBUG，INFO，WARN，ERROR和FATAL
1:如果不为log4j设置配置文件，则使用最基本的配置：只显示错误信息并打印到控制台
这是由于控制台目的地被附加了根记录器，并且限定了输出等级只能是ERROR
另注：最基本的配置还包括输出样式被规定为：%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n
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
	但是有一点不爽，它默认打印到控制台，怎么把它保存到日志文件呢？、
	简单，在Configuration标签添加一个属性dest指定保存文件路径，即可。
	如：<Configuration status="TRACE"  dest="hahaha.log">
ps:看来还是有层级的，如果把根记录器的等级设为info，低等级的TRACE，DEBUG不会显示
PS:<Configuration>属性上加上monitorInterval属性可以动态加载配置文件。
如：<Configuration monitorInterval="30">是每隔30s加载一次配置文件（实际上是这次记录日志和上次记录日志时间如果相差30s则重新加载一次配置文件）
		注意：有些配置是无法自动加载的，比如说插件的配置		
2：流跟踪，看起来好高大上，啃了一点发现其实就是记录方法的参数和返回值以及抛出的错误对象。由于官方文档的教程糟糕。。。本示例延期
	我胡汉三又回来了，唔，用了官方教程copy代码，去除了过时的方法和不知什么鬼的JSon对象，的确是可以跟踪方法的输入和输出的。
	不过,参数一旦复杂，记录就会稍显麻烦。
3：LogManager首先会找到LoggerContext加载，然后Logger从LogManager获得 继承
4：获取根记录器：
	Logger logger = LogManager.getLogger（LogManager.ROOT_LOGGER_NAME）;
	Logger logger = LogManager.getRootLogger（）;
5:相加性
	如果你再根记录器上搞了一个记录器，并设置了目的地。
	那么在记录时，该记录器除了会将记录根据自身配置保存到某个地方外，还会引用父记录器的目的地，重新记录一遍
	某些情况确实好用吧。。。你又想保存到文件里面，又想把它打印出来。但有时也挺烦的，这时候就要在记录器标签的additivity属性为false。Example！
	<Logger name="com.foo.Bar" level="trace" additivity="false">
6：Chainsaw 插件。。。。其实就是一个日志GUI化的一个插件，如果你对浩瀚无比的日志文件感到头痛的话，可以试试用这个
		我不想配置了，网络太渣，如果感兴趣的可以去看看
		好吧，说一下官网上的配置
		首先注意：这个插件只能搜集FileAppender的日志
		添加JmDns library库文件到你的class path路径下
		在Configuration标签上加上一个属性：advertiser="multicastdns"
		在 appender标签（目的地标签）上加上一个属性：advertise="true"
		如果这个插件基于FileAppender的配置，还要在文件目的地标签上加上适当的url
		例：<File name="File1" fileName="output.log" bufferedIO="false" advertiseURI="file://path/to/output.log" advertise="true">
		附上完整配置：
		<?xml version="1.0" encoding="UTF-8"?>
			<Configuration advertiser="multicastdns"></Configuration>
			<Appenders>
  						<File name="File1" fileName="output.log" bufferedIO="false" advertiseURI="file://path/to/output.log" advertise="true"> </File>
			</Appenders>
			
7：Configuration元素有一个属性：packages，官网介绍为用逗号分隔的包名称，可以用它来搜索插件，每个类加载器只加载一次插件，
	因此更改该配置额可能无法通过monitorInterval属性动态生效。。。
	有些配置文件是这么写的，老实说，我不知道它能干嘛  packages="org.apache.logging.log4j.test"
以下Configuration元素介绍（不全，我只介绍我不懂，其他我懂得就不介绍了，我相信你们也看得懂的）
	shutdownHook：指示jvm关闭时log4j是否也要狗带，默认启用，也就是自动狗带，当然你可以设置该属性为禁用
	没尝试过。
	shutdownTimeout：指示jvm关闭时目的地任务（写日志信息的任务）和后台任务存活几秒后关闭。默认值为0，但是并不是使用目的地任务都听这个配置的话
	这只是一个提示，非保证，注意啊，把这值搞的太低可能会增加未写入日志的风险！（不过本来就是0.。。。。。）
	strict：指示这个xml配置使用严格模式。Json配置不支持。
	verbose：加载插件时启用调试信息
8：过滤器：
	放置位置：	和appendersh,loggers 元素放在同一等级上。。作用：记录事件传递到LoggerConfig之前接受或拒绝事件
						logger 元素中，作用是这些配置过的记录器可以接受或者拒绝来自指定loggers的记录事件（什么鬼）
						appender 中，作用是拒绝或者接受打印指定级别的记录事件
9:标记，记录器可以使用不同的Markers来区分不同的记录。
10:日志输出乱码咋办，在目的地配置设置编码，如：									
	<PatternLayout charset="GBK" pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
	
	接下来是什么鬼的log4j架构介绍
	获取一个Logger 时，LogManager会找到对应的LoggerContext对象，然后获取Logger
	创建一个Logger 时，它必须和LoggerConfig关联，LoggerConfig包含了：a:和Logger相同的名称，父包的名称，根LoggerConfig
		注：LoggerConfig对象是由配置文件的Logger来创建的
	
	
	


