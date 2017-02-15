package com.hf.spring.mybatis.web;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler{

	private static Logger log = LoggerFactory
			.getLogger(MyControllerAdvice.class);

	@ModelAttribute
	public void sysData(Model model) {
		//应用到所有@RequestMapping注解方法，在其执行之前把返回值放入Model
		model.addAttribute("sysroot", "krtweb");
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public String processUnauthorizedException(Exception e, HttpServletRequest request) {
		return "error/403";
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> processException(Exception e, HttpServletRequest request) {
		if (isAjax(request)) {
			log.error(request.getRequestURI() + " Ajax调用发生错误:"
					+ e.getLocalizedMessage());
		}else{
			log.error(request.getRequestURI() + " 链接访问发生错误:"
					+ e.getLocalizedMessage());
		}
		
		return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}



	/*@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}

			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(text == null ? null : StringEscapeUtils
						.escapeHtml4(text.trim()));
			}

		});
	}*/

	private boolean isAjax(ServletRequest request) {
		boolean isAjax = false;
		if (request instanceof HttpServletRequest) {
			HttpServletRequest rq = (HttpServletRequest) request;
			String requestType = rq.getHeader("X-Requested-With");
			if (requestType != null && "XMLHttpRequest".equals(requestType)) {
				isAjax = true;
			}
		}
		return isAjax;
	}
}
