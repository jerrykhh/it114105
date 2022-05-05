<?php
class UserProfile {
  private $customerEmail;
  private $firstName;
  private $lastName;
  private $phoneNumber;

  function __construct($customerEmail, $firstName, $lastName, $phoneNumber){
    $this->customerEmail = $customerEmail;
    $this->firstName = $firstName;
    $this->lastName = $lastName;
    $this->phoneNumber = $phoneNumber;
  }

  public function getCustomerEmail(){
    return $this->customerEmail;
  }

  public function getFirstName(){
    return $this->firstName;
  }

  public function getLastName(){
    return $this->lastName;
  }

  public function getPhoneNumber(){
    return $this->phoneNumber;
  }

}

 ?>
