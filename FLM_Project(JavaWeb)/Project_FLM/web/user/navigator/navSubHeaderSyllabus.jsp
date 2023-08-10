<%-- 
    Document   : navSubHeaderSyllabus
    Created on : Jun 10, 2023, 8:39:01 PM
    Author     : TT
--%>

<%@page import="util.Constants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>navbar template1</title>
        <!--bootstrap css-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <!--custom css-->
        <link rel='stylesheet' href='${pageContext.request.contextPath}/assets/css/style.css' type='text/css' media='all' />
        <!--bootstrap js-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
        <!--jquery--> 
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <!--custom js-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/script.js"></script>
    </head>
    <body>
        <c:if test="${syllabus != null}">
            <nav class="navbar navbar-expand-lg" style="justify-content: space-between" id="navbar">
                <div class="collapse navbar-collapse" id="navbarNavDropdown" style="flex-grow: 0; margin: auto;">
                    <ul class="navbar-nav">
                        <li class="nav-link" style="font-weight: bold">${syllabus.getEnglish_name()}</li>
                        <li class="nav-item">
                            <a class="nav-link" href="syllabus?action=detail&sylId=${syllabus.getSyllabusId()}">Overview</a>
                        </li>
                        <li class="nav-item">
                           <a class="nav-link" href="${pageContext.request.contextPath}${Constants.URL_CLOLIST}?sylId=${syllabus.syllabusId}">CLOs</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">CLO-PLOs</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Materials</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Schedule</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">CQs</a>
                        </li>
                        
                        <li class="nav-item">
                           <a class="nav-link" href="${pageContext.request.contextPath}${Constants.URL_SESSIONLIST}?syllabusId=${syllabus.syllabusId}">Session</a>
                        </li>
                        
                        <li class="nav-item">
                            <a class="nav-link" href="#">Assessments</a>
                        </li>
                    </ul>
                </div>
                <div class="nav-item dropdown">

                </div>
            </nav> 

        </c:if> 
    </body>
</html>
