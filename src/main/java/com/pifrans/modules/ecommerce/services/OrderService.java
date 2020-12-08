package com.pifrans.modules.ecommerce.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pifrans.global.models.User;
import com.pifrans.global.securities.UserDetailSecurity;
import com.pifrans.global.services.GenericService;
import com.pifrans.global.services.UserService;
import com.pifrans.modules.ecommerce.models.Order;
import com.pifrans.modules.ecommerce.repositories.OrderRepository;

@Service
public class OrderService extends GenericService<Order> {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(OrderService.class.getName());

	@Autowired
	private UserService userService;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Page<Order> findByPage(Integer page, Integer linesPerPage, String orderBy, String direction)
			throws Exception {
		LOG.info("findByPage");
		UserDetailSecurity userDetailSecurity = UserService.authenticated();
		if (userDetailSecurity == null) {
			return null;
		} else {
			User user = userService.find(userDetailSecurity.getId());
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return orderRepository.findByUser(user, pageRequest);
		}
	}
}
