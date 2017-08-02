package com.log4j2.Messages;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.message.Message;

public class MapMessages implements  Message  {
	/**
	 *  
	 */
	private static final long serialVersionUID = 5261751284357622424L;
	private Map<String,String> map;
	public MapMessages(Map<String,String> map){
		this.map=map;
	}
	
	/**
	 * 当log4j2日志格式输出时，会自动调用这个方法，进行信息格式化
	 * 发现一个小问题，恩，就是只有当Map集合里面的元素确定时，才可以用entrySet方法来帮助遍历Map
	 * 温故而知新：Map不允许两个相同的key。
	 */
	public String getFormattedMessage() {
		StringBuilder sb=new StringBuilder();
		for(Map.Entry  entry : map.entrySet()){
			sb.append(entry.getKey()+":"+entry.getValue()+"\n");
		}
		return sb.toString();
	}

	public String getFormat() {
		return null;
	}

	public Object[] getParameters() {
		return null;
	}

	public Throwable getThrowable() {
		return null;
	}

}
