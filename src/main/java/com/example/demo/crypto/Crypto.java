package com.example.demo.crypto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@JsonIgnoreProperties(ignoreUnknown = true)
public class Crypto {
    @Id
    private String id;
    private String name;
    private float current_price;

    public Crypto(String id, String name, float current_price) {
        this.id = id;
        this.name = name;
        this.current_price = current_price;
    }

    public Crypto() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getcurrent_price() {
        return current_price;
    }

    public void setcurrent_price(float current_price) {
        this.current_price = current_price;
    }
}
