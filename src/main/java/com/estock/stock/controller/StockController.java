package com.estock.stock.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estock.stock.model.Stock;
import com.estock.stock.model.StockPrices;
import com.estock.stock.service.StockService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1.0/market")
public class StockController {
	@Autowired
	StockService stockservice;

	@PostMapping(value = "/stock/add/{companycode}", consumes = "application/json")
	public ResponseEntity<String> addStockPrice(@PathVariable Integer companycode, @RequestBody Stock request) {
		log.info("Companycode :" + companycode);
		request.setCompanyCode(companycode);
		stockservice.addStockPrice(request);

		return new ResponseEntity<String>("added stock price for company: " + companycode, HttpStatus.CREATED);
	}

	@GetMapping(value = "stock/get/{companycode}/{startdate}/{enddate}")
	public List<StockPrices> getStockPriceList(@PathVariable Integer companycode, @PathVariable Date startdate,
			@PathVariable Date enddate) {

		return stockservice.getStockPricesList(companycode, startdate, enddate);
	}

}
