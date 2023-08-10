<%-- 
    Document   : curriculumList
    Created on : May 25, 2023, 9:26:03 PM
    Author     : TT
--%>
<%@page import="util.Constants"%>
<%@page import="model.Syllabus"%>
<%@page import="model.Subject"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
        <title>Subject Predecessor</title>
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
            <h2 style="margin-top: 20px;">Subject Predecessor</h2>
        </div> 
        <div style="height: 500px">
            <form action="${pageContext.request.contextPath}${Constants.URL_SUBJECTPREDECESSOER}" method="POST">
                <div class="row" style="padding: 20px; display: flex;">
                    <div style="margin: auto;">

                        <h1>Show Learning Path of a Subject</h1>
                        <input type="text" name="key" class="btn btn-success" value="${key}" style="background-color: white; color: black; height: 30px; border: 2px solid #c86fff;">
                        <input type="submit" name="search_btn" class="btn" style="padding: 5px; letter-spacing: unset;" value="Search">
                    </div>
                </div>
            </form>
            <h2 style="color: red; text-align: center">${mess}</h2>
            <c:if test="${s  != null}">
                <div class="progress-table-wrap" style="margin: 0 10px;">
                    <table class="table table-striped table-hover" style="background-color: #f9f9ff">
                        <thead>
                            <tr>
                                <th>Subject Code</th>
                                <th>Syllabus Name</th>
                                <th>Decision No.<br>(yyyy/MM/dd)</th>
                                <th>All subjects lean before</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><a href="${pageContext.request.contextPath}${Constants.URL_SUBJECTDETAIL}?subCode=${s.getSubject().getId()}"><b>${s.getSubject().getCode()}</b></a></td>
                                <td>
                                    <a href="syllabus?action=detail&sylId=${s.getSyllabusId()}" style="color: blue;">
                                            ${s.getName()}
                                        </a>
                                </td>
                                <td> 
                                    <c:if test="${sessionScope.user == null}">
                                        <c:if test="${s.getDecision().getId() == null}">
                                            None
                                        </c:if>
                                        <c:if test="${s.getDecision().getId() != null}">
                                            ${s.getDecision().getDecision_no()} (${s.getDecision().getDecision_date() })
                                        </c:if>
                                    </c:if>
                                    <c:if test="${sessionScope.user != null}">
                                        <c:if test="${s.getDecision().getId() == null}">
                                            None
                                        </c:if>
                                        <c:if test="${s.getDecision().getId() != null}">
                                            <a href="decisionOverview?decisionId=${s.getDecision().getId()}" style="color: blue;"> ${s.getDecision().getDecision_no()} (${s.getDecision().getDecision_date() })</a>
                                        </c:if>
                                    </c:if>
                                </td>


                                <c:if test="${s.getPre().size()  == 0}">
                                    <td>None
                                    </c:if>
                                    <c:if test="${s.getPre().size()  != 0}">
                                    <td><b>*${s.getSubject().getCode()} :</b>
                                        <c:forEach items="${s.getPre()}" var="pr" varStatus="loop">
                                            <b> ${pr.getCode()}
                                                <c:if test="${not loop.last}">
                                                    ,
                                                </c:if></b>
                                            </c:forEach>
                                            <c:forEach items="${s.getPre()}" var="pr">
                                                <c:forEach items="${sList}" var="b">
                                                <c:if test="${b.getSubject().getId()  == pr.getPreId()}">
                                                    <br>
                                                    <b>-${pr.getCode()}:</b>
                                                    <c:if test="${b.getPre().size() == 0}">
                                                        (none)
                                                    </c:if>
                                                    <c:if test="${b.getPre().size() != 0}" > 
                                                        <c:forEach items="${b.getPre()}" var="b1" varStatus="loop">
                                                            ${b1.getCode()}
                                                            <c:if test="${not loop.last}">
                                                            </c:if>
                                                        </c:forEach>

                                                    </c:if>
                                                </c:if>




                                            </c:forEach>

                                        </c:forEach>
                                    </c:if>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </c:if>

        </div>

        <!--Start Footer-->
        <jsp:include page="/user/navigator/footer.jsp"/>   
        <!--End Footer-->
        <!--Scroll Up--> 
        <div id="back-top" >
            <a title="Go to Top" href="#"> <i class="fas fa-level-up-alt"></i></a>
        </div>
    </body>
</html>
