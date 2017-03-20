package com.atech.webapp.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.atech.webapp.entity.InvoiceEntity;

@Component
public class InvoiceDAO extends AbstractDAO<InvoiceEntity> {
	
	@Override
	public Class<InvoiceEntity> getType() {
		return InvoiceEntity.class;
	}
	
	public List<InvoiceEntity> findByNameCnpj(String nameProduct, String numCnpj) {

		StringBuilder sb = new StringBuilder();
		sb.append("select e from InvoiceEntity e join e.invoiceProducts ip ");
		sb.append("where 1 = 1 ");
		
		if (StringUtils.isNotBlank(nameProduct)) {
			sb.append("and ip.nameProduct = :nameProduct ");
		}
		if (StringUtils.isNotBlank(numCnpj)) {
			sb.append("and e.numCnpj = :numCnpj ");
		}
		
		Query query = super.getEm().createQuery(sb.toString());
		
		if (StringUtils.isNotBlank(nameProduct)) {
			query.setParameter("nameProduct", nameProduct);
		}
		
		if (StringUtils.isNotBlank(numCnpj)) {
			query.setParameter("numCnpj", numCnpj);
		}
		
		@SuppressWarnings("unchecked")
		List<InvoiceEntity> list = query.getResultList();
		
		return list;
		
	}
	
}
	
