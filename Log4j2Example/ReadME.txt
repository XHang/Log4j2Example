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
						
	
	
	��������ʲô���log4j�ܹ�����
	��ȡһ��Logger ʱ��LogManager���ҵ���Ӧ��LoggerContext����Ȼ���ȡLogger
	����һ��Logger ʱ���������LoggerConfig������LoggerConfig�����ˣ�a:��Logger��ͬ�����ƣ����������ƣ���LoggerConfig
		ע��LoggerConfig�������������ļ���Logger��������
	
	
	


