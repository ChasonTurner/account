package com.arnold.server.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletResponse;

import com.arnold.server.util.ArnoldUtils;
import com.jfinal.core.Controller;
import com.jfinal.render.Render;
import com.pallas.utils.Utils;

public abstract class BaseController extends Controller {
	private int returnCode=-1000;
	private String rmsg="返回消息";
	public String getRmsg() {
		return rmsg;
	}
	public void setRmsg(String rmsg) {
		this.rmsg = rmsg;
	}
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	private Map<String, Object> paras=new HashMap<String, Object>();
	public Map<String, Object> getServerParas(){
		return paras;
	}
	public void serverParasInit(){
		Enumeration<String> e=getParaNames();
		while(e.hasMoreElements()){
			String key=e.nextElement();
			paras.put(key, getPara(key));
		}
		paras.put("httpRequestUrl", getRequest().getRequestURL());
	}
	public Map<String, Object> getCheckParas(){
		Map<String, Object> checkParas=new HashMap<String, Object>();
		checkParas.put("appId", paras.get("appId"));
		checkParas.put("userId", paras.get("userId"));
		checkParas.put("token", paras.get("token"));
		return checkParas;
	}
	public void setCheckParas(){
		setAttr("appId", paras.get("appId"));
		setAttr("userId", paras.get("userId"));
		setAttr("token", paras.get("token"));
	}
	public void setMoreParas(String[] paraNames){
		for (int i = 0; i < paraNames.length; i++) {
			setAttr(paraNames[i], paras.get(paraNames[i]));
		}
	}

	public void setCheckParasHtml(){
		String checkParasHtml="<input type=\"hidden\" name=\"userId\" value=\""+paras.get("userId")+"\">"+   
     "<input type=\"hidden\" name=\"appId\" value=\""+paras.get("appId")+"\">"+
     "<input type=\"hidden\" name=\"token\" value=\""+paras.get("token")+"\">";
		setAttr("checkParasHtml", checkParasHtml);
	}

	public void renderAlert(String alertText) {
		renderHtml("<script language=\"javascript\">alert('" + alertText + "');</script>");
	}

	public void renderAlertAndGoBack(String alertText) {
		renderHtml("<script language=\"javascript\">alert('" + alertText + "');location='javascript:history.go(-1)';</script>");
	}
	public void renderAlertAndGoQuery(String alertText,String url) {
		renderHtml("<script language=\"javascript\">alert('" + alertText + "');location='"+url+"';</script>");
	}

	public void renderAlert(String alertText, String goToUrl) {
		renderHtml("<script language=\"javascript\">alert('" + alertText + "');location='" + goToUrl + "';</script>");
	}

	public void renderGZIP(String data) throws IOException {

		HttpServletResponse response = this.getResponse();

		byte[] result = data.getBytes("UTF-8");
		System.out.println(result.length);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gout = new GZIPOutputStream(out);
		gout.write(result);
		gout.close();
		result = out.toByteArray();

		response.setHeader("Content-Encoding", "gzip");
		response.setHeader("Content-Length", result.length + ArnoldUtils.EMPTY_STR);
		System.out.println(result.length);
		OutputStream rout = response.getOutputStream();
		rout.write(result);
		rout.close();
		this.render(new Render() {
			@Override
			public void render() {
			}
		});
	}
	/**
	 * @Description: 打印请求参数
	 * @author Li Bangming;
	 * @date 2017年6月28日 下午5:02:40
	 */
	public void  loggerDebugRequestUrl(){
		Map<String, String[]>  paraMaps= this.getRequest().getParameterMap();
		String parameterStr=ArnoldUtils.EMPTY_STR;
		if(ArnoldUtils.logger.isDebugEnabled()){
			Set<String> keys= paraMaps.keySet();
			for(String key:keys){
				if(!key.equals("httpRequestUrl")){
					parameterStr+=key+"="+paraMaps.get(key)[0]+"&";
				}
			}
		}
		ArnoldUtils.logger.debug(this.getRequest().getRequestURL()+"?"+parameterStr);
	}
	
