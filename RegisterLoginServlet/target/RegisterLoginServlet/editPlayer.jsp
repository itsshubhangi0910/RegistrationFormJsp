<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Player" %>
<jsp:include page="master.html"/>
<!DOCTYPE html>
<html>
<head>
    <title>Update Player</title>
    </head>

</head>
<body>
<div class="form-container">
    <form action="editPlayer" method="post">
        <% Player player = (Player) request.getAttribute("player"); %>
        <input type="hidden" name="id" value="<%= (player != null ? player.getId() : "") %>"/>
        <label for="playerName">Player Name:</label>
        <input type="text" id="playerName" name="playerName" class="text" value="<%= (player != null ? player.getPlayerName() : "") %>" required><br>
        <label for="gender">Gender:</label>
        <input type="text" id="gender" name="gender" class="text" value="<%= (player != null ? player.getGender() : "") %>" required><br>
        <label for="fatherName">Father Name:</label>
        <input type="text" id="fatherName" name="fatherName" class="text" value="<%= (player != null ? player.getFatherName() : "") %>" required><br>
        <label for="mobileNo">Mobile No:</label>
        <input type="text" id="mobileNo" name="mobileNo" class="text" value="<%= (player != null ? player.getMobileNo() : "") %>" required><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" class="text-e" value="<%= (player != null ? player.getEmail() : "") %>" required><br>
        <label for="playerAge">Player Age:</label>
        <input type="text" id="playerAge" name="playerAge" class="text" value="<%= (player != null ? player.getPlayerAge() : "") %>" required><br>
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" class="text" value="<%= (player != null ? player.getAddress() : "") %>" required><br>
        <label for="batchTime">Batch Time:</label>
        <input type="text" id="batchTime" name="batchTime" class="text" value="<%= (player != null ? player.getBatchTime() : "") %>" required><br>
        <label for="howSoon">How Soon:</label>
        <input type="text" id="howSoon" name="howSoon" class="text" value="<%= (player != null ? player.getHowSoon() : "") %>" required><br>
        <input type="submit" value="Update Player"/>
    </form>

    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
            String messageType = (String) request.getAttribute("messageType");
    %>
    <script>
        var message = "<%= message %>";
        var messageType = "<%= messageType %>";
        swal("Very Nice!", message, messageType);
    </script>
    <%
        }
    %>
</div>
</body>
<a href="viewRecords">View all players</a>
</html>
