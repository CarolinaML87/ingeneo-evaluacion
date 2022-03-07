/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingeneo.api.evaluacion.repository;

import com.ingeneo.api.evaluacion.model.CtDestinations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CtDestinationsRespository extends JpaRepository<CtDestinations, Long>{
    
}
