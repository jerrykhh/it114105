<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lab03 Task3</title>
</head>

<body>
    <?php
    extract($_POST);
    $isVaild = true;
    $errMes = "";

    if (!isset($firstName) || strlen(trim($firstName)) == 0) {
        $errMes .= "first name is invalid! ";
        $isVaild = false;
    }

    if ($isVaild && (!isset($lastName) || strlen(trim($lastName)) == 0)) {
        $errMes .= "last name is invalid!";
        $isVaild = false;
    }

    if ($isVaild && (!isset($yearOfBirth) || strlen(trim($yearOfBirth)) == 0)) {
        $errMes .= "year of birth is invalid!";
        $isVaild = false;
    }

    if (!$isVaild) {
        echo "<h3>Hello, $errMes</h3>";
        echo "<p>Please re-enter the information</p>";
        echo '<p><a href="Lab03_Task3.html">Go back to form</a></p>';
    } else {
        $age = date('Y') - $yearOfBirth;
        if ($age >= 18){
            echo "<h3>Welcome! $firstName $lastName</h3>";
			echo "<h3>You are now $age years old, so you can fill this form.</h3>";
        }else{
            echo "<h3>Sorry: Your age is: $age.</h3>";
			echo "<h3>You should be over 18 to fill this form</h3>";
        }
    }
    ?>
</body>

</html>