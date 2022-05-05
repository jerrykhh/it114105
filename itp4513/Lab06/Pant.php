<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Lab06 Task 3</title>
</head>

<body>
<?php
	session_start();
	if(isset($_POST["runQty"])){

		$_SESSION["Running Shoe"] = $_POST["runQty"];
	}
	if(isset($_POST["tenQty"])){
		$_SESSION["Tennis Shoe"] = $_POST["tenQty"];
	}
	$myForm = <<<EOD
	<form method="POST" action="OrderList.php">
	<h1>Sport Pants</h1>
	<table border=1>
	<tr>
		<th>Model Name</th>
		<th>Quantity</th>
	</tr>
	<tr>
		<td>Golf Pants</td>
		<td><input type=number name=golQty></td>
	</tr>
	<tr>
		<td>Woven Pants</td>
		<td><input type=number name=wovQty></td>
	</tr>
	</table><br>
	<button type=submit>Submit</button>
	</form>
EOD;
	echo $myForm;
	
?>
</body>
</html>