package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tile {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long id;

	private String template;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = "section_id")
	@JsonIgnore
	private Section section;
	
	@OneToMany(mappedBy = "tile",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<DataSource> dataSource;
}
