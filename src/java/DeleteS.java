/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 *
 * @author MNI
 */
public class DeleteS extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs;
        String user = "root";
        String pass = "parola11";
        
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");

                try{
                
                Class.forName("com.mysql.jdbc.Driver");
                //1.Get a connection to database
                myConn = DriverManager.getConnection("jdbc:mysql://localhost/phonebook",user,pass);
                //2.Create a statement
                myStmt = myConn.createStatement();
                //3.Execute SQL query
                String sql = " ";
                if( !nume.equals("")&&!prenume.equals("")){
                    sql = "DELETE FROM contact WHERE nume='"+nume+"'AND prenume='"+prenume+"'"; 
                } else if( nume.equals("")){
                    sql = "DELETE FROM contact WHERE prenume='"+prenume+"'"; 
                } else if( prenume.equals("")){
                    sql = "DELETE FROM contact WHERE nume='"+nume+"'"; 
                }
               
                myStmt.executeUpdate(sql);
                //4.Process the result set
        
            try (PrintWriter out = response.getWriter()) {
            
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet SearchS</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<center>");
                out.println("<h2>Stergerea datelor s-a realizat cu succes !</h2?");
                out.println("</center>");
                out.println("</body>");
                out.println("</html>");
            } // end PrintWriter try
            
        }catch(SQLException | ClassNotFoundException se){
        //Handle errors for JDBC
        }
        //Handle errors for Class.forName
        finally{
         //finally block used to close resources
         try{
            if(myStmt!=null)
               myStmt.close();
         }catch(SQLException se2){
         }// nothing we can do
         try{
            if(myConn!=null)
            myConn.close();
         }catch(SQLException se){
         }//end finally try
      } //end try
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
