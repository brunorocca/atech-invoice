package br.com.atech.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.com.atech.entity.InvoiceEntity;
import br.com.atech.service.InvoiceService;

@Component
public class InvoiceCreateConsumer {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceCreateConsumer(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    /**
     * Este método representa a Nota Fiscal sendo consumida pela fila.
     * @param invoice
     */
    @JmsListener(destination = "invoice.create", containerFactory = "jmsFactory")
    public void receiveMessage(InvoiceEntity invoice) {
        invoiceService.save(invoice);
    }
}
