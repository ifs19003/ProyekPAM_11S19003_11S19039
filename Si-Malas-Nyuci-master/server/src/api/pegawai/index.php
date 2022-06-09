<?php

use Slim\Http\Request;
use Slim\Http\Response;

$app->get("/pegawai/{id}", function(Request $request, Response $response){
    $idLaundry = $request->getAttribute("id");
    $sql = "SELECT u.* FROM laundry_employees l INNER JOIN users u ON u.id = l.idPegawai  WHERE kode_laundry=:id";
    $stmt = $this->db->prepare($sql);
    if($stmt->execute([":id"=>$idLaundry])){
        if($stmt->rowCount() <= 0){
            return $response->withJson(["msg" => "Data not found.","status"=>"Not Found.", "code"=>404], 404);
        }else{
            $result = $stmt->fetchAll();
            return $response->withJson(["status"=>"Succes.", "code"=>200, "data"=>$result], 200);
        }
    }
    return $response->withJson(["msg"=>"Sorry, Internal server error", "status"=>"Internal server error.", "code"=>500], 500);
});