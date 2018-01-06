<?php
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");

    $word = $_POST["word"];

    $statement = "SELECT * FROM Symbols WHERE word = '$word' ";
    
    $result = mysqli_query($con, $statement);
    $response = array();

    while($row = mysqli_fetch_array($result)){
        array_push($response, array("imageID"=>$row[0], "word"=>$row[1], "category"=>$row[2], "image"=>$row[3], "video"=>$row[4]));
    }

    print_r(json_encode($response));
?> 