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
            <li>
              <a href="orderhistory" class="">
                <span><i class="fas fa-history fa-lg"></i>Order Records</span>
              </a>
            </li>
            <li>
              <a href="profile" class="selected">
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
          <div class="account-profile">
            <h1>Account Profile</h1>
            <br><br>
            <h4>*Required Information</h4>
            <form action="updateprofile" method="POST">
            <div class="profile-table">
               <div class="profile-table-body">
                  <div class="profile-table-row">
                     <div class="profile-table-cell">Email Address:</div>
                     <div class="profile-table-cell">jerrykwok.khh@gmail.com</div>
                  </div>
                  <div class="profile-table-row">
                     <div class="profile-table-cell">Name:</div>
                     <div class="profile-table-cell">
                       <input type="text" name="fname" id="fname" value="Jerry" required/>
                       <input type="text" id="lname" name="lname" value="Kwok" required/>
                     </div>
                  </div>
                  <div class="profile-table-row">
                     <div class="profile-table-cell">Phone Number:</div>
                     <div class="profile-table-cell">
                       <input type="tel" id="tel" name="tel" value="12345678" required/>
                     </div>
                  </div>
               </div>
            </div>
            <input type="button" value="Save"/>
          </form>
          </div>
          <div class="account-password">
            <h1>Change Password</h1>
            <br>
            <form action="updaetpassword" method="POST" id="updatePasswordForm">
            <div class="profile-table">
              <div class="profile-table-body">
                <div class="profile-table-row">
                  <div class="profile-table-cell">Current Password:</div>
                  <div class="profile-table-cell">
                    <input type="password" name="currentPassowd" id="currentPassowd" value="">
                  </div>
                </div>
                <div class="profile-table-row">
                  <div class="profile-table-cell">New Password:</div>
                  <div class="profile-table-cell">
                    <input type="password" name="password" id="password" value="">
                  </div>
                </div>
                <div class="profile-table-row">
                  <div class="profile-table-cell">New Password Again:</div>
                  <div class="profile-table-cell"><input type="password" name="cfmPassword" id="cfmPassword" value=""></div>
                </div>
              </div>
            </div>
            <input type="button" onClick="profilePassword()" value="Save"/>
          </form>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
