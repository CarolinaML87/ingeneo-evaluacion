/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingeneo.api.evaluacion.controller;

import com.ingeneo.api.evaluacion.model.CtUsers;
import com.ingeneo.api.evaluacion.repository.CtUsersRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/crud/ctusers")
public class CtUsersController {
    
    @Autowired
    CtUsersRepository ctUsersRespository;
    
    @PostMapping("/")
    public ResponseEntity<CtUsers> createCustomer(@RequestBody CtUsers ctUser){
        Date lt = new Date();
        ctUser.setId(new Integer("2"));
        ctUser.setFhCreated(lt);
        CtUsers c = ctUsersRespository.save(ctUser);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }
        
        @GetMapping("/all")
    public ResponseEntity<List<CtUsers>> getAll(@RequestParam(required = false) String name) {
        List<CtUsers> ser = new ArrayList<CtUsers>();
        if (name == null)
            ctUsersRespository.findAll().forEach(ser::add);
        else
            //ctUsersRespository.findByNameContaining(name).forEach(ser::add);

        if (ser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ser, HttpStatus.OK);
    }
}
