package com.foreign.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.foreign.pojo.Foreigninfo;

public interface ForeigninfoDao extends JpaRepository<Foreigninfo, Long> {

	@Query(value = "call proc_info()", nativeQuery = true)
	public Foreigninfo queryIsQuerys();
	
}
