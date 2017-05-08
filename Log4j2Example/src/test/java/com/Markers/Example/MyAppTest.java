package com.Markers.Example;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.HashMap;
import java.util.Map;
 
public class MyAppTest {
	public static void main(String[] args) {
		MyApp app=new MyApp();
		app.doQuery("User");
	    Map<String,String> map=new HashMap<String,String>();
        map.put("id", "1");
		app.doUpdate("user", map);
		app.doQuery("User");
		app.doUpdate("user", map);
		app.doQuery("User");
		app.doUpdate("user", map);
		app.doQuery("User");
	}
}
