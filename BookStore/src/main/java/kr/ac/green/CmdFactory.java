package kr.ac.green;

import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import kr.ac.green.cmds.ICmd;
import kr.ac.green.cmds.ListCmd;

public class CmdFactory {
	private static Hashtable<String, ICmd> cmds = new Hashtable<String, ICmd>();
	
	public static void init(Properties prop) {
		try {
			Set<?> keys = prop.keySet();
			for(Object keyObj : keys) {
				String key = keyObj.toString();
				String className = prop.getProperty(key);
				Class klass = Class.forName(className);
				// Class Klass2 = ListCmd.class;
				ICmd cmdObj = (ICmd)klass.newInstance();
				cmds.put(key,  cmdObj);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void doAction(HttpServletRequest request, String cmd) {
		if(!cmds.containsKey(cmd)) {
			cmd = "/null.book";
		}
		cmds.get(cmd).action(request);
	}
}










