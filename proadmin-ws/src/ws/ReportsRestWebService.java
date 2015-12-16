/*
 * To change this license header, choose License Headers in Report Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.db.DAO;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ws.db.JDBCDAO;

/**
 *
 * @author Samir
 */
@WebServlet("/reports")
public class ReportsRestWebService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramsId = req.getParameter("id");
        
        Gson gson = new GsonBuilder().create();
        String jsonData;
        
        DAO dao = JDBCDAO.getInstance();
        
        dao.open();
        
        if (paramsId != null) {
            long id = Long.valueOf(paramsId);
            Report report = dao.selectReport(id);
            
            jsonData = gson.toJson(report);
        } else {
            List<Report> reports = dao.selectAllReports();
            
            jsonData = gson.toJson(reports);
        }
        
        dao.close();
        
        resp.getWriter().write(jsonData);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonReport = req.getParameter("report");
        
        Gson gson = new GsonBuilder().create();
        Report report = gson.fromJson(jsonReport, Report.class);
        
        DAO dao = JDBCDAO.getInstance();
        
        dao.open();        
        long id = dao.insertReport(report);
        dao.close();
        
        resp.getWriter().write(gson.toJson(id));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonReport = req.getParameter("report");
        
        Gson gson = new GsonBuilder().create();
        Report report = gson.fromJson(jsonReport, Report.class);
        
        DAO dao = JDBCDAO.getInstance();
        
        dao.open();
        dao.updateReport(report);
        dao.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramsId = req.getParameter("id");
        
        DAO dao = JDBCDAO.getInstance();
        
        dao.open();
        
        if (paramsId != null) {
            long id = Long.valueOf(paramsId);
            dao.deleteReport(id);
        } else {
            List<Report> reports = dao.selectAllReports();
            for (Report report : reports) {
                dao.deleteReport(report.getId());
            }
        }
        
        dao.close();
    }
}
