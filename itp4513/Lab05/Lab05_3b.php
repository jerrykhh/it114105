<?php

    extract($_POST);
    $hostname = "127.0.0.1";
    $username = "root";
    $pwd = "";
    $db = "lab05";

    $conn = new mysqli($hostname, $username, $pwd, $db);
    if($conn->connect_error){
        die("Connection Failed: ". $conn->connect_error);
    }
    echo $custPwd;
    $sql = "UPDATE customers SET custName = ?, custPswd = ?, custGender = ? WHERE custID = ?";
    $stmnt = $conn->prepare($sql);
    $stmnt->bind_param("ssss", $custName, $custPwd, $gender, $custID);
    $stmnt->execute();

    if($stmnt->affected_rows > 0){
        header("Location: Lab05_3a.php");
    }


?>