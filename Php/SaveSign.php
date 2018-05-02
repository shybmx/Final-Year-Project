<?php
//connect to database
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    if($con){
        echo "Connected";
    }else{
        echo "Failed";
    }

    $username = $_POST["user"];
    $word = $_POST["word"];
    //create array
    $response = array();
    $response["success"] = false;
    //create msql statement to store into table with username and password
    $statement = mysqli_prepare($con, "INSERT INTO `PrevisoulyVisited`(`Username`, `Word`) VALUES (?,?)");
    //prepare statement
    mysqli_stmt_bind_param($statement, "ss", $username, $word);
    //execute statement
    mysqli_stmt_execute($statement);

    $response["success"] = true;
    //return true in JSON
    print_r(json_encode($response));
?>