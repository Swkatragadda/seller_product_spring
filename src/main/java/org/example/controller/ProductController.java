package org.example.controller;

import org.example.exception.ProductNotFoundException;
import org.example.model.Product;
import org.example.service.ProductService;
import org.example.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class ProductController {
    ProductService productService;
    SellerService sellerService;
    private long id;
    private Product updatedProduct;

    public ProductController(ProductService productService,SellerService sellerService) {
        this.productService = productService;
              this.sellerService= sellerService;
    }

    @GetMapping(value = "/product")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products;
        products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<Object> addProduct(@RequestBody Product p) {
        try {
            if (!sellerService.doesSellerExist(p.getSellerName())) {
                return new ResponseEntity<>("Seller does not exist", HttpStatus.NOT_FOUND);
            }
            Product addedProduct = productService.addProduct(p);
            return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("seller/{sellerId}/product")
    public ResponseEntity<Product> Product(@RequestBody Product p, @PathVariable long id) throws Exception {
        Product product = productService.saveProduct(id, p);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id){
        try{
            Product p = productService.getProductById(id);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }catch (ProductNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
   @PutMapping ("/product/{id}")
    public ResponseEntity<Product>updateProduct(@PathVariable long id,@RequestBody Product updatedProduct)  {
       this.id = id;
       this.updatedProduct = updatedProduct;
       try{
            Product product =productService.updateProduct(id,updatedProduct);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }catch (ProductNotFoundException e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        }
   }
   @DeleteMapping ("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id){
        try{
            productService.deleteProduct(id);
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        } catch
        (ProductNotFoundException e){
            return new ResponseEntity<>("Product Not found", HttpStatus.NOT_FOUND);
        }
   }

}