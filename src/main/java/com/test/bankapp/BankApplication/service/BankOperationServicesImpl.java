package com.test.bankapp.BankApplication.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.bankapp.BankApplication.dao.BankOperationsDAO;
import com.test.bankapp.BankApplication.model.AccountDataRowMapper;
import com.test.bankapp.BankApplication.model.AccountDataVO;
import com.test.bankapp.BankApplication.model.BankData;
import com.test.bankapp.BankApplication.model.BankMetaDataResponse;
import com.test.bankapp.BankApplication.model.LoginVO;
import com.test.bankapp.BankApplication.model.RegisterVO;
import com.test.bankapp.BankApplication.model.ResponseVO;

@Service
public class BankOperationServicesImpl implements BankOperationServices {
@Autowired
BankOperationsDAO bankOperationsDAO;
@Autowired
GetBankDetailsRestServiceImpl getBankDetailsRestService;
	@Override
	public ResponseVO resgisterNewUser(RegisterVO registerVO) {
		ResponseVO responseVO = new ResponseVO();
		try {
		bankOperationsDAO.insertIntoRegisration(registerVO);
		}catch(Exception e) {
			responseVO.setStatusCd("200");
			responseVO.setStatusDesc("User already exists");
			return responseVO;
		}
		responseVO.setStatusCd("201");
		responseVO.setStatusDesc("Registered");
		return responseVO;
	}

	@Override
	public ResponseVO validateLogin(LoginVO login) {
		ResponseVO response = new ResponseVO();
		boolean isValid=false;
		try {
		isValid = bankOperationsDAO.getPasswordHashAndValidate(login);
		}catch(Exception e) {
			response.setStatusCd("401");
			response.setStatusDesc("Invalid");
		}
		if(isValid) {
			response.setStatusCd("200");
			response.setStatusDesc("valid");
		}else {
			response.setStatusCd("401");
			response.setStatusDesc("Invalid");
		}
		return response;
	}

	@Override
	public List<BankData> getBankData() {

		List<BankData> bankList = new ArrayList<BankData>();
		BankData data1 = new BankData();
		data1.setBankName("ICICI");
		data1.setBankId("ICIC0090");

		BankData data2 = new BankData();
		data2.setBankName("SBI");
		data2.setBankId("SBI0090");
		
		BankData data3 = new BankData();
		data3.setBankName("CITI");
		data3.setBankId("CITI0090");

		bankList.add(data1);
		bankList.add(data2);
		bankList.add(data3);
		
		
		return bankList;
	}

	@Override
	public List<BankMetaDataResponse> getBankMetaData(BankData bankData) {

		List<BankMetaDataResponse> bankList = new ArrayList<BankMetaDataResponse>();
		BankMetaDataResponse data1 = new BankMetaDataResponse();
		data1.setUserName("iciciadmin");
		data1.setPassword("password");
		data1.setURL("http://icici.com/login");

		BankMetaDataResponse data2 = new BankMetaDataResponse();
		data2.setUserName("sbiadmin");
		data2.setPassword("password");
		data2.setURL("http://sbi.com/login");
		
		BankMetaDataResponse data3 = new BankMetaDataResponse();
		data2.setUserName("citiadmin");
		data2.setPassword("password");
		data2.setURL("http://citibank.com/login");

		if(bankData.getBankName().contains("ICIC"))
		bankList.add(data1);
		else if(bankData.getBankName().contains("CITI"))
		bankList.add(data2);
		else if(bankData.getBankName().contains("SBI"))
		bankList.add(data3);
		
		return bankList;
	}

	@Override
	public List<AccountDataVO> getAccountDetails(BankData bankData) {
		List<AccountDataVO> list = new ArrayList<>();
		list = getBankDetailsRestService.getBankDataFromService(bankData);
		return list;
	}
	//Getbank details from DAO and return to rest service
	@Override
	public List<AccountDataVO> getAccountData(BankData bankData) {
		System.out.println("In get account data-->");
		List<AccountDataVO> acctList = new ArrayList<>();
		acctList = bankOperationsDAO.getAccountData(bankData);
		return acctList;
	}
}
