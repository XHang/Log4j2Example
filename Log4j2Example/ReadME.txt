��־����TRACE��DEBUG��INFO��WARN��ERROR��FATAL
1:�����Ϊlog4j���������ļ�����ʹ������������ã�ֻ��ʾ������Ϣ����ӡ������̨
�������ڿ���̨Ŀ�ĵر������˸���¼���������޶�������ȼ�ֻ����ERROR
��ע������������û����������ʽ���涨Ϊ��%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n
������������������Ҫд���������ǽ��ϵ�
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
ps:�����WARN��Ϊtrace ����֮���㽫�õ�һ������־
	��ΪConfiguration��status��ʵ�Ǽ�¼log4j�������е���־�ġ�ͬ����ѭ�ȼ��ƶȣ��͵ȼ��Ĳ����ӡ����
	������һ�㲻ˬ����Ĭ�ϴ�ӡ������̨����ô�������浽��־�ļ��أ���
	�򵥣���Configuration��ǩ���һ������destָ�������ļ�·�������ɡ�
	�磺<Configuration status="TRACE"  dest="hahaha.log">
ps:���������в㼶�ģ�����Ѹ���¼���ĵȼ���Ϊinfo���͵ȼ���TRACE��DEBUG������ʾ
PS:<Configuration>�����ϼ���monitorInterval���Կ��Զ�̬���������ļ���
�磺<Configuration monitorInterval="30">��ÿ��30s����һ�������ļ���ʵ��������μ�¼��־���ϴμ�¼��־ʱ��������30s�����¼���һ�������ļ���
		ע�⣺��Щ�������޷��Զ����صģ�����˵���������		
2�������٣��������øߴ��ϣ�����һ�㷢����ʵ���Ǽ�¼�����Ĳ����ͷ���ֵ�Լ��׳��Ĵ���������ڹٷ��ĵ��Ľ̳���⡣������ʾ������
	�Һ������ֻ����ˣ������˹ٷ��̳�copy���룬ȥ���˹�ʱ�ķ����Ͳ�֪ʲô���JSon���󣬵�ȷ�ǿ��Ը��ٷ��������������ġ�
	����,����һ�����ӣ���¼�ͻ������鷳��
3��LogManager���Ȼ��ҵ�LoggerContext���أ�Ȼ��Logger��LogManager��� �̳�
4����ȡ����¼����
	Logger logger = LogManager.getLogger��LogManager.ROOT_LOGGER_NAME��;
	Logger logger = LogManager.getRootLogger����;
5:�����
	������ٸ���¼���ϸ���һ����¼������������Ŀ�ĵء�
	��ô�ڼ�¼ʱ���ü�¼�����˻Ὣ��¼�����������ñ��浽ĳ���ط��⣬�������ø���¼����Ŀ�ĵأ����¼�¼һ��
	ĳЩ���ȷʵ���ðɡ����������뱣�浽�ļ����棬���������ӡ����������ʱҲͦ���ģ���ʱ���Ҫ�ڼ�¼����ǩ��additivity����Ϊfalse��Example��
	<Logger name="com.foo.Bar" level="trace" additivity="false">
6��Chainsaw �������������ʵ����һ����־GUI����һ������������Ժ���ޱȵ���־�ļ��е�ͷʹ�Ļ����������������
		�Ҳ��������ˣ�����̫�����������Ȥ�Ŀ���ȥ����
		�ðɣ�˵һ�¹����ϵ�����
		����ע�⣺������ֻ���Ѽ�FileAppender����־
		���JmDns library���ļ������class path·����
		��Configuration��ǩ�ϼ���һ�����ԣ�advertiser="multicastdns"
		�� appender��ǩ��Ŀ�ĵر�ǩ���ϼ���һ�����ԣ�advertise="true"
		�������������FileAppender�����ã���Ҫ���ļ�Ŀ�ĵر�ǩ�ϼ����ʵ���url
		����<File name="File1" fileName="output.log" bufferedIO="false" advertiseURI="file://path/to/output.log" advertise="true">
		�����������ã�
		<?xml version="1.0" encoding="UTF-8"?>
			<Configuration advertiser="multicastdns"></Configuration>
			<Appenders>
  						<File name="File1" fileName="output.log" bufferedIO="false" advertiseURI="file://path/to/output.log" advertise="true"> </File>
			</Appenders>
			
