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
        <title>Curriculum List</title>
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
                <div class="single-slider slider-height2">
                    <div class="container">
                        <div class="row">
                            <div class="col-xl-8 col-lg-11 col-md-12">
                                <div class="hero__caption hero__caption2">
                                    <h1 data-animation="bounceIn" data-delay="0.2s">Add New Curriculum</h1>
                                    <!-- breadcrumb Start-->
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="home">Home</a></li>
                                            <li class="breadcrumb-item"><a href="curriculum">Curriculum List</a></li> 
                                        </ol>
                                    </nav>
                                    <!-- breadcrumb End -->
                                </div>
                            </div>
                        </div>
                    </div>          
                </div>
            </div>
        </section>
        <div style="border: 5px #004085; width: 500px; height: 800px; margin-left: 350px; margin-top: 100px" >
            <form class="form-contact contact_form" action="curriculumAdd" method="POST" id="contactForm" novalidate="novalidate">
                <div class="row">
                    <h2 style="color:red; text-align: center;">${mess}</h2>
                    <h2 style="color:green; text-align: center;">${messs}</h2>
                    <div class="col-12">
                        <div class="form-group">
                            <label>Curriculum Code*</label>
                            <input class="form-control" name="code" id="code" type="text" value="${code}" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Subject'" placeholder="Enter Code">
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="form-group">
                            <label>Curriculum Name*</label>
                            <input class="form-control" name="name" id="name" type="text" value="${name}" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Subject'" placeholder="Enter Name">
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="form-group">
                            <label>Total Credit*</label>
                            <input class="form-control" name="total" id="total" type="number" value="${total}" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Subject'" placeholder="Total Credit"
                                   >
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="form-group">
                            <label>Curriculum Description*</label>
                            <textarea class="form-control w-100" name="description" id="description" value="" cols="30" rows="9" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Message'" placeholder=" Enter Description">${description}</textarea>
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="form-group">
                            <label>Decision ID</label>
                            <select name="decisionId">
                                <option value="1" ${d eq 1 ? "selected" : ""}>1</option>
                                <option value="2" ${d eq 2 ? "selected" : ""}>2</option>
                                <option value="3" ${d eq 3 ? "selected" : ""}>3</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <input type="radio" name="active" value="1" ${a eq 1 ? "checked" : ""} /> <label>Active </label>
                            <input type="radio" name="active" value="2" ${a eq 2 ? "checked" : ""}/> <label>De-Active </label>
                        </div>
                    </div>

                </div>
                <div class="form-group mt-3">
                    <button type="submit" name="oki" value=" oki"class="button button-contactForm boxed-btn">Save</button>
                </div>
            </form>
        </div>
        <!--Start Footer-->
        <jsp:include page="/user/navigator/footer.jsp"/>   
        <!--End Footer-->
        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>
