*&---------------------------------------------------------------------*
*& Report  Z_08_FM_DIVIDE_CHECK                                        *
*&                                                                     *
*&---------------------------------------------------------------------*
*&                                                                     *
*&                                                                     *
*&---------------------------------------------------------------------*

REPORT Z_08_FM_DIVIDE

PARAMETER NUMBER1 TYPE I.
PARAMETER NUMBER2 TYPE I.
DATA RESULT TYPE F.
DATA P_RESULT TYPE P DECIMALS 2.

CALL FUNCTION 'Z_FM_DIVIDE_B'
 EXPORTING
    N1          = NUMBER1
    N2          = NUMBER2
 IMPORTING
    R1          = REUSLT
 EXCEPTION
    ZERO_NUMBER = 1
    OTHER       = 2.

IF SY-SUBRC = 1.
    MESSAGE  I005(Z_MESSAGE_B) DISPLAY LIKE 'E'.
ELSE.
    MOVE RESULT TO P_RESULT.
    WRITE: / 'Result:', P_RESULT.
ENDIF.