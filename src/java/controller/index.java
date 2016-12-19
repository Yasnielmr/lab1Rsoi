/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author admin
 */
public class index extends HttpServlet {
 
    private  String code = "";
    private static final long serialVersionUID = 1L;
	
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
       // String code = "";
         response.setContentType("text/html;charset=UTF-8");
        try{
        code= request.getParameter("code");
        if(code==null || code.equals("")){
			throw new RuntimeException(
			"ERROR: Didn't get code parameter in callback.");
		}
        	FBConnection fbConnection = new FBConnection();
		String accessToken = fbConnection.getAccessToken(code);
                FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
                Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
                ServletOutputStream out = response.getOutputStream(); 
                
                request.setAttribute("Datos del Usuario", fbProfileData);
                RequestDispatcher reqDisp = request.getRequestDispatcher("../callback.jsp");
                reqDisp.forward(request, response);
                out.println("<h1>Facebook Login using Java</h1>");
		out.println("<h2>Application Main Menu</h2>");
		out.println("<div>Welcome "+fbProfileData.get("first_name"));
		out.println("<div>Your Email: "+fbProfileData.get("email"));
		out.println("<div>You are "+fbProfileData.get("gender"));
                response.sendRedirect("../callback.jsp");
        }
        catch(IOException | RuntimeException | ServletException e)
        {
            throw new RuntimeException("ERROR mostrando los datos " + e);
        }
        
                //response.sendRedirect("../callback.jsp?first_name="+out.println("<div>Welcome "+fbProfileData.get("first_name")));
                 //response.sendRedirect("../callback.jsp;charset=UTF-8");
            
                
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
        
        HttpSession httpSession = request.getSession();
        String faceCode = request.getParameter("code");
        String state = request.getParameter("state");
        String sessionID = httpSession.getId();
        if (state.equals(sessionID)){
            try {
                
            } catch (Exception e) {
                e.printStackTrace();
                
            }
            response.sendRedirect(request.getContextPath() +"/callback.jsp");
        } else {
            System.err.println("CSRF protection validation");
        }
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
