<?php
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    if($con){
        echo "Connected";
    }else{
        echo "Failed";
    }

    $username = $_POST["user"];
    $word = $_POST["word"];

    $response = array();
    $response["success"] = false;

    $statement = mysqli_prepare($con, "INSERT INTO `PrevisoulyVisited`(`Username`, `Word`) VALUES (?,?)");
    mysqli_stmt_bind_param($statement, "ss", $username, $word);
    mysqli_stmt_execute($statement);

    $response["success"] = true;
    
    print_r(json_encode($response));
?>