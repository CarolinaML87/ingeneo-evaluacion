/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingeneo.api.evaluacion.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CT_USER_ROL", schema = "public")
public class CtUserRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USER", referencedColumnName = "id")
    private CtUsers ctUser;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ROL", referencedColumnName = "id")
    private CtRoles ctRol;

    public CtUserRol(CtUsers user,CtRoles rol){
        this.ctUser = user;
        this.ctRol = rol;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CtUsers getCtUser() {
        return ctUser;
    }

    public void setCtUser(CtUsers ctUser) {
        this.ctUser = ctUser;
    }

    public CtRoles getCtRol() {
        return ctRol;
    }

    public void setCtRol(CtRoles ctRol) {
        this.ctRol = ctRol;
    }
    
    
}
