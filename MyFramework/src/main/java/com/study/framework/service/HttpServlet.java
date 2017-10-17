package com.study.framework.service;

import com.study.framework.domain.HttpRequest;
import com.study.framework.domain.HttpResponse;

public abstract class HttpServlet {
	
	public abstract String service(HttpRequest request,HttpResponse response);
}
