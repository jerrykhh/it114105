<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lab02 Task2</title>
</head>
<body>
    <?php

        $mathMark = array(70, 40, 60, 50, 20, 30, 10, 100, 80, 90);
        $i = 0;
        do {
            echo "$mathMark[$i] ";
            $i++;
        } while ($i < count($mathMark) );

        $max = $mathMark[0];
        $i = 1;
        while($i < count($mathMark)){
            if ($max < $mathMark[$i])
                $max = $mathMark[$i];
            $i++;
        }
        printf("<br>Maximum mark: %d", $max);

        $min = $mathMark[0];
        for($i = 1; $i < count($mathMark); $i++){
            if($min > $mathMark[$i])
                $min = $mathMark[$i];
        }
        printf("<br>Minimum mark: %d", $min);

    ?>
</body>
</html>