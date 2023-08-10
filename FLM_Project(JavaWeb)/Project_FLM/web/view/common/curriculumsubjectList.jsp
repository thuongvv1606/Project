<%-- 
    Document   : subjectList
    Author     : DungNT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="util.Constants"%>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title></title>

        <!--toast-->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

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

        <!--toast-->
        <style>
            .custom-toast {
                top: 100px !important; /* ?i?u ch?nh kho?ng cch t? top */
            }
        </style><!-- comment -->

    </head>

    <body>

        <!--toast-->
        <% if (request.getAttribute("messSuccess") != null) {%>
        <script>
            $(document).ready(function () {
                // T?o toast thng bo v ty ch?nh mu s?c
                toastr.options = {
                    "positionClass": "toast-top-right",
                    "closeButton": true,
                    "progressBar": true,
                    "timeOut": 5000, // Th?i gian hi?n th? toast (tnh b?ng mili giy)
                    "extendedTimeOut": 2000, // Th?i gian hi?n th? ti?p t?c n?u ng??i dng di chu?t qua toast (tnh b?ng mili giy)
                    "tapToDismiss": false, // Ng?n ng??i dng t?t toast b?ng cch nh?p chu?t
                    "preventDuplicates": true, // Ng?n toast trng l?p
                    "progressBarColor": "#FF3366", // Mu c?a thanh ti?n trnh
                    "toastClass": "custom-toast" // L?p CSS ty ch?nh cho toast
                };

                // L?y thng bo t? ??i t??ng request
                var message = '<%= request.getAttribute("messSuccess")%>';

                // Hi?n th? toast thnh cng
                toastr.success(message);

            <%-- Xa thng bo trong ??i t??ng request ?? ??m b?o ch? hi?n th? m?t l?n --%>
            <% request.removeAttribute("messSuccess"); %>
            });
        </script>
        <% }%>



        <!-- ? Preloader Start -->
        <div id="preloader-active">
            <div class="preloader d-flex align-items-center justify-content-center">
                <div class="preloader-inner position-relative">
                    <div class="preloader-circle"></div>
                    <div class="preloader-img pere-text">
                        <img src="${pageContext.request.contextPath}/assets/img/logo/loder.png" alt="">
                    </div>
                </div>
            </div>
        </div>
        <!-- Preloader Start -->
        <jsp:include page="/user/navigator/header.jsp"/>  
        <jsp:include page="/view/navSubHeaderCurriculum.jsp"/> 
        <main>
            <div class="courses-area section-padding40 fix">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-xl-7 col-lg-8">
                            <div class="section-tittle text-center mb-55">
                                <h2>Curriculum Subject List</h2>
                            </div>
                        </div>

                    </div>

                    <div class="p-2 w-100 bd-highlight" style="display: flex">
                        <form class="row g-3" action="${pageContext.request.contextPath}${Constants.URL_CURRISUBJECTLIST}" method="get">
                            <input hidden name="idCurrent" value="${idCurrent}"/>
                            <div class="col-auto">
                                <select name="searchBy" >  
                                    <option ${searchBy == 1 ? 'selected':''}  value="1">Code</option>
                                    <option ${searchBy == 0 ? 'selected':''} value="0">Name</option>
                                </select>
                            </div>
                            <div class="col-auto">
                                <input type="text" value="${inputSearch}" class="form-control" id="inputSearch" name="inputSearch" placeholder="">
                            </div>
                            <div class="col-auto">
                                <button type="submit" class="btn btn-primary mb-3">Search</button>
                            </div>
                        </form>
                      
                    </div>
                    <div class="row">
                        <div class="col-sm-9 float-end">
                            <c:if test="${user.getRoleFunction() == 2 }">
                                <span class="input-group-btn">
                                    <a href="${pageContext.request.contextPath}${Constants.URL_ADDCURSUBJECT}?idCurrent=${idCurrent} " 
                                       class="btn btn-sm btn-success ">Add new curriculum subject </a>
                                </span> 
                            </c:if>
                        </div> 
                    </div>
                    <c:if test="${messSuccess.length() != 0}">
                        <h3 style="color: green" id="success">${messSuccess}</h3>
                    </c:if>
                    <c:if test="${messFail.length() != 0}">
                        <p style="color: red">${messFail}</p>
                    </c:if>
                    <c:if test="${error.length() != 0}">
                        <p style="color: red">${error}</p>
                    </c:if>
                    <div>
                        <table class="table table-striped" cellspacing="0" rules="all" border="1" id="gvSubs"
                               style="border-collapse:collapse;">
                            <tbody>
                                <tr class="bg-warning">
                                    <th class="text-white" scope="col">Subject Code</th>
                                    <th class="text-white" scope="col">Subject Name</th>
                                    <th class="text-white" scope="col">Semester</th>
                                    <th class="text-white" scope="col">NoCredit</th>
                                    <th class="text-white" scope="col">PreRequisite</th>
                                        <c:if test="${sessionScope.user.getRoleFunction() == 2 }">
                                        <th class="text-white" scope="col">Function</th>
                                        </c:if>
                                </tr>
                                <c:forEach var="iteam" items="${listSubjects}">
                                    <c:choose>
                                        <c:when test="${sessionScope.user.getRoles().get(0).getId() == 6  or sessionScope.user.getRoles().get(0).getId() == 7}">
                                            <tr > 
                                            </c:when>
                                            <c:otherwise>
                                            <tr data-bs-toggle="modal" data-bs-target="#myModal" id="sb" onclick="uploadFile(${iteam.id})">

                                            </c:otherwise>
                                        </c:choose>


                                        <td>${iteam.code}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}${Constants.URL_SUBJECTDETAIL}?subCode=${iteam.id}&curriculumID=${idCurrent}"
                                               class="text-dark"> ${iteam.name}</a></td>
                                        <td>${iteam.semester}</td>
                                        <td>${iteam.no_credit}</td>
                                        <td>${iteam.pre_condition}</td>
                                        <c:if test="${sessionScope.user.getRoleFunction() == 2 }">
                                            <td class="text-white" scope="col">

                                                <a href="${pageContext.request.contextPath}${Constants.URL_EDITSUBJECT}?id=${iteam.id}&curriculumID=${idCurrent}">
                                                    <i style="color:  blue" class="fa fa-edit"></i>
                                                    </button>
                                                </a>
                                            </td>
                                        </c:if>
                                    </tr> 
                                </c:forEach> 
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
            <div class="modal fade " id="confirmImportModal" tabindex="-1" role="dialog" aria-hidden="true" enctype="multipart/form-data">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="confirmDeleteModalLabel">Import Subject</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <!-- Modal body -->
                        <div class="text-dark modal-body">
                            <form action="${pageContext.request.contextPath}${Constants.URL_SUBJECTIMPORT }" enctype="multipart/form-data" method="POST" >
                                <div class="d-flex align-items-center">
                                    <a href="assets/excel/template-subject.xlsx" download style="text-decoration: none;color: #00a8ff">Click here to download template</a><br>
                                    <input type="file" name="file" id="file"/>
                                    <button type="submit" class="btn btn-primary mb-3" name="importBtn">Import</button>
                                </div>
                            </form>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="button" style="margin-right: 5px" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>


        </main>
        <jsp:include page="/user/navigator/footer.jsp"/>      
        <!-- Scroll Up -->
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
                                                const myTimeout = setTimeout(successMess, 3000);

                                                function successMess() {
                                                    document.getElementById("success").innerHTML = "";
                                                }
        </script>
    </body>
</html>
