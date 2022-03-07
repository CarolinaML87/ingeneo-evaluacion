/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingeneo.api.evaluacion.controller;

import com.ingeneo.api.evaluacion.exception.ResourceNotFoundException;
import com.ingeneo.api.evaluacion.model.CtDestinations;
import com.ingeneo.api.evaluacion.repository.CtDestinationsRespository;
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
@RequestMapping("/destinations")
public class CtDestinationsController {
    @Autowired
    CtDestinationsRespository ctDestinationsRespository;
    
    @PostMapping("/")
    public ResponseEntity<?> createService(@Valid @RequestBody CtDestinations ctDestinations){
        CtDestinations destination = new CtDestinations();
        ctDestinations.setStatus(Boolean.TRUE);
        if(ctDestinations.getType().equals("1") || ctDestinations.getType().equals("2"))
           destination = ctDestinationsRespository.save(ctDestinations);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(destination, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CtDestinations> findById(@PathVariable("id") Long id){
        CtDestinations ctDestination = ctDestinationsRespository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra puerto o bodega con identificador = " + id));
        return new ResponseEntity<>(ctDestination, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        ctDestinationsRespository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<CtDestinations>> getAll() {
        List<CtDestinations> ctDestinations = new ArrayList<>();
            ctDestinationsRespository.findAll().forEach(ctDestinations::add);
        if (ctDestinations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ctDestinations, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CtDestinations> update(@PathVariable("id") Long id, @RequestBody CtDestinations request) {
        CtDestinations ctDestination  = ctDestinationsRespository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el registro de puerto o bodega con id = " + id));
        ctDestination.setName(request.getName());
        ctDestination.setStatus(request.getStatus());
        ctDestination.setType(request.getType());
        ctDestination.setAddress(request.getAddress());
        ctDestination.setDescription(request.getDescription());
        return new ResponseEntity<>(ctDestinationsRespository.save(ctDestination), HttpStatus.OK);
    }
}
