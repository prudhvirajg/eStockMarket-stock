package com.estock.stock.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estock.stock.dao.StockDao;
import com.estock.stock.model.Stock;
import com.estock.stock.model.StockPrices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockService {
	@Autowired
	StockDao stockdao;

	public String addStockPrice(Stock stock) {
		long mills = System.currentTimeMillis();
		stock.setAddedDate(new Date(mills));
		stock.setAddedTime(new Time(mills));
		stockdao.save(stock);
		return "success";
		

	}

	public List<StockPrices> getStockPricesList(Integer companycode, Date startdate, Date enddate) {
		// TODO Auto-generated method stub

		return stockdao.findAllByIdAndDateBetween(companycode, startdate, enddate);
	}

	
	/*
	 * public void deleteAllStockPricesById(Integer companycode) {
	 * stockdao.deleteAllStockPricesByCompanyCode(companycode); }
	 */
}
