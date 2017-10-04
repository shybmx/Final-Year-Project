<?php
    session_start();
    $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
    if(isset($_POST['registerBtn'])){
        session_start();
        $username = mysql_real_escape_string($_POST['username']);
        $password = mysql_real_escape_string($_POST['password']);
        $passwordRe = mysql_real_escape_string($_POST['passwordReType']);
        $companyName = mysql_real_escape_string($_POST['companyName']);
        $address1 = mysql_real_escape_string($_POST['address1']);
        $address2 = mysql_real_escape_string($_POST['address2']);
        $address3 = mysql_real_escape_string($_POST['address3']);
        $postCode = mysql_real_escape_string($_POST['postCode']);
        $companyNumber = mysql_real_escape_string($_POST['companyNumber']);
        if($password != $passwordRe){
            $_SESSION['message'] = "The passwords do not match";
        }else{
            $sql = "INSERT INTO Company_Accounts (username, password, company_name, address_1, address_2, address_3, post_code, company_number) VALUES ('$username', '$password', '$companyName', '$address1', '$address2', '$address3', '$postCode', '$companyNumber')";
            mysqli_query($con, $sql);
            $_SESSION['message'] = "You have now registered";
        }
    }

?>

<!DOCTYPE html>
<html>
    <header>
        <meta charset="utf-8">
        <meta name=description content="">
        <meta name="keywords" content=""> 
        <link rel="stylesheet" href="main.css" type="text/css">
        <title></title>
        <link rel="shortcut icon" href="images/Logo/favicon.ico">
        <link rel="shortcut icon" href="images/logo/favicon.png">
<link rel="shortcut icon" type="image/x-icon" href="images/logo/favicon.ico">
<link rel="icon" type="image/png" href="images/Logo/favicon.png" >
<link rel="shortcut icon" type="image/png" href="images/Logo/favicon.png">
    </header>
        <body>
            <form method="post" action="register.php"> 
                <table>
                    <tr>
                        <td>Username:</td>
                        <td><input type="text" name="username" class="textInput" placeholder="Username"></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password" class="textInput" placeholder="Password"></td>
                    </tr>
                    <tr>
                        <td>Re-Type Password:</td>
                        <td><input type="password" name="passwordReType" class="textInput" placeholder="Re-Type Password"></td>
                    </tr>
                    <tr>
                        <td>Company name:</td>
                        <td><input type="text" name="companyName" class="textInput" placeholder="Company Name"></td>
                    </tr>
                    <tr>
                        <td>Address 1:</td>
                        <td><input type="text" name="address1" class="textInput" placeholder="Address 1"></td>
                    </tr>
                    <tr>
                        <td>Address 2:</td>
                        <td><input type="text" name="address2" class="textInput" placeholder="Address 2"></td>
                    </tr>
                    <tr>
                        <td>Address 3:</td>
                        <td><input type="text" name="address3" class="textInput" placeholder="Address 3"></td>
                    </tr>
                    <tr>
                        <td>Post Code:</td>
                        <td><input type="text" name="postCode" class="textInput" placeholder="Post Code"></td>
                    </tr>
                    <tr>
                        <td>Company Number:</td>
                        <td><input type="text" name="companyNumber" class="textInput" placeholder="Phone Number"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="registerBtn" value="Register"></td>
                    </tr>
                </table>
            </form>
        </body>
</html>