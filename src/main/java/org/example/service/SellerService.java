package org.example.service;

import org.example.Main;
import org.example.model.Seller;
import org.example.repository.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SellerService {
     SellerRepo sellerRepo;

    @Autowired
    public SellerService(SellerRepo sellerRepo) {
        this.sellerRepo = sellerRepo;
    }

    public List<Seller> getAllSellers() {
    Main.log.info("Getting list of all sellers" + sellerRepo);
        return sellerRepo.findAll();
    }

    /*public void addSeller(Seller s){sellerSet.add(s);}*/

    public Seller saveSeller(Seller s) {
        return sellerRepo.save(s);
    }

    //Method to check if a seller with given name exists in the sellerlist
    public boolean doesSellerExist(String sellerName) {
        Main.log.info("checking if sellername exists in list" + sellerName);
        return sellerRepo.existsBySellerName(sellerName);

    }
}
