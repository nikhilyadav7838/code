package com.zee.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.zee.utils.GenericUtility;

public class BrokenURLsEntity {

	private List<String> resourceURL = new ArrayList();
	private List<String> statusCode = new ArrayList();
	private String articleURL;
	private List<String> responseTime = new ArrayList();
	
	public BrokenURLsEntity(XSSFWorkbook workbook, XSSFSheet sheet){
		GenericUtility.getGenericUtility().createXSLHeadersForBrokenLinks(workbook, sheet);
	}	

	public void setResourceURL(String url) {
		this.resourceURL.add(url);
	}

	public void setStatusCode(String statusCode) {
		this.statusCode.add(statusCode);
	}

	public void setArticleURL(String articleURL) {
		this.articleURL = articleURL;
	}

	public List<String> getResourceURL() {
		return resourceURL;
	}

	public List<String> getStatusCode() {
		return statusCode;
	}

	public String getArticleURL() {
		return articleURL;
	}	
	
	public void setResponseTime(String responseTime) {
		this.responseTime.add(responseTime);
	}
	
	public List<String> getResponseTime() {
		return responseTime;
	}	
}
