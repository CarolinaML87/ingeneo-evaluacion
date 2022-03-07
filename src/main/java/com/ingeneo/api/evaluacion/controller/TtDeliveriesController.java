/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingeneo.api.evaluacion.controller;

import com.ingeneo.api.evaluacion.exception.ResourceNotFoundException;
import com.ingeneo.api.evaluacion.model.CtCustomers;
import com.ingeneo.api.evaluacion.model.CtDestinations;
import com.ingeneo.api.evaluacion.model.CtProducts;
import com.ingeneo.api.evaluacion.model.TtDeliveries;
import com.ingeneo.api.evaluacion.repository.CtCustomersRepository;
import com.ingeneo.api.evaluacion.repository.CtDestinationsRespository;
import com.ingeneo.api.evaluacion.repository.CtProductsRepository;
import com.ingeneo.api.evaluacion.repository.TtDeliveriesRepository;
import com.ingeneo.api.evaluacion.request.DeliveriesRequest;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deliveries")
public class TtDeliveriesController {
    
    @Autowired 
    TtDeliveriesRepository ttDeliveriesRepository;
    
    @Autowired
    CtCustomersRepository ctCustomersRepository;
    
    @Autowired
    CtProductsRepository ctProductsRepository;
    
    @Autowired
    CtDestinationsRespository ctDestinationsRespository;
    
    @PostMapping("/")
    public ResponseEntity<TtDeliveries> createDeliverie(@Valid @RequestBody DeliveriesRequest request){
        TtDeliveries deliveries= new TtDeliveries();
        deliveries.setIdCustomer(new CtCustomers());
        deliveries.setIdProduct(new CtProducts());
        deliveries.setCtDestination(new CtDestinations());

        Pattern pattern1 ;
        Matcher matcher1 ;
        if(validData(request)){
            deliveries.setIdCustomer(ctCustomersRepository.findById(request.getIdCustomer()).
                    orElseThrow(() -> new ResourceNotFoundException("No se encuentra cliente con este identificador = " + request.getIdCustomer())));
            deliveries.setCtDestination(ctDestinationsRespository.findById(request.getCtDestination()).
                    orElseThrow(() -> new ResourceNotFoundException("No se servicio configurado = " + request.getCtDestination())));

            deliveries.setIdProduct(ctProductsRepository.findById(request.getIdProduct()).
                    orElseThrow(() -> new ResourceNotFoundException("No se encuentra este producto= " + request.getIdProduct())));

            if("1".equals(deliveries.getCtDestination().getType())){//LOGISTICA DE CAMIONES 3 LETRAS Y 3 NUMEROS
                pattern1 = Pattern.compile("^[A-Z]{3}[0-9]{3}$");
            }else{
                pattern1 =Pattern.compile("^[A-Z]{3}[0-9]{4}[A-Z]{1}$");
            }
            matcher1 = pattern1.matcher(request.getTransportNumber());
            if(!matcher1.find()){
                throw new ResourceNotFoundException("Numero de transporte no coincide con el formato 3 letras y 3 numeros");
            }
            deliveries.setTransportNumber(request.getTransportNumber());
            deliveries.setTrackingNumber(generateRandomTracking());
            deliveries.setFhDelivered(request.getFhDelivered());
            deliveries.setStatus(true);
            deliveries.setQuantity(request.getQuantity());
            if(deliveries.getQuantity()>=10 ){
                BigDecimal discount = BigDecimal.ZERO;
                if(deliveries.getCtDestination().getType().equals("1")){
                    deliveries.setDisscount(new BigDecimal("0.05"));
                }else{
                    deliveries.setDisscount(new BigDecimal("0.03"));
                }
                deliveries.setAmount(deliveries.getIdProduct().getPrice().multiply(new BigDecimal(deliveries.getQuantity())));
                deliveries.setDisscount(deliveries.getAmount().multiply(discount));
            }else{
                deliveries.setAmount(deliveries.getIdProduct().getPrice().multiply(new BigDecimal(deliveries.getQuantity())));
                deliveries.setDisscount(BigDecimal.ZERO);
            }
            deliveries.setFhCreated(new Date());
            ttDeliveriesRepository.save(deliveries);
            return new ResponseEntity<>(deliveries, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/deliveriesByCustomer/{id}")
    public ResponseEntity<List<TtDeliveries>> deliveriesByCustomer(@PathVariable("id") Long id) {
        CtCustomers ctCustomer = new CtCustomers();
        if (id <= 0 )
            throw  new ResourceNotFoundException("Debe ingresar un id de cliente valido  " + id);
        else
            ctCustomer = ctCustomersRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id = " + id));
        
        List<TtDeliveries> listDeliveries = new ArrayList<TtDeliveries>();
        listDeliveries = ttDeliveriesRepository.findByidCustomer(ctCustomer);
        if (listDeliveries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listDeliveries, HttpStatus.OK);
    }
    
    public String generateRandomTracking() {
        String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        String NUMBER = "0123456789";
        String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
        SecureRandom random = new SecureRandom();

        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            // 0-62 (exclusivo), retorno aleatorio 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }
    
    public Boolean validData(DeliveriesRequest deliveries){
        if (deliveries.getIdCustomer()<= 0 )
            throw  new ResourceNotFoundException("El codigo " + deliveries.getIdCustomer() + " de cliente no exite.");
        if (deliveries.getCtDestination()<= 0 )
            throw  new ResourceNotFoundException("El codigo " + deliveries.getIdCustomer() + " de bodega o puerto no exite.");
        if (deliveries.getIdProduct()<= 0 )
            throw  new ResourceNotFoundException("El codigo " + deliveries.getIdCustomer() + " de producto no exite.");
        if (deliveries.getQuantity()<= 0 )
            throw  new ResourceNotFoundException("La cantidad de producto debe ser mayor que cero.");
        if (deliveries.getTransportNumber() == null )
            throw  new ResourceNotFoundException("El numero de transporte no puede ser vacio");
        if (deliveries.getFhDelivered()== null )
            throw  new ResourceNotFoundException("La fecha de entrega no puede ser vacia");
        return true;
    }
}

