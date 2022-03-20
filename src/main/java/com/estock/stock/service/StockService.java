package com.estock.stock.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estock.stock.dao.CompanyDao;
import com.estock.stock.dao.StockDao;
import com.estock.stock.exceptions.ApplicationException;
import com.estock.stock.model.Company;
import com.estock.stock.model.Stock;
import com.estock.stock.model.StockPrices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockService {
	@Autowired
	StockDao stockDao;
	@Autowired
	CompanyDao companyDao;

	public String addStockPrice(Stock stock) {
		if (companyDao.existsById(stock.getCompanyCode())) {
			log.info("{} exists in companies DB",stock.getCompanyCode());
			long mills = System.currentTimeMillis();
			stock.setAddedDate(new Date(mills));
			stock.setAddedTime(new Time(mills));
			log.info("adding the stock price for {} in stock DB  ",stock.getCompanyCode());
			stockDao.save(stock);
			try {
				updateStockPrice(stock);
			} catch (Exception e) {
				throw new ApplicationException("exceptions while updating latest stock price " + e.getMessage());
			}
			return "success";
		} else
			throw new ApplicationException("company does not exits");
	}

	private void updateStockPrice(Stock stock) {
		log.info("updating the stock price for {} in compnay DB ",stock.getCompanyCode());
		Company company = companyDao.findById(stock.getCompanyCode()).get();
		company.setStockPrice(stock.getStockPrice());
		companyDao.save(company);
	}

	public List<StockPrices> getStockPricesList(Integer companycode, Date startdate, Date enddate) {
		// TODO Auto-generated method stub
		log.info("getting all stock prices for {}",companycode);
		return stockDao.findAllByIdAndDateBetween(companycode, startdate, enddate);
	}

}
