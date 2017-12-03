package com.chenzhou.bos.service.action.base;

import javax.jws.WebService;

import com.chenzhou.bos.bean.take_delivery.Order;

@WebService
public interface OrderService {
	void save(Order order);
}
