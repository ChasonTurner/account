package com.arnold.server.util;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;





import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.jfinal.core.ActionException;

/**
 * @ClassName: ArnoldUtils
 * @Description: 本系统工具类
 * @author Li Bangming;Email:1715592561@qq.com
 * @date 2017年6月1日 上午11:08:39
 */
public class ArnoldUtils {
	/**
	 * 日志记录对象
	 */
	public static Logger logger  = Logger.getLogger(ArnoldUtils.class);
	/**
	 * 方法返回码
	 */
	public static final int NOT_FIND_ERRO = 1220041;
	public static final String NOT_FIND_ERROR_STR = "找不到该信息";
	
	public static final int BEYOND_FIELD_LENGTH_ERRO = 1220042;
	public static final String BEYOND_FIELD_LENGTHR_STR = "操作失败，有字段长度超过范围！";
	
	public static final int OPER_SAME_ERRO = 1220043;
	public static final String OPER_SAME_ERROR_STR = "操作失败，已经存在类似的信息！";
	
	public static final int EXIT_REGION_COLLECTIVE_ECONOMY_ERRO = 1220044;
	public static final String EXIT_REGION_COLLECTIVE_ECONOM_ERROR_STR = "操作失败，已经存在该区域的集体经济！";
	
	public static final int EXIT_FAMILY_ECONOMIC_ERRO = 1220044;
	public static final String EXIT_FAMILY_ECONOMIC_ERROR_STR = "操作失败，已经存在该家庭经济！";
	
	public static final int NOT_FIND_REGION_COLLECTIVE_ECONOMY_ERRO = 1220045;
	public static final String NOT_FIND_REGION_COLLECTIVE_ECONOMY_ERROR_STR = "没有找到集体经济信息";
	
	public static final int NOT_FIND_FAMILY_ECONOMIC_ERRO = 1220046;
	public static final String NOT_FIND_FAMILY_ECONOMIC_ERROR_STR = "没有找到家庭经济信息";
	
	public static final int RMOTE_POSEIDON_SERVER_ERRO = 1190047;
	public static final String RMOTE_POSEIDON_SERVER_ERRO_STR = "POSEIDON远程服务器响应失败，可能是网络原因，请稍后再试";
	
	public static final String PAGE_COUNT = "pageCount";//分页统计对象
	public static final String R_TIME = "rTime";//接口响应时间
	public static final String TOTAL_COUNT = "totalCount";//总统计对象
	public static final String RECOUNT_MESSAGE = "系统正在重新统计.......";//统计消息
	public static final String INDEX = "index";//索引
	public static final String MODULE_NAME = "moduleName";//模块名

	/**
	 * 系统常量
	 */
	public static final String SPACE = " ";//空格符
	public static final String EMPTY_STR="";//空字符串
	public static final String MINUS_ONE="-1";//负一
	public static boolean  IS_SYSTEM_MAINTENANCE;//系统是否在维护
	
	

