package com.azure.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

	@RequestMapping(value = "/hello", method = { RequestMethod.GET, RequestMethod.POST })
	public String defaultPage(ModelMap model, HttpServletRequest httpRequest, Principal principal) {
		return "hello";
	}

}
