<?php
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    if($con){
        echo "Connected";
    }else{
        echo "Failed";
    }

    $username = $_POST["user"];
    $password = $_POST["pass"];

    $query = mysqli_query($con, "SELECT * FROM Accounts WHERE Username = '$username'");

    $numbers =  mysqli_num_rows($query);

    $response = array();
    $response["success"] = false;

    if(!$numbers > 0){
        $statement = mysqli_prepare($con, "INSERT INTO Accounts(Username, Password) VALUES (?,?)");
        mysqli_stmt_bind_param($statement, "ss", $username, $password);
        mysqli_stmt_execute($statement);

        $response["success"] = true;
    }

    print_r(json_encode($response));
?>