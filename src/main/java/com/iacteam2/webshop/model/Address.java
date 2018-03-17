package com.iacteam2.webshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

import javax.persistence.*;
/*
 * Embeddable class. It's instances are stored as an intrinsic part of an
 * owning entity and share the identity of the entity.
 */
@Entity
@Table(name = "address")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Address implements Serializable{


    private String id ;
    private String street;

    @Column(name = "APPT",nullable = false)
    private String appt;

    // By default column name is same as attribute name
    private String city;

    @Column(name = "ZIP_CODE",nullable = false)
    // Name of the corresponding database column
    private String zipCode;

    @OneToOne(optional=false)
    @JoinColumn(name = "id")
    private Customer customer;

    // getters and setters
    public String getAppt() {
        return appt;
    }

    public void setAppt(String appt) {
        this.appt = appt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }


}
