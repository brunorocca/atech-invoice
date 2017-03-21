package br.com.atech.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "invoices")
public class InvoiceEntity {

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Número do CNPJ do Emissor
	 */
	private String cnpj;

	/**
	 * Descrição e observações da Nota Fiscal
	 */
	private String description;

	/**
	 * Lista dos Produtos da Nota Fiscal
	 */
	@OneToMany(cascade = CascadeType.ALL)
	private List<InvoiceProductEntity> products = new ArrayList<InvoiceProductEntity>();
	
	public Long getId() {
		return id;
	}

	public InvoiceEntity setId(Long id) {
		this.id = id;

		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;

	}

	public InvoiceEntity addProduct(InvoiceProductEntity product) {
		this.products.add(product);

		return this;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<InvoiceProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<InvoiceProductEntity> products) {
		this.products = products;
	}

}
