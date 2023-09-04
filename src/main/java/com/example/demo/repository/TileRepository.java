package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Tile;

public interface TileRepository extends JpaRepository<Tile, Long> {

}
