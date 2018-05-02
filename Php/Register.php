<?php
//connect to database
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    if($con){
        echo "Connected";
    }else{
        echo "Failed";
    }
//get username and password from application
    $username = $_POST["user"];
    $password = $_POST["pass"];
    //create mysql statement and execute, to check if username is taken
    $query = mysqli_query($con, "SELECT * FROM Accounts WHERE Username = '$username'");
    //get number of rows
    $numbers =  mysqli_num_rows($query);
    //create array
    $response = array();
    $response["success"] = false;
    //if username is not taken
    if(!$numbers > 0){
        //create mysql statement
        $statement = mysqli_prepare($con, "INSERT INTO Accounts(Username, Password) VALUES (?,?)");
        //prepare statement
        mysqli_stmt_bind_param($statement, "ss", $username, $password);
        //execute statement
        mysqli_stmt_execute($statement);
        //send success is true
        $response["success"] = true;
    }
    //return array as JSON
    print_r(json_encode($response));
?>