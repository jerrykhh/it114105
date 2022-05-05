<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Lab8 Task 2</title>
</head>

<body>
	<?php
		$cparts = simplexml_load_file('cparts.xml');
		$jsontext = json_encode($cparts);
		echo "<p><b>json_encode(): display cparts.xml in json format</b></p>$jsontext";
	
		$compparts = json_decode($jsontext, true);
		echo "<p><b>json_decode(): convert json to PHP variable</b></p>";
		echo "<table border=1><tr><th>Item</th><th>Manufacturer</th><th>Model</th><th>Cost</th></tr>";
		$parts = $compparts["part"];
		for($i=0; $i<count($parts);$i++){
			printf("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",$parts[$i]["item"],$parts[$i]["manufacturer"],$parts[$i]["model"],$parts[$i]["cost"]);
		}
		echo "</table>";
	
	
	
	?>
</body>
</html>