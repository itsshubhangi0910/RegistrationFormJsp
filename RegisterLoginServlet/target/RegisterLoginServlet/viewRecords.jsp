<%@ page import="Model.Player" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Records</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1>Player Records</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Player Name</th>
            <th>Gender</th>
            <th>Father Name</th>
            <th>Mobile No</th>
            <th>Email</th>
            <th>Player Age</th>
            <th>Address</th>
            <th>Batch Time</th>
            <th>How Soon</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Player> playerList = (List<Player>) request.getAttribute("playerss");
            if (playerList!= null) {
                for (Player player : playerList) {
        %>
        <tr>
            <td><%= player.getId() %></td>
            <td><%= player.getPlayerName() %></td>
            <td><%= player.getGender() %></td>
            <td><%= player.getFatherName() %></td>
            <td><%= player.getMobileNo() %></td>
            <td><%= player.getEmail() %></td>
            <td><%= player.getPlayerAge() %></td>
            <td><%= player.getAddress() %></td>
            <td><%= player.getBatchTime() %></td>
            <td><%= player.getHowSoon() %></td>

            <td>
                <a href="editPlayer.jsp?action=edit&id=<%= player.getId() %>">Edit</a>
                <a href="deletePlayer.jsp?action=delete&id=<%= player.getId() %>" onclick="return confirm('Are you sure you want to delete this player?');">Delete</a>
            </td>

        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="9">No records found.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"></script>
</body>
</html>
