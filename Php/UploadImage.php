<?php
  $con = mysqli_connect("localhost", "id2981493_finalyear", "Testing123", "id2981493_finalyearproject");
  if($con){
      echo "Success";
  }else{
      echo "fail";
  }

  $word = $_POST['word'];
  $link = $_POST['link'];

  $statement = mysqli_prepare($son, "SELECT `image` FROM `Symbols` WHERE `word` = ?");
mysqli_stmt_bind_param($statement, )


?>
