package br.com.atech.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.atech.entity.InvoiceEntity;

public interface InvoiceService {

    Page<InvoiceEntity> findByProductName(String productName, Pageable pageable);

    InvoiceEntity save(InvoiceEntity invoice);

    void sendToQueue(InvoiceEntity invoice);

	Page<InvoiceEntity> findByCNPJ(String cnpj, Pageable pageable);
}
