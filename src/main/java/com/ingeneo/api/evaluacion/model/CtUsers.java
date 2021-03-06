package com.ingeneo.api.evaluacion.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "ct_users", schema = "public")
public class CtUsers{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "user_name")
    private String userName;
    
    @Column(columnDefinition = "fh_created")
    private Date fhCreated;
    
    @Column(columnDefinition = "password")
    private String password;
    
    
    public CtUsers(String username,String password,Date fhCrea){
        this.userName = username;
        this.password = password;
        this.fhCreated = fhCrea;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String user) {
        this.userName = user;
    }

    public Date getFhCreated() {
        return fhCreated;
    }

    public void setFhCreated(Date fhCreated) {
        this.fhCreated = fhCreated;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
