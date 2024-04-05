package io.reflectoring.banking_service.util;

import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.reflectoring.banking_service.constant.TransactionStatus;
import io.reflectoring.banking_service.model.Transaction;

public class Java2JSON {
	
	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		
		Transaction transaction = new Transaction();
		transaction.setTransactionId("12345");
		transaction.setDate(new Date().toString());
		transaction.setAmountDeducted(1.0d);
		transaction.setStoreName("Mystore");
		transaction.setStoreId("98765");
		transaction.setCardId("45678");
		transaction.setTransactionLocation("Mybank");
	    transaction.setStatus(TransactionStatus.INITIATED);
		
		String json = mapper.writeValueAsString(transaction);
		
		System.out.println(json);
	}

}
