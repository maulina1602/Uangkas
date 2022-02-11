<?php
    require_once('connection.php');

    $transaksi_id = $_REQUEST['transaksi_id'];
    $status       = $_REQUEST['status'];
    $jumlah       = $_REQUEST['jumlah'];
    $keterangan   = $_REQUEST['keterangan'];
    $tanggal      = $_REQUEST['Y-m-d'];

    //update data dimulai dari kode dibawah ini
    $query = mysqli_query($conn, "UPDATE transaksi set status = '$status', jumlah = '$jumlah', keterangan = '$keterangan', tanggal = '$tanggal' WHERE transaksi_id = '$transaksi_id'");

    if($query){
        echo json_encode(array('response' => 'success'));
    }else{
        echo json_encode(array('response' => 'failed'));
    }
?>