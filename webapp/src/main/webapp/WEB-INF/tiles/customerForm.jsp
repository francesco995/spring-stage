<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<div class="row">
    <form:form commandName="customerCommand">
        
        <form:errors path="*" element="div"/>


        <form>


          <div class="form-group">
              <form:input path="defaultLocale" cssClass="form-control" id="locale"></form:input>
          </div>


          <div class="form-group">
            <label for="name"><spring:message code="customers.details.name"/></label>
              <form:input path="customer.name" cssClass="form-control" id="name" placeholder="${ustomers.details.name.placeholder}"></form:input><form:errors path="customer.name" element="span"/>
          </div>
                   <div class="form-group">
            <label for="name"><spring:message code="customers.details.lastName"/></label>
              <form:input path="customer.lastName" cssClass="form-control" id="lastName" placeholder="${ustomers.details.lastName.placeholder}"></form:input><form:errors path="customer.lastName" element="span"/>
          </div>
            
            <div class="form-group">
            <label for="name"><spring:message code="customers.details.birthdate"/></label>
              <form:input path="customer.birthDate" cssClass="form-control" id="birthDate"></form:input><form:errors path="customer.birthDate" element="span"/>
          </div>
            
          <button type="submit" class="btn btn-primary">Submit</button>
        </form>

    </form:form>
    
    
    
    <script type="text/javascript">
    $(".form_datetime").datetimepicker({
        format: "yyyy-MM-dd",
        autoclose: true,
        todayBtn: true,
        pickerPosition: "bottom-left"
    });
</script>            

</div>