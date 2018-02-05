<?php
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    if($con){
        echo "Success";
    }else{
        echo "fail";
    }

    $username = $_POST["user"];

    $query = mysqli_query($con, "SELECT word FROM PrevisoulyVisited WHERE username = '$username' LIMIT 10");
    
    $temparray = array();
    $response = array();

    while($row = mysqli_fetch_array($query)){
        array_push($temparray, array("word"=>$row[0]));
    }

    foreach($temparray as $words){
        foreach($words as $word){
            $result1 = mysqli_query($con, "SELECT * FROM Symbols WHERE word = '$word' ");

            while($row1 = mysqli_fetch_array($result1)){
                array_push($response, array("imageID"=>$row1[0], "word"=>$row1[1], "category"=>$row1[2], "image"=>$row1[3], "video"=>$row1[4]));
            }
        }
    }

    echo json_encode(array("List"=>$response));
?>