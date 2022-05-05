<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lab02 Task3</title>
</head>
<body>
    <?php
        $num = array(1, 1);
    ?>
    <table border="1">
        <?php
            for($i=1; $i < 20; $i++){
                echo "<tr>";
                
                printf("<td>%d</td>", $num[$i-1]);
                printf("<td>%d</td>", $num[$i]);
                printf("<td>%d</td>", $num[$i-1]+$num[$i]);
                
                echo "</tr>";
                array_push($num, $num[$i-1]+$num[$i]);
            }
        ?>
    </table>
</body>
</html>