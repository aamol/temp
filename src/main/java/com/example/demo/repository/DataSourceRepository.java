package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.DataSource;

public interface DataSourceRepository extends JpaRepository<DataSource, Long> {

}
