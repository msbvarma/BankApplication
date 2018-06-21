package com.test.bankapp.BankApplication.model;

public class Acct {
private String type;
private long  bal;
private String creditReport;
private String debitReport;
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public long getBal() {
	return bal;
}
public void setBal(long bal) {
	this.bal = bal;
}
public String getCreditReport() {
	return creditReport;
}
public void setCreditReport(String creditReport) {
	this.creditReport = creditReport;
}
public String getDebitReport() {
	return debitReport;
}
public void setDebitReport(String debitReport) {
	this.debitReport = debitReport;
}
}
