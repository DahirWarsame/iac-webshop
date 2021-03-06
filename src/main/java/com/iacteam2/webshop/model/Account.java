package com.iacteam2.webshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Created by dahir on Fri 16-03-18.
 */
@Entity
@Table(name = "accounts")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Date openDate;

    @ManyToOne(optional=false)
    @JoinColumn(name="address_fk",referencedColumnName="id", insertable = false, updatable = false)
    private Address invoiceAddress;

    @NotNull
    private boolean isActive;

    @ManyToOne(optional=true)
    @JoinColumn(name="customer_fk",referencedColumnName="id", insertable = false, updatable = false)
    private Customer customer;

    @OneToMany(mappedBy="account",targetEntity=Order.class, fetch=FetchType.EAGER)
    private Collection orders;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}