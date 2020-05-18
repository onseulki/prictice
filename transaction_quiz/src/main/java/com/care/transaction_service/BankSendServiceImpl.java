package com.care.transaction_service;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.care.transaction_dao.BankDAO;
public class BankSendServiceImpl implements BankService{
	private BankDAO dao;
	public BankSendServiceImpl() {
		String configLocation = "classpath:template/template-config.xml";
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext(configLocation);
		dao = ctx.getBean("dao",BankDAO.class);
	}
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String money = request.getParameter("sendmoney");
		model.addAttribute("sendresult",dao.send(money));
	}
}





