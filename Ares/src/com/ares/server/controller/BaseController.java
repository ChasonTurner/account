package com.ares.server.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletResponse;

import com.jfinal.core.Controller;
import com.jfinal.render.Render;

public abstract class BaseController extends Controller {

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
		response.setHeader("Content-Length", result.length + "");
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

}
