package com.atech.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atech.webapp.dao.InvoiceDAO;
import com.atech.webapp.entity.InvoiceEntity;
import com.atech.webapp.jobs.InsertInvoiceQueue;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceDAO invoiceDAO;
	
	@Autowired
	private InsertInvoiceQueue insertInvoiceQueue;

	@Override
	public InvoiceEntity insert(InvoiceEntity invoice) throws Exception {

		insertInvoiceQueue.sendToQueue(invoice);
		
		return invoice;
	}

	@Override
	public List<InvoiceEntity> find(String nameProduct, String numCnpj) {
		
		return invoiceDAO.findByNameCnpj(nameProduct, numCnpj);
	}

	@Override
	public InvoiceEntity findById(Integer id) {
		return invoiceDAO.findById(id);
	}

}
