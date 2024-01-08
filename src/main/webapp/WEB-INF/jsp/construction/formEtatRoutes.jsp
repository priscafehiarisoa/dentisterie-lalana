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
            <form  class="forms-sample" action="${pageContext.request.contextPath}/submitEtatRoutes" method="post" >


                <%--    Liste routes --%>
                <div class="form-group">
                    <label for="patient">Routes </label>
                    <select name="idRoutes" class="form-control" id="patient">
                        <%for(int i = 0; i < routes.size(); i++) {%>
                        <option value="<%=routes.get(i).getId()%>"><%=routes.get(i).getNonRoutes()%></option>
                        <%}%>
                    </select>
                </div>

                <%--    debut --%>
                <div class="form-group">
                    <label for="patient3">debut km </label>
                    <input type="number" class="form-control" id="patient3" name="debut">
                </div>
                    <%--    fin --%>
                <div class="form-group">
                    <label for="patient3">fin km </label>
                    <input type="number" class="form-control" id="patient2" name="fin">
                </div>
                    <%--    etat niveau --%>
                <div class="form-group">
                    <label for="patient3">etat</label>

                    <select name="etatNiveau" id="activite" class="form-control" >
                        <%for (int j = 10; j >=0 ; j--) { %>
                        <option value="<%=j%>"><%=j%></option>
                        <%  }%>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary me-2">Valider</button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../template/footer.jsp" />

