
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : Home
    Created on : Feb 15, 2023, 8:45:35 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="CSS\index.css"/>
        <style>
            .right_header :hover{
                color: white;
                background: greenyellow;
            }
            .right_body_menu :hover {
                background: #99FFFF;
            }
            .right_foot :hover{
                color: white;
                background:  greenyellow;
            }
            .box{
                border: 1px solid black;
                height: 50px;
                width: 150px;
                background: brown;
                margin: 2px 7px;
                color: orange;
                text-align: center;

            }
            .box_menu_1{
                border: 1px solid black;
                height: 70px;
                width: 200px;
                background: navajowhite;
                margin: 5px 5px;
                text-align: center;
            }

        </style>
    </head>
    <body>

        <!--        //<form action="login" method="POST">-->
        <div class="header">
            <img src="images\logo.png" alt="alt"/>
        </div>
        <div class="body">
            <div class="left">

                <div class="left_header" >
                    <b style="color: red; margin-right: 150px">TW5</b>
                    <b style="color: blue; text-align: right" id="dateTime"></b><br>
                    <c:if test="${sessionScope.acc != null}">
                        <b style="margin-right: 200px">Staff: ${sessionScope.acc.getName()}</b>
                    </c:if>
                    <c:if test="${sessionScope != null}">
                        <b>${sessionScope.mang}</b>
                        
                    </c:if>

                </div>

                <div class="left_body">
                    <jsp:include page="Order.jsp"></jsp:include>
                    </div>
                </div>

                <div class="right">
                    <div class="right_header" style="background: #99FFFF;display: flex; flex-wrap: wrap">

                        <div class="box" style="text-align: center; ${tag == null?"background:greenyellow":""}">
                        <a href="home" 
                           style="text-decoration: none;">
                            <p style="color: orange">Bán Chạy</p></a>
                    </div> 

                    <c:forEach items="${listC}" var="o" >
                        <div class="box" style="text-align: center; ${tag == o.getCategoryID()?"background:greenyellow":""}">
                            <a href="category?CategoryID=${o.getCategoryID()}" 
                               style="text-decoration: none;">
                                <p style="color: orange">${o.getCategoryName()}</p></a>
                        </div>
                    </c:forEach>


                    <form action="search" method="POST">
                        <div style="text-align: center; margin: 10px auto; background: none">
                            <input style="width: 250px ; height: 30px" type="text" name="key" value="${keys}">
                            <input style="height: 30px"type="submit" value="Search" name="search" />
                        </div>
                    </form>
                </div>

                <div class="right_body">
                    <div class="right_body_left" style="background: #167bff">
                        <div class="right_body_left_box1">
                            <p style="font-size: 15px; text-align: center"></p>
                        </div>
                        <div class="right_body_left_box1">
                            <p style="font-size: 15px"></p>
                        </div>
                        <div class="right_body_left_box">
                            <p></p>
                        </div>
                        <div class="right_body_left_box1">
                            <p></p>
                        </div>
                        <div class="right_body_left_box1">
                            <p></p>
                        </div>
                        <div class="right_body_left_box">
                            <p></p>
                        </div>
                        <div class="right_body_left_box1">

                        </div>
                    </div>
                    <div class="right_body_menu" style="display: flex; flex-wrap: wrap;overflow: hidden">

                        <c:forEach items="${listP}" var="o">
                            <c:if test="${o.isDiscontinued() == true }">
                                <div class="box_menu_1 ">
                                    <p style="margin-bottom: 12px"><a href="buy?productID=${o.getProductID()}" style="text-decoration: none;">${o.getProductName()}</a></p>
                                    <div style="display: flex; text-align: center">
                                        <p style="margin-right: 100px; margin-top: 0px; margin-bottom: 0px">${o.getProductID()}</p>
                                        <p style="text-align: right; margin-top: 0px; margin-bottom: 0px">${o.getUnitPrice()}00</p>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${o.isDiscontinued() != true }">
                                <div class="box_menu_1 " style="background: red">
                                    <p style="margin-bottom: 12px">${o.getProductName()}</a></p>
                                    <div style="display: flex; text-align: center">
                                        <p style="margin-right: 100px; margin-top: 0px; margin-bottom: 0px">${o.getProductID()}</p>
                                        <p style="text-align: right; margin-top: 0px; margin-bottom: 0px">${o.getUnitPrice()}00</p>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>

                    </div> 
                    <div class="right_body_right" style="background: #167bff">

                    </div>
                </div>
                <div class="right_foot" style="background: #99FFFF">
                    <jsp:include page="RightFoot.jsp"></jsp:include>
                </div>
            </div>
        </div>
        <!--        </form>-->
    </body>
    <script>
        var today = new Date();
        //        var date  = (today.getMonth()+1) + " " + today.getDate() +" " + today.getDay();
        //        var time = today.getHours()+":" + today.getMinutes() +":" + today.getSeconds();
        //        var dateTime = date + " " + time;
        // Gán vào thẻ HTML
        document.getElementById("dateTime").innerHTML = today.toUTCString();
    </script>
</html>
