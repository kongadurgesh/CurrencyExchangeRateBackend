package com.infy.CurrencyExchangeRateAnalysis.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.CurrencyExchangeRateAnalysis.dto.CustomerDTO;
import com.infy.CurrencyExchangeRateAnalysis.exception.CurrencyExchangeException;
import com.infy.CurrencyExchangeRateAnalysis.service.CurrencyExchangeService;

@CrossOrigin
@RestController
@RequestMapping(value="/currency-exchange")
public class CurrencyExchangeAPI {
	
	@Autowired
	private CurrencyExchangeService currencyExchangeService;
	@Autowired
	private Environment environment;
	
	@PostMapping(value="/customer")
	public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customerDTO) throws CurrencyExchangeException{
		int id=currencyExchangeService.registerCustomer(customerDTO);
		return new ResponseEntity<>(environment.getProperty("API.REGISTRATION_SUCCESS")+id,HttpStatus.CREATED);
	}
	@GetMapping(value="/{customer_id}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Integer customer_id) throws CurrencyExchangeException{
		return new ResponseEntity<>(currencyExchangeService.getCustomer(customer_id),HttpStatus.OK);
	}
	@PostMapping(value="/login")
	public ResponseEntity<CustomerDTO> authenticateUser(@RequestBody CustomerDTO customer) throws CurrencyExchangeException
	{
		CustomerDTO cdto =currencyExchangeService.authenticateUser(customer);
		return new ResponseEntity<>(cdto,HttpStatus.OK);
	}
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<String> updatePassword(@PathVariable(value="id") Integer id,@RequestBody CustomerDTO customer) throws CurrencyExchangeException{
		String success = currencyExchangeService.updatePassword(customer.getCustomer_password(),id);
		String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage,HttpStatus.OK);
		
	}
	@PutMapping(value = "/editprofile/{id}")
	public ResponseEntity<String> editDetails(@PathVariable(value = "id") Integer id, @RequestBody CustomerDTO customer) throws CurrencyExchangeException{
		String success=currencyExchangeService.editDetails(customer.getCustomer_name(),customer.getCustomer_email(),id);
		String successmessage=environment.getProperty("API.EDIT_DETAILS_SUCCESS");
		return new ResponseEntity<>(successmessage,HttpStatus.OK);
	}
	
}
