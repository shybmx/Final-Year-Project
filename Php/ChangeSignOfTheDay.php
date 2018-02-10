<?php
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    if($con){
        echo "Success";
    }else{
        echo "fail";
    }

    $statement = "DELETE FROM `SignOfTheDay`; INSERT INTO SignOfTheDay SELECT * FROM Symbols ORDER BY RAND() LIMIT 1"; 
    $result = mysqli_query($con, $statement);
?>