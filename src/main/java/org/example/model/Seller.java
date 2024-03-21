package org.example.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long sellerId;
    public String sellerName;
    @JsonIgnore
    @OneToMany
    @JoinColumn(name="seller_fk")
    public List<Product> products;


}
