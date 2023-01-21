package com.lazybeast.springSecurity.controller;

import com.lazybeast.springSecurity.dto.AuthRequest;
import com.lazybeast.springSecurity.dto.Product;
import com.lazybeast.springSecurity.service.JwtService;
import com.lazybeast.springSecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SampleController {
    @Autowired
    ProductService productService;

    @Autowired
    JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to unsecured endpoint";
    }
    @GetMapping("/all")
    @PreAuthorize("hasAuthority(@Roles.ROLE_ADMIN)")
    public List<Product> allProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(@Roles.ROLE_USER)")
    public Product getProduct(@PathVariable("id") int id){
        return productService.getProduct(id);
    }

    @PostMapping("/generateJwtToken")
    public String generateJwtToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//        generate token only if user is authenticated
        if (authentication.isAuthenticated())
            return jwtService.generateToken(authRequest.getUsername());
        throw new UsernameNotFoundException("Invalid Username");
    }
}
