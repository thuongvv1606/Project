<%-- 
    Document   : curriculumList
    Created on : May 25, 2023, 9:26:03 PM
    Author     : TT
--%>
<%@page import="util.Constants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
        <title>Syllabus List</title>
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

            .toast{
                position: absolute;
                top: 25px;
                right: 30px;
                border-radius: 12px;
                background: #fff;
                padding: 20px 35px 20px 25px;
                box-shadow: 0 5px 10px rgba(0,0,0,0.1);
                border-left: 6px solid #4070f4;
                overflow: hidden;
                display: none;
                margin-top: 60px; 
                transition: all 0.5s cubic-bezier(0.68, -0.55, 0.265, 1.35);
            }

            .toast.active{
                transform: translateX(0%);
                display: unset;
            }

            .toast .toast-content{
                display: flex;
                align-items: center;
            }

            .toast-content .check{
                display: flex;
                align-items: center;
                justify-content: center;
                height: 35px;
                width: 35px;
                background-color: #4070f4;
                color: #fff;
                font-size: 20px;
                border-radius: 50%;
            }

            .toast-content .message{
                display: flex;
                flex-direction: column;
                margin: 0 20px;
            }

            .message .text{
                font-size: 20px;
                font-weight: 400;
                ;
                color: #666666;
            }

            .message .text.text-1{
                font-weight: 600;
                color: #333;
            }

            .toast .close{
                position: absolute;
                top: 10px;
                right: 15px;
                padding: 5px;
                cursor: pointer;
                opacity: 0.7;
            }

            .toast .close:hover{
                opacity: 1;
            }

            .toast .progress{
                position: absolute;
                bottom: 0;
                left: 0;
                height: 3px;
                width: 100%;
                background: #ddd;
            }

            .toast .progress:before{
                content: '';
                position: absolute;
                bottom: 0;
                right: 0;
                height: 100%;
                width: 100%;
                background-color: #4070f4;
            }

            .progress.active:before{
                animation: progress 5s linear forwards;
            }

            @keyframes progress {
                100%{
                    right: 100%;
                }
            }

            button{
                padding: 12px 20px;
                font-size: 20px;
                outline: none;
                border: none;
                background-color: #4070f4;
                color: #fff;
                border-radius: 6px;
                cursor: pointer;
                transition: 0.3s;
            }

            button:hover{
                background-color: #0e4bf1;
            }

            .toast.active ~ button{
                pointer-events: none;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/user/navigator/header.jsp"/>  
        <div class="section-tittle text-center">
            <h2 style="margin-top: 20px;">Syllabus List</h2>
        </div>
        <div class="container">
            <form action="syllabus" method="POST">
                <div class="row" style="display: flex;">
                    <div style="margin: auto;">
                        <input type="text" name="key" class="btn btn-success" style="background-color: white; color: black; height: 30px; border: 2px solid #c86fff;">
                        <input type="submit" name="search_btn" class="btn" style="padding: 5px; letter-spacing: unset;" value="Search">
                    </div>
                </div>
                <div class="row" style="padding: 20px 20px 0px 20px; display: flex;">
                    <div style="margin: auto;">
                        <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                            <table>
                                <tr>
                                    <td><select name="in_charge"">
                                            <option value="0" ${in_charge eq 0 ? "selected" : ""}>In charge</option>
                                            <c:forEach items="${owner}" var="o">
                                                <option value="${o.getUserId()}" ${o.getUserId() eq in_charge ? "selected" : ""}>${o.getUserName()}</option>
                                            </c:forEach>
                                        </select></td>
                                    <td><select name="filterByStatus">
                                            <option value="-1" ${filterByStatus eq -1 ? "selected" : ""}>Choose Status</option>
                                            <option value="1" ${filterByStatus eq 1 ? "selected" : ""}>Active</option>
                                            <option value="0" ${filterByStatus eq 0 ? "selected" : ""}>Dormant</option>
                                        </select></td>
                                    <td>
                                        <input type="submit" name="filter_btn" class="btn" style="padding: 5px; letter-spacing: unset;" value="Filter">
                                    </td>
                                </tr>
                            </table>
                        </c:if>
                    </div>
                </div>
            </form>
            <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                <div class="row">
                    <a href="#">
                        <input class="action btn" type="submit" value="Add Syllabus" name="add" style="margin-left: 30px;">
                    </a>
                </div>
            </c:if>
            <c:if test="${sList.size() == 0}">
                <h2 style="color: red; text-align: center;">Cannot found any syllabus!!!</h2>
                <div class="row" style="padding: 20px; display: flex;">
                    <div style="margin: auto;">
                        <img src="assets/img/list.jpg" alt="alt" width="300px"/>
                    </div>
                </div>
            </c:if>
            <c:if test="${sList.size() != 0}">
                <br>
                <div class="hint-text" style="margin-left: 20px; color: green;">Found <b>${count}</b> syllabus(s)</div>
                <br>
                <div class="progress-table-wrap" style="margin: 0 10px;">
                    <table class="table table-striped table-hover table-sortable" style="background-color: #f9f9ff">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Decision No.<br>(yyyy/MM/dd)</th>
                                <th>Subject</th>
                                    <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                                    <th>Status</th>
                                    <th>In charge</th>
                                    <th>Actions</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sList}" var="c">
                                <tr>
                                    <td>${c.getSyllabusId()}</td>
                                    <td>
                                        <a href="syllabus?action=detail&sylId=${c.getSyllabusId()}" style="color: blue;">
                                            ${c.getName()}
                                        </a>
                                    </td>
                                    <td>${c.getDescription()}</td>
                                    <td>${c.getDecision().getDecision_no()} (${c.getDecision().getDecision_date()})</td>
                                    <td>${c.getSubject().getCode()}</td>
                                    <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                                        <td>${c.getStatus() ? "Active" : "Dormant"}</td>
                                        <td>${c.getDesigner_id().getUserName()}</td>
                                        <td>
                                            <a href="#">
                                                <i style="color: blue" class="fa fa-edit"></i>
                                            </a>
                                            <c:if test="${c.getStatus()}">
                                                <a href="#confirmDeactiveModal${c.getSyllabusId()}" data-toggle="modal">
                                                    <i style="color: red" class="fa fa-ban"></i>
                                                </a>
                                            </c:if>
                                            <c:if test="${!c.getStatus()}">
                                                <a href="#confirmActiveModal${c.getSyllabusId()}" data-toggle="modal">
                                                    <i style="color: green" class="fa fa-unlock"></i>
                                                </a>
                                            </c:if>
                                        </td>
                                    </c:if>
                            <div class="modal fade " id="confirmDeactiveModal${c.getSyllabusId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="confirmDeleteModalLabel">Deactivate Confirmation</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="text-dark modal-body">
                                            Are you sure you want to deactivate this syllabus?
                                        </div>
                                        <div class="modal-footer">
                                            <a href="syllabus?action=deactive&sylId=${c.getSyllabusId()}"><button type="submit" class="btn btn-danger">Deactivate</button></a>
                                            <button type="button" style="margin-right: 5px" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal fade " id="confirmActiveModal${c.getSyllabusId()}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="confirmDeleteModalLabel">Activate Confirmation</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="text-dark modal-body">
                                            Are you sure you want to activate this syllabus?
                                        </div>
                                        <div class="modal-footer">
                                            <a href="syllabus?action=active&sylId=${c.getSyllabusId()}"><button type="submit" class="btn btn-danger">Activate</button></a>
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
                            <li class="page-item"><a href="syllabus?index=${i}" class="page-link">${i}</a></li>
                            </c:forEach>
                    </ul>
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
