<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Lab06 Task2</title>
</head>

<body>
    <?php

    $myForm = <<<EOD
	<form method="post" action="$_SERVER[PHP_SELF]">
	<h1>Session Demo Page</h1>
	<p>Programming language: <input type=text name=tfLang> will be stored in a session variable</p>
	<button type=submit>Submit</button>
	<button type=reset>Reset</button>
	</form>

EOD;    
    if (isset($_POST['tfLang'])) {
        session_start();
        $_SESSION['lang'] = $_POST['tfLang'];
        echo "\$_POST['tfLang'] = $_POST[tfLang] <br> \$_SESSION['lang'] = $_SESSION[lang]";
    } else {
        echo $myForm;
    }
    ?>
</body>

</html>