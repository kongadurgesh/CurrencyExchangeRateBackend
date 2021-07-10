package com.infy.CurrencyExchangeRateAnalysis.dto;

public class CustomerDTO {
	
	private Integer customer_id;
	private String customer_email;
	private String customer_password;
	private String customer_name;
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public String getCustomer_password() {
		return customer_password;
	}
	public void setCustomer_password(String customer_password) {
		this.customer_password = customer_password;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	@Override
	public String toString() {
		return "CustomerDTO [customer_id=" + customer_id + ", customer_email=" + customer_email + ", customer_password="
				+ customer_password + ", customer_name=" + customer_name + "]";
	}
	
}
