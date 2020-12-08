package com.pifrans.modules.ecommerce.resources;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pifrans.global.exceptions.errors.ErrorException;
import com.pifrans.global.resources.GenericResource;
import com.pifrans.modules.ecommerce.models.Order;
import com.pifrans.modules.ecommerce.services.OrderService;

@RestController
@RequestMapping(value = "/ecommerce/orders")
public class OrderResource extends GenericResource<Order> {
	private static final Logger LOG = Logger.getLogger(OrderResource.class.getName());

	@Autowired
	private OrderService orderService;

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Order>> findAll() {
		return super.findAll();
	}

	@Override
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Order>> findByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "instant") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		try {
			Page<Order> list = orderService.findByPage(page, linesPerPage, orderBy, direction);
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw new ErrorException("Erro: " + e.getMessage());
		}
	}
}
