<%-- 
    Document   : index
    Created on : Nov 19, 2015
    Author     : Allen
--%>

<%@page import="Project.StudentInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> 321 Project</title>
    </head>
    <body>
        <!--  Form (table) takes user to schedule.jsp on button click of "Enter" button -->
        <h1 align = "center"> Enter Name & Student ID To View Class Schedule </h1>
        <form action="schedule.jsp" method="POST" 
        <table border="1" align = "center">
            <thead>
                <tr>
                   <th> Name </th>
                   <th> <input type="text" name="studentName" /> </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                   <td> ID # </td>
                   <td> <input type="password" name="studentID" /> </td>
                </tr>
                <tr>
                    <td colspan="2"> <input type="submit" value="Enter" /></td>               
                </tr>
            </tbody>
        </table>
    </body>
</html>
