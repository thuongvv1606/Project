<%-- 
    Document   : electiveAdd
    Created on : Jun 5, 2023, 11:35:57 PM
    Author     : TT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Elective Add</title>
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
    </head>
    <body>
        <jsp:include page="/user/navigator/header.jsp"/>  
        <div class="section-tittle text-center">
            <h2 style="margin-top: 20px;">Add Elective</h2>
        </div>
        <div class="container">
            <div class="row">
                <!-- Your Profile Views Chart -->
                <div class="col-lg-12 m-b30">
                    <div class="widget-box">
                        <div class="widget-inner">
                            <form class="edit-profile m-b30" action="electiveAdd" method="POST">
                                <div class="row">
                                    <div class="form-group col-6">
                                        <label class="col-form-label">Elective Code<i style="color: red">*</i></label>
                                        <div>
                                            <input class="form-control" name="ename" pattern="${Constants.HTML_REGEX_NAME}" type="text" placeholder="Enter elective code" value="${ename}" required="">
                                        </div>
                                    </div> 
                                    <div class="form-group col-6">
                                        <label class="col-form-label">Elective Name<i style="color: red">*</i></label>
                                        <div>
                                            <input class="form-control" name="edes" pattern="${Constants.HTML_REGEX_NAME}" type="text" placeholder="Enter elective name" value="${edes}" required="">
                                        </div>
                                    </div>

                                    <div class="form-group col-6">
                                        <label class="col-form-label">Status</label>
                                        <div>
                                            <input name="status" type="radio" value="1" checked><label class="small mb-1">Active</label>
                                            <input name="status" type="radio" value="0"><label class="small mb-1">Dormant</label>
                                            <p style="color: red;">${messFail}</p> 
                                            <p style="color: green;">${messSuccess}</p>
                                        </div>
                                    </div>  
                                    <div class="col-12">
                                        <div class="text-end">
                                            <button type="submit" class="btn btn-primary" style="margin-bottom: 5px;">Save changes</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="text-end">
                                <a href="elective?action=list&&curId=${sessionScope.curId}">
                                    <button class="btn btn-primary" type="submit" style="margin-bottom: 20px;">Back</button> 
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--                        <div class="col-md-6">
                                    <label class="small mb-1">Subject Code</label><br><br>
        <c:forEach items="${sList}" var="s">
            <input style="margin-left: 10px;" name="esubject" type="checkbox" value="${s.getId()}"> <label class="small mb-1">${s.getCode()}</label>
            <c:if test="${s.getId() % 4 == 0}"><br></c:if>
        </c:forEach>
    </div>-->
        <jsp:include page="/user/navigator/footer.jsp"/>      
        <!-- Scroll Up -->
        <div id="back-top" >
            <a title="Go to Top" href="#"> <i class="fas fa-level-up-alt"></i></a>
        </div>
    </body>
</html>
