package com.infy.CurrencyExchangeRateAnalysis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.infy.CurrencyExchangeRateAnalysis.entity.Customer;

public interface CurrencyExchangeRepository extends CrudRepository<Customer,Integer> {
	@Query("select c from Customer c where c.customer_email=?1")
	Customer findByEmailOfCust(String customer_email);
	@Query("update Customer c set c.customer_password=?1 where c.customer_id = ?2")
	void updatePassword(String password,Integer customer_id);
}
