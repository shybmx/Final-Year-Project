<?php
//connect to database
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    //get category from application
    $category = $_POST["category"];
    //create mysql statement to get sign and symbol based of category
    $statement = "SELECT * FROM Symbols WHERE category = '$category' ";
    //execute and get result
    $result = mysqli_query($con, $statement);
    //create array
    $response = array();
    //for every value in result place into array
    while($row = mysqli_fetch_array($result)){
        array_push($response, array("imageID"=>$row[0], "word"=>$row[1], "category"=>$row[2], "image"=>$row[3], "video"=>$row[4]));
    }
    //return array as JSON
    echo json_encode(array("List"=>$response));
?>  