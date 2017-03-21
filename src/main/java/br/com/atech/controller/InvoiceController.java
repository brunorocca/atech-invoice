package br.com.atech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.atech.entity.ResponseVO;
import br.com.atech.entity.InvoiceEntity;
import br.com.atech.service.InvoiceService;

/**
 * Controller de requisições de Notas Fiscais
 * 
 * @author brunorocca
 *
 */
@RestController
@RequestMapping("/atech/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    /**
     * Método responsável por consultar os Produtos e Notas Fiscais.
     * 
     * @param productName Se esse parametro for passado, será utilizado como filtro
     * @param pageable
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Page<InvoiceEntity> index(@RequestParam(value = "productName", required = false) String productName, Pageable pageable) {

        return invoiceService.findByProductName(productName, pageable);
    }
    
    /**
     * Método utilizado para filtrar as Notas Fiscais por CNPJ do Emissor
     * 
     * @param cnpj
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/cnpj", method = RequestMethod.GET)
    public Page<InvoiceEntity> cnpj(@RequestParam(value = "cnpj", required = false) String cnpj, Pageable pageable) {

        return invoiceService.findByCNPJ(cnpj, pageable);
    }

    /**
     * Essa chamada é responsável por criar uma Nota Fiscal. Esse método não é chamado dentro da aplicação,
     * desse modo parar criar uma Nota é necessário fazer um POST manualmente. Na documentação no GIT encontra-se
     * o JSON de exemplo
     * 
     * @param invoice
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseVO create(@RequestBody final InvoiceEntity invoice) {
        invoiceService.sendToQueue(invoice);

        return new ResponseVO(HttpStatus.OK);
    }
}
