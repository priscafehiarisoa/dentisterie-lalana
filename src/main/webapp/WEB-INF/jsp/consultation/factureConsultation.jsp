<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.example.dentisterie.model.nify.*" %>
<jsp:include page="../template/header.jsp" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
List<EtatDent> etatDentList= new ArrayList<>();
Consultation consultation=new Consultation();
if(request.getAttribute("etatdentInitial")!=null){
    etatDentList= (List<EtatDent>) request.getAttribute("etatdentInitial");
    consultation=etatDentList.get(0).getConsultation();
}
List<EtatDent> etatdentfinal= new ArrayList<>();
if(request.getAttribute("etatdentfinal")!=null){
    etatdentfinal= (List<EtatDent>) request.getAttribute("etatdentfinal");
}
List<HistoriqueIntervention> historiqueInterventions= new ArrayList<>();
if(request.getAttribute("historique")!=null){
    historiqueInterventions= (List<HistoriqueIntervention>) request.getAttribute("historique");
}

double restebudget=0;
if(historiqueInterventions.size()>=1){
    restebudget=historiqueInterventions.get(historiqueInterventions.size()-1).getResteBudget();
}

%>


<div class="col-md-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <div class="card m-5">
                <div class="card-body">
                    <h4 class="card-title">detail consultation </h4>

                    <p>n* consultation : <strong><%=consultation.getId()%></strong></p>
                    <p>nom du patient : <strong><%=consultation.getPatient().getNomPatient()%></strong></p>
                    <p>date : <strong><%=consultation.getDateConsultation()%></strong></p>
                    <p>budget : <strong><%=consultation.getBudget()%></strong></p>
                    <p>reste budget apres itervention : <strong><%=restebudget%></strong></p>
                </div>
            </div>
<%--           afficher les dents --%>

            <div class="card m-5">

                <div class="card-body row d-inline-flex mx-3 ">
                    <h4 class="card-title">Etat initiale des dents </h4>

                    <h5 class="card-title-dash">haut</h5>
                    <%for (int i = 0; i < etatDentList.size()/2 ; i++) {
                    String etat="bg-light";
                    String text="text-dark";
                    if(etatDentList.get(i).getNiveauEtat()<10){
                        etat="bg-dark";
                        text="";
                    }
                    %>
                    <span class="badge rounded-pill  <%=etat%> <%=text%> col-1 m-4">D-<strong><%=etatDentList.get(i).getDents().getId()%></strong> | state : <strong><%=etatDentList.get(i).getNiveauEtat()%></strong>  </span>
                    <% }%>
                    <hr>
                    <h5 class="card-title-dash">bas</h5>
                    <%for (int i = etatDentList.size()/2; i <  etatDentList.size(); i++) {
                    String etat="bg-light";
                    String text="text-dark";
                    if(etatDentList.get(i).getNiveauEtat()<10){
                        etat="bg-dark";
                        text="";
                    }
                    %>
                    <span class="badge rounded-pill <%=etat%> <%=text%> col-1 m-4">D-<strong><%=etatDentList.get(i).getDents().getId()%></strong> | state : <strong><%=etatDentList.get(i).getNiveauEtat()%></strong>  </span>
                    <% }%>
                </div>
            </div>
<%--            facture --%>
            <div class="card m-5">
                <div class="card-body">
                    <h4 class="card-title">proposition de facture</h4>

                    <table border="1" class="table">
                        <tr>
                            <th>Dent</th>
                            <th>type dent</th>
                            <th>etat initiale</th>
                            <th>etat finale</th>
                            <th>prix total intervention</th>
                            <th>reste budget</th>
                        </tr>
                        <tbody>

                        <% for (HistoriqueIntervention historique: historiqueInterventions) { %>
                        <tr>
                            <td><%=historique.getEtatDent().getDents().getId()%></td>
                            <td><%=historique.getEtatDent().getDents().getTypeDent().getDenomination()%></td>
                            <td><%=historique.getEtatInitiale()%></td>
                            <td><%=historique.getEtatFinale()%></td>
                            <td><%=historique.getPrixIntervention()%></td>
                            <td><%=historique.getResteBudget()%></td>

                        <% } %>
                        </tbody>

                    </table>

                </div>
            </div>

            <div class="card m-5">

                <div class="card-body row d-inline-flex mx-3 ">
                    <h4 class="card-title">Etat Finale des dents </h4>

                    <h5 class="card-title-dash">haut</h5>
                    <%for (int i = 0; i < etatdentfinal.size()/2 ; i++) {
                        String etat="bg-light";
                        String text="text-dark";
                        if(etatdentfinal.get(i).getNiveauEtat()<10){
                            etat="bg-dark";
                            text="";
                        }
                    %>
                    <span class="badge rounded-pill  <%=etat%> <%=text%> col-1 m-4">D-<strong><%=etatdentfinal.get(i).getDents().getId()%></strong> | state : <strong><%=etatdentfinal.get(i).getNiveauEtat()%></strong>  </span>
                    <% }%>
                    <hr>
                    <h5 class="card-title-dash">bas</h5>
                    <%for (int i = etatdentfinal.size()/2; i <  etatdentfinal.size(); i++) {
                        String etat="bg-light";
                        String text="text-dark";
                        if(etatdentfinal.get(i).getNiveauEtat()<10){
                            etat="bg-dark";
                            text="";
                        }
                    %>
                    <span class="badge rounded-pill <%=etat%> <%=text%> col-1 m-4">D-<strong><%=etatdentfinal.get(i).getDents().getId()%></strong> | state : <strong><%=etatdentfinal.get(i).getNiveauEtat()%></strong>  </span>
                    <% }%>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../template/footer.jsp" />

