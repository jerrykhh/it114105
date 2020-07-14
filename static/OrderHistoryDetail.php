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
            <li>
              <a href="" class="selected">
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
          <div class="back-button">
            <a href="orderhistory"><button type="button"><i class="fas fa-long-arrow-alt-left"></i> Back</button></a>
          </div>
          <div class="order-detials">
            <span>Order No: 1</span>
            <span>Order Date: 30/02/2020</span>
          </div>
          <div class="status">Order Completed</div>
          <br>
          <div class="order-information">
            <h4>Shop name: ABC Shop</h4>
            <p>Shipment To:</p>
            <p>B33/F Jone Mult Ind Bldg169 Wai Yip St</p>
          </div>
          <div class="order-table">
            <div class="order-table-heading">
              <div class="order-table-head">Goods ID</div>
              <div class="order-table-head">Goods Name</div>
              <div class="order-table-head">Quantity</div>
              <div class="order-table-head">Selling Price</div>
            </div>
            <div class="order-table-body">
              <div class="order-table-row">
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
              </div>
              <div class="order-table-row">
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
              </div>
              <div class="order-table-row">
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
              </div>
              <div class="order-table-row">
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
              </div>
              <div class="order-table-row">
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
              </div>
            </div>
          </div>
          <table class="order-total">
            <tr>
              <td class="item-total">Total </td>
              <td class="total">$10000.00</td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <footer><p>Hong Kong Cube Shop Shopping System © 2020</p></footer>
  </body>
</html>
