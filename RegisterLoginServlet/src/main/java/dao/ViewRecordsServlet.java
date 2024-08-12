package dao;

import Model.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/viewRecords")
public class ViewRecordsServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/players";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "oms123";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Player> playerList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a database connection
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Prepare SQL query
            String sql = "SELECT * FROM player";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
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
                playerList.add(p);
            }

            // Set the list of players as a request attribute
            request.setAttribute("playerss", playerList);

            // Forward to JSP page
            request.getRequestDispatcher("viewRecords.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
            response.sendRedirect("error.jsp");
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

