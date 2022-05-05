<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lab04 Task 3</title>
</head>

<body>
    <form method="post">
        <h1>Add a new customer</h1>
        <p>Customer ID <input type="text" name="custID" value="" /></p>
        <p>Customer Name <input type="text" name="custName" value=""></p>
        <p>Password <input type="password" name="custPwd" value="" /></p>
        <p>Gender <input type="radio" name="gender" value="M" checked>Male <input type="radio" name="gender" value="F">Female</p>
        <p><input type="submit" value="Submit"><input type="reset" value="Reset"></p>
    </form>
    <?php

    if ($_SERVER["REQUEST_METHOD"] == "POST") {

        extract($_POST);
        $hostname = "localhost";
        $username = "root";
        $pwd = "";
        $db = "lab04";

        $conn = new mysqli($hostname, $username, $pwd, $db);
        if ($conn->connect_error) {
            die("Connection Failed: " . $conn->connect_error);
        }

        $sql = "SELECT COUNT(*) AS total FROM customers WHERE custID = ?";
        $stmnt = $conn->prepare($sql);
        $stmnt->bind_param('s', $custID);
        $stmnt->execute();

        $result = $stmnt->get_result();
        $result = $result->fetch_assoc();
        if($result['total'] > 0){
            echo "<h1>Record already exist!</h1>";
            $conn->close();
        }else{
            $sql = "INSERT INTO customers VALUES(?,?,?,?)";
            $stmnt = $conn->prepare($sql);
            $stmnt->bind_param('ssss', $custID, $custName, $custPwd, $gender);
            $stmnt->execute();
            
            if($stmnt->affected_rows == 1){
                echo "<h1>A record is added successfully</h1>";
            }else{
                echo "Unknow Error";
            }

        }
    }
    ?>
</body>

</html>