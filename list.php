<?php

require_once('connection.php');
error_reporting(E_ERROR | E_PARSE);

$query = mysqli_query($conn, "SELECT * FROM transaksi ORDER BY transaksi_id DESC");

$result = array();

while($row = mysqli_fetch_array($query)){
    array_push($result, array(
    'transaksi_id' => $row['transaksi_id'],
    'status'       => $row['status'],
    'jumlah'       => $row['jumlah'],
    'keterangan'   => $row['keterangan'],
    'tanggal'      => date("d/m/Y", strtotime($row['tanggal'])),
    'tanggal2'     => $row['tanggal'],
     ));
    
}

$query =mysqli_query($conn, "SELECT
    (SELECT SUM(jumlah) FROM transaksi WHERE status = 'MASUK') AS masuk,
    (SELECT SUM(jumlah) FROM transaksi WHERE status = 'KELUAR') AS keluar
    ");

while($row = mysqli_fetch_assoc($query))
{
    $masuk  = $row['masuk'];
    $keluar = $row['keluar'];
}

echo json_encode(array(
    'masuk'  => $masuk,
    'saldo'  => ($masuk - $keluar),
    'result' => $result
));

mysqli_close($conn);

?>