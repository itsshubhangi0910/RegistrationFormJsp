<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enquiry Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        form {
            max-width: 600px;
            margin: 0 auto;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input[type="text"], input[type="tel"], textarea, select {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
        }
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h2>Enquiry Form</h2>
<form action="submitEnquiry" method="post">
    <label for="playerName">Player Name:</label>
    <input type="text" id="playerName" name="playerName" required>

    <label>Gender:</label>
    <input type="radio" id="male" name="gender" value="Male" required>
    <label for="male">Male</label>
    <input type="radio" id="female" name="gender" value="Female" required>
    <label for="female">Female</label>

    <label for="fatherName">Father Name:</label>
    <input type="text" id="fatherName" name="fatherName" required>

    <label for="mobileNo">Mobile No:</label>
    <input type="tel" id="mobileNo" name="mobileNo" required>

    <label for="playerAge">Player Age:</label>
    <select id="playerAge" name="playerAge" required>
        <option value="" disabled selected>Select Age</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <!-- Add more options as needed -->
    </select>

    <label for="address">Address:</label>
    <textarea id="address" name="address" rows="4" required></textarea>

    <label for="batchTime">Select Batch Time:</label>
    <select id="batchTime" name="batchTime" required>
        <option value="" disabled selected>Select Batch Time</option>
        <option value="Morning">Morning</option>
        <option value="Afternoon">Afternoon</option>
        <option value="Evening">Evening</option>
    </select>

    <label for="howSoon">How Soon You Want To Start:</label>
    <select id="howSoon" name="howSoon" required>
        <option value="" disabled selected>Select Time</option>
        <option value="Immediately">Immediately</option>
        <option value="In a Week">In a Week</option>
        <option value="In a Month">In a Month</option>
    </select>

    <input type="submit" value="Submit">
</form>
</body>
</html>
