<?php

use Slim\App;
use Slim\Http\Request;
use Slim\Http\Response;

return function (App $app) {
    $container = $app->getContainer();

    // $app->get('/[{name}]', function (Request $request, Response $response, array $args) use ($container) {
    //     // Sample log message
    //     $container->get('logger')->info("Slim-Skeleton '/' route");

    //     // Render index view
    //     return $container->get('renderer')->render($response, 'index.phtml', $args);
    // });

    $cekApiKey = function ($request, $response, $next) {
        $key = $request->getQueryParam('key');
        if (!isset($key)) {
            return $response->withJson(["status" => "API Key required"], 401);
        }

        $sql = "SELECT * FROM api_users WHERE api_key=:api_key";
        $stmt = $this->db->prepare($sql);
        $stmt->execute([":api_key" => $key]);

        if ($stmt->rowCount() > 0) {
            $result = $stmt->fetch();
            if ($key == $result["api_key"]) {

                // update hit
                $sql = "UPDATE api_users SET hit=hit+1 WHERE api_key=:api_key";
                $stmt = $this->db->prepare($sql);
                $stmt->execute([":api_key" => $key]);

                return $response = $next($request, $response);
            }
        }
        return $response->withJson(["status" => "Unauthorized"], 401);
    };

    require_once('api/auth/index.php');
    $app->group('/api', function() use ($app){
        require_once('api/pegawai/index.php');
        require_once('api/pemilik/index.php');
    })->add($cekApiKey);
};
