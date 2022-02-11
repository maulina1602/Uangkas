<?php
    define('HOST', 'localhost');
    define('USER', 'root');
    define('PASS', '');
    define('DB', 'uangkas');

    $conn = mysqli_connect(HOST, USER, PASS, DB) or die('Unable to connect');

    date_default_timezone_set("Asia/Jakarta");
?>