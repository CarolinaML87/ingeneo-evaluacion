/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingeneo.api.evaluacion.controller;

import com.ingeneo.api.evaluacion.exception.ResourceNotFoundException;
import com.ingeneo.api.evaluacion.model.CtCustomers;
import com.ingeneo.api.evaluacion.repository.CtCustomersRepository;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CtCustomersController {
    
    @Autowired
    CtCustomersRepository ctCustomersRepository;
    
    @PostMapping("/")
    public ResponseEntity<?> createService(@Valid @RequestBody CtCustomers ctCustomers){
        CtCustomers customer = ctCustomersRepository.save(ctCustomers);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CtCustomers> findById(@PathVariable("id") Long id){
        CtCustomers ctCustomer = ctCustomersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el producto con identificador = " + id));
        return new ResponseEntity<>(ctCustomer, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        ctCustomersRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<CtCustomers>> getAll() {
        List<CtCustomers> ctCustomer = new ArrayList<>();
            ctCustomersRepository.findAll().forEach(ctCustomer::add);
        if (ctCustomer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ctCustomer, HttpStatus.OK);
    }
}
