package com.atech.webapp.service;

import java.util.List;

import com.atech.webapp.entity.InvoiceEntity;

public interface InvoiceService {
	
	InvoiceEntity insert(InvoiceEntity invoice) throws Exception;
	
	InvoiceEntity findById(Integer id);
	
	List<InvoiceEntity> find(String nameProduct, String numCnpj);

}
