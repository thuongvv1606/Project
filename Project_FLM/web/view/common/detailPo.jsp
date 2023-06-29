<%-- 
    Document   : detailPo
    Created on : Jun 8, 2023, 9:26:32 PM
    Author     : LanChau
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="util.Constants"%>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="manifest" href="site.webmanifest">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">

        <!-- CSS here -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/slicknav.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/flaticon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/progressbar_barfiller.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/gijgo.css">


        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/nice-select.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
        <style>

        </style>
    </head>

    <body>
        <!-- ? Preloader Start -->

        <!-- Preloader Start -->
        <jsp:include page="/user/navigator/header.jsp"/>  
        <main >
             <section class="slider-area slider-area2">
                <div class="slider-active">
                    <!-- Single Slider -->
                    <div style="height:100px; " >   
                        <jsp:include page="/view/navSubHeaderCurriculum.jsp"/> 
                    </div>
                </div>
            </section>
            <div class="courses-area section-padding40 fix">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-xl-7 col-lg-8">
                            <div class="section-tittle text-center mb-55">
                                <h2>Detail PO</h2>
                            </div>
                        </div> 
                    </div>
                    <div class="row">
                        <!-- Your Profile Views Chart -->
                        <div class="col-lg-12 m-b30">
                            <div class="widget-box">
                                <div class="widget-inner">
                                    <div class="row">

                                        <div class="form-group col-6">
                                            <label class="col-form-label">Name: ${po.poName}</label>

                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Description: ${po.poDescription}</label>

                                        </div> 
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Curriculum: ${po.curriculum.name}</label>

                                        </div>

                                        <div class="form-group col-6">
                                            <label class="col-form-label">Status: ${po.isActive== true?'Active':'Block'}</label>

                                        </div> 
                                        <div class="col-12">
                                            <div class="text-end">
                                                <a href="${pageContext.request.contextPath}${Constants.URL_POLIST}?idCurrent=${idCurrent}" >
                                                    <button type="button" class="btn btn-primary">Back</button>
                                                </a> 
                                            </div>
                                        </div>

                                    </div> 
                                </div>
                            </div>
                        </div>
                        <!-- Your Profile Views Chart END-->
                    </div>
                </div>
            </div>



        </main>
        <jsp:include page="/user/navigator/footer.jsp"/>      
    </body>
</html>