<?php
//connect to database
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    if($con){
        echo "Success";
    }else{
        echo "fail";
    }
    //get list of words from application
    $listOfWords = $_POST["word"];
    //split by space and place in an array
    $breakDown = explode(" ", $listOfWords);
    //create an array
    $response = array();
    //for each value in the array of words
    foreach($breakDown as $word){
        //check for empty words
        if(!$word == ""){
            //create mysql statement to get all item for searched word
            $statement = "SELECT * FROM `Symbols` WHERE `word` = '$word' ";
            //execute and get reuslt
            $result = mysqli_query($con, $statement);
            //check if only one value
            if(mysqli_num_rows($result) == 1){
                //place in array 
                while($row = mysqli_fetch_array($result)){
                    array_push($response, array("imageID"=>$row[0], "word"=>$row[1], "category"=>$row[2], "image"=>$row[3], "video"=>$row[4]));
                }
                //if more than one value
            }else{
                //create mysql statement to get all item for searched word
                $statement = "SELECT * FROM `Symbols` WHERE `word` LIKE '$word%' LIMIT 1";
                //execute and get reuslt
                $result = mysqli_query($con, $statement);
                //place in array
                while($row = mysqli_fetch_array($result)){
                    array_push($response, array("imageID"=>$row[0], "word"=>$row[1], "category"=>$row[2], "image"=>$row[3], "video"=>$row[4]));
                }
            }
        }
    }
    //return array as JSON
    echo json_encode(array("List"=>$response));
?> 