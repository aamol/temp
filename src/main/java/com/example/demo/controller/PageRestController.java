package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Page;
import com.example.demo.service.PageService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PageRestController {

	
	private PageService pageService;
	
	
	@GetMapping("/page/{pageId}")
	public Page getPageDetails(@PathVariable Long pageId) {
		
		
		return pageService.getPageDetails(pageId);
	}
	
	@GetMapping("/init")
	public String init() {
		
		
		return pageService.init();
	}
	
	
	
}
