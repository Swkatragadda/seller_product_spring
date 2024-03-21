package org.example.repository;

import org.example.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SellerRepo extends JpaRepository<Seller, Long> {

    @Query("Select CASE when count(s) >0 then true else false END from Seller s where s.sellerName = :sellerName")
    boolean existsBySellerName(String sellerName);

}

