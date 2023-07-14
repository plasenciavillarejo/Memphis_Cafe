package com.memphis.cafe.filter;

import java.io.IOException;
import java.security.SecureRandom;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@WebFilter(urlPatterns = { "/inicio", "/" })
@Component
public class AdminFilter implements Filter{

	private Logger logFiltro = LoggerFactory.getLogger(this.getClass());
	
	 @Override
	   public void init(FilterConfig filterconfig) throws ServletException {
		 logFiltro.info(" ### Iniciando Filtro ###");	
	 }
	
	 @Override
	   public void destroy() {
		 logFiltro.info(" ### Destruyendo Filtro ###");
	 }
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logFiltro.info(" ### Init doFilter ###");
		aplicarFiltrosCabecers(request, response, chain);
		
	}

	

	private void aplicarFiltrosCabecers(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequestWrapper httpReqWrapper = new HttpServletRequestWrapper((HttpServletRequest) request);
		HttpServletResponseWrapper httpRespWrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
		
		logFiltro.info("filter:"+ ((HttpServletRequest)httpReqWrapper).getRequestURL());
		
		if(request.isSecure()) {
			httpRespWrapper.setHeader("Strict-Transport-Security", "max-age=31622400, includeSubDomains");
			httpRespWrapper.setHeader("Content-Security-Policy", "script-src 'self' 'unsafe-inline' 'unsafe-eval' require-trusted-types-for 'script'");
			httpRespWrapper.setHeader("X-Xss-Protection", "1; mode=block");
			httpRespWrapper.setHeader("X-Content-Type-Options", "nosniff");
		}
		httpRespWrapper.setHeader("X-Frame-Options", "DENY");
		httpRespWrapper.setHeader("X-Powered-By", "none");
		httpRespWrapper.setHeader("Cache-Control", "max-age=0, no-cache, no-store, must-revalidate");
		httpRespWrapper.setHeader("Pragma", "no-cache");
		httpRespWrapper.setHeader("Expires", "0");
		
		String sessionId = ((HttpServletRequest) request).getSession().getId();
		httpRespWrapper.setHeader("Set-Cookie", "JSESSIONID=" + sessionId + "path=/; Secure; HttpOnly; SameSite=Strict");
		
		chain.doFilter(httpReqWrapper, httpRespWrapper);
	}

	
	private String generarToken() {
		byte[] buffer = new byte[50];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(buffer);
		return DatatypeConverter.printHexBinary(buffer);
	}
	
	
}
