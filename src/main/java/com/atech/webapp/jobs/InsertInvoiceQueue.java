package com.atech.webapp.jobs;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.atech.webapp.dao.InvoiceDAO;
import com.atech.webapp.entity.InvoiceEntity;

@Component
public class InsertInvoiceQueue {
	
	private static final Logger logger = Logger.getLogger(InsertInvoiceQueue.class);

	private static final String JNDI_NAME = "ImportInvoiceQueue.import";
	
	@Autowired
	private InvoiceDAO invoiceDAO;	
	
	private final JmsTemplate jmsTemplate;
	
	@Autowired
    public InsertInvoiceQueue(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

	public void sendToQueue(InvoiceEntity invoice) throws Exception {
		
		logger.info("[" + JNDI_NAME + " send to queue] ");
		
        jmsTemplate.convertAndSend(JNDI_NAME, invoice);
        
    }
	
	@JmsListener(destination = JNDI_NAME)
	public void runQueue(InvoiceEntity invoice) throws Exception {
		
		logger.info("[" + JNDI_NAME + " begin] - idLicense: " + invoice.getIdInvoice());
		
		invoice.setDateInsert(Calendar.getInstance());
		invoiceDAO.persist(invoice);

		logger.info("[" + JNDI_NAME + " end] ");
		
	}
	
}
