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
              <a href="home" class="">
                <span><i class="fas fa-shopping-bag fa-lg"></i>Order</span>
              </a>
            </li>
            <li id="cart">
              <a href="#" class="selected">
                <span>
                  <div class="item-count"><?php echo count($data->getCartList()) ?></div>
                  Cart
                </span>
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
            <a href="home"><button type="button"><i class="fas fa-long-arrow-alt-left"></i> Back</button></a>
          </div>
          <form action="checkout" method="post">
          <div class="order-information">
            <h2>Shop name: <?php echo $data->getStoreName() ?></h2>
          </div>
          <br>
          <p><h3>Pick up To:</h3><br>
            <div class="selectWrapper">
              <select name="address" id="address">
              <?php foreach($data->getStoreInf() as $row){?>
                  <option value="<?php echo $row["shopID"] ?>"><?php echo $row["address"] ?></option>
              <?php } ?>
              </select>
            </div>

          </p>
          <div class="order-table">
            <div class="order-table-heading">
              <div class="order-table-head">Goods ID</div>
              <div class="order-table-head">Goods Name</div>
              <div class="order-table-head">Quantity</div>
              <div class="order-table-head">Status</div>
              <div class="order-table-head ">Selling Price</div>
              <div class="order-table-head store-item-Action"></div>
            </div>
            <div class="order-table-body">
            <?php $sum = 0.0; ?>
            <?php foreach($data->getCartList() as $row){?>
              <?php $sum += $row->getPrice() * $row->getQty(); ?>
              <div class="order-table-row">
                <div class="order-table-cell"><?php echo $row->getId() ?></div>
                <div class="order-table-cell"><?php echo $row->getName() ?></div>
                <div class="order-table-cell store-item-Qty"><input type="number" name="<?php echo $row->getId() ?>" id="<?php echo $row->getId() ?>" onChange="checkQty(this.id)" min="1" max="<?php echo $row->getStock() ?>" step="1" value="<?php echo $qty = ($row->getQty() > $row->getStock())? $row->getStock() : $row->getQty();  ?>"/></div>
                <div class="order-table-cell"><?php echo $status = ($row->getStatus() == 1) ? "Available": "Unavailable" ?></div>
                <div class="order-table-cell store-item-price">$<?php echo $row->getPrice() ?></div>
                <div class="order-table-cell">
                  <button type="button" name="button" onclick="removeInCart(<?php echo $row->getId() ?>)">Remove</button>
                </div>
              </div>
            <?php } ?>
            </div>
          </div>
          <div class="payment-button">
            <input type="hidden" name="store" id="store" value="<?php echo $data->getStoreId() ?>">
            <a href="checkout"><button type="submit" name="button">Submit <i class="fas fa-shopping-cart"></i></button></a>
          </div>
        </form>
        </div>
      </div>
    </div>
    <footer><p>Hong Kong Cube Shop Shopping System © 2020</p></footer>
    <script src="static/js/shopping.js"></script>
    <script>
      var xhr = new XMLHttpRequest();
      var url = "viewCart";
      var json;
      var storeId;
      xhr.open("GET", url);
      xhr.send();
      xhr.onreadystatechange = function () {
          if (xhr.readyState === 4 && xhr.status === 200) {
              json = JSON.parse(xhr.responseText);
              console.log(json);
              if(json.statue && json.itemCount < 1){
                  location.replace("home?cart");
              }
          }
      };
    </script>
  </body>
</html>
