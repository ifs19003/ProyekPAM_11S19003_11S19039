<?php

use Slim\Http\Request;
use Slim\Http\Response;
use Slim\Http\UploadedFile;

function getRandomId(){
    $asciis = str_split(time()."");
        $kode_laundry = "";
        foreach($asciis as $ascii){
            $num = intval($ascii) + 65 + rand(0, 9);
            $kode_laundry .= chr(intval($num));
        }
        return $kode_laundry;
}
function moveUploadedFile($directory, UploadedFile $uploadedFile){
    $extension = pathinfo($uploadedFile->getClientFilename(), 
    PATHINFO_EXTENSION);
    $basename = bin2hex(random_bytes(8));
    $filename = sprintf('%s.%0.8s', $basename, $extension);
    $uploadedFile->moveTo($directory . DIRECTORY_SEPARATOR . $filename);
   
   return $filename;
   }

$app->post("/cucian", function(Request $request, Response $response){
    $body = $request->getParsedBody();
    $files = $request->getUploadedFiles();
    if(count($body) > 0){
        $kode_cucian = getRandomId();
        $nama_pelanggan = $body["nama"];
        $harga = $body["harga"];
        $no_telepon = $body["no_telepon"];
        $cucian = moveUploadedFile("./uploads/laundry/",$files["cucian"]);
        $deskripsi = $body["deskripsi"];
        $kode_laundry = $body["kode_laundry"];
        $tanggal_selesai = $body["tanggal_selesai"];
        $sql = "INSERT INTO cucian (kode_cucian, status, image,deskripsi, harga, kode_laundry,tanggal_selesai, nama_pelanggan, no_telepon) 
                                    VALUE (:kode_cucian,0, :cucian,:deskripsi,:harga,:kode_laundry, :tanggal_selesai, :nama_pelanggan, :no_telepon)";
        $stmt = $this->db->prepare($sql);
        $needed = [
            ":kode_cucian"=>$kode_cucian,
            ":cucian"=>$cucian,
            ":deskripsi"=>$deskripsi,
            ":harga"=>$harga,
            ":kode_laundry"=>$kode_laundry,
            ":tanggal_selesai"=>$tanggal_selesai,
            ":nama_pelanggan"=>$nama_pelanggan,
            ":no_telepon"=>$no_telepon
        ];
        if($stmt->execute($needed)){
            return $response->withJson(["msg"=> "Succes add new cucian.", "status"=>"Succes add data.", "code"=>201],201);
        }
    }else{
        return $response->withJson(["msg"=>"Body can't empty.", "status"=>"Body is empty.", "code"=>400], 400);
    }
    return $response->withJson(["msg"=>"Sorry, Internal server error", "status"=>"Internal server error.", "code"=>500], 500);
});
$app->post("/laundry", function(Request $request, Response $response){
    $body = $request->getParsedBody();
    $files = $request->getUploadedFiles();
    if(count($body) > 0){
        $kode_laundry = getRandomId();
        $nama_laundry = $body["name"];
        $id_pemilik = $body["owner"];
        $profile = moveUploadedFile("./uploads/laundry/",$files["profile"]);
        $alamat = $body["address"];
        $deskripsi = $body['description'];
        $sql = "INSERT INTO laudry (kode_laundry,nama_laundry, id_pemilik, profile_laundry, alamat, deskripsi) VALUE (:kode,:nama, :id_pemilik,:profile,:alamat,:deskripsi)";
        $stmt = $this->db->prepare($sql);
        $needed = [
            ":nama"=>$nama_laundry, 
            ":id_pemilik"=>$id_pemilik, 
            ":profile"=>$profile, 
            ":kode"=>$kode_laundry, 
            ":alamat"=>$alamat, 
            ":deskripsi"=>$deskripsi
        ];
        if($stmt->execute($needed)){
            return $response->withJson(["msg"=> "Succes add new laundry.", "status"=>"Succes add data.", "code"=>201],201);
        }
    }else{
        return $response->withJson(["msg"=>"Body can't empty.", "status"=>"Body is empty.", "code"=>400], 400);
    }
    return $response->withJson(["msg"=>"Sorry, Internal server error", "status"=>"Internal server error.", "code"=>500], 500);
});

$app->get("/laundry/{id}", function(Request $request, Response $response){
    $idLaundry = $request->getAttribute("id");
    $sql = "SELECT * FROM laudry WHERE kode_laundry=:id";
    $stmt = $this->db->prepare($sql);
    if($stmt->execute([":id"=>$idLaundry])){
        if($stmt->rowCount() <= 0){
            return $response->withJson(["msg" => "Data not found.","status"=>"Not Found.", "code"=>404], 404);
        }else{
            $result = $stmt->fetch();
            return $response->withJson(["status"=>"Succes.", "code"=>200, "data"=>$result], 200);
        }
    }
    return $response->withJson(["msg"=>"Sorry, Internal server error", "status"=>"Internal server error.", "code"=>500], 500);
});
$app->get("/laundry", function(Request $request, Response $response){
    $sql = "SELECT * FROM laudry";
    $stmt = $this->db->prepare($sql);
    if($stmt->execute()){
        $result = $stmt->fetchAll();
        return $response->withJson(["status"=>"Succes.", "code"=>200, "data"=>$result], 200);
    }
    return $response->withJson(["msg"=>"Sorry, Internal server error", "status"=>"Internal server error.", "code"=>500], 500);
});
