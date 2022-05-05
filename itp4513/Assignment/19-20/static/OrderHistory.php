<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Order History</title>
    <link rel="stylesheet" href="./static/css/fontawesome.css">
    <link rel="stylesheet" href="./static/css/style.css">
  </head>
  <body>
    <div class="title-nav">
      <h1 class="title">Hong Kong Cube Shop Shopping System</h1>
    </div>
    <div class="content-container">
      <div class="nav">
        <a href="profile">
          <div class="profile-detials">
            <i class="fas fa-user-alt fa-lg"></i>
            <h3>Account</h3>
            <p><?php echo $_SESSION['customer']['customerEmail']?></p>
          </div>
        </a>
          <ol>
            <li>
              <a href="home" class="">
                <span><i class="fas fa-shopping-bag fa-lg"></i>Order</span>
              </a>
            </li>
            <li>
              <a href="#" class="selected">
                <span><i class="fas fa-history fa-lg"></i>Order Records</span>
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
          <div class="message">
            <?php
             if (isset($_GET['checkout'])){
              echo '<div class="bs-callout bs-callout-success"><h4>Order Created</h4>Order #' . $_GET["checkout"].' created</div>';
            }
             ?>
          </div>
          <div class="processing-table-header-cell">Processing Orders</div>
          <div class="processing-table">
            <div class="processing-table-heading">
              <div class="processing-table-head list-item-OrderId">Order ID #</div>
              <div class="processing-table-head list-item-ShopName">Shop Name</div>
              <div class="processing-table-head list-item-OrderDate">Order Date</div>
              <div class="processing-table-head list-item-Status">Status</div>
              <div class="processing-table-head list-item-Amount">Amount</div>
              <div class="processing-table-head list-item-Action">Action</div>
            </div>
            <div class="processing-table-body">

              <?php foreach($data as $row){ ?>
              <?php  if($row['status'] < 3) {?>
              <div class="processing-table-row">
                <div class="processing-table-cell list-item-OrderId"><?php echo $row['orderID'] ?></div>
                <div class="processing-table-cell list-item-ShopName"><?php echo $row['ConsignmentStoreName'] ?></div>
                <div class="processing-table-cell list-item-OrderDate"><?php echo $row['orderDateTime'] ?></div>
                <div class="processing-table-cell list-item-Status"><?php echo $status = ($row['status'] == 1)? "Delivery": "Awaiting"?></div>
                <div class="processing-table-cell list-item-Amount">$<?php echo $row['totalPrice'] ?></div>
                <div class="processing-table-cell list-item-Action"><a href="orderhistory?id=<?php echo $row['orderID'] ?>"><button><i class="fas fa-eye"></i></button></a></div>
              </div>
            <?php }} ?>
            </div>
          </div>
          <div class="processed-table-container">
            <div class="processed-table-header-cell">Processed Orders</div>
            <div class="processed-table">
              <div class="processed-table-heading">
                <div class="processed-table-head list-item-OrderId">Order ID #</div>
                <div class="processed-table-head list-item-ShopName">Shop Name</div>
                <div class="processed-table-head list-item-OrderDate">Order Date</div>
                <div class="processed-table-head list-item-Status">Status</div>
                <div class="processed-table-head list-item-Amount">Amount</div>
                <div class="processed-table-head list-item-Action">Action</div>
              </div>
              <div class="processed-table-body">
              <?php  foreach($data as $row){ ?>
                <?php  if($row['status'] == 3) {?>
                <div class="processed-table-row">
                  <div class="processed-table-cell list-item-OrderId"><?php echo $row['orderID'] ?></div>
                  <div class="processed-table-cell list-item-ShopName"><?php echo $row['ConsignmentStoreName'] ?></div>
                  <div class="processed-table-cell list-item-OrderDate"><?php echo $row['orderDateTime'] ?></div>
                  <div class="processed-table-cell list-item-Status">Completed</div>
                  <div class="processed-table-cell list-item-Amount">$<?php echo $row['totalPrice'] ?></div>
                  <div class="processed-table-cell list-item-Action"><a href="orderhistory?id=<?php echo $row['orderID'] ?>"><button><i class="fas fa-eye"></i></button></a></div>
                </div>
                <?php }} ?>
              </div>
            </div>
        </div>
        </div>
      </div>
    </div>
    <footer><p>Hong Kong Cube Shop Shopping System © 2020</p></footer>
    <script src="./static/js/initOrder.js"></script>
  </body>
</html>
