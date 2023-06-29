<%-- 
    Document   : cloList
    Created on : Jun 17, 2023, 1:42:15 PM
    Author     : NgTienDung
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
        <title>Clo List</title>
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
            .btn
        </style>
    </head>
    <body>
        <jsp:include page="/user/navigator/header.jsp"/>
        <main >
            <section class="slider-area slider-area2">
                <div class="slider-active">
                    <!-- Single Slider -->
                    <div style="height:60px; " >   
                        <jsp:include page="/user/navigator/navSubHeaderSyllabus.jsp"/> 
                    </div>
                </div>
            </section> 
            <div class="courses-area  fix">
                <div class="container">
                    <div class="section-tittle text-center">
                        <h2 style="margin-top: 20px;">Clo List</h2>
                    </div>

                    <form action="${pageContext.request.contextPath}${Constants.URL_CLOLIST}" method="get" >
                        <div class="row" style="text-align: center">
                            <div style="margin: auto;">
                                <input hidden type="text" name ="sylId" value="${syllabus.syllabusId}" />
                                <input type="text" name="nameSearch" value="${nameSearch}" class="btn btn-success" style="background-color: white; color: black; height: 30px; border: 2px solid #c86fff;">
                                <input type="submit" name="search_btn" class="btn btn-primary" style="padding: 5px; " value="Search">
                            </div>
                        </div>
                        <br/>
                        <div class="row ">
                            <div style="text-align: center">
                                <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                                    <table style="display: inline">
                                        <tr> 
                                            <td>

                                                <select name="filterByStatus">
                                                    <option value="-1" ${filterByStatus eq -1 ? "selected" : ""} >All Status</option>
                                                    <option value="1" ${filterByStatus eq 1 ? "selected" : ""}>Active</option>
                                                    <option value="0" ${filterByStatus eq 0 ? "selected" : ""}>Dormant</option>
                                                </select>
                                            </td>
                                            <td>
                                                <input type="submit" name="filter_btn" class="btn btn-primary" style="margin-left: 5px;padding: 8px; letter-spacing: unset;" value="Filter">
                                            </td>
                                        </tr>
                                    </table>
                                </c:if>
                            </div>

                        </div>

                    </form>
                    <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                        <div class="">
                            <a href="${pageContext.request.contextPath}${Constants.URL_ADDCLO}?sylId=${syllabus.syllabusId}">
                                <input class="action btn  btn-primary" type="submit" value="Add Clo" name="add" style="margin-left: 30px;">
                            </a>
                            <input class="btn btn-sm btn-success " type="button" id="importButton" value="Import File">
                            <form id="fExcel" action="${pageContext.request.contextPath}${Constants.URL_UPLOADEXCELCLO}" method="post" enctype="multipart/form-data">
                                <input hidden type="file" id="file" name="file" accept=".xlsx, .xls">
                                <input type="text" hidden value="${syllabus.syllabusId}" name="sylId" />
                            </form>

                            <script>
                                const importButton = document.getElementById('importButton');
                                const fileInput = document.querySelector('#fExcel input[type="file"]');
                                const form = document.getElementById('fExcel');

                                importButton.addEventListener('click', () => {
                                    fileInput.click();
                                });
                                fileInput.addEventListener('change', () => {
                                    form.submit();
                                });
                            </script>
                        </div>





                        <c:if test="${listClosFail != null}">
                            <div class="modal fade  in show " id="confirmDeactiveModal0" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" >
                                <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="confirmDeleteModalLabel">Confirmation</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">

                                            </button>
                                        </div>

                                        <c:choose>
                                            <c:when test="${checkSheet}">

                                                <div class="text-dark modal-body">
                                                    <p class="text-danger">You need have Sheet CloList in file excel</p>
                                                </div>
                                                <div class="modal-footer">
                                                    <input class="btn btn-sm btn-success " type="button" id="importButton2222" value="Import File Again">
                                                    <form id="fExcel" action="${pageContext.request.contextPath}${Constants.URL_UPLOADEXCELCLO}" method="post" enctype="multipart/form-data">
                                                        <input hidden type="file" id="file" name="file" accept=".xlsx, .xls">
                                                        <input type="text" hidden value="${syllabus.syllabusId}" name="sylId" />
                                                    </form>

                                                    <script>
                                                        const importButton22 = document.getElementById('importButton2222');
                                                        importButton22.addEventListener('click', () => {
                                                            fileInput.click();
                                                        });
                                                    </script>

                                                    <a href="${pageContext.request.contextPath}${Constants.URL_CLOLIST}?sylId=${syllabus.syllabusId}"><button type="button" class="btn btn-danger">Cancle</button></a>

                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="text-dark modal-body">
                                                    <p class="text-success" >${countOk} Clo add success</p>
                                                    <p class="text-danger"> ${countFaillFormat} wrong format</p>
                                                    <p class="text-warning">${listClosFail.size()} Duplicated ( List Duplicated )</p>
                                                    <c:if test="${listClosFail.size() != 0}">
                                                         <table class="table table-striped table-hover table-sortable" style="background-color: #f9f9ff">
                                                        <thead  class="bg-warning">
                                                            <tr>
                                                                <th>#</th>
                                                                <th>Name</th>
                                                                <th>Description</th> 
                                                                <th>Syllabus</th> 
                                                                    <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                                                                    <th>Status</th>

                                                                </c:if>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${listClosFail}" var="item" varStatus="loop">
                                                                <tr>
                                                                    <td>${loop.index+1}</td>
                                                                    <td>
                                                                        ${item.cloName}
                                                                    </td>
                                                                    <td>${item.cloDescription}</td>
                                                                    <td>${item.syllabus.name}</td>

                                                                    <td>${item.status ? "Active" : "Dormant"}</td>


                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                    </c:if>
                                                   
                                                </div>
                                                <div class="modal-footer">
                                                    <input class="btn btn-sm btn-success " type="button" id="importButton2222" value="Import File Again">
                                                    <form id="fExcel" action="${pageContext.request.contextPath}${Constants.URL_UPLOADEXCELCLO}" method="post" enctype="multipart/form-data">
                                                        <input hidden type="file" id="file" name="file" accept=".xlsx, .xls">
                                                        <input type="text" hidden value="${syllabus.syllabusId}" name="sylId" />
                                                    </form>

                                                    <script>
                                                        const importButton22 = document.getElementById('importButton2222');
                                                        importButton22.addEventListener('click', () => {
                                                            fileInput.click();
                                                        });
                                                    </script>

                                                    <a href="${pageContext.request.contextPath}${Constants.URL_CLOLIST}?sylId=${syllabus.syllabusId}"><button type="button" class="btn btn-danger">Cancle</button></a>

                                                </div>
                                            </c:otherwise>
                                        </c:choose>



                                    </div>
                                </div>
                            </div>
                        </c:if>  

                    </c:if>
                    <c:if test="${listClo.size() == 0}">
                        <h2 style="color: red; text-align: center;">Cannot found any clo of clo!!!</h2>
                        <div class="row" style="padding: 20px; display: flex;">
                            <div style="margin: auto;">
                                <img src="assets/img/list.jpg" alt="alt" width="300px"/>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${listClo.size() != 0}">
                        <br>
                        <div class="hint-text" style="margin-left: 20px; color: green;">Found <b>${listClo.size()}</b> clo(s)</div>
                        <br>
                        <div class="progress-table-wrap" style="margin: 0 10px;">
                            <table class="table table-striped table-hover table-sortable" style="background-color: #f9f9ff">
                                <thead  class="bg-warning">
                                    <tr>
                                        <th>#</th>
                                        <th>Name</th>
                                        <th>Description</th> 
                                            <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                                            <th>Status</th>
                                            <th>Actions</th>
                                            </c:if>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listClo}" var="item" varStatus="loop">
                                        <tr>
                                            <td>${loop.index+1}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}${Constants.URL_DETAILCLO}?sylId=${syllabus.syllabusId}&cloId=${item.cloId}" style="color: blue;">
                                                    ${item.cloName}
                                                </a>
                                            </td>
                                            <td>${item.cloDescription}</td>

                                            <c:if test="${sessionScope.user.getRoleFunction() == 2}">
                                                <td>${item.status ? "Active" : "Dormant"}</td>
                                                <td>
                                                    <a href="${pageContext.request.contextPath}${Constants.URL_EDITCLO}?sylId=${syllabus.syllabusId}&cloId=${item.cloId}">
                                                        <i style="color: blue" class="fa fa-edit"></i>
                                                    </a>
                                                    <c:if test="${item.status}">
                                                        <a href="#confirmDeactiveModal${loop.index}" data-toggle="modal">
                                                            <i style="color: red" class="fa fa-ban"></i>
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${!item.status}">
                                                        <a href="#confirmActiveModal${loop.index}" data-toggle="modal">
                                                            <i style="color: green" class="fa fa-unlock"></i>
                                                        </a>
                                                    </c:if>
                                                </td>
                                            </c:if>
                                    <div class="modal fade " id="confirmDeactiveModal${loop.index}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="confirmDeleteModalLabel">Deactivate Confirmation</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="text-dark modal-body">
                                                    Are you sure you want to deactivate this clo?
                                                </div>
                                                <div class="modal-footer">
                                                    <a href="${pageContext.request.contextPath}${Constants.URL_UPDATEACTIVECLO}?sylId=${syllabus.syllabusId}&cloId=${item.cloId}&active=0&index=${index}&nameSearch=${nameSearch}&filterByStatus=${filterByStatus}"><button type="submit" class="btn btn-danger">Deactivate</button></a>
                                                    <button type="button" style="margin-right: 5px" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal fade " id="confirmActiveModal${loop.index}" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="confirmDeleteModalLabel">Activate Confirmation</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="text-dark modal-body">
                                                    Are you sure you want to activate this clo?
                                                </div>
                                                <div class="modal-footer">
                                                    <a href="${pageContext.request.contextPath}${Constants.URL_UPDATEACTIVECLO}?sylId=${syllabus.syllabusId}&cloId=${item.cloId}&active=1&index=${index}&nameSearch=${nameSearch}&filterByStatus=${filterByStatus}"><button type="submit" class="btn btn-danger">Activate</button></a>
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
                                    <li class="page-item"><a href="${pageContext.request.contextPath}${Constants.URL_CLOLIST}?sylId=${syllabus.syllabusId}&index=${i}&nameSearch=${nameSearch}&filterByStatus=${filterByStatus}" class="page-link">${i}</a></li>
                                    </c:forEach>
                            </ul>
                        </div>
                    </c:if> 
                </div>
            </div>
        </main>



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
