/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import app.feedback.FeedbackDetailDTO;
import app.response.ResponseDAO;
import app.response.ResponseDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ShowFeedbackResponeDetailForManagerController", urlPatterns = {"/ShowFeedbackResponeDetailForManagerController"})
public class ShowFeedbackResponeDetailForManagerController extends HttpServlet {

    private static final String ERROR = "##";
    private static final String SUCCESS = "adminPage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            ResponseDAO dao = new ResponseDAO();
            String feedbackID = (String) request.getParameter("feedback_response_id");
            String styleList = request.getParameter("style_list");
            if (feedbackID == null) {
                feedbackID = (String) request.getAttribute("FEEDBACK_RESPONE_ID");
            }
            List<ResponseDTO> dto = dao.showListResponeDetail(feedbackID);
            session.setAttribute("RESPONE_DETAIL_LIST", dto);
            request.setAttribute("RESPONE_ACTIVE", feedbackID);
            request.setAttribute("STYLE_PIPE", "active");
            request.setAttribute("STYLE_LIST_ALL", "active");
            if (styleList != null) {
                request.setAttribute("STYLE_COMMENT", "active");
            }else {
                request.setAttribute("STYLE_TASK", "active");
            }
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at ShowFeedbackResponeDetailForManagerController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

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
