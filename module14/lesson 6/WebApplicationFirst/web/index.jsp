

<%@page import="java.util.HashMap"%>
<%@page import="VoteAnalyzer.WorkTime"%>
<%@page import="VoteAnalyzer.Handler"%>
<%@page import="VoteAnalyzer.Loader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
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
        <%
            out.write("Voting station work times: ");
        for (Integer votingStation : worktimes.keySet()) {
            WorkTime workTime = worktimes.get(votingStation);
            out.write("\t" + votingStation + " - " + workTime);
        }
        %>
    </body>
</html>