	/**
	 * 行政区域常量
	 * 0省级行政区，1地级行政区，2县级行政区，3乡级行政区，4村级行政区,5组级行政区域
	 */
	public static final int[] REGION_TYPE = {1,2,3,4,5,6};
	/**
	 * 凭证文件类型
	 */
	public static final String[] DOCUMENT_BASIS_TYPE = {"1","2","3","4","5","6"};//(1补偿详情,2拨付(拨入),3拨付(拨出(到账凭证)),4经费(拨入),5经费(拨出到账凭证)),6涉农依据凭证)
	/**
	 * 分页默认大小
	 */
	public static final int PAGE_SIZE_DEFULT=10;//

	
	/**
	 * @Description:通过json对象获取Map集合
	 * @author Li Bangming;
	 * @date 2017年7月5下午1:51:26
	 */
	public static Map<String,Object>  getMapByJSONObject(JSONObject jsonObject) {
		Map<String, Object> resultMap=new HashMap<String, Object> ();
		try {
			Iterator<String> it = jsonObject.keys();  
		    while(it.hasNext()) {  
		        String key = (String) it.next();  
		        Object value =jsonObject.get(key) ; 
		        if(value==null){
			        resultMap.put(key, EMPTY_STR);  
		        }else{
			        resultMap.put(key, value);  
		        }
		    }  
		} catch (Exception e) {
		    logger.debug(e.getLocalizedMessage());
		}
		return resultMap;
	}
	/**  
     * 将json对象中包含的null和JSONNull属性修改成
     * @param jsonObj  
     */  
    public static  JSONObject filterNull(JSONObject jsonObj){  
        Iterator<String> it = jsonObj.keys();  
        Object obj = null;  
        String key = null;  
        while (it.hasNext()) {  
            key = it.next();  
            obj = jsonObj.get(key);  
            if(obj instanceof JSONObject){  
                filterNull((JSONObject)obj);  
            }  
            if(obj instanceof JSONArray){  
                JSONArray objArr = (JSONArray) obj;  
                for(int i=0; i<objArr.length(); i++){  
                    filterNull(objArr.getJSONObject(i));  
                }  
            }  
            if(obj == null ||obj.equals(null)){  
                jsonObj.put(key, EMPTY_STR);  
            }  
        }
		return jsonObj;  
        
    }  

	
	public static String getRemoteLoginUserIp(HttpServletRequest request){
		  String ip = request.getHeader("x-forwarded-for"); 
		  if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		  ip = request.getHeader("Proxy-Client-IP"); 
		  } 
		  if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		  ip = request.getHeader("WL-Proxy-Client-IP"); 
		  } 
		  if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		      ip = request.getRemoteAddr(); 
		  } 
		  return ip; 

	}
	/**
	 * @Description: 过滤转义关键词的特殊字符
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午1:49:27
	 * @param content
	 * @return
	 */
    public static String decodeSpecialChars(String content)  
    {  
    	// 单引号是oracle字符串的边界,oralce中用2个单引号代表1个单引号  
        String afterDecode = content.replaceAll("'", "''");  
        // 由于使用了\作为ESCAPE的转义特殊字符,所以需要对该字符进行转义  
        // 由于\在java和正则表达式中都是特殊字符,需要进行特殊处理  
        // 这里的作用是将"a\a"转成"a\\a"  
        afterDecode = afterDecode.replaceAll("\\\\", "\\\\\\\\");  
        // 使用转义字符 \,对oracle特殊字符% 进行转义,只作为普通查询字符，不是模糊匹配  
        afterDecode = afterDecode.replaceAll("%", "\\\\%");  
        // 使用转义字符 \,对oracle特殊字符_ 进行转义,只作为普通查询字符，不是模糊匹配  
        afterDecode = afterDecode.replaceAll("_", "\\\\_");  
        
        return afterDecode;  
    } 
    /**
     * @Description: 判断一个字符串是否是数字(包括整数和小数)
     * @author Li Bangming;
     * @date 2017年8月10日 下午1:50:25
     * @param str
     * @return
     */
	public static boolean isNumber(String str){  
	    String reg = "^[0-9]+(.[0-9]+)?$";  
	    return str.matches(reg);  
	}  
    /**
     * @Description: 获取四位字符串编码
     * @author Li Bangming;
     * @date 2017年8月10日 下午1:50:25
     * @param str
     * @return
     */
	public static String get4BitStrByNumber(int number){ 
		String numberStr=EMPTY_STR;
		if(number<10){
			numberStr="000"+number;
		}else if(number>=10&&number<100){
			numberStr="00"+number;
		}else if(number>=100&&number<1000){
			numberStr="0"+number;
		}else {
			numberStr=String.valueOf(number);
		}
	    return numberStr;  
	}  
	
	public static boolean isMobileNO(String mobiles){  
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
		Matcher m = p.matcher(mobiles);  
		return m.matches();  
	}  
	/**
	 * @Description: 
	 * @author Li Bangming;
	 * @date 2017年9月21日 下午4:51:11
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(isMobileNO("158888888"));
	}	  
	
	
	/**
	 * @Author PanChangGui
	 * @Time 2017年10月9日 上午11:05:12
	 * @Description 
	 */
	public static Date getBirthdayByIDnumber(String IDnumber) {
		if(IDnumber==null || IDnumber.isEmpty() || IDnumber.length()!=18){
			return null;
		}
		
		String year = IDnumber.substring(6,10);	
		String month = IDnumber.substring(10, 12);
		String day = IDnumber.substring(12, 14);
		
		StringBuffer s_birthday = new StringBuffer();;
		s_birthday.append(year);
		s_birthday.append("-");
		s_birthday.append(month);
		s_birthday.append("-");
		s_birthday.append(day);					
		
		return toDate(s_birthday.toString(), null);
	}
	
	/**
	 * @Author PanChangGui
	 * @Time 2017年10月9日 上午11:05:00
	 * @Description 
	 */
	public static Date toDate(String value, Date defaultValue) {		
		try {
			if (value == null || "".equals(value.trim()))
				return defaultValue;
			
			return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(value.trim());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return defaultValue;
	}
	
}
