<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/snarknews.css">
</head>
<body>
<table class="standings">
    <tbody><tr>
        <th class="stnd">Place</th>
        <th class="stnd">Team name</th>
        <c:forEach var="task" items="${tasks}">
            <th class="${task.acceptedTries > 0 ? 'success' : 'tried'}"
                title="${task.name}">
                <c:out value="${task.numLetter}"/>
                <br>
                <s><c:out value="${task.acceptedTries}/${task.totalTries}"/></s>
            </th>
        </c:forEach>
        <th class="stnd">Total</th><th class="stnd">Time</th>
    </tr>
    <c:set var="prev" value="-1"/>
    <c:set var="stand" value="0"/>
    <c:forEach var="team" items="${registrants}">
        <c:if test="${prev != team.tasksSolved}">
            <c:set var="prev" value="${team.tasksSolved}"/>
            <c:set var="stand" value="${stand == 0 ? 1 : 0}"/>
        </c:if>
        <tr class="${stand == 1 ? 'stand01' : 'stand00'}">
            <td class="stnd">
                <center><c:out value="${team.place}."/></center>
            </td>
            <td class="stnd">
                <c:out value="${team.fullTeamName}"/>
            </td>
            <c:set var="team_tasks" value="${teamTasks[team.team.id]}"/>
            <c:forEach var="task" items="${team_tasks}">
                <c:choose>
                    <c:when test="${task.taskStatus == 'FIRST_OK' || task.taskStatus == 'OK'}">
                        <c:set var="team_task_status"
                               value="+${task.attemptCount > 1 ? task.attemptCount - 1  : ''}"/>
                    </c:when>
                    <c:when test="${task.taskStatus == 'FAILED'}">
                        <c:set var="team_task_status"
                               value="-${task.attemptCount}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="team_task_status" value=""/>
                    </c:otherwise>
                </c:choose>
                <td class="${task.taskStatus == 'FIRST_OK' ? 'opener' : 'stnd'}">
                    <c:if test="${task.taskStatus != 'UNTRIED'}">
                        <center>
                            <c:out value="${team_task_status}"/><br>
                            <font size="1">
                                <fmt:formatNumber value="${(task.lastAttemptTime - task.lastAttemptTime
                                % 60) / 60}" maxFractionDigits="0" />:<fmt:formatNumber
                                    value="${task.lastAttemptTime % 60}" maxFractionDigits="0" />
                            </font>
                        </center>
                    </c:if>
                </td>
            </c:forEach>
            <td class="stnd"><c:out value="${team.tasksSolved}"/></td>
            <td class="stnd"><c:out value="${team.penalty}"/></td>
        </tr>
    </c:forEach>
    <tr>
        <td class="stnd"></td>
        <td class="stnd"><b>Submits sent:</b></td>
        <c:forEach var="task" items="${tasks}">
            <td class="stnd"><b><c:out value="${task.totalTries}"/></b></td>
        </c:forEach>
        <td class="stnd"></td><td class="stnd"></td>
    </tr>
    <tr>
        <td class="stnd"></td><td class="stnd"><b>Accepted:</b></td>
        <c:forEach var="task" items="${tasks}">
            <td class="stnd">
                <b><c:out value="${task.acceptedTries}"/></b>
                <br>(<fmt:formatNumber value="${task.acceptedTries / task.totalTries * 100}" maxFractionDigits="0" />%)
            </td>
        </c:forEach>
        <td class="stnd"></td><td class="stnd"></td>
    </tr>
    </tbody></table>
</body>
</html>
