package com.springdata.mvcexample.SpringDataMvc.controller;

import com.springdata.mvcexample.SpringDataMvc.domain.Product;
import com.springdata.mvcexample.SpringDataMvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/start-up-page")
    public String showFrontPage(Product product){
        return "add-product";
    }

    @PostMapping(value = "/addproduct")
    public String addProduct(@Valid Product product, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-product";
        }

        productRepository.save(product);
        model.addAttribute("products", productRepository.findAll());
        return "product-index";
    }

    @GetMapping(value = "/editProduct/{id}")
    public String showEditPage(@PathVariable Long id, Model model){
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No product found: " + id));
        model.addAttribute("product", product);
        return "update-product";
    }

    @PostMapping(value = "/updateProduct/{id}")
    public String update(@PathVariable Long id, Model model, @Valid Product product, BindingResult result){
        if(result.hasErrors()){
            product.setId(id);
            return "update-product";
        }

        productRepository.save(product);
        model.addAttribute("products",productRepository.findAll());
        return "product-index";
    }

    @GetMapping(value = "/deleteProduct/{id}")
    public String delete(@PathVariable Long id, Model model){
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("There is no product found with id: " + id));
        productRepository.delete(product);
        model.addAttribute("products", productRepository.findAll());
        return "product-index";
    }
}
