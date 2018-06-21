package com.test.bankapp.BankApplication.model;

public class BankData extends ResponseVO {

	private String bankName;
	private String bankId;
	/*private BankMetaDataResponse metaData;
	public BankMetaDataResponse getMetaData() {
		return metaData;
	}
	public void setMetaData(BankMetaDataResponse metaData) {
		this.metaData = metaData;
	}*/
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	
}
