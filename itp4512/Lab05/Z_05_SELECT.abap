*&---------------------------------------------------------------------*
*& Report  Z_05_SELECT                                                 *
*&                                                                     *
*&---------------------------------------------------------------------*
*&                                                                     *
*&                                                                     *
*&---------------------------------------------------------------------*

REPORT Z_05_SELECT

DATA WA TYPE ZCUST.
DATA ITAB TYPE TABLE OF ZCUST.

WRITE:  /5(5) 'Cust ID',
        20 'First Name',
        40 'Last Name'.
        60 'Sales Val'.

SELECT * FROM ZCUST INTO TABLE ITAB
         WHERE VIP_FLAG <> 'X'

LOOP AT ITAB INTO WA.
    WRITE: / WA-KUNNR UNDER 'Cust ID',
             WA-FNAME UNDER 'First Name',
             WA-LNAME UNDER 'Last Name',
             WA-SALES_VAL UNDER 'Sales Val'.
ENDLOOP.