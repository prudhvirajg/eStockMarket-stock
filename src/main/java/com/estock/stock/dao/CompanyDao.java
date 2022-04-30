package com.estock.stock.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estock.stock.model.Company;

@Repository
public interface CompanyDao extends JpaRepository<Company, Integer> {

}
