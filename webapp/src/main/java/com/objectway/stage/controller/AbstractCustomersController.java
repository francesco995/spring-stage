package com.objectway.stage.controller;

import com.objectway.stage.example.Customer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;

public abstract class AbstractCustomersController implements ApplicationContextAware {

    private ApplicationContext ac;



    //@Autowired NON si può fare autowired perchè è scope session (customersListContainer)
    //@Qualifier("customersListContainer")

    //private CustomersListContainer customersListContainer;

    public List<Customer> getCustomers() {

        CustomersListContainer customersListContainer = (CustomersListContainer) ac.getBean("customersListContainer");

        return customersListContainer.getCustomers();
    }


//    @Autowired
//    @Qualifier("importer") //Cerca l'ID
//    private CustomersImporter customersImporter;
//
//    public List<Customer> getCustomers () {
//        return customersImporter.getCustomers();
//    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }

}
