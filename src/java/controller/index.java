/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author admin
 */
public class index extends HttpServlet {
private  String code = "AQDh4IfjVJYl1vIzvadO5Z0Tra16qrUfz2XoVvfgspF7GoxMN8zCf1xcK8Yp-rgpWM_y8et4ke9XjQTIP9Fd5Hn-HNvJIiWhzTgSJimJZVldCrZIlBfEPgKSzVnC4jBMrSi9f3j_U_LFl80rDYZvVARL63qoRxq_k2JFIDrk9ndg8ItupsdiLHE4r8krXl4QA7DdBJx_6vMqcxKxeVcLUBWhKm85lq44-6J6FRkxWIelrVkeffqeQcJ6R5KepsFVMZVSd4NmCoXLVZ1of5Yo2eFTQWsYoKwgIgOiMqbtlcUgkMPPD-A9OTha0y6awkKWbl7j99l31e27RB44CsDm9yoc#_=_";
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
                response.sendRedirect("../callback.jsp?graph"+graph);

        response.setContentType("text/html;charset=UTF-8");
        out.println("<h1>Facebook Login using Java</h1>");
		out.println("<h2>Application Main Menu</h2>");
		out.println("<div>Welcome "+fbProfileData.get("first_name"));
		out.println("<div>Your Email: "+fbProfileData.get("email"));
		out.println("<div>You are "+fbProfileData.get("gender"));
                
                
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
