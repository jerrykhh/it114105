<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lab05 Task3</title>
</head>

<body>
    <?php
    $servername = "localhost";
    $username = "root";
    $password = "";
    $db = "lab05";

    // Create connection
    $conn = new mysqli($servername, $username, $password, $db);

    // Check connection
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    $sql = "SELECT * FROM customers";
    $result = $conn->query($sql);
    ?>
    <table border="1">
        <tr>
            <th>Action</th>
            <th>Cust ID</th>
            <th>Cust Name</th>
            <th>Cust Pswd</th>
            <th>Cust Gender</th>
        </tr>
        <?php
        while ($row = $result->fetch_assoc()) {
            echo "<tr>";
            echo "<td><a href='Lab05_3a.php?custID=$row[custID]'>Update Record</a></td>";
            echo "<td>$row[custID]</td>";
            echo "<td>$row[custName]</td>";
            echo "<td>$row[custPswd]</td>";
            echo "<td>$row[custGender]</td>";
            echo "</tr>";
        }
        ?>
    </table>


    <?php

        $custId = '';
        $custName = '';
        $pwd = '';
        $gender = 'M';

        if(isset($_GET['custID'])){
            $hostname = "127.0.0.1";
            $username = "root";
            $pwd = "";
            $db = "lab05";
    
            $conn = new mysqli($hostname, $username, $pwd, $db);
            if($conn->connect_error){
                die("Connection Failed: ". $conn->connect_error);
            }

            $sql = "SELECT * FROM customers WHERE custID = ?";
            $stmnt = $conn->prepare($sql);
            $stmnt->bind_param("s", $_GET['custID']);
            $stmnt->execute();

            $result = $stmnt->get_result();
            $result = $result->fetch_assoc();

            $custId = $result["custID"];
            $custName = $result["custName"];
            $pwd = $result["custPswd"];
            $gender = $result["custGender"];
        }

    ?>
    <form action="Lab05_3b.php" method="post">
        <p>Customer ID <input type='text' name='custID' value='<?php echo $custId ?>' readonly /></p>
        <p>Customer Name <input type='text' name='custName' value='<?php echo $custName ?>' /></p>
        <p>Password <input type='password' name='custPwd' value='<?php echo $pwd ?>' /></p>
        <?php
            if($gender === 'M')
                echo "<p>Gender <input type='radio' name='gender' value='M' checked>Male <input type='radio' name='gender' value='F'>Female</p>";
            else
                echo "<p>Gender <input type='radio' name='gender' value='M'/>Male <input type='radio' name='gender' value='F' checked>Female</p>"
        ?>
        <p><input type="submit" value="Update Record"><input type="button" value="Cancel" onclick="window.location.href='Lab05_3a.php'"></p>
    </form>
</body>

</html>