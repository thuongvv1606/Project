<%-- 
    Document   : ListOrderDay
    Created on : Mar 17, 2023, 2:41:14 PM
    Author     : hp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="dayBill" method="POST">
            OrderDate:<input type="Date" name="date" value="${day}">
            <input type="submit" name="search" value="Search">
            <div class="alert alert-primary" role="alert">
                <b style="color: red">${mess}</b> 
            </div>
            
        </form>
        <c:if test="${listCart != null && listO != null && listO.size() != 0}">
            <c:forEach begin="0" end="${listO.size()-1}" step="1" var="b">
                <div style="height: auto; width: 350px; margin: 0 auto; border: 1px solid black; padding: 10px 10px">
                    <p>HIGHLANDS COFEE</p>
                    <b>ĐẠI HỌC FPT UNIVERSITY</b><BR>
                    <b>TEL(+84) 8816 0699</b>
                    <br>
                    <h1 style="text-align: center">* Receipt *</h1>
                    <p style="color: blue; text-align:left" id="dateTime"></p>
                    <b>OrderID:${listO.get(b).getOrderID()}</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b>Date:${listO.get(b).getOrderDate()}</b>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                    ----------------------------------------------------------

                    <br>

                    <c:set value="${listCart.get(b)}" var="o"/>
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
                            <td style="width: 30px">${o.getTotalMoney()}00$</td>
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
                            <td style="width: 30px">${o.getTotalMoney()*0.02}0$</td>
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
                            <td style="width: 20px"><h1 style="margin: 0px 0px">${o.getTotalMoney() + (o.getTotalMoney()*0.02)}0$</h1></td>
                        </tr>
                    </table>
                    <p style="text-align: center">----------------------------------</p>
                    <a href="home" style="text-decoration: none"><p style="text-align: center">Thank you!</p>
                        <p style="text-align: center">And See You Again!</p></a>
                </div>
            </c:forEach>
        </c:if>
    </body>
</html>
