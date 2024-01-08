<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.example.dentisterie.model.nify.*" %>
<%@ page import="com.example.dentisterie.model.lalana.Construction" %>
<%@ page import="com.example.dentisterie.model.lalana.EtatRoutes" %>
<%@ page import="com.example.dentisterie.model.lalana.HIstoriqueReparation" %>
<jsp:include page="../template/header.jsp" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<EtatRoutes> etatRoutesList= new ArrayList<>();
    Construction construction=new Construction();
    if(request.getAttribute("construction")!=null){
        construction= (Construction) request.getAttribute("construction");
    }

    if(request.getAttribute("etatRoutes")!=null){
        etatRoutesList= (List<EtatRoutes>) request.getAttribute("etatRoutes");
    }
    List<HIstoriqueReparation> hIstoriqueReparations= new ArrayList<>();
    if(request.getAttribute("historique")!=null){
        hIstoriqueReparations= (List<HIstoriqueReparation>) request.getAttribute("historique");
    }

    double restebudget=0;
    if(hIstoriqueReparations.size()>=1){
        restebudget=hIstoriqueReparations.get(hIstoriqueReparations.size()-1).getResteBudget();
    }

%>


<div class="col-md-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <div class="card m-5">
                <div class="card-body">
                    <h4 class="card-title">detail consultation </h4>

                    <p>n* construction : <strong><%=construction.getId()%></strong></p>
                    <p>Route : <strong><%=construction.getRoutes().getNonRoutes()%></strong></p>
                    <p>date : <strong><%=construction.getDateConstruction()%></strong></p>
                    <p>budget : <strong><%=construction.getBudget()%></strong></p>
                    <p>reste budget apres itervention : <strong><%=restebudget%></strong></p>
                </div>
            </div>

            <%--            facture --%>
            <div class="card m-5">
                <div class="card-body">
                    <h4 class="card-title">proposition de facture</h4>

                    <table border="1" class="table">
                        <tr>
                            <th>Route</th>
                            <th>portion à réparer</th>
                            <th>etat initiale</th>
                            <th>etat finale</th>
                            <th>date historique</th>
                            <th>prix total construction</th>
                            <th>reste budget</th>
                        </tr>
                        <tbody>

                        <% for (HIstoriqueReparation historique: hIstoriqueReparations) {
                        if(historique.getCoutDepenses()>0){
                        %>

                        <tr>
                            <td><%=historique.getEtatRoutes().getRoutes().getNonRoutes()%></td>
                            <td><%=historique.getEtatRoutes().getDebutEtatRoutes()%> - <%=historique.getEtatRoutes().getFinEtatRoutes()%> </td>
                            <td><%=historique.getEtatInitiale()%></td>
                            <td><%=historique.getEtatFinale()%></td>
                            <td><%=historique.getDateHistorique()%></td>
                            <td><%=historique.getCoutDepenses()%></td>
                            <td><%=historique.getResteBudget()%></td>

                                <% }} %>
                        </tbody>

                    </table>

                </div>
            </div>


            <% if (construction.getEtat()==0){ %>

            <div>
                <a href="http://localhost:8080/validerConstruction/<%=construction.getId()%>" class="btn btn-primary me-2"
                >Valider la construction</a>
            </div>
            <%}%>



        </div>
    </div>
</div>

<jsp:include page="../template/footer.jsp" />

