package com.objectway.stage.controller;

import com.objectway.stage.example.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
//@SessionAttributes("customers")
public class CustomersController extends AbstractCustomersController {


	@RequestMapping("/customers")
    public String showCustomers(Model model) {

//        List<Customer> customers = importer.getCustomers();
//        model.addAttribute("customers", customers);

        return "customersTiles";
    }

    @ModelAttribute("customers")
    public List<Customer> customers() {
	    return super.getCustomers();
    }


}
