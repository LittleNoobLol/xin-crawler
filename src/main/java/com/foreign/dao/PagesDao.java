package com.foreign.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.foreign.pojo.Pages;

public interface PagesDao extends JpaRepository<Pages, Long> {
 
	@Query(value = "call proc_page()", nativeQuery = true)
	public Pages queryPageState();

}
