<%-- 
    Document   : curriculumList
    Created on : May 25, 2023, 9:26:03 PM
    Author     : TT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="util.Constants"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Edit Plo</title>
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
        <!--End Header-->

        <section class="slider-area slider-area2">
                <div class="slider-active">
                    <!-- Single Slider -->
                    <div style="height:100px; " >   
                        <jsp:include page="/view/navSubHeaderCurriculum.jsp"/> 
                    </div>
                </div>
            </section>
        <div style="border: 5px #004085; width: 500px; height: 800px; margin-left: 350px; margin-top: 100px" >
            <form class="form-contact contact_form" action="${pageContext.request.contextPath}${Constants.URL_PLODEDIT}" method="POST" id="contactForm">
                <input type="text" hidden name="idSetting" value="${a}"/>
                <!--<input type="text" hidden name="idCurri" value="${b}"/>-->
                <div class="row">
                    <h2 style="color:red; text-align: center;">${mess}</h2>
                    <h2 style="color:green; text-align: center;">${messs}</h2>
                    <div class="col-12">
                        <div class="form-group">
                            <label>PLO Name*</label>
                            <input class="form-control" name="name" 
                                   type="text" value="${plo.getName()}"  required=""
                                   placeholder="Enter Name">
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="form-group">
                            <label>PLO Description</label>
                            <!--<input class="form-control" name="description" id="Description" type="text" value="${plo.getDescription()}" required="" placeholder="Enter Description">-->
                            <textarea id="description"
                                      name="description"
                                      cols="100"
                                      rows="10"
                                      required=""
                                      >${plo.getDescription()}</textarea>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="form-group">
                            <label>Curriculum Id</label>
                            <input class="form-control" name="curri_id" id="total" type="text" value="${plo.getCurriculum_id()}" readonly="" required="" placeholder="Curriculum Id"
                                   >
                        </div>
                    </div>
                    <div class="col-12">
                        
                    </div>
                    <div class="col-12">
                        
                    </div>
                    <div class="col-sm-6">

                    </div>

                </div>
                <div class="form-group mt-3">
                    <button type="submit" name="update" value="Update" class="button button-contactForm boxed-btn">Update</button>
                </div>
            </form>
        </div>
        <!--Start Footer-->
        <jsp:include page="/user/navigator/footer.jsp"/>   
        <!--End Footer-->

        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>
