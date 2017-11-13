package com.objectway.stage.controller;

import com.objectway.stage.controller.validators.CustomerCommandValidator;
import com.objectway.stage.example.Customer;
import com.objectway.stage.example.CustomersImporter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller //Spring -> inizializza come bean all'avvio
public class AddCustomerController extends AbstractCustomersController implements MessageSourceAware, ApplicationContextAware {

    private MessageSourceAccessor messageSourceAccessor;
    private CustomerCommandValidator customerCommandValidator;

    private ApplicationContext ac;

    @Value("${default.locale}")
    private String defaultLocale;

    @Autowired
    public AddCustomerController(CustomerCommandValidator customerCommandValidator, CustomersImporter customersImporter) {
        this.customerCommandValidator = customerCommandValidator;
    }

    @Scope("session")
    @ModelAttribute("customerCommand")
    public CustomerCommand initCommand(){

        CustomerCommand customerCommand = new CustomerCommand();
        customerCommand.setCustomer(new Customer());

        customerCommand.getCustomer().setBirthDate(new Date());

        customerCommand.setDefaultLocale(this.defaultLocale);

        return customerCommand;

    }


//    @Override
//    protected Object formBackingObject(HttpServletRequest request) throws Exception {
//
//        CustomerCommand customerCommand = new CustomerCommand();
//        customerCommand.setCustomer(new Customer());
//
//        customerCommand.getCustomer().setBirthDate(new Date());
//
//        return customerCommand;
//    }


    @RequestMapping(value="/addcustomer", method= RequestMethod.GET)
    public String viewCommand() {
        return "customerFormTiles";
    }


    @RequestMapping(value="/addcustomer", method= RequestMethod.POST)
    public String postCommand
            (HttpSession httpSession,
             Model model,
             @ModelAttribute("customerCommand") CustomerCommand customerCommand,
             BindingResult bindingResult,
             SessionStatus sessionStatus) {

        customerCommandValidator.validate(customerCommand, bindingResult);

        if(bindingResult.hasErrors())
            return "customerFormTiles";

        List<Customer> customers = super.getCustomers();

        customers.add(customerCommand.getCustomer());

        model.addAttribute("customers", customers);

        sessionStatus.setComplete();

        return "customersTiles";
    }

//    @Override
//    protected ModelAndView onSubmit
//            (HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
//            throws Exception {
//
//        CustomerCommand customerCommand = (CustomerCommand) command;
//
//        customersImporter.getCustomers().add(((CustomerCommand) command).getCustomer());
//
//        return new ModelAndView(getSuccessView(), "customers", customersImporter.getCustomers());
//
//    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                messageSourceAccessor.getMessage("customers.details.birthdate.pattern"));

        dateFormat.setLenient(false);

        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

    }


//    @Override
//    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat(
//                getMessageSourceAccessor().getMessage("customers.details.birthdate.pattern"));
//
//        dateFormat.setLenient(false);
//
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//
//        super.initBinder(request, binder);
//    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        messageSourceAccessor = new MessageSourceAccessor(messageSource);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }
}