	/**
	 * @Description: 获取用户IP
	 * @author Li Bangming;
	 * @date 2017年6月28日 下午5:02:40
	 */
	public String  getUserIp(){
		return this.getRequest().getRemoteHost();
	}
	
	/**
	 * 检测参数合法性
	 */
	protected String checkPara(Map<String, Object> checkMap){	
		Iterator<Entry<String, Object>> it = checkMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, Object> e =(Entry<String, Object>) it.next();
			System.out.println("键"+e.getKey () + "的值为" + e.getValue());
			if(Utils.isBlankOrEmpty((String) e.getValue())){
				return new StringBuffer().append("参数 ").append(e.getKey()).append(" 不能为空").toString();
			}	
		}
		
		return "true";
	}
	
	/**
	 * @Description: 将参数转成double类型
	 * @author Li Bangming;
	 * @date 2017年8月17日 下午12:45:59
	 * @return
	 */
	protected Double getParaToDouble(String name){	
		name =this.getRequest().getParameter(name);
		if(!Utils.isBlankOrEmpty(name)){
			return Double.valueOf(name);
		}
		return null;
	}
	
	/**
	 * @Description: 将参数转成double类型
	 * @author Li Bangming;
	 * @date 2017年8月17日 下午12:45:59
	 * @return
	 */
	protected Double getParaToDouble(String name,double defualtValue){	
		name =this.getRequest().getParameter(name);
		if(!Utils.isBlankOrEmpty(name)){
			return Double.valueOf(name);
		}
		return defualtValue;
	}
	/**
	 * 
	 * @Description 创建字符串取参数后与float之间的转换
	 * @author maomj
	 * 
	 */
	protected Float getParaToFloat(String name, Float defaultValue) {
		name = this.getRequest().getParameter(name);
		if (!Utils.isBlankOrEmpty(name)) {
			return Float.valueOf(name);
		}
		return defaultValue;
	}
	
	/**
	 * @description  获取bean对象
	 * @author luzy
	 * @date 2017年12月20日
	 * @param pClass
	 * @param pKeys
	 * @return
	 */
	protected <T>T getRequestBean(Class<T> pClass, String...pKeys){
		T resInstance = null;
		try {
			resInstance = pClass.newInstance();
		} catch (Exception e) {
			//pClass创建实例有误
			e.printStackTrace();
		}
		
		if(null==resInstance){
			return null;
		}
		
		if(null==pKeys || pKeys.length==0){
			return resInstance;//不需要处理
		}
		
		for(String str : pKeys){
			if(null==str || str.length()==0){
				continue;//无效key值
			}
			
			try {
				//参数Field
				Field keyField = pClass.getDeclaredField(str);
				
				//invoke
				String fieldTypeStr = keyField.getGenericType().toString();
				switch (fieldTypeStr) {
					case "class java.lang.String":
						pClass.getMethod(getMethodName(str), String.class).invoke(resInstance, getPara(str));
						break;
					case "class java.lang.Integer":
						pClass.getMethod(getMethodName(str), Integer.class).invoke(resInstance, getParaToInt(str));
						break;
					case "class java.lang.Double":
						pClass.getMethod(getMethodName(str), Double.class).invoke(resInstance, getParaToDouble(str));
						break;
					case "class java.util.Date":
						pClass.getMethod(getMethodName(str), Date.class).invoke(resInstance, getParaToDate(str));
						break;
					default:
						continue;
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		
		return resInstance;
	}
	
	/** 依据参数名获取对应得set统一方法名 */
    private String getMethodName(String pFildeName){
    	//首字母大写
        byte[] resMethodName = pFildeName.getBytes();
        resMethodName[0] = (byte) ((char) resMethodName[0] - 'a' + 'A');
        
        //添加set返回
        return new StringBuilder("set").append(new String(resMethodName)).toString();
    }
	
}
