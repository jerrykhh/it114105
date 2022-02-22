*&---------------------------------------------------------------------*
*& Report  Z_06_SELECT_SCR_2                                           *
*&                                                                     *
*&---------------------------------------------------------------------*
*&                                                                     *
*&                                                                     *
*&---------------------------------------------------------------------*

REPORT Z-06-Z_06_SELECT_SCR_2
TABLES SPFLI.
SELECT-OPTIONS S-CARRID FOR SPFLI-CARRID NO INTERVALS DEFAULT 'LH'.
SELECT-OPTIONS S-CONNID FOR SPFLI-CONNID NO-EXTENSION DEFAULT 99 TO 1999.

SELECT * FROM SPFLI WHERE CARRID IN S-CARRID
                    AND   CONNID IN S-CONNID.

                    WRITE: / SPFLI-CARRID, SPFLI-CONNID.

ENDSELECT.