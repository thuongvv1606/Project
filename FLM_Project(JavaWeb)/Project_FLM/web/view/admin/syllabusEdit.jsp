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
        <title>Syllabus Edit</title>
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
            <h2 style="margin-top: 20px;">Edit Syllabus</h2>
        </div>
        <div class="container">
            <div class="row">
                <!-- Your Profile Views Chart -->
                <div class="col-lg-12 m-b30">
                    <div class="widget-box">
                        <div class="widget-inner">
                            <form class="edit-profile m-b30" action="syllabus" method="POST">
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="sylId" value="${syllabus.getSyllabusId()}">
                                <div class="row">
                                    <p style="color: red;">${messFail}</p>
                                    <div class="form-group col-4">
                                        <label class="col-form-label">Name<i style="color: red">*</i></label>
                                        <div>
                                            <input class="form-control" name="name" type="text" placeholder="Enter syllabus name" value="${name == null ? syllabus.getName() : name}" required=""
                                                   pattern="^.{1,100}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Can only enter maximum 100 charactes.' : '')">
                                        </div>
                                    </div>
                                    <div class="form-group col-4">
                                        <label class="col-form-label">English Name</label>
                                        <div>
                                            <input class="form-control" name="engname" type="text" placeholder="Enter syllabus english name" value="${engname == null ? syllabus.getEnglish_name() : engname}"
                                                   pattern="^.{1,100}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Can only enter maximum 100 charactes.' : '')">
                                        </div>
                                    </div>
                                    <div class="form-group col-4">
                                        <label class="col-form-label">Degree Level</label>
                                        <div>
                                            <select name="degree" class="form-control">
                                                <option value="Bachelor" ${syllabus.getDegree_level() == "Bachelor" ? "selected" : ""}>Bachelor</option>
                                                <option value="Master" ${syllabus.getDegree_level() == "Master" ? "selected" : ""}>Master</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group col-4">
                                        <label class="col-form-label">No Credit</label>
                                        <div>
                                            <input class="form-control" name="credit" type="number" value="${credit == null ? syllabus.getCredit() : credit}" min="1">
                                        </div>
                                    </div>
                                    <div class="form-group col-4">
                                        <label class="col-form-label">Scoring Scale</label>
                                        <div>
                                            <input class="form-control" name="score" type="number" value="${score == null ? syllabus.getScore() : score}" min="0" max="10">
                                        </div>
                                    </div>
                                    <div class="form-group col-4">
                                        <label class="col-form-label">Min Mark to Pass</label>
                                        <div>
                                            <input class="form-control" name="mark" type="number" value="${mark == null ? syllabus.getMin_mark() : mark}" min="0" max="10">
                                        </div>
                                    </div>
                                    <div class="form-group col-4">
                                        <label class="col-form-label">Is Active</label>
                                        <div>
                                            <input name="status" type="radio" value="1" ${status == null ? (syllabus.getStatus() ? "checked" : "") : (status == 1 ? "checked" : "")}><label class="small mb-1">True</label>
                                            <input name="status" type="radio" value="0" ${status == null ? (!syllabus.getStatus() ? "checked" : "") : (status == 0 ? "checked" : "")}><label class="small mb-1">False</label>
                                        </div>
                                        <label class="col-form-label">Time Allocation</label>
                                        <div>
                                            <textarea name="time" class="form-control" rows="3" pattern="^.{0,150}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Can only enter maximum 150 charactes.' : '')">${time == null ? syllabus.getTime() : time}</textarea>
                                        </div>
                                        <label class="col-form-label">Tool</label>
                                        <div>
                                            <textarea name="tool" class="form-control" rows="3" pattern="^.{0,150}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Can only enter maximum 150 charactes.' : '')">${tool == null ? syllabus.getTool() : tool}</textarea>
                                        </div>
                                    </div>
                                    <div class="form-group col-4">
                                        <label class="col-form-label">Student Task</label>
                                        <div>
                                            <textarea name="task" class="form-control" rows="4" pattern="^.{0,150}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Can only enter maximum 150 charactes.' : '')">${task == null ? syllabus.getStudent_task() : task}</textarea>
                                        </div>
                                        <label class="col-form-label">Note</label>
                                        <div>
                                            <textarea name="note" class="form-control" rows="5" pattern="^.{0,150}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Can only enter maximum 150 charactes.' : '')">${note == null ? syllabus.getNote() : note}</textarea>
                                        </div>
                                    </div>
                                    <div class="form-group col-4">
                                        <label class="col-form-label">Description</label>
                                        <div>
                                            <textarea name="des" class="form-control" rows="11" pattern="^.{0,300}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Can only enter maximum 300 charactes.' : '')">${des == null ? syllabus.getDescription() : des}</textarea>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="text-end">
                                            <button type="submit" name="save" class="btn btn-primary" style="margin-bottom: 5px;">Save changes</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="text-end">
                                <a href="syllabus">
                                    <button class="btn btn-primary" type="submit" style="margin-bottom: 20px;">Back</button> 
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/user/navigator/footer.jsp"/>      
        <!-- Scroll Up -->
        <div id="back-top" >
            <a title="Go to Top" href="#"> <i class="fas fa-level-up-alt"></i></a>
        </div>
    </body>
</html>
