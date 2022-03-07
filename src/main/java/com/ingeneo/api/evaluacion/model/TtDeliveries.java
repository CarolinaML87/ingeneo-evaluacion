/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingeneo.api.evaluacion.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tt_deliveries", schema = "logistica")
public class TtDeliveries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    private CtCustomers idCustomer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_destination", referencedColumnName = "id")
    private CtDestinations ctDestination;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private CtProducts idProduct;
    
    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "disscount")
    private BigDecimal disscount;

    @Column(name = "fh_created")
    private Date fhCreated;
    
    @Column(name = "fh_delivered")
    private Date fhDelivered;
    
    @Column(name = "transport_number")
    private String transportNumber;
    
    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "status")
    private Boolean status;

    public CtCustomers getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(CtCustomers idCustomer) {
        this.idCustomer = idCustomer;
    }

    public CtDestinations getCtDestination() {
        return ctDestination;
    }

    public void setCtDestination(CtDestinations ctDestination) {
        this.ctDestination = ctDestination;
    }

    public CtProducts getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(CtProducts idProduct) {
        this.idProduct = idProduct;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDisscount() {
        return disscount;
    }

    public void setDisscount(BigDecimal disscount) {
        this.disscount = disscount;
    }

    public Date getFhCreated() {
        return fhCreated;
    }

    public void setFhCreated(Date fhCreated) {
        this.fhCreated = fhCreated;
    }

    public Date getFhDelivered() {
        return fhDelivered;
    }

    public void setFhDelivered(Date fhDelivered) {
        this.fhDelivered = fhDelivered;
    }

    public String getTransportNumber() {
        return transportNumber;
    }

    public void setTransportNumber(String transportNumber) {
        this.transportNumber = transportNumber;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    
}
