
/********************************
 *                              *
 *   @ Author: Allen Pagan      *
 *   321 Project - due 12/10/15 *
 *   Started Nov 19 2015        *
 *   Last edited - 12/9/15      *
 *                              *
 ********************************/


 /**********************************************************************************
 *                                                                                 *
 *   Project to create a database backed webpage application.                      *
 *   Application is a class schedule application which enables                     *
 *   users to keep track of classes which are stored in the database               *
 *                                                                                 *
 * This class holds the java methods that are called from the schedule.jsp webpage *
 *                                                                                 *
 ***********************************************************************************/
package Project;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class StudentInfo {
  
    private  String studentName;
    private static  String studentID;
    private static String classCourse;
    private static String classCRN;
    private static String classDays;
    private static String classTimes;
    private static final String dbURL = "jdbc:derby://localhost:1527/ProjectDB;create=true;user=DBUSER; password=ics321";
    private static final String tableName = "CLASSINFO";
    private static Connection conn = null;
    private static Statement stmt = null;
    private static PreparedStatement prepStmt = null;
    private static ResultSet resultSet = null;
    private static StringBuilder schedule = new StringBuilder();  
     
    /************************************************
     *                                              *
     *      Class Constructor                       *
     *  Calls create table method when              *
     *  User goes from index.jsp to schedule.jsp    *
     *                                              *
     ************************************************/
    
    public StudentInfo() 
    {           
       // try
       // {
            createTable();
        //}
        //catch(SQLException except)
        //{ System.out.println("in constructor catch");
            //System.out.println(except);
        //}    
    }
    
    /****************************
     *                          *
     * @return the studentName  *
     *                          *
     ****************************/
    
    public String getStudentName() {
        return studentName;
    }

    /****************************
     *                          *
     * sets the studentName  *
     *                          *
     ****************************/
    
    public void setStudentName(String inStudentName) {
        this.studentName = inStudentName;
        
    }

    /****************************
     *                          *
     * @return the studentID    *
     *                          *
     ****************************/
    
    public String getStudentID() {
        return studentID;
    }

    /****************************
     *                          *
     * sets the studentID       *
     *                          *
     ****************************/
    
    public void setStudentID(String inStudentID) {
        StudentInfo.studentID = inStudentID;
      
    }

    /******************************
     *                            *
     * @return the classCourse    *
     *                            *
     ******************************/
    
    public String getClassCourse() {
        return classCourse;
    }

    /******************************
     *                            *
     * sets the classCourse       *
     *                            *
     ******************************/
    
    public void setClassCourse(String inClassCourse) {
        StudentInfo.classCourse = inClassCourse;
       
    }

    /******************************
     *                            *
     * @return the classCRN       *
     *                            *
     ******************************/
    
    public String getClassCRN() {
        return classCRN;
    }

    /******************************
     *                            *
     * sets the classCRN          *
     *                            *
     ******************************/
    
    public void setClassCRN(String classCRN) {
        StudentInfo.classCRN = classCRN;
        
    }

    /******************************
     *                            *
     * @return the classDays      *
     *                            *
     ******************************/
    
    public String getClassDays() {
        return classDays;
    }

    /***************************
     *                         *
     * sets the classDays      *
     *                         *
     ***************************/
    
    public void setClassDays(String classDays) {
        StudentInfo.classDays = classDays;
        
    }

    /******************************
     *                            *
     * @return the classTimes     *
     *                            *
     ******************************/
    
    public String getClassTimes() {       
        return classTimes;
    }

    /***************************
     *                         *
     * sets the classTimes     *
     *                         *
     ***************************/
    
    public void setClassTimes(String classTimes) {
        StudentInfo.classTimes = classTimes;
       
    }
       
    /********************************************
     *                                           *
     *          Store Info                       *
     * Method gets student and class             *
     * information, if any of the class          *
     * information is missing (null) then info   *
     * isn't stored.  After storing information  * 
     * calls the reset variables method          *
     *                                           *
     *********************************************/
    
    public void storeInfo()
    {
        getStudentName();
        getStudentID();
        getClassCourse();
        getClassCRN();
        getClassDays();
        getClassTimes(); 
        
        if(classCourse == null || classCRN == null || classDays == null || classTimes == null)
        {           
            return;
        }
        else
        {                 
            try
            {
 
                String insertStatement = "insert into CLASSINFO VALUES (?, ?, ?, ?, ?, ?) ";             
                prepStmt = conn.prepareStatement(insertStatement);
                prepStmt.setString(1, studentName);
                prepStmt.setString(2, studentID );
                prepStmt.setString(3, classCourse);
                prepStmt.setString(4, classCRN);   
                prepStmt.setString(5, classDays);
                prepStmt.setString(6, classTimes);
                        
                prepStmt.execute();
                
                      
            }
        
            catch (Exception except)
            {    
                //System.out.println(except);
                System.out.println("Error inserting, class information");
            }
            
        }
        
        resetVariables();      
        
        return;
    }
    
    /**********************
     *                    *
     *   Resets Variables *
     *                    *
     **********************/
    
    public void resetVariables()
    {
        
        classCourse = null;
        classCRN = null;
        classDays = null;
        classTimes = null;
        
       
    }
    
    /************************************************
     *                                              *
     *           Get Connection                     *
     * Establishes connection with the database     *
     * and is called from the create table method   *
     * because there needs to be a connection to    *
     * the database before the table can be created *
     *                                              *
     ************************************************/
    
    public void getConnection()
    {
        
        
        if(conn == null)
        {
            try
            {
                Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                conn = DriverManager.getConnection(dbURL);
            }
            catch(Exception except)
            {  
                System.out.println("Connection didnt work");
                //System.out.println(except);
            }
        }
        
    }
    
    /******************************************
     *                                        *
     *          Display Info                  *
     * Method is called from schedule.jsp     *
     * before page is displayed to user       *
     * so that the classes stored in the      *
     * database will be displayed on the page.*
     *                                        *
     ******************************************/
    
    public String displayInfo()
    {  
      // Calls create table verify that there is a table to get info from.  
        /*try
        { 
           createTable();
        }
        catch(Exception except)
        {
            System.out.println(except);
        } */
        
        // Clearing out the string builder
        schedule.delete(0,schedule.length());
                  
        try
        {     
            stmt = conn.createStatement();              
            resultSet = stmt.executeQuery("select COURSE, CRN, DAYS, TIME from CLASSINFO where STUDENTID = '" + studentID + "'" );
            
            while(resultSet.next())
            {               
                classCourse = resultSet.getString("COURSE");                    
                schedule.append(classCourse + " ");
                classCRN = resultSet.getString("CRN");                  
                schedule.append(classCRN + " ");
                classDays = resultSet.getString("DAYS");                  
                schedule.append(classDays + " ");
                classTimes = resultSet.getString("TIME");                  
                schedule.append(classTimes + " ");                  
                schedule.append("</br>");
                
                // Building up the string, once each value is got its added on to the end of the string.
                // </br>  is the newline character for html
            }
              
        }
        
        catch (Exception except)
        {   
            System.out.println("Error getting, class schedule");
            System.out.println(except);
        } 
        
        resetVariables(); 
        return schedule.toString();
    } 
    
    /***********************************
     *                                 *
     *          Delete Info            *
     * Delete the class info based     *
     * on the name of the class course *
     *                                 *
     ***********************************/
    
    public void deleteInfo()
    {       
        getStudentName();
        getStudentID();
        getClassCourse();
        
        // Cant delete the class if you dont know which class to delete 
        if(classCourse == null )
        {
            return;
        }
        else
        {
            try 
            {
                stmt = conn.createStatement(); 
                stmt.executeUpdate("DELETE FROM CLASSINFO WHERE STUDENTID = '" + studentID + "' and STUDENTNAME = '" + studentName +"' and COURSE = '" + classCourse +"'");
            } 
            catch (Exception except) 
            {    
                System.out.println("Error deleting, class information");
                System.out.println(except);
            }
        }
    }
    
    /*************************************************
     *                                               *
     *              Create Table                     *
     * Creates table after connection is established *                          *
     *                                               *
     *************************************************/
    
    public void createTable()
    {
      try
      {
         getConnection();
         String sqlCreate = "CREATE TABLE " + "CLASSINFO"
                          + " (STUDENTNAME   VARCHAR(20),"
                          + "  STUDENTID     VARCHAR(10),"
                          + "  COURSE        VARCHAR(15),"
                          + "  CRN           VARCHAR(10),"
                          + "  DAYS          VARCHAR(30),"
                          + "  TIME          VARCHAR(15))";
        
         Statement stmt = conn.createStatement();
         stmt.execute(sqlCreate);
      }
      catch(SQLException except)
      {
           System.out.println("Table was not created because table alread exists.");
      }    
      
    }
    
}
