<?php
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    if($con){
        echo "Success";
    }else{
        echo "fail";
    }

    $listOfWords = $_POST["word"];

    $breakDown = explode(" ", $listOfWords);
    $response = array();

    foreach($breakDown as $word){
        if(!$word == ""){
            $statement = "SELECT * FROM `Symbols` WHERE `word` LIKE '$word%' LIMIT 1";
            $result = mysqli_query($con, $statement);

            while($row = mysqli_fetch_array($result)){
                array_push($response, array("imageID"=>$row[0], "word"=>$row[1], "category"=>$row[2], "image"=>$row[3], "video"=>$row[4]));
            }
        }
    }
    echo json_encode(array("List"=>$response));
?> 