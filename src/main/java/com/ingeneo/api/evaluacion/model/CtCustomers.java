/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingeneo.api.evaluacion.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "ct_customers", schema = "public")
public class CtCustomers {  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotBlank(message = "Nombres son requeridos son requerido")
    @Column(name = "first_name")
    private String firstName;
    
    @NotBlank(message = "Apellidos son requeridos son requerido")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Numero de contacto es requerido")
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "status")
    private Boolean status;
    
    @Column(name = "fh_created")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fhCreated;
    
    @NotBlank(message = "Correo electronico es requerido")
    @Column(name = "email")
    private String email;
    
    @NotBlank(message = "Direccion es requerida")
    @Column(name = "address")
    private String address;

    public CtCustomers(){    }
    public CtCustomers(String firsName, String lastName, String phone, Boolean status, Date fhcreated, String email,String address) {
        this.firstName = firsName;
        this.lastName = lastName;
        this.phone = phone;
        this.status = status;
        this.fhCreated = fhcreated;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getFhCreated() {
        return fhCreated;
    }

    public void setFhCreated(Date fhCreated) {
        this.fhCreated = fhCreated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
