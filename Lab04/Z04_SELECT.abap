*&---------------------------------------------------------------------*
*& Report  Z04_SELECT                                                  *
*&                                                                     *
*&---------------------------------------------------------------------*
*&                                                                     *
*&                                                                     *
*&---------------------------------------------------------------------*

REPORT Z04_SELECT

TABLES ZCUST.

SELECT * FROM ZCUST.
    WRITE: / ZCUST-KUNNR, 
             ZCUST-MOD_DATE,
             ZCUST-LNAME,
             ZCUST-FNAME,
             ZCUST-VIP_FLAG,
             ZCUST-DEL_FLAG,
             ZCUST-SALES_VAL,
             ZCUST-WAERS.
ENDSELECT.