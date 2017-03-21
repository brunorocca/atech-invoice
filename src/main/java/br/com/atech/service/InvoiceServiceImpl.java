package br.com.atech.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.atech.dao.InvoiceDAO;
import br.com.atech.entity.InvoiceEntity;
import br.com.atech.jms.InvoiceCreateProducer;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceDAO repository;

    private final InvoiceCreateProducer producer;

    @Autowired
    public InvoiceServiceImpl(InvoiceDAO repository, InvoiceCreateProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    @Override
    public Page<InvoiceEntity> findByProductName(String productName, Pageable pageable) {

        if (!StringUtils.isEmpty(productName)) {
            return repository.findByProductName(productName, pageable);
        }

        return repository.findAll(pageable);
    }
    
    @Override
    public Page<InvoiceEntity> findByCNPJ(String cnpj, Pageable pageable) {

        if (!StringUtils.isEmpty(cnpj)) {
            return repository.findByProductCNPJ(cnpj, pageable);
        }

        return repository.findAll(pageable);
    }

    @Override
    public InvoiceEntity save(InvoiceEntity invoice) {
        return repository.save(invoice);
    }

    /**
     * Envia a Nota Fiscal para a Fila de Processamento
     */
    @Override
    public void sendToQueue(InvoiceEntity invoice) {
        producer.send(invoice);
    }
}
