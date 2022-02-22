<?php 
	if(strlen(trim($_POST['username']))>0 && strlen(trim($_POST['password']))>0){
		
		$userName = $_POST['username'];
		$pwd = $_POST['password'];
		if(substr_count($userName,"@")==1){
			require_once('ini/conn.php');
			$sql= "SELECT password FROM administrator WHERE email='$userName'";
			$result = mysqli_query($conn,$sql) or die(mysqli_errno($conn));
			
			
			if(mysqli_num_rows($result)==1){
				$record = mysqli_fetch_assoc($result);
				if($pwd==$record["password"]){
					setcookie("AdminName", $userName, time()+60*15);
					header("location: adminOrder.php");
				}else{
					header("location: index.php?Login=FALSE");
				}
			}else{
				header("location: index.php?Login=FALSE");
				
			}
			
			
			
		}else{
			require_once('ini/conn.php');
			$sql= "SELECT password FROM dealer WHERE dealerID='$userName'";
			$result = mysqli_query($conn,$sql) or die(mysqli_errno($conn));
			if(mysqli_num_rows($result)==1){
				$record = mysqli_fetch_assoc($result);
				if($pwd==$record["password"]){
					setcookie("DealerName", $userName, time()+60*15);
					header("location: createOrder.php");
				}else{
					header("location: index.php?Login=FALSE");
				}
			}else{
				header("location: index.php?Login=FALSE");
			}
			
		}	
	}else{
		header("location: index.php?userName=FALSE&pwd=FALSE");
	}
	
?>