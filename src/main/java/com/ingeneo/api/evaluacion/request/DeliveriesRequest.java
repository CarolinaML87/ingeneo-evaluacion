/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingeneo.api.evaluacion.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class DeliveriesRequest implements Serializable{

    private Long idCustomer;
    private Long ctDestination;
    private Long idProduct;
    private BigDecimal amount;
    private Integer quantity;
    private Date fhDelivered;
    private String transportNumber;

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getCtDestination() {
        return ctDestination;
    }

    public void setCtDestination(Long ctDestination) {
        this.ctDestination = ctDestination;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
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
    
    
}
