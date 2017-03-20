package com.atech.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atech.webapp.entity.InvoiceEntity;
import com.atech.webapp.service.InvoiceService;

@RestController
@RequestMapping("/invoice")
public class InvoiceController extends BaseController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@RequestMapping("/list")
	public @ResponseBody List<InvoiceEntity> list(@RequestParam(value = "nameProduct", required = false) String nameProduct,
			@RequestParam(value = "numCnpj", required = false) String numCnpj) throws Exception {

		List<InvoiceEntity> listInvoice = invoiceService.find(nameProduct, numCnpj);

		return listInvoice;
		
	}
	
	@RequestMapping (method = RequestMethod.POST, headers ={"Accept=application/json"}, value = "/insert")
	public void insert(@RequestBody InvoiceEntity invoice) {

		try {
			invoiceService.insert(invoice);
			super.getHttpResponse().setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			super.getHttpResponse().setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
	}
	
}
