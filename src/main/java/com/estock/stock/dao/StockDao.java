package com.estock.stock.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estock.stock.model.Stock;
import com.estock.stock.model.StockPrices;

@Repository
public interface StockDao extends JpaRepository<Stock, Integer> {

	@Query("SELECT new com.estock.stock.model.StockPrices(s.stockPrice,s.addedDate,s.addedTime) FROM Stock s  WHERE s.companyCode = ?1 and s.addedDate between ?2 and ?3")
//	@Query("SELECT s FROM Stock s  WHERE s.companyCode = ?1 and s.addedDate between ?2 and ?3")
	List<StockPrices> findAllByIdAndDateBetween(Integer companycode, Date startdate, Date enddate);

}