7��ConfigurationԪ����һ�����ԣ�packages����������Ϊ�ö��ŷָ��İ����ƣ��������������������ÿ���������ֻ����һ�β����
	��˸��ĸ����ö�����޷�ͨ��monitorInterval���Զ�̬��Ч������
	��Щ�����ļ�����ôд�ģ���ʵ˵���Ҳ�֪�����ܸ���  packages="org.apache.logging.log4j.test"
����ConfigurationԪ�ؽ��ܣ���ȫ����ֻ�����Ҳ����������Ҷ��þͲ������ˣ�����������Ҳ���ö��ģ�
	shutdownHook��ָʾjvm�ر�ʱlog4j�Ƿ�ҲҪ������Ĭ�����ã�Ҳ�����Զ���������Ȼ��������ø�����Ϊ����
	û���Թ���
	shutdownTimeout��ָʾjvm�ر�ʱĿ�ĵ�����д��־��Ϣ�����񣩺ͺ�̨��������رա�Ĭ��ֵΪ0�����ǲ�����ʹ��Ŀ�ĵ�������������õĻ�
	��ֻ��һ����ʾ���Ǳ�֤��ע�Ⱑ������ֵ���̫�Ϳ��ܻ�����δд����־�ķ��գ���������������0.������������
	strict��ָʾ���xml����ʹ���ϸ�ģʽ��Json���ò�֧�֡�
	verbose�����ز��ʱ���õ�����Ϣ
8����������
	����λ�ã�	��appendersh,loggers Ԫ�ط���ͬһ�ȼ��ϡ������ã���¼�¼����ݵ�LoggerConfig֮ǰ���ܻ�ܾ��¼�
						logger Ԫ���У���������Щ���ù��ļ�¼�����Խ��ܻ��߾ܾ�����ָ��loggers�ļ�¼�¼���ʲô��
						appender �У������Ǿܾ����߽��ܴ�ӡָ������ļ�¼�¼�
9:��ǣ���¼������ʹ�ò�ͬ��Markers�����ֲ�ͬ�ļ�¼������һ�º���û��ʲô���ã�����������Ʋ����������ĵ�д�����ǵ���
10:��־�������զ�죬��Ŀ�ĵ��������ñ��룬�磺									
	<PatternLayout charset="GBK" pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
	
	��������ʲô���log4j�ܹ�����
	��ȡһ��Logger ʱ��LogManager���ҵ���Ӧ��LoggerContext����Ȼ���ȡLogger
	����һ��Logger ʱ���������LoggerConfig������LoggerConfig�����ˣ�a:��Logger��ͬ�����ƣ����������ƣ���LoggerConfig
		ע��LoggerConfig�������������ļ���Logger��������
11:
	xml�����ļ��ṹ
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
	
