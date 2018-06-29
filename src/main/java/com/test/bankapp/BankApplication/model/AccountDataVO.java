package com.test.bankapp.BankApplication.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AccountDataVO implements RowMapper{

	private String accountName;
	private String acctId;
	private Acct saAcct;
	private Acct cuAcct;
	
	public Acct getSaAcct() {
		return saAcct;
	}
	public void setSaAcct(Acct saAcct) {
		this.saAcct = saAcct;
	}
	public Acct getCuAcct() {
		return cuAcct;
	}
	public void setCuAcct(Acct cuAcct) {
		this.cuAcct = cuAcct;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}
	@Override
	public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
		
		return null;
	}
}
