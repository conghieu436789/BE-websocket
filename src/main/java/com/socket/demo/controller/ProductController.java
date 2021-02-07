package com.socket.demo.controller;

import com.socket.demo.model.Product;
import com.socket.demo.model.RespProduct;
import com.socket.demo.model.User;
import com.socket.demo.service.IProductService;
import com.socket.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<Product> getProduct() {
        return (List<Product>) productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            return productService.findById(id).get();
        }
        return null;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') ")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody RespProduct respProduct) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName()).get();
        Product product = new Product();
        product.setTitle(respProduct.getTitle());
        product.setDescription(respProduct.getDescription());
        product.setUser(user);
        try {
            productService.save(product);
            return new ResponseEntity<>(product,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long id) {
        productService.remove(id);
        return new ResponseEntity<>("Product has been removed",HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody RespProduct respProduct) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            product.get().setDescription(respProduct.getDescription());
            product.get().setTitle(respProduct.getTitle());
            productService.save(product.get());
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
