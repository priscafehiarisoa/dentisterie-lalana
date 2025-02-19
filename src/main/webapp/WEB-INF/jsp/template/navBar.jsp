<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-fluid page-body-wrapper">

  <nav class="sidebar sidebar-offcanvas" id="sidebar">
    <ul class="nav">
<%--      <li class="nav-item">--%>
<%--        <a class="nav-link" href="">--%>
<%--          <i class="mdi mdi-grid-large menu-icon"></i>--%>
<%--          <span class="menu-title">Dashboard</span>--%>
<%--        </a>--%>
<%--      </li>--%>

      <li class="nav-item nav-category">Consultation</li>
      <li class="nav-item">
        <a class="nav-link" data-bs-toggle="collapse" href="#Voyage" aria-expanded="false"
           aria-controls="Voyage">
          <i class="menu-icon mdi mdi-earth"></i>
          <span class="menu-title">consultation</span>
          <i class="menu-arrow"></i>
        </a>
        <div class="collapse" id="Voyage">
          <ul class="nav flex-column sub-menu">
            <li class="nav-item"><a class="nav-link" href="/consultation">ajouter</a></li>
            <li class="nav-item"><a class="nav-link" href="/listConsultation">liste</a></li>
            <li class="nav-item"><a class="nav-link" href="/alea">alea</a></li>
          </ul>
        </div>
      </li>

      <li class="nav-item nav-category">Lalana</li>
      <li class="nav-item">
        <a class="nav-link" data-bs-toggle="collapse" href="#note" aria-expanded="false"
           aria-controls="note">
          <i class="menu-icon mdi mdi-map-check-outline"></i>
          <span class="menu-title">lalana</span>
          <i class="menu-arrow"></i>
        </a>
        <div class="collapse" id="note">
          <ul class="nav flex-column sub-menu">
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/getFormEtatRoutes">ajouter EtatRoutes</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/getFormConstruction">ajouter Construction</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/listConstruction">liste Construction</a></li>
          </ul>
        </div>
      </li>

<%--      <li class="nav-item nav-category">Activités</li>--%>
<%--      <li class="nav-item">--%>
<%--        <a class="nav-link" data-bs-toggle="collapse" href="#activite" aria-expanded="false"--%>
<%--           aria-controls="activite">--%>
<%--          <i class="menu-icon mdi mdi-cards-variant"></i>--%>
<%--          <span class="menu-title">Activités</span>--%>
<%--          <i class="menu-arrow"></i>--%>
<%--        </a>--%>
<%--        <div class="collapse" id="activite">--%>
<%--          <ul class="nav flex-column sub-menu">--%>
<%--            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/formActivite">ajouter</a></li>--%>
<%--            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/listActivite">liste</a></li>--%>
<%--          </ul>--%>
<%--        </div>--%>
<%--      </li>--%>
    </ul>
  </nav>

