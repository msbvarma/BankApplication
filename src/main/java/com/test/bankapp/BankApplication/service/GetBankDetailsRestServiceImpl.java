package com.test.bankapp.BankApplication.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.test.bankapp.BankApplication.model.AccountDataRowMapper;
import com.test.bankapp.BankApplication.model.AccountDataVO;
import com.test.bankapp.BankApplication.model.BankData;

@Service
public class GetBankDetailsRestServiceImpl {

	public List<AccountDataVO> getBankDataFromService(BankData bankData) {
	HttpHeaders headers = new HttpHeaders();
	ResponseEntity<AccountDataVO[]> acctList=null;
	List<AccountDataVO> list = new ArrayList<>();
	headers.setContentType(MediaType.APPLICATION_JSON);
	
	String url = "http://localhost:8081/bankapp/getAccountData";
	HttpEntity<BankData> entity = new HttpEntity<BankData>(bankData, headers);
	System.out.println("Entity:-->>"+entity);
	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<AccountDataVO[]> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, AccountDataVO[].class);
	AccountDataVO[] resp = responseEntity.getBody();
	list = Arrays.asList(resp);
	return list;
	
	} 
	
	/*public static void main(String args[]) {
		GetBankDetailsRestServiceImpl util = new GetBankDetailsRestServiceImpl();
    	List<AccountDataVO> list = new ArrayList<>();
    	BankData bankData = new BankData();
    	bankData.setBankId("ICIC0090");
    	bankData.setBankName("ICICI");
    	list = util.getBankDataFromService(bankData);  	
    } */
}
