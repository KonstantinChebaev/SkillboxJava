<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="VoteAnalyzer.DBConnection"%>
<%@page import="java.util.HashMap"%>
<%@page import="VoteAnalyzer.WorkTime"%>
<%@page import="VoteAnalyzer.Handler"%>
<%@page import="VoteAnalyzer.Loader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
     Class.forName("com.mysql.jdbc.Driver");
     String fileName = "D:/Materials/netBeans/WebApplicationFirst/res/data-0.2M.xml";
     Handler handler = Loader.parseFile(fileName);
     HashMap <Integer, WorkTime> worktimes = handler.getWorkTimes();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>О, привет</p>
        <p>Voting station work times: </p>
        <table><tr><td>№</td><td>day</td><td>time</td><td>day</td><td>time</td><td>day</td><td>time</td></tr>
        <%
        for (Integer votingStation : worktimes.keySet()) {
            WorkTime workTime = worktimes.get(votingStation);
            String line = workTime.toString();
            line = line.replaceAll(" ", "</td><td>");
            out.write("<tr><td>" + votingStation + "</td><td>"+line+"</td></tr>");            
        }
        %>
        </table>
        <p>Voters</p>
        <table>
            <tr><td>Name</td><td>BirthDate</td><td>Count<td></tr>
        <%  
        try {
            ResultSet rs = DBConnection.printVoterCounts();
            while (rs.next()) {
            out.write("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
        %>
        </table>
    </body>
</html>
