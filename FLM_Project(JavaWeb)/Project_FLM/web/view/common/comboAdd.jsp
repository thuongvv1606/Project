<%-- 
    Document   : curriculumList
    Created on : May 25, 2023, 9:26:03 PM
    Author     : TT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Combo Add</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- CSS here -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/slicknav.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/flaticon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/progressbar_barfiller.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/gijgo.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/animate.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/animated-headline.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/magnific-popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fontawesome-all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/themify-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/slick.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/nice-select.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
        <style>
            .action {
                background-color: black;
                border-radius: 5px;
            
            }
            
        </style>
    </head>
    <body>
        <!--Start Header-->
        <jsp:include page="/user/navigator/header.jsp"/>  
        <main>
            <jsp:include page="/view/navSubHeaderCurriculum.jsp"/> 
            <!--End Header-->
            <div class="section-tittle text-center">
                <h2 style="margin-top: 20px;">New Combo</h2>
            </div>
            <div style="border: 5px #004085; width: 500px; height: 800px; margin-left: 350px; margin-top: 100px" >
                <form class="form-contact contact_form" action="comboAdd" method="POST" id="contactForm" novalidate="novalidate">
                    <div class="row">
                        <h2 style="color:red; text-align: center;">${message}</h2>
                        <h2 style="color:red; text-align: center;">${messFail}</h2>
                        <div class="col-12">
                            <div class="form-group">
                                <label>Curriculum Code*</label>
                                <input class="form-control" name="curiculumCode" id="curiculumCode" readonly="" type="text" value="${curriculum.getCode()}" ">
                                <input class="form-control" name="curId" id="curId" type="text" hidden="" value="${curId}" ">
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-group">
                                <label>Combo Name*</label>
                                <textarea class="form-control w-100" name="comboName" id="comboName" value="" cols="30" rows="9" placeholder=" Enter combo name">${comboName}</textarea>
                            </div>
                        </div>
                            <div class="col-12">
                            <div class="form-group">
                                <label>Combo English Name</label>
                                <textarea class="form-control w-100" name="comboEnglishName" id="comboEnglishName" value="" cols="30" rows="9" placeholder=" Enter combo English name">${comboEnglishName}</textarea>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-group">
                                <label>Combo Type</label>
                                <input class="form-control" name="comboType" type="text" value="${comboType}" placeholder="Enter Combo Type">
                            </div>
                        </div>



                    </div>
                    <div style="width: 100%;">
                        <div class="form-group mt-3" style="float: left; width: 50%;">
                            <button type="submit" name="oki" value="oki" class="button button-contactForm boxed-btn">New Combo</button>
                        </div>
                        <div class="form-group mt-3" style="float: right; width: 50%; text-align: right;">
                            <a href="combo?curId=${curId}"><b>Combo List</b></a>
                        </div>
                    </div>
                </form>
            </div>
        </main>
        <!--Start Footer-->
        <jsp:include page="/user/navigator/footer.jsp"/>   
        <!--End Footer-->
        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>
