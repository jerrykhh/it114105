<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Home Page</title>
    <link rel="stylesheet" href="./static/css/fontawesome.css">
    <link rel="stylesheet" href="./static/css/style.css">
  </head>
  <body>
    <div class="title-nav">
      <h1 class="title">Hong Kong Cube Shop Shopping System</h1>
    </div>
    <div class="content-container">
      <div class="nav">
          <ol>
            <li>
              <a href="#" class="selected">
                <span><i class="fas fa-shopping-bag fa-lg"></i>Order</span>
              </a>
            </li>
            <li>
              <a href="orderhistory" class="">
                <span><i class="fas fa-history fa-lg"></i>Order Records</span>
              </a>
            </li>
            <li>
              <a href="profile" class="">
                <span><i class="fas fa-user-cog fa-lg"></i>Setting</span>
              </a>
            </li>
            <li>
              <a href="logout" class="logout">
                <span><i class="fas fa-sign-out-alt fa-lg"></i>Logout</span>
              </a>
            </li>
          </ol>
      </div>
      <div class="main">
        <div class="content">
          <div class="message"></div>
          <div class="content-bar">
            <div class="title">
              Shop List
            </div>
          </div>
          <div class="store-list">
            <?php while($row = $data->fetch_array(MYSQLI_ASSOC)){ ?>
            <div class="card">
              <img src="static/image/default-store.jpg" alt="default-store-icon" style="width:100%">
              <div class="card-content">
                <h1><?php echo $row["ConsignmentStoreName"] ?></h1>
              </div>
              <p><a href="store?id=<?php echo $row["consignmentStoreID"] ?>"><button>View</button></a></p>
            </div>
            <?php } ?>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
