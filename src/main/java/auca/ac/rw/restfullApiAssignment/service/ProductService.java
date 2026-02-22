package auca.ac.rw.restfullApiAssignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.rw.restfullApiAssignment.modal.Product;
import auca.ac.rw.restfullApiAssignment.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository proRepository;

    public String saveProduct(Product product){
       proRepository.save(product);
       return "Product saved successfully";
    }

    public List<Product> getAllProducts(){
        return proRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return proRepository.findById(id);
    }

    public String updateProduct(Long id, Product product){
        if(!proRepository.existsById(id)){
            return "Product not found";
        }
        product.setId(id);
        proRepository.save(product);
        return "Product updated successfully";
    }

    public String deleteProduct(Long id){
        if(!proRepository.existsById(id)){
            return "Product not found";
        }
        proRepository.deleteById(id);
        return "Product deleted successfully";
    }
}
