<?php
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    if($con){
        echo "Success";
    }else{
        echo "fail";
    }

    $statement = mysqli_prepare($con, "SELECT * FROM Symbols ORDER BY RAND() LIMIT 1");
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $imageID, $word, $category, $image, $gif);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["imageID"] = $imageID;
        $response["word"] = $word;
        $response["category"] = $category;
        $response["image"] = $image;
        $response["gif"] = $gif;
    }

    print_r(json_encode($response));
?>