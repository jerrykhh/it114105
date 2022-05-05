<?php
    if(isset($_GET['custID'])){

        $hostname = "127.0.0.1";
        $username = "root";
        $pwd = "";
        $db = "lab05";

        $conn = new mysqli($hostname, $username, $pwd,$db);
        if ($conn->connect_error){
            die("Connection Failed" . $conn->connect_error);
        }

        $sql = "DELETE FROM customers WHERE custID = ?";
        $stmnt = $conn->prepare($sql);
        $stmnt->bind_param("s", $_GET['custID']);
        $stmnt->execute();

        header('Location: lab05_2a.php');
    }
?>