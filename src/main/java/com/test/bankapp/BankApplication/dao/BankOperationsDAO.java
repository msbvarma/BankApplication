package com.test.bankapp.BankApplication.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.test.bankapp.BankApplication.model.AccountDataVO;
import com.test.bankapp.BankApplication.model.BankData;
import com.test.bankapp.BankApplication.model.LoginVO;
import com.test.bankapp.BankApplication.model.RegisterVO;
import com.test.bankapp.BankApplication.security.HashIt;

@Repository
public class BankOperationsDAO implements IBankOperationsDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	HashIt hash;
	
	@Override
	public synchronized void insertIntoRegisration(RegisterVO registerVO) throws
				SQLIntegrityConstraintViolationException {

		String hashConv = "";
		try {
			hashConv = hash.SHA1(registerVO.getPwd());
			System.out.println("HashConverted-->"+hashConv);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		String sql = "INSERT INTO REGISTER (username, password) values (?, ?)";
		try {
		jdbcTemplate.update(sql, registerVO.getUsername(), hashConv);
		}
		catch(Exception e) {
			System.out.println("Exception in Registration:"+e);
			throw new SQLIntegrityConstraintViolationException();
		}
	}

	@Override
	public boolean getPasswordHashAndValidate(LoginVO login) {

		String sql = "SELECT password FROM REGISTER WHERE username = ?";
		String passwordHash = jdbcTemplate.queryForObject(sql, String.class, login.getUserName());

		try {
			if (passwordHash.equalsIgnoreCase(hash.SHA1(login.getPassword()))) {
				return true;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	@Override
	public  List<AccountDataVO> getAccountData(BankData bankdata) {
			   String sql = "SELECT accountName, acctId, type,bal,creditReport,debitReport FROM ACCOUNTDETAILS";
			   RowMapper<AccountDataVO> rowMapper = new BeanPropertyRowMapper<AccountDataVO>(AccountDataVO.class);		
			   return this.jdbcTemplate.query(sql, rowMapper);
	}
}