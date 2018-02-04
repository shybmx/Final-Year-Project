<?php
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    if($con){
        echo "Success";
    }else{
        echo "fail";
    }

    $username = $_POST["user"];

    $query = mysqli_query($con, "SELECT word FROM PrevisoulyVisited WHERE username = ? LIMIT 10");
    
    $temparray = array();
    $response = array();

    while($row = mysqli_fecth_array($query)){
        array_push($temparray, array("words"=>$row[0]));
    }

    foreach($temparray as $words){
        $result1 = mysqli_query($con, "SELECT * FROM Symbols WHERE word = '$words' ");

        while($row1 = mysqli_fetch_array($result1)){
            array_push($response, array("imageID"=>$row[0], "word"=>$row[1], "category"=>$row[2], "image"=>$row[3], "video"=>$row[4]));
        }
    }

    echo json_encode(array("List"=>$response));
?>