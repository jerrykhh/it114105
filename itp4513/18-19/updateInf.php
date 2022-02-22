<?php
if(isset($_POST["dealerName"])){

	require_once('ini/conn.php');
	$sql = "SELECT password FROM dealer WHERE dealerID='$_GET[dealerID]'";
	$result = mysqli_query($conn, $sql) or die(mysqli_error($conn));
	$record = mysqli_fetch_assoc($result);
	if($_POST["pwdOld"]!=$record["password"])
		header("location:dealerSetting.php?pwdOld=FALSE");
	else if($_POST["pwdNew"]!=$_POST["pwdNewConf"])
		header("location:dealerSetting.php?pwdNew=FALSE");
	else{
		extract($_POST);
		$sql = "UPDATE dealer SET name='$dealerName', password='$pwdNew', address='$dealerAddr', phoneNumber=$phone WHERE dealerID='$_GET[dealerID]'";
		$result = mysqli_query($conn, $sql) or die(mysqli_error($conn));
		if(mysqli_affected_rows($conn)>0){
			setcookie("DealerName",$_GET["dealerID"],time()*60*15);
			header("location:dealerSetting.php?Update=TRUE");
		}else
			header("location:dealerSetting.php?Update=FALSE");
	}
	
	
	
	
}





?>