<?php
        require './vendor/autoload.php';
        use Symfony\Component\Dotenv\Dotenv;

        $dotenv = new Dotenv();;
        $dotenv->load('./.env');

        //url to api
        $url = "https://socs.nuigalway.ie/webservices/index.php";


        //Data array of post fields that includes the username and password that will be checked in the rest server for the feed
        //An error will be returned if any parameter is missing a value

        $data = "method=" . base64_encode("getAllMembers") .
            "&username=" . base64_encode(getenv('username')) .
            "&password=" . base64_encode(getenv('password')).
            "&encodeOutput=" . base64_encode(TRUE);

        // Set up curl options

        $ch = curl_init();

        curl_setopt($ch,CURLOPT_URL,$url);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch,CURLOPT_POSTFIELDS,$data);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

        //json array returned.
        $result = json_decode(curl_exec($ch), true);

        // Get the array of events
        $results = $result["Response"]["data"];

        // Write resuts to file
        $myfile = fopen("members-" . date("h") . ".txt", "w") or die("Unable to open file!");
        fwrite($myfile, json_encode($results));
        fclose($myfile);

        $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
        curl_close($ch);

?>
