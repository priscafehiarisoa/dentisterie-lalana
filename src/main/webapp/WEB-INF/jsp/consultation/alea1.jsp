<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.dentisterie.model.nify.Dents" %>
<%@ page import="com.example.dentisterie.model.nify.Patient" %>
<%@ page import="java.util.Enumeration" %>
<jsp:include page="../template/header.jsp" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Patient> listPatient= new ArrayList<>();

    if(request.getAttribute("patient")!=null){
    listPatient= (List<Patient>) request.getAttribute("patient");
}


%>


<div class="col-md-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">faire une consultation </h4>
            <form  class="forms-sample" action="${pageContext.request.contextPath}/formAlea" method="post" >


                <%--    patient --%>
                <div class="form-group">
                    <label for="patient">patients </label>
                    <select name="patient" class="form-control" id="patient">
                        <%for(int i = 0; i < listPatient.size(); i++) {%>
                        <option value="<%=listPatient.get(i).getId()%>"><%=listPatient.get(i).getNomPatient()%></option>
                        <%}%>
                    </select>
                </div>
                <%--    priorité --%>
                <div class="form-group">
                    <label for="patient2">priorité </label>
                    <select name="priorite" class="form-control" id="patient2">
                        <option value="1">beauté</option>
                        <option value="2">santé</option>
                    </select>
                </div>

                <%--    budjet --%>
                <div class="form-group">
                    <label for="patient3">budget </label>
                    <input type="number" class="form-control" id="patient3" name="budget">
                </div>

                    <%--    dents --%>
                <div class="form-group">
                    <label for="patient3">dents </label>
                    <input type="text" class="form-control" id="patien3" name="dent" placeholder="d1-d3 ou d1,d2,d3">
                </div>

                    <%--    valeur --%>
                <div class="form-group">
                    <label for="patient3">dents </label>
                    <input type="text" class="form-control" id="patwien3" name="etat" placeholder="10 ou 10,4,5">
                </div>



                <button type="submit" class="btn btn-primary me-2">Valider</button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../template/footer.jsp" />

