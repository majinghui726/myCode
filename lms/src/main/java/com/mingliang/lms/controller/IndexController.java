package com.mingliang.lms.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页或者权限url跳转控制器
 * 
 *
 */
@Controller
public class IndexController {
	private static final Logger logger = LogManager.getLogger(IndexController.class);
	
	/**
     * 网站根目录请求
     * @return
     */
	@RequestMapping("/")
	public String index() {
		return "/login";
	}
	
	@RequestMapping("/main")
	public String main() {
		return "/main";
	}
	
}
