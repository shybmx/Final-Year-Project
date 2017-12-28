<?php
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    if($con){
        echo "Success";
    }else{
        echo "fail";
    } 

    $category = $_POST["category"];

    $statement = mysqli_prepare($con, "SELECT COUNT(*) FROM Symbols WHERE `category` = ?");
    mysqli_stmt_bind_param($statement, "s", $category);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $count);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["count"] = $count;
    }

    print_r(json_encode($response));
?>