package com.goodchobo.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CORSFilter implements Filter {
	private static final Logger log = Logger.getLogger(CORSFilter.class);

	public CORSFilter() {
		log.info("CORSFilter init");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {

		try {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;
			log.info("method: " + request.getMethod());
			log.info("Header Origin: " + request.getHeader("origin"));
			log.info("Header Content-Type: " + request.getHeader("content-Type"));
			response.setHeader("access-control-allow-origin", request.getHeader("origin"));
			response.setHeader("access-control-allow-credentials", "true");
			response.setHeader("access-control-allow-methods", "POST, GET, OPTIONS");
			response.setHeader("access-control-max-age", "3600");
			response.setHeader("access-control-allow-headers","content-Type, accept, accept-encoding, accept-language, origin, referer, user-agent, referer-url,"
					+ "accessToken, host, X-Forwarded-For, Proxy-Client-IP, WL-Proxy-Client-IP, HTTP_CLIENT_IP, HTTP_X_FORWARDED_FOR");
			chain.doFilter(req, res);
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (ServletException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void destroy() {
	}
}
