<?php
//connect to database
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    if($con){
        echo "Success";
    }else{
        echo "fail";
    }
    //get username and password from application
    $userName = $_POST["user"];
    $password = $_POST["pass"];
    //create mysql statement to find accounts
    $statement = mysqli_prepare($con, "SELECT * FROM Accounts WHERE Username = ? AND Password = ?");
    //prepare statement
    mysqli_stmt_bind_param($statement, "ss", $userName, $password);
    //execute statement
    mysqli_stmt_execute($statement);
    //store results
    mysqli_stmt_store_result($statement);
    //bind results
    mysqli_stmt_bind_result($statement, $userName, $password);
    //create array
    $response = array();
    $response["success"] = false;
    //if username is found set success to true and place all items within array
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["user"] = $userName;
        $response["pass"] = $password;
    }
    //return array as JSON
    print_r(json_encode($response));
?>