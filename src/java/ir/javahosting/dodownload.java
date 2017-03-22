/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.javahosting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mohammadghasemy
 */
public class dodownload extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        db d = new db("database").getCurrectConnection(request);
        int fid = 0;

        try {
            fid = Integer.parseInt(request.getParameter("fid"));
        } catch (Exception s) {
            getServletContext().getRequestDispatcher("/notread.jsp").forward(
                        request, response);
                
            return;
        }

        String fp = d.getfilepath(fid);
        try {
            File file = new File("/home/mghasemy/" + fp);
            if (!file.canRead()) {
                getServletContext().getRequestDispatcher("/notread.jsp").forward(
                        request, response);
                return;
            }
            response.setContentType("application/octet-stream");
            response.setContentLength((int) file.length());
            response.setHeader("Content-Disposition",
                    String.format("attachment; filename=\"%s\"", file.getName()));
            OutputStream out = response.getOutputStream();
            FileInputStream in = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.flush();
            getServletContext().getRequestDispatcher("/downloading.jsp").forward(
                    request, response);
            
        } catch (Exception s) {
            out.flush();
            getServletContext().getRequestDispatcher("/notread.jsp").forward(
                    request, response);
            return;
        }
    }
}
