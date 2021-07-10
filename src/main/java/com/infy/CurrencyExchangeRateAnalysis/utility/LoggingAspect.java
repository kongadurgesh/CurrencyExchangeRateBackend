package com.infy.CurrencyExchangeRateAnalysis.utility;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.infy.CurrencyExchangeRateAnalysis.exception.CurrencyExchangeException;

@Component
@Aspect
public class LoggingAspect {

	private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
	
	@AfterThrowing(pointcut="execution(* com.infy.CurrencyExchangeRateAnalysis.service.CurrencyExchangeServiceImpl.*(..))",throwing="exception")
	public void logServiceException(CurrencyExchangeException exception)
	{
		LOGGER.error(exception.getMessage(),exception);
	}
}
