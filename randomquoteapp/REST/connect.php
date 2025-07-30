<?php

$host = "localhost";
$user = "root";
$pass = "";
$dbname = "quotedb";

$conn = mysqli_connect($host, $user, $pass, $dbname);

if ($conn->connect_error) {
    die("Unable to select database");
};

?>