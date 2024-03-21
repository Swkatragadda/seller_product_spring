package org.example.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long productId;
    public String productName;
    public double price;
    public String sellerName;

    @JsonIgnore
    @ManyToOne
    @JsonIgnoreProperties("products")
    public Seller seller;

}
