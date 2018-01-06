<?php
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");

    $category = $_POST["category"];

    $statement = "SELECT * FROM Symbols WHERE category = '$category' ";
    
    $result = mysqli_query($con, $statement);
    $response = array();

    while($row = mysqli_fetch_array($result)){
        array_push($response, array("imageID"=>$row[0], "word"=>$row[1], "category"=>$row[2], "image"=>$row[3], "video"=>$row[4]));
    }

    echo json_encode(array("ListOfSignsAndSymbols"=>$response));
?>  