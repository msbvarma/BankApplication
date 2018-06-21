package com.test.bankapp.BankApplication.service;



import java.util.List;

import com.test.bankapp.BankApplication.model.AccountDataVO;
import com.test.bankapp.BankApplication.model.BankData;
import com.test.bankapp.BankApplication.model.BankMetaDataResponse;
import com.test.bankapp.BankApplication.model.LoginVO;
import com.test.bankapp.BankApplication.model.RegisterVO;
import com.test.bankapp.BankApplication.model.ResponseVO;

public interface BankOperationServices {
		public ResponseVO resgisterNewUser(RegisterVO registerVO);		
		public ResponseVO validateLogin(LoginVO login);
		public List<BankData> getBankData();
		public List<BankMetaDataResponse> getBankMetaData(BankData bankData);
		public List<AccountDataVO> getAccountDetails(BankData bankData);
		public List<AccountDataVO> getAccountData(BankData bankData);
		

}
