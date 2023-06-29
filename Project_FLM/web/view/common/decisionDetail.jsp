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
        <title>Decision Detail</title>
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

        <div class="section-tittle text-center">
            <h2 style="margin-top: 20px;">Decision Detail</h2>
        </div>
        <div style="border: 5px #004085; width: auto; height: auto; " >
            <form class="form-contact contact_form" action="decisionDetail" method="POST" id="contactForm" novalidate="novalidate">
                <div style="width: 100%; height: auto; display: flex">
                    <div style="width: 30%; height: auto">
                        <h2 style="color:green; text-align: center;">${message}</h2>
                        <h2 style="color:red; text-align: center;">${error}</h2>
                        <input name="decisionId" type="text" value="${decision.getId()}" hidden="">
                        <div class="col-12">
                            <div class="form-group">
                                <label>Decision No*</label>
                                <input class="form-control" readonly="" name="decisionNo" id="decisionNo" type="text" value="${decision.getDecision_no()}" >
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-group">
                                <label>Decision Name</label>
                                <input class="form-control" name="decisionName" id="name" type="text" value="${decision.getDecision_name()}" >
                            </div>
                        </div>

                        <div class="col-12">
                            <div class="form-group">
                                <label>Decision Date</label>
                                <input class="form-control" name="decisionDate" id="decisionDate" type="Date" value="${decision.getDecision_date()}" >
                            </div>
                        </div>

                        <div class="col-md-12">
                            <label style=""s class="small mb-1"><b>Curriculum</b></label><br>
                            <c:forEach items="${curriculumList}" var="listc">
                                <div class="checkbox-container">
                                    <input type="checkbox" name="curriculum" value="${listc.curriculumID}"
                                           <c:forEach items="${decision.curriculumList}" var="c">
                                               <c:if test="${c.curriculumID eq listc.curriculumID}">
                                                   checked
                                               </c:if>
                                           </c:forEach>
                                           >
                                    <b>${listc.code}</b>

                                </div>
                            </c:forEach>

                        </div>
                        <br>
                    </div>
                    <div style="width: 70%; height: auto">
                        <div class="col-md-12">
                            <label class="small mb-1"><b>Syllabus</b></label><br>
                            <c:forEach items="${syllabusList}" var="lists">
                                <input type="checkbox" name="syllabus" value="${lists.syllabusId}"
                                       <c:forEach items="${decision.syllabusList}" var="c">
                                           <c:if test="${c.syllabusId eq lists.syllabusId}">
                                               checked
                                           </c:if>
                                       </c:forEach>
                                       >
                                <label>${lists.name}</label>
                                <c:if test="${lists.syllabusId % 2 == 0}"><br></c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div>
                    <div style="margin: 10px">
                        <div class="form-group">
                            <input type="radio" name="active" value="1"${decision.isIs_active() eq true ? "checked" : ""}/> <label>Active </label>
                            <input type="radio" name="active" value="0"${decision.isIs_active() eq false ? "checked" : ""}/> <label>De-Active </label>
                        </div>
                    </div>


                    <div style="display: flex; justify-content: space-between; margin: 50px 100px">
                        <div>
                            <button type="submit" name="oki" value="oki" class="button button-contactForm boxed-btn">Edit</button>
                        </div>
                        <div>
                            <a href="decisionController"><b>Decision List</b></a>
                        </div>
                    </div>

                </div>
            </form>
        </div>
        <!--Start Footer-->
        <jsp:include page="/user/navigator/footer.jsp"/>   
        <!--End Footer-->
        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>
