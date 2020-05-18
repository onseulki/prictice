package com.care.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.care.transaction_service.*;
@Controller
public class BankController {
	private BankService bs;
	@RequestMapping("/form")
	public String form() {
		return "bank/form";
	}
	@RequestMapping("/deposit")
	public String deposit() {
		return "bank/deposit";
	}
	@RequestMapping("/indeposit")
	public String indeposit(HttpServletRequest request,Model model) {
		model.addAttribute("request",request);
		bs = new BankInputServiceImpl();
		bs.execute(model);
		
		return "redirect:balance";
	}
	@RequestMapping("/balance")
	public String balance(Model model) {
		bs = new BankBalanceServiceImpl();
		bs.execute(model);
		return "bank/balanceRS";
	}
	@RequestMapping("/send")
	public String send() {
		return "bank/sendForm";
	}
	@RequestMapping("/sendmoney")
	public String sendmoney(HttpServletRequest request,Model model) {
		model.addAttribute("request",request);
		bs = new BankSendServiceImpl();
		bs.execute(model);
		return "redirect:balance";
	}

}

