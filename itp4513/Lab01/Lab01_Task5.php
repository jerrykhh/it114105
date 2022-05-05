<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lab01 Task5</title>
</head>
<body>
    <?php
        $today = getdate(time());
        echo "Today is $today[mday] $today[month] $today[year] <br />";
        
        switch($today['mon']) {
            case 2:
                $days = (($today['year'] % 4 == 0 && $today['year'] % 100 !=0) || $today['year'] % 400 == 0) ? 29 : 28 ;
                break;
            case 4: case 6: case 9: case 11:
                $days = 30;
                break;
            default:
                $days = 31;
        }
    
        echo "$today[month] has $days days.";
    ?>
</body>
</html>