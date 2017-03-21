package br.com.atech.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.atech.entity.InvoiceEntity;

/**
 * Representa o DAO (ou Repository) para consulta dos dados de Notas Fiscais.
 * Mesmo o Front não contendo paginação, foi optador por utilizar o
 * PagingAndSortingRepository por padrão e facilidade de paginação futura.
 * 
 * @author brunorocca
 *
 */
public interface InvoiceDAO extends PagingAndSortingRepository<InvoiceEntity, Long> {

	@Query("select i from InvoiceEntity i where i.id in "
			+ "(select i.id from InvoiceEntity i join i.products p where p.productName = :productName)")
	Page<InvoiceEntity> findByProductName(@Param("productName") String productName, Pageable pageable);

	@Query("select i from InvoiceEntity i where i.cnpj = :cnpj")
	Page<InvoiceEntity> findByProductCNPJ(@Param("cnpj") String cnpj, Pageable pageable);
}