12:log4j2�ȼ��̳С�����һ��logger������Ӧ��LoggerConfig��LoggerConfig�ֿ��������ϼ����Ӷ�ʵ����ͬ��Ч��
	���log�������ļ�û�����ø�LoggerConfig����ʹ��Ĭ�ϼ�����������־�����󣬴�ӡ��
	���1�����ֻ�����ø���¼��������������־�����Ŀ�ĵ�
				������¼�����伶������߲���Ŀ�ĵ����á�
				��ô������¼�������ø���¼������־�����������־��¼��������
	���2������com.QuickStart.Example.QuickStartLog��¼����������¼���������˵ȼ���Ŀ�ĵء���ô��˵x��¼���������ĸ���¼���ĵȼ��أ�
					���ǻ�����com������Ƶļ�¼�����á�
					һ����˵���������û������LoggerConfig��¼�����������ĸ�����¼����LoggerConfig
					���ĸ�����ô���㣿ȡ��¼��������ʼλ�ú����ĸȥƥ��������¼������ƥ����ϣ����Ǹ�����¼����ƥ�䲻�ϣ�Ĭ�ϸ�����¼�����Ǹ���¼��
	PS:���û��Ϊĳ���������ü�¼������ôĬ��Ҳ����һ��ֻ�д�������ֵļ�¼�����á�
	�ܽ�һ�£�һ����¼����θ�������¼��ƥ�䣿
	���һ����¼��������Ϊxx.yy.zz��ô���Ȼ�ȥƥ���֮ǰ�ļ�¼��������xxΪ����ȥ�Ҷ�Ӧ���ֵļ�¼����
	���һ����¼����������xyz,������˵����Ӧ�ú������ϵ�x����xyƥ�䣬��xy���õĻ�����ȥƥ��xy��	��Ȼ�������Ҳ��ԡ�xyz�����¼��ƥ�䲻��xy
	ֻ��ƥ�����¼��
	xml��������
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
  RT���������ȡ��xyz��¼���������־ʱ����������xy��¼����������x��¼��Ҳû�����ã������������ˣ�
  ԭ�򡣡����Ժ���˵
  13��log�Ĳ����������ʵ˵���˾���log�������־��ʽ������������Ŀ�ĵ������У�eg��
  	<Console name="Console" target="SYSTEM_OUT">	
      <PatternLayout charset="GBK" pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
    </Console>
    PatternLayout�������ò�������ĵط��ˣ�charset��������ַ����룬����Ӧ��֪���˰�
    pattern���������ʽ�����ָ�ʽ��ͨ����ʽ����ȷ���ģ�һ����ʽ���������ڡ�%r�������Ķ�����
    ��ô�������˳��˵˵��ʽ���ɡ�
    ��r  ��ʾ�������������������ĺ��������ղݣ�����6�ģ����Ը�����������Ǹ�������ʱ���
    ��t  ��ʾ������־��������Ǹ��߳�
    ��c �����¼��������
    ��p ��־����  �е����û�д�ɣ���-5p  ������û���κ�����Ȩ�Ҽ�¼һ�¡�
    14���������
    	�������ַ���ƴ�ӣ����������ַ���ƴ�ӣ�̫����ˡ�
    	logger.error("����ľ�������{},{}",new Date().toString(),new Date().toLocaleString());
    	�������ַ�ʽ�����ڼ�¼���ĵȼ����ϵ�����²Żṹ���ַ����������˷���Դ
    	����
    15:��ʽ�������
    	��Logger logger = LogManager.getFormatterLogger("Foo");��ȡ�ļ�¼���������ʹ��java.util.Formatter��ʽ������Ĺ��������������Ҫ����Ϣ
    	eg��logger.debug("Long.MAX_VALUE = %,d", Long.MAX_VALUE); ������������ֲ���ǧλ���ŷָ�����
    	�йظ����ʽ�� ����������java.util.Formatter�ࡣ
    16�������أ���ʵ��Ҳ��ɲ���ר�Ż�ȡ�����ʽ������ļ�¼������ֱ�ӻ�ȡ��ͨ�ļ�¼������Ȼ���������printf����Ҳ����ʹ�ø�ʽ�����
    		����ע���������һ����������־�ȼ����ڶ����Ǹ�ʽ�ַ������������ǣ����������㶮��
    		eg��logger.printf(Level.INFO, "Logging in user %1$s with birthday %2$tm %2$te,%2$tY", user.getName(), user.getBirthdayCalendar());
    17:������log4j��־��¼
    	��ʵ����֧��lambda���ʽ�����ֱ��ʽ�����ڲ���ʽ���	��־�����������ӳ���־��¼
    	���磺logger.trace("Some long-running operation returned {}", () -> expensiveOperation());
    	�����Ļ������δ����TRACE�����򲻻�����lambda���ʽ		
    18��Event Logging�о��ò��ϣ���ռһ��λ��
    19��Log4j    Messages ���������Ҫ�����������Ϣ����ʽ��
    		��Ȼ��log4j2�ṩ��һЩ�����������͵���������磬�ַ������������֣�������ʱ���������һ��Map��ô�죿
    		��ֱ�����ֻ�ܵ���map�����toString()������
    		��ʵ����¼�������ṩ��һ�������������Message����Ķ���Ȼ����ø���������getFormattedMessage������Ϣ���и�ʽ��
    		�����Ļ�����ֻҪдһ�����࣬ȥʵ��Message��ͨ�����캯������һ�����󣬲���getFormattedMessage���������ʽ�����ɡ�
    		��������ʵ�ɣ��㲻ȥʵ�������Ҳ���Դﵽ��ʽ����Ŀ�ġ�ֻҪ����һ����������ʽ�����󣬷��ظ�ʽ������ַ������ü�¼����¼����ַ��������ˡ�
    		�ýӿڵĻ������ֱ�Ӵ����󣬲��ýӿڵĻ��㻹��Ҫ���÷�������ַ�������
    ����ʵ��Message�ӿ��⣬log4j2���ṩ��һЩֱ�ӿ����õ�Message�����࣬����ֱ��new�������󣬾Ϳ��Ը�ʽ�������
    	FormattedMessage�����ڴ����ʽ���ַ������ᶯ̬ȷ����Ϣ������MessageFormat����String.format������ǣ��ֱ����MessageFormatMessage��StringFormattedMessage
    	���������Ϣ��ʽ������������ǣ�����ParameterizedMessage������Ϣ��ʽ��
    	LocalizedMessage�����ڼ���log4j��һ������������
    	LoggerNameAwareMessage���������setLoggerName����.�о�ûʲ�ã�����һ���ӿڣ�������Ҳ���Ǽ�¼��¼�������֡�
    	MapMessage�����ʵ����FormattedMessage�ӿڣ�������һ��XML�ĸ�ʽ˵���������Ը�ʽ����XML.
    	����Map������ʽ����key1=value1 key2=value2...����˵��ֱ��ʹ��MapMessage����ʽ�������ˣ������ٶ���һ��ʲô���Message����
    	�������ҾͲ�˵�ˣ����˸о�����ô�������ҿ�����Ҳ��������
    20��Thread Context  �о��е�ߴ��ϣ�ʲô���ǣ�ʲô��ʲô������ӳ�䡣
    	˽��Ϊ��ѧ������ϣ����Ѿ�������־�Ĵ󲿷�Ӧ���ˡ�����������һ���Ǹ߷ֲ�ʽ�ġ����ڽӴ�����
    	�����أ��������ռ��λ�ã��������õ����ٲ���
    21��log4j2����Scala ���ԡ���������������о���Χ�ڣ��ԡ�
    22��ͨ���������滻��xml����ı�����
    	���ǰ�xml����ÿ��Ա�������ñ�������������޸ġ�
    	��ô����
    	�ڸ�Ԫ��дһ��Properties��ǩ������׷��
    	һЩ���ԣ�
    23:��������֮ǰ���񽲹��ˣ����ǽ���ֻ��������Ϣ�ȼ���������Ϣ�Ĺ��ˡ���ʵ����һ�֣��������õ�9����˵�ı����������Ϣ����
    ��������ǽ��ϵģ�
    <filters>
        <MarkerFilter marker="FLOW" onMatch="ACCEPT" onMismatch="NEUTRAL"/>
        <MarkerFilter marker="EXCEPTION" onMatch="ACCEPT" onMismatch="DENY"/>
     </filters>
     24��Ŀ�ĵ������е�Routing Ŀ�ĵ�����
     


