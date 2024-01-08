<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.dentisterie.model.nify.Dents" %>
<%@ page import="com.example.dentisterie.model.nify.Patient" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.example.dentisterie.model.lalana.Routes" %>
<jsp:include page="../template/header.jsp" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Routes> routes=new ArrayList<>();
    if(request.getAttribute("routes")!=null){
        routes= (List<Routes>) request.getAttribute("routes");
    }


%>


<div class="col-md-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">faire une consultation </h4>
            <form  class="forms-sample" action="${pageContext.request.contextPath}/submitConstruction" method="post" >


                <%--    Routes --%>
                <div class="form-group">
                    <label for="patient">patients </label>
                    <select name="idRoutes" class="form-control" id="patient">
                        <%for(int i = 0; i < routes.size(); i++) {%>
                        <option value="<%=routes.get(i).getId()%>"><%=routes.get(i).getNonRoutes()%></option>
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


                <button type="submit" class="btn btn-primary me-2">Valider</button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../template/footer.jsp" />

