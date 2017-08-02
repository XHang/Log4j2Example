package com.FlowTracing.Example;
/**
 * 该示例程序为流跟踪的测试程序
 * 附带演示自定义记载log配置文件的程序
 * @author DELL
 *
 */
public class FlowTracingTest {
	public static void main(String[] args) throws Exception {
		FlowTracingExample service = new FlowTracingExample();
		String[] messages=new String[2];
		messages[0]="厉害";
		messages[1]="我的哥";
		service.setMessages(messages);
	}
}
