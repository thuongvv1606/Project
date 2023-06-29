<%-- 
    Document   : accountList
    Created on : May 23, 2023, 5:30:53 PM
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
        <title>Decision List</title>
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
            .action {
                background-color: black;
                border-radius: 5px;
            }
            .row {
                width: 100%;
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
    </head>
    <body>
        <!--Start Header-->
        <jsp:include page="/user/navigator/header.jsp"/>   
        <!--End Header-->
        <div class="section-tittle text-center">
            <h2 style="margin-top: 20px;">Decision List</h2>
        </div>
        <form action="decisionController" method="get">
            <div s="row" style="display: flex;">
                <div class="row" style="display: flex;">
                    <div style="margin: auto;">
                        <input type="text" name="inputSearch" class="btn btn-success" style="background-color: white; color: black; height: 30px; border: 2px solid #c86fff;">
                        <input type="submit" name="search_btn" class="btn" style="padding: 5px; letter-spacing: unset;" value="Search">
                    </div>
                </div>
                <div class="row" style=" display: flex;">
                    <div style="margin: auto; ">
                        <table>
                            <tr>
                                <td><select name="filterByStatus" >
                                        <option value="-1" ${filterByStatus eq -1 ? "selected" : ""}><b>Choose Status</b></option>
                                        <option value="1" ${filterByStatus eq 1 ? "selected" : ""}><b>Active</b></option>
                                        <option value="0" ${filterByStatus eq 0 ? "selected" : ""}><b>Dormant</b></option>
                                    </select></td>
                                <td><input type="submit" name="filter_btn" class="btn" style="padding: 5px; letter-spacing: unset;" value="Filter"></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </form>
        <div class="row"  style="justify-content: right">
            <a href="decisionAdd">
                <input class="btn" type="submit" value="Add Decision" name="add" style="background-color: green;">
            </a>
        </div>
        <c:if test="${decisionList.size() == 0}">
            <h2 style="color: red; text-align: center;">Cannot found any account!!!</h2>
            <div class="row" style="padding: 20px; display: flex;">
                <div style="margin: auto;">
                    <img src="assets/img/list.jpg" alt="alt" width="300px"/>
                </div>
            </div>
        </c:if>
        <c:if test="${decisionList.size() != 0}">
            <br><br>
            <div class="hint-text" style="margin-left: 20px; color: green;">Found <b>${count}</b> Decision(s)</div>
            <br>
            <div class="progress-table-wrap" style="margin: 0 10px;">
                <table class="table table-striped table-hover table-sortable" style="background-color: #f9f9ff">
                    <thead>
                        <tr>
                            <th>Decision ID</th>
                            <th>Decision No</th>
                            <th>Decision Name</th>
                            <th>Decision Date</th>
                            <th>Creator</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${decisionList}" var="d">
                            <tr>
                                <td>${d.getId()}</a></td>
                                <td><a href="decisionDetail?decisionId=${d.getId()}" style="color: blue;">${d.getDecision_no()}</a></td>
                                <td>${d.getDecision_name()}</td>
                                <td>${d.getDecision_date()}</td>
                                <td>${d.getCreator().getFullName()}</td>
                                <td>${d.isIs_active() ? "Active" : "Dormant"}</td>
                                <td>
                                    <a href="decisionDetail?decisionId=${d.getId()}">
                                        <i style="color: blue" class="fa fa-edit"></i>
                                    </a>
                                    <c:if test="${d.isIs_active()}">
                                        <a href="#confirmDeactiveModal${d.getId()}" data-toggle="modal">
                                            <i style="color: red" class="fa fa-ban"></i>
                                        </a>
                                    </c:if>
                                    <c:if test="${!d.isIs_active()}">
                                        <a href="#confirmActiveModal${d.getId()}" data-toggle="modal">
                                            <i style="color: green" class="fa fa-unlock"></i>
                                        </a>
                                    </c:if>
                                    <div class="modal fade " id="confirmDeactiveModal${d.getId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="confirmDeleteModalLabel">Deactivate Confirmation</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="text-dark modal-body">
                                                    Are you sure you want to deactivate this account?
                                                </div>
                                                <div class="modal-footer">
                                                    <a href="decisionController?action=active&is_active=0&id=${d.getId()}"><button type="submit" class="btn btn-danger">Deactivate</button></a>
                                                    <button type="button" style="margin-right: 5px" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal fade " id="confirmActiveModal${d.getId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="confirmDeleteModalLabel">Activate Confirmation</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="text-dark modal-body">
                                                    Are you sure you want to activate this account?
                                                </div>
                                                <div class="modal-footer">
                                                    <a href="decisionController?action=active&is_active=1&id=${d.getId()}"><button type="submit" class="btn btn-danger">Activate</button></a>
                                                    <button type="button" style="margin-right: 5px" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="clearfix" style="margin: 0 10px;">
                <ul class="pagination">
                    <c:forEach begin="1" end="${end}" var="i">
                        <li class="page-item"><a href="decisionController?index=${i}" class="page-link">${i}</a></li>
                        </c:forEach>
                </ul>
            </div>
        </c:if>

        <!--Start Footer-->
        <jsp:include page="/user/navigator/footer.jsp"/>   
        <!--End Footer-->
        <!-- Scroll Up -->
        <div id="back-top" >
            <a title="Go to Top" href="#"> <i class="fas fa-level-up-alt"></i></a>
        </div>
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
