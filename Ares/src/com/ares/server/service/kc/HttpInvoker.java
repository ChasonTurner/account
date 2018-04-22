/** 
 * Copyright (c) 2016, 贵州智通天下信息技术有限公司 All Rights Reserved. 
 * Project Name: Ares 
 * Author: YangCheng 
 * Date: 2016年12月5日下午3:42:38 
 */
package com.ares.server.service.kc;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ares.server.service.loadReqServiceI.LoadReqServiceI;


public class HttpInvoker {
	private static final Logger logger = LoggerFactory.getLogger(HttpInvoker.class);

	/** 圈存子系统接口url */
    private static String rechargeSystemUrl = "";

    /** 接口子系统url */
    private static String interfaceManageUrl = "";
    
	static {   
        Properties prop = new Properties();   
        InputStream in = Object.class.getResourceAsStream("/setting.properties");   
        try {   
            prop.load(in);   
            rechargeSystemUrl = prop.getProperty("interface_manage_url");   
            interfaceManageUrl = prop.getProperty("recharge_system_url");   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
    }
    

    /**
     * 调用ws接口
     */
    public String callWs(String params) {
        //调用WebService
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(LoadReqServiceI.class);
        factory.setAddress(rechargeSystemUrl);
        LoadReqServiceI service = (LoadReqServiceI) factory.create();
        String str = service.loadReq(params);
        return str;
    }


    /**
     * 调用url接口
     */
    public String post(String url, Map<String, String> params) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpEntity entity = null;
        HttpPost httpPost;
        try {
            httpPost = new HttpPost(url);
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            // 封装参数
            for (String s : params.keySet()) {
                formParams.add(new BasicNameValuePair(s, String.valueOf(params.get(s))));
            }
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
            httpPost.setEntity(uefEntity);
            logger.debug("调用HTTP请求：{}", httpPost.getURI());
            CloseableHttpResponse response = httpclient.execute(httpPost);
            entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                if (result.indexOf("(") != -1 && result.indexOf(")") != -1) {
                    int start = result.indexOf("(");
                    int end = result.lastIndexOf(")");
                    result = result.substring(start + 1, end);
                }
                return result;
            }
            response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
