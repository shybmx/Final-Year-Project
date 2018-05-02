<?php
//connect to database
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    if($con){
        echo "Success";
    }else{
        echo "fail";
    }
    //get username from application
    $username = $_POST["user"];
//create mysql statement
    $query = mysqli_query($con, "SELECT word FROM PrevisoulyVisited WHERE `Username` = '$username' ORDER BY id DESC LIMIT 10");
    //create 2 arrays
    $temparray = array();
    $response = array();
    //get all values from the result to store into array
    while($row = mysqli_fetch_array($query)){
        array_push($temparray, array("word"=>$row[0]));
    }
    //get values from array to search in another table
    foreach($temparray as $words){
        foreach($words as $word){
            $result1 = mysqli_query($con, "SELECT * FROM Symbols WHERE word = '$word' ");
            //place items in array
            while($row1 = mysqli_fetch_array($result1)){
                array_push($response, array("imageID"=>$row1[0], "word"=>$row1[1], "category"=>$row1[2], "image"=>$row1[3], "video"=>$row1[4]));
            }
        }
    }
    //return array as JSON
    echo json_encode(array("List"=>$response));
?>