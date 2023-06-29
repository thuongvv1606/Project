<%-- 
    Document   : contentgroupList
    Created on : Jun 8, 2023, 3:01:05 PM
    Author     : TT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Content Group List</title>
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
        </style>
    <body>
        <jsp:include page="/user/navigator/header.jsp"/> 
        <main>
            <jsp:include page="/user/navigator/navSubHeaderCurriculum.jsp"/>  
            <div class="section-tittle text-center">
                <h2 style="margin-top: 20px;">Content Group List</h2>
            </div>
            <div class="container">
                <table class="table table-bordered">
                    <tbody>
                        <tr>
                            <td>Curriculum Id:</td>
                            <td><b>${curriculum.curriculumID}</b></td>
                        </tr>
                        <tr>
                            <td>Curriculum Code:</td>
                            <td><b>${curriculum.code}</b></td>

                        </tr>
                        <tr>
                            <td>Name:</td>
                            <td><b>${curriculum.name}</b></td>
                        </tr>
                    </tbody>
                </table>
                <hr style="border: 1px solid gray;">
                <div class="row">
                <div class="col-sm-6">
                    <form action="contentgroup" method="post">
                        <input type="hidden" name="action" value="list">
                        <input type="hidden" name="curId" value="${curriculum.curriculumID}">
                        <input type="text" name="key" class="btn btn-success" style="background-color: white; color: black; height: 30px; border: 2px solid #c86fff;">
                        <input type="submit" name="search_btn" class="btn" style="padding: 5px; letter-spacing: unset;" value="Search">
                    </form>
                </div>
                <div class="col-sm-6">
                    <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                        <a href="contentgroup?action=add"><button class="btn btn-primary" type="submit" style="float: right;">Add Content Group</button> </a>
                    </c:if>
                </div>
                </div>
                <c:if test="${cgList.size() == 0}">
                    <h4 style="color: red; text-align: center; margin: 8px;">Cannot found any content groups of this curriculum!!!</h4>
                    <div class="row" style="padding: 20px; display: flex;">
                        <div style="margin: auto;">
                            <img src="assets/img/list.jpg" alt="alt" width="300px"/>
                        </div>
                    </div>
                </c:if>
                <c:if test="${cgList.size() != 0}">
                    <br>
                    <div class="hint-text" style="margin-left: 20px; color: green;">Found <b>${count}</b> content group(s)</div>
                    <br>
                    <div class="progress-table-wrap" style="margin: 0 10px;">
                        <table class="table table-striped table-hover table-sortable" style="background-color: #f9f9ff">
                            <thead>
                                <tr>
                                    <th>Group ID</th>
                                    <th>Group Name</th>
                                    <th>Group Type</th>
                                    <th>Group Subjects</th>
                                        <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                                        <th>Actions</th>
                                        </c:if>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${cgList}" var="c">
                                    <tr>
                                        <td>
                                            ${c.getId()}
                                        </td>
                                        <td><a href="contentgroup?action=detail&&contentgroupId=${c.getId()}" style="color: blue">
                                                ${c.getName()}
                                            </a></td>
                                        <td>${c.getType()}</td>
                                        <td>
                                            <% int dem = 0;%>
                                            <c:forEach items="${c.getsList()}" var="s">
                                                <label style="font-weight: unset; margin-right: 15px;">${s.getCode()}</label>
                                                <%if (dem % 3 == 2) {%>
                                                <br>
                                                <%}
                                                    dem++;%>
                                            </c:forEach>
                                        </td>
                                        <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                                            <td>
                                                <a href="contentgroup?action=edit&contentgroupId=${c.getId()}">
                                                    <i style="color: blue" class="fa fa-edit"></i>
                                                </a> 
                                                <a href="#confirmDeleteModal${c.getId()}"  data-toggle="modal">
                                                    <i style="color:  red" class="fa fa-trash"></i>
                                                </a>
                                            </td>
                                        </c:if>
                                <div class="modal fade " id="confirmDeleteModal${c.getId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="confirmDeleteModalLabel">Delete Confirmation</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="text-dark modal-body">
                                                Are you sure you want to delete?
                                            </div>
                                            <div class="modal-footer">
                                                <a href="contentgroup?action=delete&&contentgroupId=${c.getId()}"><button type="submit" class="btn btn-danger">Delete</button></a>
                                                <button type="button" style="margin-right: 5px" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="clearfix" style="margin: 0 10px;">
                        <ul class="pagination">
                            <c:forEach begin="1" end="${end}" var="i">
                                <li class="page-item"><a href="contentgroup?action=list&&curId=${curId}&&index=${i}" class="page-link">${i}</a></li>
                                </c:forEach>
                        </ul>
                    </div>
                </c:if>
                <a href="curriculum">
                    <button class="btn btn-primary" type="submit">Back</button> 
                </a>
            </div>
        </main>
        <!--Start Footer-->
        <jsp:include page="/user/navigator/footer.jsp"/>   
        <div id="back-top" >
            <a title="Go to Top" href="#"> <i class="fas fa-level-up-alt"></i></a>
        </div>
        <!-- JS here -->
        <script src="${pageContext.request.contextPath}/assets/js/vendor/modernizr-3.5.0.min.js"></script>
        <!-- Jquery, Popper, Bootstrap -->
        <script src="${pageContext.request.contextPath}/assets/js/vendor/jquery-1.12.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
        <!-- Jquery Mobile Menu -->
        <script src="${pageContext.request.contextPath}/assets/js/jquery.slicknav.min.js"></script>

        <!-- Jquery Slick , Owl-Carousel Plugins -->
        <script src="${pageContext.request.contextPath}/assets/js/owl.carousel.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/slick.min.js"></script>
        <!-- One Page, Animated-HeadLin -->
        <script src="${pageContext.request.contextPath}/assets/js/wow.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/animated.headline.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.magnific-popup.js"></script>

        <!-- Date Picker -->
        <script src="${pageContext.request.contextPath}/assets/js/gijgo.min.js"></script>
        <!-- Nice-select, sticky -->
        <script src="${pageContext.request.contextPath}/assets/js/jquery.nice-select.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.sticky.js"></script>
        <!-- Progress -->
        <script src="${pageContext.request.contextPath}/assets/js/jquery.barfiller.js"></script>

        <!-- counter , waypoint,Hover Direction -->
        <script src="${pageContext.request.contextPath}/assets/js/jquery.counterup.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/waypoints.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.countdown.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/hover-direction-snake.min.js"></script>

        <!-- contact js -->
        <script src="${pageContext.request.contextPath}/assets/js/contact.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.form.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.validate.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/mail-script.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.ajaxchimp.min.js"></script>

        <!-- Jquery Plugins, main Jquery -->	
        <script src="${pageContext.request.contextPath}/assets/js/plugins.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
        <script>
            function sortTableByColumn(table, column, asc = true) {
                const dirModifier = asc ? 1 : -1;
                const tBody = table.tBodies[0];
                const rows = Array.from(tBody.querySelectorAll("tr"));

                // Sort each row
                const sortedRows = rows.sort((a, b) => {
                    const aColText = a.querySelector(`td:nth-child(${column + 1})`);
                    const bColText = b.querySelector(`td:nth-child(${column + 1})`);

                    return aColText > bColText ? (1 * dirModifier) : (-1 * dirModifier);
                });

                // Remove all existing TRs from the table
                while (tBody.firstChild) {
                    tBody.removeChild(tBody.firstChild);
                }

                // Re-add the newly sorted rows
                tBody.append(...sortedRows);

                // Remember how the column is currently sorted
                table.querySelectorAll("th").forEach(th => th.classList.remove("th-sort-asc", "th-sort-desc"));
                table.querySelector(`th:nth-child(${column + 1})`).classList.toggle("th-sort-asc", asc);
                table.querySelector(`th:nth-child(${column + 1})`).classList.toggle("th-sort-desc", !asc);
            }

            document.querySelectorAll(".table-sortable th").forEach(headerCell => {
                headerCell.addEventListener("click", () => {
                    const tableElement = headerCell.parentElement.parentElement.parentElement;
                    const headerIndex = Array.prototype.indexOf.call(headerCell.parentElement.children, headerCell);
                    const currentIsAscending = headerCell.classList.contains("th-sort-asc");

                    sortTableByColumn(tableElement, headerIndex, !currentIsAscending);
                });
            });
        </script>
    </body>
</html>
