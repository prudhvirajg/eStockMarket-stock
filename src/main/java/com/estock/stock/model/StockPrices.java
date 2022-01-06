package com.estock.stock.model;

import java.sql.Date;
import java.sql.Time;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockPrices {

	private float stockPrice;

	private Date addedDate;

	private Time addedTime;

	public StockPrices(float stockPrice, java.util.Date addedDate,java.util.Date addedTime) {
		super();
		this.stockPrice = stockPrice;
		this.addedDate = (Date)addedDate;
		this.addedTime =(Time) addedTime;
	}

}
