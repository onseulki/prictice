package com.care.transaction_service;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.care.transaction_dao.BankDAO;

public class BankBalanceServiceImpl implements BankService{
	private BankDAO dao;
	public BankBalanceServiceImpl() {
		String configLocation = "classpath:template/template-config.xml";
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext(configLocation);
		dao = ctx.getBean("dao",BankDAO.class);
	}
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		model.addAttribute("balance",dao.balance());
	}
}

