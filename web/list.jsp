<%-- 
    Document   : list
    Created on : Mar 22, 2017, 11:29:37 PM
    Author     : mghasemy
--%>

<%@page import="java.util.List"%>
<%@page import="ir.javahosting.db"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Files list</title>
    </head>
    <body>
        <h1>List of File</h1>
        <table>
            <th>
            <td>Id</td>
            <td>File name</td>
            <td>+</td>
        </th>
        <tbody>
            <%
                db d = new db("database").getCurrectConnection(request);
                List<db.file> fils = d.getlist();
                for (db.file f : fils) {
                    String n = f.getName();
                    String i = f.getId();
            %>
            <tr>
                <td><%=i%></td>
                <td><%=n%></td>
                <td><a href="dl?fid=<%=i%>">Download</a></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
