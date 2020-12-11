*&---------------------------------------------------------------------*
*& Report  Z_07_MESSAGE                                                *
*&                                                                     *
*&---------------------------------------------------------------------*
*&                                                                     *
*&                                                                     *
*&---------------------------------------------------------------------*

REPORT Z_07_MESSAGE MESSAGE-ID Z_MESSAGE_B

TABLES SPFLI.

SELECT-OPTIONS S_CARRID FOR SPFLI-CARRID.
SELECT * FROM SPFLI WHERE CARRID IN S_CARRID.
    WRITE: / SPFLI-CARRID, SPFLI-CONNID, SPFLI-CITYFROM, SPFLI-CITYTO.
ENDSELECT.

IF SY-SUBRC = 4.
    MESSAGE I002 WITH 'SPFLI'.
ELSEIF SY-SUBRC <> 0.
    MESSAGE I000.
ENDIF.