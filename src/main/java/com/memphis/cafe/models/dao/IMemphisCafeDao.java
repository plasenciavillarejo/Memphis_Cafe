package com.memphis.cafe.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.memphis.cafe.models.entity.MemphisCafe;


public interface IMemphisCafeDao extends JpaRepository<MemphisCafe, String>{


}


