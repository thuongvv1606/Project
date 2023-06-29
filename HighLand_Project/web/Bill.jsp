<%-- 
    Document   : Bill
    Created on : Mar 14, 2023, 7:12:03 PM
    Author     : hp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body{
                color: blue;
            }
        </style>
    </head>
    <body>
        <div style="height: auto; width: 350px; margin: 0 auto; border: 1px solid black; padding: 10px 10px">
            <p>HIGHLANDS COFEE</p>
            <b>ĐẠI HỌC FPT UNIVERSITY</b><BR>
            <b>TEL(+84) 8816 0699</b>
            <br>
            <h1 style="text-align: center">* Receipt *</h1>
            <p style="color: blue; text-align:left" id="dateTime"></p>
            <b>Staff: </b>${staff.getName()}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <c:if test="${sessionScope != null}">
                <b>${sessionScope.mang}</b>
            </c:if><br>
            ----------------------------------------------------------
            <br>
            <c:set value="${sessionScope.cart}" var="o"/>
            <c:forEach items="${o.items}" var="i">
                <table>
                    <tr>
                        <td style="width: 250px">${i.getProduct().getProductName()}</td>
                        <td style="width: 20px">${i.getQuantity()}</td>
                        <td style="width: 30px">${i.getPrice()}</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
            </c:forEach>
            <table>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td style="width: 260px">Sub-Total</td>
                    <td></td>
                    <td style="width: 30px">${o.getTotalMoney()}$</td>
                </tr>
            </table>
                <table>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td style="width: 260px">VAT</td>
                    <td></td>
                    <td style="width: 30px">${o.getTotalMoney()*0.02}$</td>
                </tr>
            </table>
            <table>
                <tr>
                    <td style="width: 250px"></td>
                    <td></td>
                    <td>----------</td>
                </tr>
            </table>
            <table>
                <tr>
                    <td style="width: 220px"><h1>Total</h1></td>
                    <td></td>
                    <td style="width: 20px"><h1 style="margin: 0px 0px">${o.getTotalMoney()+(o.getTotalMoney()*0.02)}$</h1></td>
                </tr>
            </table>
            <p style="text-align: center">----------------------------------</p>
            <a href="home" style="text-decoration: none"><p style="text-align: center">Thank you!</p>
                <p style="text-align: center">And See You Again!</p></a>
        </div> 
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
