package com.atech.webapp.dao;

import org.springframework.stereotype.Component;

import com.atech.webapp.entity.InvoiceProductEntity;

@Component
public class InvoiceProductDAO extends AbstractDAO<InvoiceProductEntity> {

	@Override
	public Class<InvoiceProductEntity> getType() {
		return InvoiceProductEntity.class;
	}

}
