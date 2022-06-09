<?php

use Slim\Http\Request;
use Slim\Http\Response;

$app->post('/api/login', function (Request $request, Response $response) {

    if ($request->getParsedBody() !== null) {
        $users = $request->getParsedBody();
        $sql = "SELECT * FROM users where email=:email";
        $stmt = $this->db->prepare($sql);
        $stmt->execute([':email' => $users['email']]);
        if ($stmt->rowCount() > 0) {
            $result = $stmt->fetch();
            if (password_verify($users['password'], $result['password'])) {
                return $response->withJson(['status' => 'success', 'message' => 'Succes To Login', 'user' => $result], 200);
            } else {
                return $response->withJson(['status' => 'failed', 'message' => 'Auth Invalid', 'user' => null], 401);
            }
        } else {
            return $response->withJson(['status' => 'failed', 'message' => 'Email Not Found', 'user' => null], 404);
        }
    }
    return $response->withJson(['status' => 'failed', 'message' => 'Email and Password Needed', 'user' => null], 500);
});
$app->post('/api/register', function (Request $request, Response $response) {

    if ($request->getParsedBody() !== null) {
        $users = $request->getParsedBody();
        $sql = "SELECT * FROM users where email=:email";
        $stmt = $this->db->prepare($sql);
        $stmt->execute([':email' => $users['email']]);
        if ($stmt->rowCount() <= 0) {
            $full_name = $users['full_name'];
            $email = $users['email'];
            $password = $users['password'];
            $phone_number = $users['phone_number'];
            $role = $users['role'] === 1 ? 'Owner' : 'Employees';

            $sql = "INSERT INTO users (full_name, email, password, phone_number, role) VALUES (:full_name, :email, :password, :phone_number, :role)";

            $needed = [
                ':full_name' => $full_name,
                ':email' => $email,
                ':password' => password_hash($password, PASSWORD_DEFAULT),
                ':phone_number' => $phone_number,
                ':role' => $role
            ];

            $stmt = $this->db->prepare($sql);
            if ($stmt->execute($needed)) {
                $sql = "SELECT * FROM users where email=:email";
                $stmt = $this->db->prepare($sql);
                $stmt->execute([':email' => $users['email']]);
                $result = $stmt->fetch();
                if($users["role"] !== 0){
                    $sql = "INSERT INTO laundry_employees (kode_laundry, idPegawai) VALUE (:kode, :id)";
                    $stmt = $this->db->prepare($sql);
                    if($stmt->execute([':kode' => $users['kode'], ':id' => $result['id']])){
                        return $response->withJson(['status' => 'success', 'message' => 'Succes To Register', 'user' => $result], 200);
                    }
                }else{
                    return $response->withJson(['status' => 'success', 'message' => 'Succes To Register', 'user' => $result], 200);
                }
            } else {
                return $response->withJson(['status' => 'failed', 'message' => 'Failed to insert data', 'user' => null], 400);
            }
        } else {
            return $response->withJson(['status' => 'failed', 'message' => 'Email Exist', 'user' => null], 400);
        }
    }
    return $response->withJson(['status' => 'failed', 'message' => 'Internal Server Error', 'user' => null], 500);
});
