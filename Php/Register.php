<?php
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    if($con){
        echo "Connected";
    }else{
        echo "Failed";
    }

    $eMail = $_POST["email"];
    $username = $_POST["username"];
    $firstName = $_POST["firstname"];
    $lastName = $_POST["lastname"];
    $dateOfBirth = $_POST["dateofbirth"];
    $phoneNumber = $_POST["phonenumber"];
    $password = $_POST["pass"];

    $statement = mysqli_prepare($con, "INSERT INTO Accounts(email, username, firstname, lastname, dateofbirth, phonenumber, pass) VALUES (?,?,?,?,?,?,?)");
    mysqli_stmt_bind_param($statement, "sssssis", $eMail, $username,$firstName, $lastName, $dateOfBirth, $phoneNumber, $password);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;
    
    
    print_r(json_encode($response));
?>