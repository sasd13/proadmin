/*
 * To change this license header, choose License Headers in Report Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest;

import com.sasd13.javaex.ws.rest.MimeType;
import com.sasd13.javaex.ws.rest.MimeTypeParser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ws.ContentIO;
import ws.rest.persistence.ReportsPersistenceService;

/**
 *
 * @author Samir
 */
@WebServlet("/reports")
public class ReportsWebService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramsId = req.getParameter("id");
        
        String respData;
        if (paramsId != null) {
            respData = ReportsPersistenceService.read(paramsId);
        } else {
            respData = ReportsPersistenceService.readAll();
        }
        
        ContentIO.write(resp, respData);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String reqData = ContentIO.read(req);
    	
    	MimeType mimeType = MimeTypeParser.decode(req.getContentType());
    	
    	String respData = ReportsPersistenceService.create(reqData, mimeType);
        
        ContentIO.write(resp, respData);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String reqData = ContentIO.read(req);
    	
    	MimeType mimeType = MimeTypeParser.decode(req.getContentType());
    	
    	ReportsPersistenceService.update(reqData, mimeType);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String paramsId = req.getParameter("id");
        
        String respData;
        if (paramsId != null) {
            respData = ReportsPersistenceService.read(paramsId);
        } else {
            respData = ReportsPersistenceService.readAll();
        }
        
        ContentIO.write(resp, respData);
    }
}
