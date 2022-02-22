*&---------------------------------------------------------------------*
*& Report  Z_05_INT_TABLE                                              *
*&                                                                     *
*&---------------------------------------------------------------------*
*&                                                                     *
*&                                                                     *
*&---------------------------------------------------------------------*

REPORT Z_05_INT_TABLE

TYPES:  BEGIN OF INT_REC.
            CUSTOMER TYPE C LENGTH 5,
            LNAME    TYPE C LENGTH 9,
            FNAME    TYPE C LENGTH 9,
        END OF INT_REC.

DATA: WA     TYPE INT_REC,
      ITABLE TYPE TABLE OF INT_REC.

WA-CUSTOMER = '12345'
WA-LNAME = 'Porter'.
WA-FNAME = 'John'.
APPEND WA TO ITABLE.

WA-CUSTOMER = '12346'.
WA-LNAME = 'Porter'.
WA-FNAME = 'May'.
APPEND WA TO ITABLE.

WA-CUSTOMER = '12347'.
WA-LNAME = 'Carter'.
WA-FNAME = 'John'.
APPEND WA TO ITABLE.

WA-CUSTOMER = '12348'.
WA-LNAME = 'Porter'.
WA-FNAME = 'Peter'.
APPEND WA TO ITABLE.

WRITE: / 'Customer', 'Last Name', 'First Name'.
ULINE / (29).

LOOP AT ITABLE INTO WA.
    WRITE: / WA-CUSTOMER UNDER 'Customer'.
             WA-LNAME UNDER 'Last Name',
             WA-FNAME UNDER 'First Name'.
ENDLOOP.