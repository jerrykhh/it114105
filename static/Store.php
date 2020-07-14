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
          <div class="message"></div>
          <div class="back-button">
            <a href="store"><button type="button"><i class="fas fa-long-arrow-alt-left"></i> Back</button></a>
          </div>
          <div class="shop-information">
            <h1><?php echo $data->getStoreName() ?></h1>
          </div>
          <br>
          <div class="content-bar">
            <div class="title">
              Shop List
            </div>
          </div>
          <div class="store-item-list">
            <div class="item-table">
              <div class="item-table-heading">
                <div class="item-table-head store-item-Name">Product Name</div>
                <div class="item-table-head store-item-StQty">Stock Quantity</div>
                <div class="item-table-head store-item-Status">Status</div>
                <div class="item-table-head store-item-Price">Price</div>
                <div class="item-table-head store-item-Qty">Quantity</div>
                <div class="item-table-head store-item-Action"></div>
              </div>
              <div class="item-table-body">
                <?php while($row = $data->getStoreItemList()->fetch_array(MYSQLI_ASSOC)){ ?>
                <div class="item-table-row">
                  <div class="item-table-cell store-item-Name"><?php echo $row["goodsName"] ?></div>
                  <div class="item-table-cell store-item-StQty"><?php echo $row["remainingStock"] ?></div>
                  <div class="item-table-cell store-item-Status"><?php echo $status = ($row["status"] == 1)? "Available":"Unavailable";?></div>
                  <div class="item-table-cell store-item-Price">$<?php echo $row["stockPrice"] ?></div>
                  <div class="item-table-cell store-item-Qty">
                    <input type="number" id="<?php echo $_GET['id'] ?>-<?php echo $row["goodsNumber"]?>" onChange="checkQty(this.id)" min="1" max="<?php echo $row["remainingStock"] ?>" step="1" value="1"/>
                  </div>
                  <div class="item-table-cell store-item-Action">
                    <button type="button" name="button" onclick="addToCart(<?php echo $_GET['id'] ?>, <?php echo $row["goodsNumber"]?>)">Add to Cart</button>
                  </div>
                </div>
              <?php } ?>
              </div>
            </div>
          </div>
          <div class="checkout-button">
            <a href="cart"><button type="button" name="button">Check out <i class="fas fa-shopping-cart"></i></button></a>
          </div>
        </div>
      </div>
    </div>
    <footer><p>Hong Kong Cube Shop Shopping System © 2020</p></footer>
    <script src="static/js/shopping.js"></script>
  </body>
</html>
