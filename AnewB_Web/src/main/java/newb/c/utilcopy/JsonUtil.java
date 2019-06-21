package newb.c.utilcopy;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	/**
	 * @param obj
	 *            任意对象
	 * @return String
	 */
	public static String object2json(Object obj) {
		StringBuilder json = new StringBuilder();
		if (obj == null) {
			json.append("\"\"");
		} else if (obj instanceof String || obj instanceof Integer
				|| obj instanceof Float || obj instanceof Boolean
				|| obj instanceof Short || obj instanceof Double
				|| obj instanceof Long || obj instanceof BigDecimal
				|| obj instanceof BigInteger || obj instanceof Byte) {
			json.append("\"").append(string2json(obj.toString())).append("\"");
		} else if (obj instanceof Object[]) {
			json.append(array2json((Object[]) obj));
		} else if (obj instanceof List) {
			json.append(list2json((List<?>) obj));
		} else if (obj instanceof Map) {
			json.append(map2json((Map<?, ?>) obj));
		} else if (obj instanceof Set) {
			json.append(set2json((Set<?>) obj));
		} else {
			json.append(bean2json(obj));
		}
		return json.toString();
	}

	/**
	 * @param bean
	 *            bean对象
	 * @return String
	 */
	public static String bean2json(Object bean) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		PropertyDescriptor[] props = null;
		try {
			props = Introspector.getBeanInfo(bean.getClass(), Object.class)
					.getPropertyDescriptors();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		if (props != null) {
			for (int i = 0; i < props.length; i++) {
				try {
					String name = object2json(props[i].getName());
					String value = object2json(props[i].getReadMethod().invoke(
							bean));
					json.append(name);
					json.append(":");
					json.append(value);
					json.append(",");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	/**
	 * @param list
	 *            list对象
	 * @return String
	 */
	public static String list2json(List<?> list) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * @param array
	 *            对象数组
	 * @return String
	 */
	public static String array2json(Object[] array) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (array != null && array.length > 0) {
			for (Object obj : array) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * @param map
	 *            map对象
	 * @return String
	 */
	public static String map2json(Map<?, ?> map) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		if (map != null && map.size() > 0) {
			for (Object key : map.keySet()) {
				json.append(object2json(key));
				json.append(":");
				json.append(object2json(map.get(key)));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	/**
	 * @param set
	 *            集合对象
	 * @return String
	 */
	public static String set2json(Set<?> set) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (set != null && set.size() > 0) {
			for (Object obj : set) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * @param s
	 *            参数
	 * @return String
	 */
	public static String string2json(String s) {
		if (null == s) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				if (ch >= '\u0000' && ch <= '\u001F') {
					String ss = Integer.toHexString(ch);
					sb.append("\\u");
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				} else {
					sb.append(ch);
				}
			}
		}
		return sb.toString();
	}
	
	/**method:Json字符转换成一个对象<br/>
	 * demo:<br/>
	 * {<br/>
	 * 		Object object =  JsonHandle.getJsonToObject("{id:'01',name:'zhangsan',abean:{aBeanId:'0101'}}",TestBean.class);
	 *		TestBean bean = (TestBean) object;
	 *		System.out.println(bean.getId());
	 *		System.out.println(bean.getName());
	 *		System.out.println(bean.getAbean().aBeanId);
	 * }
	 **/
	public static Object getJsonToObject(String jsonStr,Class<?> cls){	
		  jsonStr = jsonStr.replace("[", "");
		  jsonStr = jsonStr.replace("]", "");
		  JSONObject jo = JSONObject.fromObject(jsonStr);
		  Object object = JSONObject.toBean(jo,cls);
		  return object;
	}
	
	/**method:Json字符转换成一个对象集合<br/>
	 * demo:<br/>
	 * {<br/>
	 *		List<Object> objectList =  JsonHandle.getJsonToObjectList("[{id:'01',name:'zhangsan'},{id:'02',name:'zhangsan2'}]",TestBean.class);<br/>
	 *		TestBean bean = (TestBean) objectList.get(0);<br/>
	 *		System.out.println(bean.getId());<br/>
	 * }
	 **/
	public static List<Object> getJsonToObjectList(String jsonStr,Class<?> cls){
		JSONArray array = JSONArray.fromObject(jsonStr);
		List<Object> objectList = new ArrayList<Object>();
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			objectList.add(JSONObject.toBean(jsonObject, cls));
		}
		return objectList;
	}
	
	public static HashMap<String, String> getJsonToMap(String jsonStr) {
		HashMap<String, String> data = new HashMap<String, String>();
		// 将json字符串转换成jsonObject
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		Iterator<?> it = jsonObject.keys();
		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			String value = (String) jsonObject.get(key);
			data.put(key, value);
		}
		return data;
	}
	
	public static HashMap<String, Object> getJsonToMap(JSONObject jsonObject) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		// 将json字符串转换成jsonObject
		Iterator<?> it = jsonObject.keys();
		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			Object value = jsonObject.get(key);
			data.put(key, value);
		}
		return data;
	}
	
	public static List<HashMap<String, Object>> getJsonToMapList(String jsonStr) {
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		// 将json字符串转换成jsonObject
		JSONArray array = JSONArray.fromObject(jsonStr);
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			data.add(getJsonToMap(jsonObject));
		}
		return data;
	}
	
	/**method:Object转Json字符串<br/>
	 * demo:
	 * {<br/>
	 * 		TestBean bean
	 * 		JsonHandle.getJSONString(bean)<br/>
	 *	}
	 **/
	public static String getJSONString(Object object) {
		String jsonStr = null;
		if (object != null) {
			if (object instanceof Collection<?> || object instanceof Object[]) {
				jsonStr = JSONArray.fromObject(object).toString();
			} else {
				jsonStr = JSONObject.fromObject(object).toString();
			}
		}
		return jsonStr == null ? "{}" : jsonStr;
	}
	
	public static void main(String[] args) {
		Map<String, String> ldb = new HashMap<String, String>();
		ldb.put("id", "001");
		System.out.println(JsonUtil.object2json(ldb));
	}
}