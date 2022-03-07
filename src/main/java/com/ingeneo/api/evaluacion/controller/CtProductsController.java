/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingeneo.api.evaluacion.controller;

import com.ingeneo.api.evaluacion.exception.ResourceNotFoundException;
import com.ingeneo.api.evaluacion.model.CtProducts;
import com.ingeneo.api.evaluacion.repository.CtProductsRepository;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class CtProductsController {
    
    @Autowired
    CtProductsRepository ctProductsRepository;
    
    @PostMapping("/")
    public ResponseEntity<?> createService(@Valid @RequestBody CtProducts ctProduct){
        CtProducts prod = ctProductsRepository.save(ctProduct);
        return new ResponseEntity<>(prod, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CtProducts> findById(@PathVariable("id") Long id){
        CtProducts products = ctProductsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra el producto con identificador = " + id));
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        ctProductsRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<CtProducts>> getAll() {
        List<CtProducts> ctproducts = new ArrayList<>();
            ctProductsRepository.findAll().forEach(ctproducts::add);
        if (ctproducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ctproducts, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CtProducts> update(@PathVariable("id") Long id, @RequestBody CtProducts request) {
        CtProducts ctProducts  = ctProductsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro de producto con id = " + id));
        ctProducts.setName(request.getName());
        ctProducts.setDescription(request.getDescription());
        ctProducts.setPrice(request.getPrice());
        return new ResponseEntity<>(ctProductsRepository.save(ctProducts), HttpStatus.OK);
    }
}
