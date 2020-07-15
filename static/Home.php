<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Home Page</title>
    <link rel="stylesheet" href="./static/css/fontawesome.css">
    <link rel="stylesheet" href="./static/css/style.css">
    <link rel="stylesheet" href="./static/css/modal.css">
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
              <a href="logout" class="logout">
                <span><i class="fas fa-sign-out-alt fa-lg"></i>Logout</span>
              </a>
            </li>
          </ol>
      </div>
      <div class="main">
        <div class="content">
          <div class="message">
            <?php  if(isset($_GET["cart"])){
                echo '<div class="bs-callout bs-callout-success"><h4>Cart is clear</h4>All items in the cart are cleared</div>';
            }?>
          </div>
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
                <h2><?php echo $row["ConsignmentStoreName"] ?></h2>
              </div>
              <p><button onclick="viewStore(<?php echo $row["consignmentStoreID"] ?>)">View</button></p>
            </div>
            <?php } ?>
          </div>

        </div>
      </div>
    </div>

    <div class="modal-container" id="wraning-modal">
    <!-- Modal  -->
      <div class="modal">
        <h2>System Message</h2>
        <br>
        <p>You only order the item in one shop. If you still want to go to another store. Your cart will clear all.</p>
        <br>
        <input type="button" class="btn-confirm" value="Confirm" onclick="clearCart()">
        <input type="button" class="btn-cancel" value="Cancel" onclick="closeModal()">
      </div>
    <!-- Background, click to close -->
      <a href="#" class="modal-bg"></a>
   </div>
    <footer>
      <p>Hong Kong Cube Shop Shopping System © 2020</p>
    </footer>
    <script src="./static/js/initOrder.js"></script>
  </body>
</html>
