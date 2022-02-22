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
        <a href="#">
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
            <?php
            if(isset($_GET['pwd'])){
              echo '<div class="bs-callout bs-callout-danger"><h4>Change Password Failed</h4>current password incorrect</div>';
            }else if (isset($_GET['update'])){
              echo '<div class="bs-callout bs-callout-success"><h4>Update successful</h4>Profile Information is updated</div>';
            }else if (isset($_GET['inf'])){
              echo '<div class="bs-callout bs-callout-danger"><h4>Update Information Failed</h4>User Information update failed</div>';
            }

             ?>

          </div>
          <div class="account-profile">
            <h1>Account Profile</h1>
            <br><br>
            <h4>*Required Information</h4>
            <form action="profile" id="updateprofile" method="POST">
            <div class="profile-table">
               <div class="profile-table-body">
                  <div class="profile-table-row">
                     <div class="profile-table-cell">Email Address:</div>
                     <div class="profile-table-cell"><?php echo $data->getCustomerEmail() ?></div>
                  </div>
                  <div class="profile-table-row">
                     <div class="profile-table-cell">Name:*</div>
                     <div class="profile-table-cell">
                       <input type="text" name="fname" id="fname" value="<?php echo $data->getFirstName() ?>" placeholder="First Name" required/>
                       <input type="text" id="lname" name="lname" value="<?php echo $data->getLastName() ?>" placeholder="Last Name" required/>
                     </div>
                  </div>
                  <div class="profile-table-row">
                     <div class="profile-table-cell">Phone Number:*</div>
                     <div class="profile-table-cell">
                       <input type="tel" id="tel" name="tel" value="<?php echo $data->getPhoneNumber() ?>" required/>
                     </div>
                  </div>
               </div>
              <div class="profile-table-body">
                <div class="profile-table-row">
                  <div class="profile-pwd-h-table-cell">Current Password:</div>
                  <div class="profile-pwd-table-cell">
                    <input type="password" name="currentPassword" id="currentPassword" value="">
                  </div>
                </div>
                <div class="profile-table-row">
                  <div class="profile-pwd-h-table-cell">New Password:</div>
                  <div class="profile-pwd-table-cell">
                    <input type="password" name="password" id="password" value="">
                  </div>
                </div>
                <div class="profile-table-row">
                  <div class="profile-pwd-h-table-cell">New Password Again:</div>
                  <div class="profile-pwd-table-cell"><input type="password" name="cfmPassword" id="cfmPassword" value=""></div>
                </div>
              </div>
            </div>
            <input type="button" onClick="profilePassword()" value="Save"/><a href="#wraning-modal" style="margin-left: 50px; color: #3897f0; font-weight: 500;">DELETE Accont</a>
          </div>
          </form>
        </div>
      </div>
      <div class="modal-container" id="wraning-modal">
      <!-- Modal  -->
        <div class="modal">
          <div class="modal-message"></div>
          <h2>System Message</h2>
          <br>
          <p>If you click confirm button, system will delete all information about this account included the order record.</p>
          <br>
          <p>Password:</p>
          <br>
          <p><input type="password" name="deletePassword" id="deletePassword"></p>
          <br>
          <input type="button" class="btn-confirm" value="Confirm" onclick="deleteAccount()">
          <a href="#"><input type="button" class="btn-cancel" value="Cancel"></a>
        </div>
      <!-- Background, click to close -->
        <a class="modal-bg"></a>
     </div>
    </div>
    <footer><p>Hong Kong Cube Shop Shopping System © 2020</p></footer>
    <script src="./static/js/profile.js"></script>
    <script src="./static/js/initOrder.js"></script>
  </body>
</html>
