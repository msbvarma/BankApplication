package com.test.bankapp.BankApplication.model;

public class AccountDataVO {

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
}
