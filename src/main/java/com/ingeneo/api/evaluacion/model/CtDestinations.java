/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingeneo.api.evaluacion.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "ct_destinations", schema = "public")
public class CtDestinations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    
    @NotBlank(message = "Nombre de bodega es requerido")
    @Column(name = "name")
    private String name;
    
    @NotBlank(message = "Descripcion de bodega es requerido")
    @Column(name = "description")
    private String description;
    
    @NotBlank(message = "Tipo de bodega es requerido 1:Terrestre 2:Maritima")
    @Column(name = "type")
    private String type;
    
    @Column(name = "status")
    private Boolean status;
    
    @NotBlank(message = "Direccion de bodega es requerida")
    @Column(name = "address")
    private String address;
    
    public CtDestinations(){    }
    public CtDestinations(String name, String description,String type, Boolean status,String address) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.status = status;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
