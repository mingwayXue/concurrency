package com.xue.concurrency.filter;

import com.xue.concurrency.threadlocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mingway on Date:2019-03-26 16:49.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 请求前
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("preHandle");
		return true;
	}

	/**
	 * 请求后
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 出现异常后/请求后
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
		log.info("afterCompletion");
		RequestHolder.remove();
		return;
	}
}
