<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task01 Task6</title>
</head>
<body>
    <?php
        $today = getdate(time());
        echo "Today is $today[mday] $today[month] $today[year] <br />";
        
        if($today['mon'] == 2)
            $days = (($today['year'] % 4 == 0 && $today['year'] % 100 !=0) || $today['year'] % 400 == 0) ? 29 : 28 ;
        else if($today['mon'] == 4 || $today['mon'] ==  6 || $today['mon'] == 9 || $today['mon'] == 11)
            $days = 30;
        else
            $days = 31;
    
        echo "$today[month] has $days days.";

    ?>
</body>
</html>