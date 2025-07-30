<?php

include_once('connect.php');
$author = $_GET['author'];
$quote = $_GET['quote'];

$result = mysqli_query($conn, "INSERT INTO quotes (author, quote) 
			       VALUES ('$author', '$quote')");

echo "Data inserted";

?>