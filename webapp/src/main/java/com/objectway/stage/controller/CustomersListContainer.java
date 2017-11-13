package com.objectway.stage.controller;

import com.objectway.stage.example.Customer;
import com.objectway.stage.example.CustomersImporter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Component("customersListContainer") //ASSEGNA L'ID a questo bean
@Scope("session")
public class CustomersListContainer implements Serializable, InitializingBean{

    @Autowired()
    @Qualifier("importer") //Cerca l'ID del bin nelle configurazioni
    private CustomersImporter customersImporter;

    private List<Customer> customers;


    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override //DOPO il set di customersImporter
    public void afterPropertiesSet() throws Exception {
        customers = new ArrayList<>(this.customersImporter.getCustomers());
    }
}
