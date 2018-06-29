package com.test.bankapp.BankApplication.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.bankapp.BankApplication.model.AccountDataRowMapper;
import com.test.bankapp.BankApplication.model.AccountDataVO;
import com.test.bankapp.BankApplication.model.BankData;
import com.test.bankapp.BankApplication.model.BankMetaDataResponse;
import com.test.bankapp.BankApplication.model.LoginVO;
import com.test.bankapp.BankApplication.model.RegisterVO;
import com.test.bankapp.BankApplication.model.ResponseVO;
import com.test.bankapp.BankApplication.service.BankOperationServicesImpl;

@RestController
@RequestMapping("bankapp")
public class BankServicesController {

	@Autowired
	BankOperationServicesImpl bankOperations;

	@PostMapping(value = "/register")
	public ResponseEntity<ResponseVO> registerNewUser(@RequestBody RegisterVO register) {
		ResponseVO response = new ResponseVO();
		boolean isValid = validateInitialData(register);
		if (isValid) {
			response = bankOperations.resgisterNewUser(register);
		} else {
			response.setStatusCd("200");
			response.setStatusDesc("Password and Re-Enterd password doesn't macth");
		}
		return new ResponseEntity<ResponseVO>(response, HttpStatus.OK);

	}

	@PostMapping(value = "/getBankData")
	public ResponseEntity<List<BankData>> getBankData(@RequestBody LoginVO login) {
		ResponseVO loginResp = new ResponseVO();
		BankData bankData = new BankData();
		List<BankData> response = new ArrayList<BankData>();
		loginResp = bankOperations.validateLogin(login);
		if (loginResp != null && loginResp.getStatusDesc().equalsIgnoreCase("valid"))
			response = bankOperations.getBankData();
		else {
			/*
			 * bankData.setStatusCd("401"); bankData.setStatusDesc("Invalid user");
			 */
			response.add(bankData);
			return new ResponseEntity<List<BankData>>(response, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<List<BankData>>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/getBankMetaData")
	public ResponseEntity<List<BankMetaDataResponse>> getBankMetaData(@RequestBody BankData bankData) {
		List<BankMetaDataResponse> response = new ArrayList<BankMetaDataResponse>();

		response = bankOperations.getBankMetaData(bankData);

		return new ResponseEntity<List<BankMetaDataResponse>>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/getBankAccountDetails")
	public ResponseEntity<List<AccountDataVO>> getBankAccountDetails(@RequestBody BankMetaDataResponse meta) {
		BankData bankData = new BankData();
		if (meta != null && meta.getUserName() != null) {
			System.out.println("Username: " + meta.getUserName());
			if (meta.getUserName().contains("icic"))
				bankData.setBankId("ICIC0090");
			else if (meta.getURL().contains("sbi"))
				bankData.setBankId("SBI0090");
			else
				bankData.setBankId("CITI0090");
		}
		List<AccountDataVO> response = bankOperations.getAccountDetails(bankData);

		return new ResponseEntity<List<AccountDataVO>>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/getAccountDetails")
	public ResponseEntity<List<AccountDataVO>> getAccountDetails(@RequestBody BankData bankData) {

		List<AccountDataVO> response = bankOperations.getAccountDetails(bankData);

		return new ResponseEntity<List<AccountDataVO>>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/getAccountData")
	public ResponseEntity<List<AccountDataVO>> getAccountData(@RequestBody BankData bankData) {
		System.out.println("In get account data-->");
		List<AccountDataVO> response = bankOperations.getAccountData(bankData);

		return new ResponseEntity<List<AccountDataVO>>(response, HttpStatus.OK);
	}

	private boolean validateInitialData(RegisterVO register) {
		if (!register.getPwd().equalsIgnoreCase(register.getRepwd())) {

			return false;
		}
		return true;
	}
}
