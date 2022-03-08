/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingeneo.api.evaluacion.controller;

import com.ingeneo.api.evaluacion.config.JwtTokenUtil;
import com.ingeneo.api.evaluacion.model.CtRoles;
import com.ingeneo.api.evaluacion.model.CtUserRol;
import com.ingeneo.api.evaluacion.model.CtUsers;
import com.ingeneo.api.evaluacion.repository.CtRolesRepository;
import com.ingeneo.api.evaluacion.repository.CtUserRoleRepository;
import com.ingeneo.api.evaluacion.repository.CtUsersRepository;
import com.ingeneo.api.evaluacion.request.JwtRequest;
import com.ingeneo.api.evaluacion.request.SingUpRequest;
import com.ingeneo.api.evaluacion.response.JwtResponse;
import com.ingeneo.api.evaluacion.response.MessageResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
        
        @Autowired
        CtUsersRepository ctUsersRepository;
        
        @Autowired
        PasswordEncoder encoder;
        
        @Autowired
        CtRolesRepository ctRolesRepository;
        
        @Autowired
        CtUserRoleRepository ctUserRoleRepository;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
        
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SingUpRequest signUpRequest) {
        if (ctUsersRepository.existsByUserName(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: nombre de usuario ya utilizado!"));
        }
        // Create new user's account
        CtUsers user = new CtUsers(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),new Date());
        ctUsersRepository.save(user);
        List<String> strRoles = signUpRequest.getRole();
        List<CtUserRol> roles = new ArrayList<>();
        if (strRoles == null) {
            CtRoles rol = ctRolesRepository.findByName("USER");
            CtUserRol userRol = new CtUserRol(user, rol);
            roles.add(userRol);
        } else {
            strRoles.forEach(role -> {
                CtRoles rol = ctRolesRepository.findByName(role);
                CtUserRol userRol = new CtUserRol(user, rol);
                roles.add(userRol);
            });
        }
        ctUserRoleRepository.saveAll(roles);
        return ResponseEntity.ok(new MessageResponse("usuario creado exitosamente!"));
    }
}

