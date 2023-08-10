<%-- 
    Document   : contentgroupDetail
    Created on : Jun 8, 2023, 3:01:05 PM
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
        <title>Content Group Detail</title>
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
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
            input {
                background-color: black;
                border-radius: 5px;
            }

            .table-bordered td:first-child {
                text-align: right;
                background-color: #20c997;
                color: white;
            }

            .table-sortable th {
                cursor: pointer;
            }

            .table-sortable .th-sort-asc::after {
                content: "\25b4";
            }

            .table-sortable .th-sort-desc::after {
                content: "\25be";
            }

            .table-sortable .th-sort-asc::after,
            .table-sortable .th-sort-desc::after {
                margin-left: 5px;
            }

            .table-sortable .th-sort-asc,
            .table-sortable .th-sort-desc {
                background: rgba(0, 0, 0, 0.1);
            }

            .custom-toast {
                top: 100px !important; /* Điều chỉnh khoảng cách từ top */
            }
        </style>
    </head>
    <body>
        <jsp:include page="/user/navigator/header.jsp"/>  
        <jsp:include page="/view/navSubHeaderCurriculum.jsp"/> 
        <div class="row" style="margin: 20px;">
            <div class="section-tittle text-center">
                <h2 style="margin-top: 20px;">Content Group Detail</h2>
            </div>
            <table class="table table-bordered">
                <tbody>
                    <tr>
                        <td style="width: 200px" >Curriculum Code:</td>
                        <td><b>${curriculum.getCode()}</b>
                            <input class="form-control" name="curId" id="curId" type="text" hidden="" value="${curId}" "></td>
                    </tr>
                    <tr>
                        <td style="width: 200px">Content Group ID:</td>
                        <td>${cg.getId()} </td>
                    </tr>
                    <tr>
                        <td style="width: 200px">Name:</td>
                        <td>${cg.getName()}</td>
                    </tr>
                    <tr>
                        <td style="width: 200px">English Name:</td>
                        <td>${cg.getEname()}</td>
                    </tr>
                    <tr>
                        <td style="width: 200px">Display Order:</td>
                        <td>${cg.getOrder()}</td>
                    </tr>
                </tbody>                        
            </table>
            <hr style="border: 1px solid gray; margin-top: 20px">
            <div class="section-tittle text-center">
                <h2 style="margin-top: 20px;">List Subject Content Group</h2>
            </div>
            <c:if test="${cg.getsList().size() == 0}">
                <h4 style="color: red; text-align: center; margin: 8px;">Cannot found any subjects of this content group!!!</h4>
                <div class="row" style="padding: 20px; display: flex;">
                    <div style="margin: auto;">
                        <img src="assets/img/list.jpg" alt="alt" width="300px"/>
                    </div>
                </div>
            </c:if>
            <c:if test="${cg.getsList().size() != 0}">
                <div class="progress-table-wrap" style="margin: 0 10px;">
                    <table class="table table-striped table-hover table-sortable" style="background-color: #f9f9ff">
                        <thead>
                            <tr>
                                <th>Subject Id</th>
                                <th>Subject Code</th>
                                <th>Subject Name</th>
                                <th>Semester</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${cg.getsList()}" var="s">
                                <tr>
                                    <td>${s.getId()}</td>
                                    <td><a href="subjectsDetail?subCode=${s.getId()}&curriculumID=${curriculum.curriculumID}" style="color: blue">
                                            ${s.getCode()}
                                        </a></td>
                                    <td>
                                        ${s.getName()}
                                    </td>

                                    <td>${s.getSemester()}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <c:if test="${user.getRoleFunction() == 2}">
                <a href="contentgroup?action=edit&&contentgroupId=${cg.getId()}">
                    <button class="btn btn-primary" name="save" type="submit" style="margin-bottom: 5px;">Edit Elective</button>
                </a>
            </c:if>
            <a href="contentgroup?action=list&&curId=${curId}">
                <button class="btn btn-primary" type="submit">Back</button> 
            </a>
        </div>
        <jsp:include page="/user/navigator/footer.jsp"/>      
        <!-- Scroll Up -->
        <div id="back-top" >
            <a title="Go to Top" href="#"> <i class="fas fa-level-up-alt"></i></a>
        </div>
    </body>
</html>
