package com.atech.webapp.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "invoice_product")
@Audited
public class InvoiceProductEntity implements IDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "invoice_product")
	private Integer idInvoiceProduct;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_insert")
	private Calendar dateInsert;

	@JsonIgnore
	@ManyToOne(targetEntity = InvoiceEntity.class)
	private InvoiceEntity invoice;

	@Column(name = "name_product")
	private String nameProduct;

	@Column(name = "qtd_product")
	private Double qtdProduct;

	@Column(name = "type_qtd_product")
	private String typeQtdProduct; // Unidade de Medida (Kg, Litros, Unidade,
									// etc)

	public Integer getIdInvoiceProduct() {
		return idInvoiceProduct;
	}

	public void setIdInvoiceProduct(Integer idInvoiceProduct) {
		this.idInvoiceProduct = idInvoiceProduct;
	}

	public Calendar getDateInsert() {
		return dateInsert;
	}

	public void setDateInsert(Calendar dateInsert) {
		this.dateInsert = dateInsert;
	}

	public InvoiceEntity getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceEntity invoice) {
		this.invoice = invoice;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public Double getQtdProduct() {
		return qtdProduct;
	}

	public void setQtdProduct(Double qtdProduct) {
		this.qtdProduct = qtdProduct;
	}

	public String getTypeQtdProduct() {
		return typeQtdProduct;
	}

	public void setTypeQtdProduct(String typeQtdProduct) {
		this.typeQtdProduct = typeQtdProduct;
	}

}
