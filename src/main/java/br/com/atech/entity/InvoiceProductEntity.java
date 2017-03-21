package br.com.atech.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "invoice_products")
public class InvoiceProductEntity {

	@Id
	@GeneratedValue
	private Long id;

	@JsonIgnore
	@ManyToOne(targetEntity = InvoiceEntity.class)
	private InvoiceEntity invoice;

	/**
	 * Nome descritivo do Produto
	 */
	private String productName;

	/**
	 * Quantidade de Produto
	 */
	private Integer quantity;

	public Long getId() {
		return id;
	}

	public InvoiceProductEntity setId(Long id) {
		this.id = id;

		return this;
	}

	public InvoiceEntity getInvoice() {
		return invoice;
	}

	public InvoiceProductEntity setInvoice(InvoiceEntity invoice) {
		this.invoice = invoice;

		return this;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public InvoiceProductEntity setQuantity(Integer quantity) {
		this.quantity = quantity;

		return this;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
