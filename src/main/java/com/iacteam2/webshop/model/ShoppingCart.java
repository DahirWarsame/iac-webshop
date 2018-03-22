package com.iacteam2.webshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * Created by dahir on Thu 22-03-18.
 */
@Entity
@Table(name = "shoppingcart")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class ShoppingCart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private int quantity;
    @ManyToMany(mappedBy="categories", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("categories")
    private List<Product> products;

}
