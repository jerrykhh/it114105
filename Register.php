<?php
	if($_POST["Cpassword"]==$_POST["password"]){
		require_once("ini/conn.php");
		$sql = "SELECT dealerID FROM dealer WHERE dealerID='$_POST[dealerID]'";
		$result = mysqli_query($conn, $sql) or die(mysqli_error($conn));
		if(mysqli_num_rows($result)>0)
			header("location: index.php?regRe=FALSE");
		else{
			extract($_POST);
			$sql = "INSERT INTO dealer VALUES('$dealerID','$password','$dealerName','$phoneNo','$Address')";
			$result = mysqli_query($conn, $sql) or die(mysqli_error($conn));
			if(mysqli_affected_rows($conn)==1){
				mysqli_free_result($result);
				mysqli_close();
				header("location: index.php?Register=TRUE");
			}		
		}
	}else{
		header("location: index.php?regPwd=FALSE");
	}




	




?>