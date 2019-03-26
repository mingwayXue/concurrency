package com.xue.concurrency.filter;

import com.xue.concurrency.threadlocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by mingway on Date:2019-03-26 16:36.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
public class HttpFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		log.info("do filter,{},{}", Thread.currentThread().getId(), request.getServletPath());
		RequestHolder.add(Thread.currentThread().getId());
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}
