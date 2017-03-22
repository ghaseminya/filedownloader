/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.javahosting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mghasemy
 */
public class db {

    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost:3306/filedownloader?useUnicode=true&characterEncoding=UTF-8";
    final String USER = "root";
    final String PASS = "XXXXX";
    Connection conn = null;
    Statement stmt = null;

    public db(String db) {

    }

    public db() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (Exception s) {
            s.printStackTrace();
        }
    }

    public db getCurrectConnection(HttpServletRequest request) {
        db d = (db) request.getSession().getAttribute("database");
        if (null == d) {
            d = new db();
            request.getSession().setAttribute("database", d);
        }
        return d;
    }
    String name, fpath;

    public String getfilepath(int id) {
        try {
            String sql = "select fpath,name from file where id=" + id + " ";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                this.fpath = resultSet.getString("fpath");
                this.name = resultSet.getString("name");
            }
            if (this.name.length() > 0) {
                sql = "update file set downloaded=downloaded+1 where id=" + id;
                stmt.executeUpdate(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fpath + "/" + name;
    }

    public List<file> getlist() {
        List<file> res = new ArrayList<file>();
        try {
            String sql = "select id,name from file ";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                file f = new file();
                f.id = resultSet.getString("id");
                f.name = resultSet.getString("name");
                res.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public class file {

        String name;
        String id;

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

    }
}
