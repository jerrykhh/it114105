<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lab03 Task1</title>
</head>
<body>
    <?php
        extract($_POST);
        $isVaild = true;

        if(!isset($name) || strlen(trim($name)) == 0){
            echo "Name field is missing <br />";
            $isVaild = false;
        }
        
        if(!isset($year) || strlen(trim($year)) == 0){
            echo "Graduate Year field is missing <br />";
            $isVaild = false;
        }

        if(!isset($comments) || strlen(trim($comments)) == 0){
            echo "Comment field is missing <br />";
            $isVaild = false;
        }

        if($isVaild){
            echo "Name: $name<br>";
            echo "Graduate Year: $year<br>";
            echo "Alumi: $alumi<br>";
            echo "Comments: $comments";
        }


    ?>

</body>

</html>