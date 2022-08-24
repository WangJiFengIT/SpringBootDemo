package com.example.demo.utils;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageData extends HashMap implements Map {
    private static final long serialVersionUID = 1L;

    Map map = null;
    HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public PageData(HttpServletRequest request) {
        this.request = request;
        Map properties = request.getParameterMap();
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Entry entry;
        String name = "";
        String value = "";
        String regEx="[\\[][0-9]*[\\]]"; //此处可以去掉 [0-9]* 支持 a[]类型
        Pattern pattern = Pattern.compile(regEx);
        while (entries.hasNext()) {
            entry = (Entry) entries.next();
            name = (String) entry.getKey();
            Matcher matcher = pattern.matcher(name);
            boolean isArr = matcher.find();

            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }

            if (isArr) {//说名是数组
                int endIndex = name.indexOf("[");
                name = name.substring(0, endIndex);
                Object paramValues = returnMap.get(name);
                List paramList = null;
                if (paramValues == null || "".equals(paramValues)) {//说名是初次
                    paramList = new ArrayList<>();
                } else {
                    paramList = (List) paramValues;
                }
                paramList.add(value);
                returnMap.put(name, paramList);
            } else {
                returnMap.put(name, value);
            }
        }
		/*Object loginUser = request.getAttribute("loginUser");
		if (loginUser!=null){
			User user= (User) loginUser;
			returnMap.put("schoolId",user.getSchoolId());
		}*/
		/*try {
			//处理post请求的流数据
			ServletInputStream inputStream = request.getInputStream();
			if (inputStream!=null){
				BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
				StringBuffer sb = new StringBuffer();
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				String msg=sb.toString();
				if (msg!=null && !"".equals(msg)){
					Map maps =JsonUtils.toObject(msg,Map.class);
					if (maps!=null){
						returnMap.putAll(maps);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/
        map = returnMap;
    }

    public PageData() {
        map = new HashMap();
    }

    //判断是否是数字
    public Boolean isInteger(String str){
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    @Override
    public Object get(Object key) {
        Object obj = null;
        if (map.get(key) instanceof Object[]) {
            Object[] arr = (Object[]) map.get(key);
            obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
        } else {
            obj = map.get(key);
        }
        return obj;
    }

    public String getString(Object key) {
        String keyValue =  get(key)==null?"":(String)get(key);
        return keyValue.trim();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object put(Object key, Object value) {
        return map.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    public void clear() {
        map.clear();
    }

    public boolean containsKey(Object key) {
        // TODO Auto-generated method stub
        return map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        // TODO Auto-generated method stub
        return map.containsValue(value);
    }

    public Set entrySet() {
        // TODO Auto-generated method stub
        return map.entrySet();
    }

    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return map.isEmpty();
    }

    public Set keySet() {
        // TODO Auto-generated method stub
        return map.keySet();
    }

    @SuppressWarnings("unchecked")
    public void putAll(Map t) {
        // TODO Auto-generated method stub
        map.putAll(t);
    }

    public int size() {
        // TODO Auto-generated method stub
        return map.size();
    }

    public Collection values() {
        // TODO Auto-generated method stub
        return map.values();
    }
}
