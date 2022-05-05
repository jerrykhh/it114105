# ITP4513-Assignment (18-19) Internet &amp; Multimedia Application Development

# Requirements
## 1. Design for Dealer (Interface Design: 15 marks; Function: 35 marks)

### a. Register account into system
Managed to create a new dealer account into the system with all necessary information.
Required information during registration as below:
```
1. Dealer’s ID (It should be created by dealer as Primary Key)
2. Dealer’s Name
3. Phone Number
4. Address
5. Password
```

### b. Update dealer’s profile
Managed to view and update dealer’s information
Dealer’s information including:
```
1. Name
2. Address
3. Phone Number
4. Password
```

### c. Make the orders
Managed to make orders into the system with all necessary information
```
#### View product information
List of parts shown on site for dealer to choose (show the parts when status is “Available” and the stock quantity should be greater than zero)
#### The parts should include 2 stock status:
1. “Available”: Show only the available parts.
2. “Unavailable”: The parts are unavailable.


####  Required information for creating an order
1. Delivery Address:
- The address default as it registered.
2. Part Name & Quantity:
- Being able to order more than one parts in the same order.
- Order quantity cannot be greater than the stock quantity in Part table
<br>
*orderID should be generate automatically by the system as Primary Key
```

### d. View & update order records
Managed to view the orders with necessary information
```
#### Required information for viewing the orders
1. Order ID
2. Address
- Dealer address (Default) /New delivery address
3. Order Date
4. Part Number
5. Part name
6. Quantity
7. Status
8. Total Price
,etc.


#### The site should include 4 statuses:
1. “In processing”: The order was made
2. “Delivery”: The parts are delivering to the dealer
3. “Completed”: The dealer received the parts ordered
4. “Canceled”: The order is canceled by administrator


#### Required functions:
1. Being able to cancel the order even if the status is “In processing” stage
- Records should be able to delete from the database
2. Being able to make Confirmation when all parts are received by dealer
- e.g. Click buttons to confirm completed orders
- Order status should be able to change from “Delivery” to “Completed”
```


## 2. Design for Administrator (Interface Design: 15 marks; Function: 35 marks)

### a. Login to system
Managed to login into the system with all necessary information.
Required information during login
```
1. Email
2. Password
```

### b. View and edit parts’ information
Managed to create a page contain the list of parts

```
#### Required part information:
1. Part Number
2. Part Name
3. Stock Quantity
4. Stock Price
5. Display the email of the administrator who did the latest editing
,etc.
*partNumber should be generate automatically by the system as Primary Key.


#### Required functions:
- Create an “edit” button to make change of stock quantity and stock price
- Administrator can add in new parts /remove the parts (change
the stock status from “Available” to “Unavailable”)
- The latest modified email will be stored in Part table when the related administrator
add/update the part record
```


### c. Manage order
Managed to view the order with necessary information.
```
#### Required information for order page
1. Order ID
2. Dealer ID
3. Dealer Name
4. Order Date
5. Address
6. Part Number
7. Quantity
8. Total Price
,etc.

#### Required Functions:
- Deliver the parts when they are ready to deliver.
  -> Change the order status from “In Processing” to “Delivery”
- -> Deduct the quantity of delivered part from Part table
- Only dealer can change the “Delivery” stage to “Completed” stage, after they receive the
goods
#### Required functions:
- Being able to cancel order if the dealer wish to
  -> Change the order status to “Canceled”
```

# Additional requirements
```
a. Your web site should only use PHP as the server-side programming language (i.e. not ASP, ASP.NET, JSP, servlet etc.), however, you may use JavaScript and CSS for specific purposes. The database server used must be mySQL (version 5.0 or above).

b. In your PHP code, you must ensure to use the following parameter values for the following mySQL
database functions :
  $conn = mysqli_connect($hostname, $username, $password, $database);
set to the values below in a PHP script which is shared by the web pages :
  $hostname = "127.0.0.1";
  $database = "projectDB";
  $username = "root";
  $password = "";
```