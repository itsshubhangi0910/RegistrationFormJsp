package dao;

import Model.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/editPlayer")
public class EditPlayerServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/players"; // Ensure database name is correct
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "oms123";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String playerName = request.getParameter("playerName");
        String gender = request.getParameter("gender");
        String fatherName = request.getParameter("fatherName");
        String mobileNo = request.getParameter("mobileNo");
        String email = request.getParameter("email");
        String playerAge = request.getParameter("playerAge");
        String address = request.getParameter("address");
        String batchTime = request.getParameter("batchTime");
        String howSoon = request.getParameter("howSoon");


        if (playerName == null || gender == null || fatherName == null || mobileNo == null || email == null
                || playerAge == null || address == null || batchTime == null || howSoon == null ||
                playerName.isEmpty() || gender.isEmpty() || gender.isEmpty() || fatherName.isEmpty() || mobileNo.isEmpty() || email.isEmpty()
                || playerAge.isEmpty() || address.isEmpty() || batchTime.isEmpty() || howSoon.isEmpty()) {
            request.setAttribute("errorMessage", "Invalid player ID.");
            request.getRequestDispatcher("/editPlayer.jsp").forward(request, response);
            return;
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM player WHERE id = ?")) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Create Player object and set its properties
                    Player p = new Player();
                    p.setId(resultSet.getInt("id"));
                    p.setPlayerName(resultSet.getString("playerName"));
                    p.setGender(resultSet.getString("gender"));
                    p.setFatherName(resultSet.getString("fatherName"));
                    p.setMobileNo(resultSet.getString("mobileNo"));
                    p.setEmail(resultSet.getString("email"));
                    p.setPlayerAge(resultSet.getString("playerAge"));
                    p.setAddress(resultSet.getString("address"));
                    p.setBatchTime(resultSet.getString("batchTime"));
                    p.setHowSoon(resultSet.getString("howSoon"));

                    // Debugging: Print the player object
                    System.out.println("Player object: " + p);

                    // Set player object as request attribute and forward to edit form
                    request.setAttribute("player", p);
                    request.getRequestDispatcher("/editPlayer.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Player not found.");
                    request.getRequestDispatcher("/editPlayer.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/editPlayer.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String playerName = request.getParameter("playerName");
        String gender = request.getParameter("gender");
        String fatherName = request.getParameter("fatherName");
        String mobileNo = request.getParameter("mobileNo");
        String email = request.getParameter("email");
        String playerAge = request.getParameter("playerAge");
        String address = request.getParameter("address");
        String batchTime = request.getParameter("batchTime");
        String howSoon = request.getParameter("howSoon");

        // Validate ID
        if (playerName == null || gender == null || fatherName == null || mobileNo == null || email == null
                || playerAge == null || address == null || batchTime == null || howSoon == null ||
                playerName.isEmpty() || gender.isEmpty() || gender.isEmpty() || fatherName.isEmpty() || mobileNo.isEmpty() || email.isEmpty()
                || playerAge.isEmpty() || address.isEmpty() || batchTime.isEmpty() || howSoon.isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("/editPlayer.jsp").forward(request, response);
            return;
        }

        String sql = "UPDATE player SET playerName = ?, gender = ?, fatherName = ?, mobileNo = ?, email = ?, playerAge = ?, address = ?, batchTime = ?, howSoon = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, playerName);
            statement.setString(2, gender);
            statement.setString(3, fatherName);
            statement.setString(4, mobileNo);
            statement.setString(5, email);
            statement.setString(6, playerAge);
            statement.setString(7, address);
            statement.setString(8, batchTime);
            statement.setString(9, howSoon);
            statement.setInt(10, id);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                response.sendRedirect("player");
            } else {
                request.setAttribute("errorMessage", "Failed to update player.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            handleError(request, response, "Error updating user", e);

        }
    }
    private void handleError(HttpServletRequest request, HttpServletResponse response, String message, Exception e) throws ServletException, IOException {
        // Log the exception (you might want to use a logging framework here)
        e.printStackTrace();
        // Forward to an error page with a user-friendly message
        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}
