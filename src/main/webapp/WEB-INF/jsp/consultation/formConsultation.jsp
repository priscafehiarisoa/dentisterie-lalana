<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.dentisterie.model.nify.Dents" %>
<%@ page import="com.example.dentisterie.model.nify.Patient" %>
<%@ page import="java.util.Enumeration" %>
<jsp:include page="../template/header.jsp" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Dents> listDents= new ArrayList<>();
    List<Patient> listPatient= new ArrayList<>();

    if(request.getAttribute("dents")!=null){
        listDents= (List<Dents>) request.getAttribute("dents");
     } if(request.getAttribute("patient")!=null){
        listPatient= (List<Patient>) request.getAttribute("patient");
    }


%>


<div class="col-md-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">faire une consultation </h4>
            <form  class="forms-sample" action="${pageContext.request.contextPath}/submitConsultation" method="post" >


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

    <%--   etat  --%>
    <div class="form-group ">
        <label >definir l'état des dents </label>
        <div class="m-5 row d-inline-flex mx-5">
            <hr class="m-5">
            <li>haut</li>
            <% for(int i = 0; i < listDents.size()/2; i++) { %>
            <div class="form-check col-3">
                <label class="" for="activite<%= i %>">dent n* : <%= listDents.get(i).getId() %></label>
                <select name="etatDents" id="activite<%= i %>" class="form-control" >
                    <%for (int j = 10; j >=0 ; j--) { %>
                    <option value="<%=listDents.get(i).getId()+","+j%>"><%=j%></option>
                      <%  }%>
                </select>
            </div>
            <% } %>
            <hr class="m-5">
            <li>bas</li>
            <% for(int i = listDents.size()/2; i < listDents.size(); i++) { %>
            <div class="form-check col-3">
                <label class="" for="activite<%= i %>">dent n* : <%= listDents.get(i).getId() %></label>
                <select name="etatDents" id="activite<%= i %>" class="form-control" >
                    <%for (int j = 10; j >=0 ; j--) { %>
                    <option value="<%=listDents.get(i).getId()+","+j%>"><%=j%></option>
                    <%  }%>
                </select>
            </div>
            <% } %>
        </div>
    </div>


    <button type="submit" class="btn btn-primary me-2">Valider</button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../template/footer.jsp" />

