package com.test.bankapp.BankApplication.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;

public class AccountDataRowMapper implements RowMapper<AccountDataVO> {

	@Override
	public AccountDataVO mapRow(ResultSet rs, int arg1) throws SQLException {
		
		AccountDataVO acct = new AccountDataVO();
		acct.setAcctId(rs.getString("acctId"));
		acct.setAccountName(rs.getString("accountName"));
		
		Acct other = new Acct();
		other.setBal(rs.getString("bal"));
		other.setCreditReport(rs.getString("creditReport"));
		other.setDebitReport(rs.getString("debitReport"));
		other.setType(rs.getString("type"));
		if(!StringUtils.isEmpty(rs.getString("type")) && rs.getString("type").contains("Savin"))
			acct.setSaAcct(other);
		else
			acct.setCuAcct(other);
		return acct;
	}

}
