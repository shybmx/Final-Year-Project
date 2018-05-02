<?php
//connect to database
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    if($con){
        echo "Success";
    }else{
        echo "fail";
    }
    //create mysql statement to change sign of the day
    $statement = "DELETE FROM `SignOfTheDay`; INSERT INTO SignOfTheDay SELECT * FROM Symbols ORDER BY RAND() LIMIT 1"; 
    //execute statement
    $result = mysqli_query($con, $statement);
?>