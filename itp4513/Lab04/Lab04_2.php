<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lab04 Task1</title>
</head>
<body>
    <?php
        $servername = "localhost";
        $username = "root";
        $password = "";
        $db = "lab04";
        
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
            <th>Cust ID</th>
            <th>Cust Name</th>
            <th>Cust Pswd</th>
            <th>Cust Gender</th>
        </tr>
        <?php
            while($row = $result->fetch_assoc()) {
                echo "<tr>";
                echo "<td>$row[custID]</td>";
                echo "<td><input type='text' value='$row[custName]'</td>";
                echo "<td><input type='text' value='$row[custPswd]'</td>";
                echo "<td>";
                if($row["custGender"] == 'M'){
                    echo "<input type='radio' name='custGender-$row[custID]' value='M' checked/> Male";
                    echo "<input type='radio' name='custGender-$row[custID]' value='F'/> Female";
                }else{
                    echo "<input type='radio' name='custGender-$row[custID]' value='M'/> Male";
                    echo "<input type='radio' name='custGender-$row[custID]' value='F' checked/> Female";
                }
                echo "</td>";
                echo "</tr>";
            }
        ?>
    </table>
</body>
</html>