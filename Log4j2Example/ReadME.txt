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
    	��������ͱ�Ϲ�ȱ�
    	<Properties>
				<Property  name="pattern">����  %r [%t] %-5p %c - %m%n</Property>
		</Properties>
		���ϣ����ö�̬����ʱ�ͽ���
		 <PatternLayout charset="utf-8" pattern="${pattern}"/>
		 �������
		 ���⣬log4j2�������ļ��Դ��������������㲻�����þ�����
		 һ����${log4j:configLocation}  ���õ���log4j2��������ļ��ľ���·�������ļ����ģ�Ĭ�Ͼ���tarage��classs�ļ�
		 һ����${log4j:configParentLocation} ���õ���log4j2�������ļ������·���ľ���·��
		 ��Ȼ���·���ǲ����ļ����ģ��������·�������·�����������log4j2�������ļ����Ǹ��ļ��С�
		 Ĭ���������£�����ļ����Ǳ������ɵ�targe�ļ��������class��
		 ${date:YYYY}  ���Ҳ�ǲ�������Ҳ���ã����õ��ǵ�ǰ��ʱ�䣬����ʽ���ġ�
		 $(env:ϵͳ����������}  ������õľ���ϵͳ����������ɶ��ʲô��ϵͳ�������������ǰ�װjdkʱ���õ�JAVA_HOME���Ǿ��ǻ�������
		 $(sys:������}   ���õľ���ϵͳ���ԣ�ͨ��System.getProperties();���Ի�ȡ��ǰϵͳ���������ԡ�
		 
    23:��������֮ǰ���񽲹��ˣ����ǽ���ֻ��������Ϣ�ȼ���������Ϣ�Ĺ��ˡ���ʵ����һ�֣��������õ�9����˵�ı����������Ϣ����
    ��������ǽ��ϵģ�
    <filters>
        <MarkerFilter marker="FLOW" onMatch="ACCEPT" onMismatch="NEUTRAL"/>
        <MarkerFilter marker="EXCEPTION" onMatch="ACCEPT" onMismatch="DENY"/>
     </filters>
     24��Ŀ�ĵ������е�Routing Ŀ�ĵ�����
     25:�ű���ǩ
     log4j2֧���ڲ��������ʹ��JSR 223�ű����ԡ�
     ����˵����JavaScript��Groovy ��Beanshell��Щ�ű����ԣ�����ֱ��֧�ֵģ�����ֻҪ��������Ե�jar������
     ֧�ֽű������ʹ��<script>, <scriptFile>, or <scriptRef>��ǩ����ɽű������á�
     <script>Ԫ�ذ����ű������ƣ��ű������Ժͽű��ı�
     <scriptFile>Ԫ�ذ����ű������ƣ�λ�ã����ԣ��ַ������Լ��Ƿ���ӽű��ļ��Զ�̬����
     <scriptRef>Ԫ�ش���<script>Ԫ���У����ڶ���ű������ƣ������������洢�ű��ͽű����档
     �ű����ƷǱ��룬�������ڵ���ʱ�ҽű�λ�á�
     �ű����Ա�����<script>�ж���á�
     �����<scriptFile>û�ж���ű����ԣ����ͨ���ļ��ĺ�׺���ж���ʲô�ű����ԡ�
     ����������ļ����ӣ���ֻ�ᵱ��ConfigurationԪ���������˷����monitorInterval���ԲŻ�����
     ĳЩ�ű��ļ�֧�ְ�ִ����ϵķ���ֵ���ص������ߣ�Ҳ����java�����ϡ�����javaScript��֧����ô��
     ��Ϊ����������ʹ�÷�����䣬�������ں����ڣ����е������ǣ������ڽű���ִ�е����һ������ֵ��
     var result=1;
      result++��
      result;
      �����߽��õ�result��ֵ��
      ʾ��С����
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
    ��������jar���Ҳ��������Ա�ʾ��������ͣ
    
    26��log4j2�����������������ļ���
    		��������һ��log4j2�������ļ��ܷ��࣬���԰�һЩ�����������д�ڵ�����һ��xml�ļ��������ļ�ֻҪ����xml�ļ����ɡ�
    		eg��
    		����һ��log4j-xinclude-appenders.xml�ļ�
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
      		�����log�����ļ�
      		<?xml version="1.0" encoding="UTF-8"?>
			<configuration xmlns:xi="http://www.w3.org/2001/XInclude"
			               status="warn" name="XIncludeDemo">
			  <properties>
			    <property name="filename">xinclude-demo.log</property>
			  </properties>
			  <ThresholdFilter level="debug"/>
			  <xi:include href="log4j-xinclude-appenders.xml" />
			</configuration>
      		�о��򵥵Ŀ��ԣ��Ͳ�дʾ�������ˡ�
      27��web����ʹ��log4j2
      			��web����ʹ��log4j2ʱ������ǳ�С�ģ��������رջ���webӦ�ó���ȡ������ʱ����Ҫ����Դ��ȷ�رգ��ر����ݿ����ӣ��ļ��ر�
      			������Ϊweb����������������ʣ�log�޷���������ʽ������Դ��
      			���ȣ���Ҫ��log4j2��webģ������.
      			Ȼ�����ѡ����web.xml����log4jConfiguration ָ��һ�������ļ�·�������߽�log4j2�������ļ��ŵ�web-inf�ļ����ڡ�
      			����Ҫע�⣬�ļ���������Ϊlog4j2��ͷ��
      			PS��log4j2ֻ�ܹ�����servlct3.0���߸����µ�webӦ�ó��򡣵�Ӧ�ò������ȡ������ʱ�����Զ�����
      			���⣺Tomcat7�����log4j*.jar��jar�ļ����Ӷ�ʹ��־��¼ʧЧ��
      			����㲻��������־����Ч����������Ը���catalina.properties����jarsToSkip������ɾ����log4j * .jar.
      			��������ֹlog4j��web��������ʱ�Զ�������������web.xml���������ã�
      			<context-param>
        			<param-name>isLog4jAutoInitializationDisabled</param-name>
        			<param-value>true</param-value>
    			</context-param>
      
      
      
      
      
      �����ʼ�
      ��ô�Ѿ���url����ת�������ģ�
		��������ͨ��SocketЭ������ʱ�ͻ��������ӵ�url��ַ��һЩ���Ĳ�������ô��ô�ڷ���˰���Щ���ֽ���������أ�
		˵��Ҳͦ�򵥵�
		new String(URLDecoder.decode(Ҫת�����ַ���, "utf-8")
		ʩ�����
		
		1��
����дһ��webSocket��demoʱ�����ַ���websocket��·��û�����⣬����ȴ��404����
�����ǣ�Eclipse��tomcat7�汾��8.0. jdk�汾��1.8
���������ϵ����Ϻ󣬷���tomcat8��ʵ�Ѿ�����webSocket�İ������һ��ǰ�webSocket��������Ŀ��lib�С�
���԰�webSocket��lib·���Ƴ����ٳ��ԣ���Ȼ�ɹ���


