package com.arnold.test;

import java.util.Calendar;
import java.util.Date;

import com.arnold.server.util.ArnoldUtils;
import com.huntersun.tool.Utils;


/**
 * @Description: 
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月9日 上午10:31:51
 */
public class TestUtils {
	static int i=0;
	public static void main(String[] args) {
		
		delPrint();
	}
    /**
     * @Description: 获取四位字符串编码
     * @author Li Bangming;
     * @date 2017年8月10日 下午1:50:25
     * @param str
     * @return
     */
	public static String get4BitStrByNumber(int number){ 
		String numberStr="";
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
	/**
	 * @Description: 死循环
	 * @author Li Bangming;
	 * @date 2017年8月12日 上午11:51:25
	 */
	public static void delPrint(){
		String numberStr="ewgfwgewew0002";
		numberStr=numberStr.substring(numberStr.length()- 3);
		System.out.println(numberStr);

	}
}

