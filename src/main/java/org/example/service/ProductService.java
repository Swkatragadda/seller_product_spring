package org.example.service;


import org.example.Main;
import org.example.exception.ProductNotFoundException;
import org.example.model.Product;
import org.example.model.Seller;
import org.example.repository.ProductRepo;
import org.example.repository.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;

@Service
public class ProductService {

    private ProductRepo productRepo;
    private SellerRepo sellerRepo;
    @Autowired

    public ProductService(ProductRepo productRepo, SellerRepo sellerRepo){
        this.productRepo= productRepo;
        this.sellerRepo=sellerRepo;
    }
    public List<Product> getAllProducts(){
        Main.log.info("Getting List of Products: " + productRepo);
        return productRepo.findAll();
    }
  public Product saveProduct(long productId,Product p) throws ProductNotFoundException {
        Optional<Seller> optional = sellerRepo.findById(productId);
        Seller s;
        if (optional.isEmpty()) {
            Main.log.info("No such seller found when using product Id: "+ productId);
            throw new ProductNotFoundException("No such seller");
        } else {
            s = optional.get();
        }
        Product savedProduct = productRepo.save(p);
        s.getProducts().add(savedProduct);
        sellerRepo.save(s);
        return savedProduct;
    }


    public Product addProduct(Product p) throws Exception {
        if (p.getProductName() == null || p.getSellerName() == null || p.getPrice()<0) {
            Main.log.info("throwing product exception if product name or seller name is null  and if price is less than 0:" + p);

            throw new Exception("Invalid product details");
        }
        if (sellerRepo.existsBySellerName(p.getSellerName())) {
            return productRepo.save(p);
        }
        Main.log.info("Seller with the name: " + p.getSellerName() + " does not exist.");
        throw new Exception("Seller does not exist");
    }




    public Product getProductById(long productId) throws ProductNotFoundException {
            Optional<Product>op = productRepo.findById(productId);
            if (op.isPresent()) {
                Main.log.info("Product found "+ op);
                return op.get();
            } else {
                Main.log.info("No product found using product Id: "+ productId);
                throw new ProductNotFoundException("no such product...");
            }
        }

        public Product updateProduct(long productId,Product updatedProduct) throws ProductNotFoundException{
        Optional<Product> op= productRepo.findById(productId);
        if(op.isPresent()) {
            Product product = op.get();
            product.setProductName(updatedProduct.getProductName());
            product.setPrice(updatedProduct.getPrice());
            return productRepo.save(product);
        }else{
            throw new ProductNotFoundException("No such product to update ..");
        }
        }
        public void deleteProduct(long productId) throws ProductNotFoundException{
        Optional<Product>op=productRepo.findById(productId);
        if(op.isPresent()) {
            productRepo.deleteById(productId);
                }else{
            throw new ProductNotFoundException("No such product to delete");
        }
        }
    }



