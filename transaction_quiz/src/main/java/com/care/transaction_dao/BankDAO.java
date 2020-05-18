package com.care.transaction_dao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
public class BankDAO {
	private JdbcTemplate template;
	private TransactionTemplate transactionTemplate;
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public int[] input(String money) {
		final  String sql_my="update myaccount set money="+money+" where num=1";
		final String sql_sys="update sysaccount set money="+money+" where num=1";
		final String totMoney = "update balance set totmoney=totmoney+"+money+" where num=1";
		final int[] arr=new int[3];
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					arr[0] = template.update(sql_my);
					arr[1] = template.update(sql_sys);
					arr[2] = template.update(totMoney);
				}
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	public int balance() {
		String sql = "select totmoney from balance where num=1";
		try {
			int bal = template.queryForObject(sql, Integer.class);
			return bal;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int send(String money) {
		final String sql_my="update myaccount set money=money-"+money+" where num=1";
		final String sql_sys="update sysaccount set money=money-"+money+" where num=1";
		final String totMoney = "update balance set totmoney=totmoney-"+money+" where num=1";
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					template.update(sql_my);
					template.update(sql_sys);
					template.update(totMoney);
				}
			});
		}catch(Exception e) {return 0;}
		return 1;
	}

	
}






