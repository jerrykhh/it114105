*&---------------------------------------------------------------------*
*& Report  Z_05_UPDATE                                                 *
*&                                                                     *
*&---------------------------------------------------------------------*
*&                                                                     *
*&                                                                     *
*&---------------------------------------------------------------------*

REPORT Z_05_UPDATE

DATA WA TYPE ZCUST.
WA-KUNNR = '0000012345'.

SELECT * FROM ZCUST INTO WA WHERE KUNNR = WA-KUNNR.
    WRITE: / WA-KUNNR, WA-MOD_DATE, WA-SALES_VAL.
ENDSELECT.

WA-MOD_DATE = SY-DATUM.
WA-SALES_VAL = 123456.

UPDATE ZCUST SET MOD_DATE = WA-MOD_DATE 
                 SALES_VAL = WA-SALES_VAL
             WHERE KUNNR = WA-KUNNR.
            
SELECT * FROM ZCUST INTO WA WHERE KUNNR = WA-KUNNR.
    WRITE: / WA-KUNNR, WA-MOD_DATE, WA-SALES_VAL.
ENDSELECT.