<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Lab06 Task 1</title>

</head>

<?php
  $myForm = <<<EOD
    <form method="POST" action="Lab06_1.php">
		<h1>I can tell who you are if you visit this page again within next 2 minutes</h1>
		<p>Your name:<input type=text name=username></p>
		<p>Preferred color:<input type=text name=color></p>
		<input type=submit value="Write Cookie">
		<input type="reset" value="Reset">
		</form>
EOD;

		if(isset($_COOKIE["color"])){
			echo "<body bgcolor=$_COOKIE[color]> Welcome back 			$_COOKIE[username]"; 
		}else if(isset($_POST["username"])){
			setcookie("username",$_POST["username"],time() + 2*60);
			setcookie("color",$_POST["color"],time() + 2*60);
			echo "<body> I have created a cookie to remember your submitted information You are $_POST[username] and preferred background color is $_POST[color] Reload this page or visit agin, you will see my welcome message";
		}else{
			echo $myForm;
		}
		
?>

	
</body>
</html>