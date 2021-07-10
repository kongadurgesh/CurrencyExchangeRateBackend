package com.infy.CurrencyExchangeRateAnalysis.service;


import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.infy.CurrencyExchangeRateAnalysis.dto.CustomerDTO;
import com.infy.CurrencyExchangeRateAnalysis.entity.Customer;
import com.infy.CurrencyExchangeRateAnalysis.exception.CurrencyExchangeException;
import com.infy.CurrencyExchangeRateAnalysis.repository.CurrencyExchangeRepository;


@Service(value="currencyExchangeService")
@Transactional
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {
	
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	@Override
	public Integer registerCustomer(CustomerDTO customerDTO) throws CurrencyExchangeException{
		Customer cus=currencyExchangeRepository.findByEmailOfCust(customerDTO.getCustomer_email());
		if(cus!=null)
			throw new CurrencyExchangeException("Service.Customer_Exists");
		Customer c=new Customer();
		c.setCustomer_email(customerDTO.getCustomer_email());
		c.setCustomer_password(customerDTO.getCustomer_password());
		c.setCustomer_name(customerDTO.getCustomer_name());
		return currencyExchangeRepository.save(c).getCustomer_id();
		
	}
	@Override
	public CustomerDTO getCustomer(Integer customer_id) throws CurrencyExchangeException{
		Optional<Customer> op=currencyExchangeRepository.findById(customer_id);
		Customer c=op.orElseThrow(()->new CurrencyExchangeException("Service.Invalid_ID"));
		CustomerDTO cdto = new CustomerDTO();
		cdto.setCustomer_email(c.getCustomer_email());
		cdto.setCustomer_id(c.getCustomer_id());
		cdto.setCustomer_name(c.getCustomer_name());
		cdto.setCustomer_password(c.getCustomer_password());
		
		return cdto;
	}
	@Override
	public CustomerDTO authenticateUser(CustomerDTO customer) throws CurrencyExchangeException {
				
				CustomerDTO cdto=new CustomerDTO();
				Customer customerLogin = currencyExchangeRepository
						.findByEmailOfCust(customer.getCustomer_email());
				if(customerLogin==null)
					throw new CurrencyExchangeException("Service.WRONG_CREDENTIALS");
				else 
				{
					if (customer.getCustomer_password().equals(customerLogin.getCustomer_password()))
					{
						cdto.setCustomer_email(customerLogin.getCustomer_email());
						cdto.setCustomer_id(customerLogin.getCustomer_id());
						cdto.setCustomer_name(customerLogin.getCustomer_name());
						cdto.setCustomer_password(customerLogin.getCustomer_password());
					}
					else
					throw new CurrencyExchangeException("Service.WRONG_CREDENTIALS");
				}
				return cdto;
	}
	@Override
	public String updatePassword(String password,Integer customer_id) throws CurrencyExchangeException {
		// TODO Auto-generated method stub
		Optional<Customer> op=currencyExchangeRepository.findById(customer_id);
		Customer c=op.orElseThrow(()->new CurrencyExchangeException("Service.Invalid_ID"));
		c.setCustomer_password(password);
		return "PASSWORD UPDATED SUCCESSFULLY";
		
	}
	@Override
	public String editDetails(String name, String emailId,Integer id) throws CurrencyExchangeException {
		Optional<Customer> optional=currencyExchangeRepository.findById(id);
		Customer customer=optional.orElseThrow(()->new CurrencyExchangeException("Service.Invalid_ID"));
		Customer customer2=currencyExchangeRepository.findByEmailOfCust(emailId);
		if(customer2!=null && customer2.getCustomer_email().equalsIgnoreCase(emailId)) {
			throw new CurrencyExchangeException("Service.SAME_EMAIL_ID");
		}
		else if(customer2!=null && customer2.getCustomer_name().equalsIgnoreCase(name)) {
			throw new CurrencyExchangeException("Service.Same_USER_NAME");
		}
		customer.setCustomer_name(name);
		customer.setCustomer_email(emailId);
		return "DETAILS UPDATED SUCCESSFULLY";
	}
	
}
