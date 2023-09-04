package com.example.demo.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.PageService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PageController {
	
	private PageService pageService;
	
	@GetMapping("/page")
	public ModelAndView displayArticle(Map<String, Object> model) {

	    model.put("page", pageService.getPageDetails(new Long(1)));

	    return new ModelAndView("index", model);
	}

}
