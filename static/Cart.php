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
            <a href="home" class="">
              <span><i class="fas fa-shopping-bag fa-lg"></i>Order</span>
            </a>
          </li>
          <li id="cart">
            <a href="#" class="selected">
              <span>
                <div class="item-count">1</div>
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
          <div class="back-button">
            <a href="orderhistory"><button type="button"><i class="fas fa-long-arrow-alt-left"></i> Back</button></a>
          </div>
          <div class="order-information">
            <h2>Shop name: ABC Shop</h2>
          </div>
          <br>
          <p><h3>Pick up To:</h3><br>
            <div class="selectWrapper">
              <select>
                  <option>Lorem</option>
                  <option>Parturient</option>
                  <option>Euismod</option>
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
              <div class="order-table-row">
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell store-item-Qty"><input type="number" name="qtyApple" id="qtyBlue1" onChange="checkQty(this.id)" min="1" step="1" value="1"/></div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell store-item-Action">Goods Name</div>
                <div class="order-table-cell">
                  <button type="button" name="button">Remove</button>
                </div>
              </div>
              <div class="order-table-row">
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell store-item-Qty"><input type="number" name="qtyApple" id="qtyBlue2" onChange="checkQty(this.id)" min="1" step="1" value="1"/></div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">
                  <button type="button" name="button">Remove</button>
                </div>
              </div>
              <div class="order-table-row">
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell store-item-Qty"><input type="number" name="qtyApple" id="qtyBlue3" onChange="checkQty(this.id)" min="1" step="1" value="1"/></div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">
                  <button type="button" name="button">Remove</button>
                </div>
              </div>
              <div class="order-table-row">
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell store-item-Qty"><input type="number" name="qtyApple" id="qtyBlue4" onChange="checkQty(this.id)" min="1" step="1" value="1"/></div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">
                  <button type="button" name="button">Remove</button>
                </div>
              </div>
              <div class="order-table-row">
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell store-item-Qty"><input type="number" name="qtyApple" id="qtyBlue5" onChange="checkQty(this.id)" min="1" step="1" value="1"/></div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">Goods Name</div>
                <div class="order-table-cell">
                  <button type="button" name="button">Remove</button>
                </div>
              </div>
            </div>
          </div>
          <table class="checkout-total">
            <tr>
              <td class="item-total">Total </td>
              <td class="total">$10000.00</td>
            </tr>
          </table>
          <div class="payment-button">
            <a href="checkout"><button type="button" name="button">Submit <i class="fas fa-shopping-cart"></i></button></a>
          </div>
        </div>
      </div>
    </div>
    <footer><p>Hong Kong Cube Shop Shopping System © 2020</p></footer>
    <script src="static/js/shopping.js"></script>
  </body>
</html>
