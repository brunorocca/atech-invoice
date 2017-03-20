package com.atech.webapp.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "invoice")
@Audited
public class InvoiceEntity implements IDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_invoice")
	private Integer idInvoice;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_insert")
	private Calendar dateInsert; // data do Insert no banco de dados

	@Column(name = "num_invoice")
	private String numInvoice;

	@Column(name = "num_cnpj")
	private String numCnpj; // número do CNPJ do emissor

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_create")
	private Calendar dateCreate; // data da emissão da nota fiscal

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<InvoiceProductEntity> invoiceProducts;
	
	public Integer getIdInvoice() {
		return idInvoice;
	}

	public void setIdInvoice(Integer idInvoice) {
		this.idInvoice = idInvoice;
	}

	public Calendar getDateInsert() {
		return dateInsert;
	}

	public void setDateInsert(Calendar dateInsert) {
		this.dateInsert = dateInsert;
	}

	public String getNumInvoice() {
		return numInvoice;
	}

	public void setNumInvoice(String numInvoice) {
		this.numInvoice = numInvoice;
	}

	public String getNumCnpj() {
		return numCnpj;
	}

	public void setNumCnpj(String numCnpj) {
		this.numCnpj = numCnpj;
	}

	public Calendar getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Calendar dateCreate) {
		this.dateCreate = dateCreate;
	}

	public List<InvoiceProductEntity> getInvoiceProducts() {
		return invoiceProducts;
	}

	public void setInvoiceProducts(List<InvoiceProductEntity> invoiceProducts) {
		this.invoiceProducts = invoiceProducts;
	}

}
