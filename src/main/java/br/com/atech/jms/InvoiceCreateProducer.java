package br.com.atech.jms;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import br.com.atech.entity.InvoiceEntity;

@Service
@Transactional
public class InvoiceCreateProducer {

    private ConfigurableApplicationContext configurableApplicationContext;

    @Autowired
    public InvoiceCreateProducer(ConfigurableApplicationContext configurableApplicationContext) {
        this.configurableApplicationContext = configurableApplicationContext;
    }

    public void send(InvoiceEntity invoiceEntity) {
        JmsTemplate jmsTemplate = configurableApplicationContext.getBean(JmsTemplate.class);
        jmsTemplate.convertAndSend("invoice.create", invoiceEntity);
    }
}
