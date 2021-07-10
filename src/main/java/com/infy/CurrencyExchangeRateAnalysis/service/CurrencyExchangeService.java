package com.infy.CurrencyExchangeRateAnalysis.service;

import com.infy.CurrencyExchangeRateAnalysis.dto.CustomerDTO;
import com.infy.CurrencyExchangeRateAnalysis.exception.CurrencyExchangeException;

public interface CurrencyExchangeService {
	Integer registerCustomer(CustomerDTO customerDTO) throws CurrencyExchangeException;
	public CustomerDTO getCustomer(Integer customer_id) throws CurrencyExchangeException;
	public CustomerDTO authenticateUser(CustomerDTO customer) throws CurrencyExchangeException;

	public String updatePassword(String password,Integer customer_id) throws CurrencyExchangeException; 
	public String editDetails(String name,String emailId,Integer id) throws CurrencyExchangeException;
}
