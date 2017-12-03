package com.chenzhou.bos.jobs.mailjob;

import java.util.List;

import javax.annotation.Resource;

import com.chenzhou.bos.bean.take_delivery.WorkBill;
import com.chenzhou.bos.dao.base.WorkBillDao;

public class MailJob {

	@Resource
	private WorkBillDao workBillDao;
	
	public void sendMail() {
		List<WorkBill> list = workBillDao.findAll();
		String emailBody = "工单编号\t工单类型\t区间状态\t快递员<br/>";
		for (WorkBill workBill : list) {
			emailBody+= workBill.getId()+"\t"+workBill.getType()+"\t"+workBill.getPickstate()+"\t"+workBill.getCourier().getName()+"<br/>";
		}
		String receiver = "ls@store.com";
		String subject="工单信息";
		MailUtils.sendMail(receiver, subject, emailBody);
	}
	
}
