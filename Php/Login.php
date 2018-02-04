<?php
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    if($con){
        echo "Success";
    }else{
        echo "fail";
    }
    
    $userName = $_POST["user"];
    $password = $_POST["pass"];

    $statement = mysqli_prepare($con, "SELECT * FROM Accounts WHERE Username = ? AND Password = ?");
    mysqli_stmt_bind_param($statement, "ss", $userName, $password);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userName, $password);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["user"] = $userName;
        $response["pass"] = $password;
    }

    print_r(json_encode($response));
?>