package com.objectway.stage.controller;

import com.objectway.stage.example.Customer;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

public class CustomerCommand implements Serializable {

    @Value("${default.locale}")
    private String defaultLocale;

    private Customer customer;

    public String getDefaultLocale() {
        return defaultLocale;
    }

    public void setDefaultLocale(String defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
