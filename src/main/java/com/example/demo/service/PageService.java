package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.DataSource;
import com.example.demo.model.Page;
import com.example.demo.model.Section;
import com.example.demo.model.Tile;
import com.example.demo.repository.DataSourceRepository;
import com.example.demo.repository.PageRepository;
import com.example.demo.repository.SectionRepository;
import com.example.demo.repository.TileRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PageService {
	
	private PageRepository pageRepository;
	
	private SectionRepository sectionRepository;
	
	private TileRepository tileRepository;
	
	private DataSourceRepository dataSourceRepository;

	public Page getPageDetails(Long pageId) {
		
		return pageRepository.findById(pageId).get();
	}

	public String init() {
		
		Page page = new Page();
		page.setName("Page 1");
		page.setTemplate("Template 1");
		
		Section section = new Section();
		section.setTemplate("Template 1" );
		
		
		Tile tile = new Tile();
		tile.setTemplate("Template 2");
		
		DataSource dataSource =  new DataSource();
		dataSource.setContent("test text");

		section.setPage(page);
		
		sectionRepository.save(section);
		
		tile.setSection(section);
		
		tileRepository.save(tile);
		
		
		dataSource.setTile(tile);
		
		dataSourceRepository.save(dataSource);
		
		pageRepository.save(page);
		
		
		
		return "OK";
	}

}
