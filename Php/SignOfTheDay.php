<?php
//connect to database
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    if($con){
        echo "Success";
    }else{
        echo "fail";
    }
    //create mysql statement to get sign of the day 
    $statement = mysqli_prepare($con, "SELECT * FROM `SignOfTheDay`");
    //execute statement
    mysqli_stmt_execute($statement);
    //store results
    mysqli_stmt_store_result($statement);
    //bind results
    mysqli_stmt_bind_result($statement, $imageID, $word, $category, $image, $video);
    //create array
    $response = array();
    $response["success"] = false;
    //for every value insert into array
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["imageID"] = $imageID;
        $response["word"] = $word;
        $response["category"] = $category;
        $response["image"] = $image;
        $response["video"] = $video;
    }
    //return array as JSON
    print_r(json_encode($response));
?>