package com.example.demo.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.DataSource;
import com.example.demo.model.Page;

@Service
public class DataSourceService {

	@Autowired
	@Qualifier("secondary1JdbcTemplate")
	private JdbcTemplate jdbcTemplate1;

	@Autowired
	@Qualifier("secondary2JdbcTemplate")
	private JdbcTemplate jdbcTemplate2;

	public Page extractDataFromSource(Page page) {
		page.setSections(page.getSections().stream().map(section -> {
			section.setTiles(section.getTiles().stream().map(tile -> {
				tile.setDataSource(tile.getDataSource().stream().map(this::getData).collect(Collectors.toList()));
				return tile;
			}).collect(Collectors.toList()));
			return section;
		}).collect(Collectors.toList()));

		return page;
	}

	public DataSource getData(DataSource dataSource) {

		switch (dataSource.getType()) {
		case "CONTENT":
			dataSource.setContent(dataSource.getQuery());

			break;
		case "DB":
			switch (dataSource.getDataconn()) {
			case "template1":
				dataSource.setContent(jdbcTemplate1.queryForObject(dataSource.getQuery(), Long.class).toString());
				break;
			case "template2":
				jdbcTemplate2.queryForObject(dataSource.getQuery(), Long.class);
				break;
			default:
				break;
			}

		default:
			break;
		}

		return dataSource;
	}

}
