package com.test.bankapp.BankApplication.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.test.bankapp.BankApplication.model.AccountDataVO;
import com.test.bankapp.BankApplication.model.BankData;
import com.test.bankapp.BankApplication.model.LoginVO;
import com.test.bankapp.BankApplication.model.RegisterVO;

public interface IBankOperationsDao {

	public boolean getPasswordHashAndValidate(LoginVO login);
	public void insertIntoRegisration(RegisterVO registerVO) throws SQLIntegrityConstraintViolationException;
	public List<AccountDataVO> getAccountData(BankData bankdata);
}
