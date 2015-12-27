<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : schedule
    Created on : Nov 19, 2015
    Author     : Allen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Class Schedule </title>
    </head>
    
        <!--  Use Bean calls StudentInfo class which calls the constructor -->
        <jsp:useBean id="StudentInfo" scope="session" class="Project.StudentInfo" />
        
        <!-- set property calls the set methods and sets the variables to the 
             values entered in the previous index.jsp page -->
        <jsp:setProperty name="StudentInfo" property="studentName" />
        <jsp:setProperty name="StudentInfo" property="studentID" />
        
        <h1 align="center"> Displaying Class Schedule For  </h1>
        <h2 align = "center" ><big><jsp:getProperty name="StudentInfo" property="studentName" /></big>  </h2> 
        <h4 align = "center"> All information is required to add class.  Only course name is needed to delete class. </h4>
        <form action =" schedule.jsp" >
        <table border="1" align="center">
                <thead> 
                    <tr>
                        <th colspan="10"> 
                            Enter Class Information To Add Class
                        </th>                      
                    </tr>
                </thead>
                <tbody>
                    
                        <td> Course </td>
                        
                        <td> <input type="text" name="classCourse" />
                             <jsp:setProperty name="StudentInfo" property="classCourse" /> 
                             
                        </td>
                        
                        <td> CRN </td>
                        <td> <input type="text" name="classCRN"  />
                             <jsp:setProperty name="StudentInfo" property="classCRN" />
                             
                        </td>
                        
                        <td> Days </td>
                        <td> <input type="text" name="classDays" /> 
                             <jsp:setProperty name="StudentInfo" property="classDays" />
                             
                        </td>
                        
                        <td> Time </td>
                        <td> <input type="text" name="classTimes" /> 
                             <jsp:setProperty name="StudentInfo" property="classTimes" />
                             
                        </td>
                        
                         <!-- Only calls storeInfo method if the Add Class button is clicked -->
                         
                        <td> <% 
                                   if(request.getParameter("Add_Class")!=null)
                                   {
                                       StudentInfo.storeInfo();
                                   }
                              %>
                              <input type = "submit" id = "Add_Class" name = "Add_Class" value="Add Class"/>
                                    
                         
                        </td>
                        
                        <!-- Only calls deleteInfo method if the Delete Class button is clicked -->
                        
                        <td> <% 
                                   if(request.getParameter("Delete_Class")!=null)
                                   {
                                       StudentInfo.deleteInfo();
                                   }
                              %>
                       <input type="submit" id= "Delete_Class" name="Delete_Class" value="Delete Class" />
                           
                        </td>                                              
                   
                    </tbody>
            </table>
                     
            <!-- display info method is called everytime page is loaded -->           
            <table align = "center" >
                <h2 align = "center"> Currently Enrolled In: </h2>
                 <td>                               
                     <b><big> ${StudentInfo.displayInfo()} </big></b>                              
                 </td>
            </table> 
                 
            
                      
                     
                      
                     
           
                        
                      
                      
                      
                     
                       