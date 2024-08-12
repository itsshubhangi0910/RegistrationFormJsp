package dao;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

@WebServlet("/player")
public class SubmitEnquiryServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/players";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "oms123";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String playerName = request.getParameter("playerName");
        String gender = request.getParameter("gender");
        String fatherName = request.getParameter("fatherName");
        String mobileNo = request.getParameter("mobileNo");
        String email = request.getParameter("email");
        String playerAge = request.getParameter("playerAge");
        String address = request.getParameter("address");
        String batchTime = request.getParameter("batchTime");
        String howSoon = request.getParameter("howSoon");

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a database connection
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Prepare SQL query
            String sql = "INSERT INTO player (playerName, gender, fatherName, mobileNo, email, playerAge, address, batchTime, howSoon) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, playerName);
            statement.setString(2, gender);
            statement.setString(3, fatherName);
            statement.setString(4, mobileNo);
            statement.setString(5, email);
            statement.setString(6, playerAge);
            statement.setString(7, address);
            statement.setString(8, batchTime);
            statement.setString(9, howSoon);

            // Execute the update
            statement.executeUpdate();

            // Send email
            sendEmail(playerName, email);

            // Redirect to success page
            response.sendRedirect("success.jsp");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
            response.sendRedirect("error.jsp");
        } finally {
            // Close resources
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle resource closure exceptions
            }
        }
    }

    private void sendEmail(String playerName, String email) {
        // Email configurations
        String fromEmail = "shubhangi.omsoftware1@gmail.com"; // sender email
        String fromEmailPassword = "yuxc qdif betv jizt"; // sender email password
        String host = "smtp.gmail.com"; // SMTP server

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587"); // SMTP port

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, fromEmailPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Enquiry Submission Confirmation");
            message.setText("Dear " + playerName + ",\n\nThank you for your enquiry. We will get back to you soon.\n\nBest Regards,\nYour Team");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
