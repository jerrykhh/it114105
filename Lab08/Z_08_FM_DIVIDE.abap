*&---------------------------------------------------------------------*
*& Report  Z_08_FM_DIVIDE                                              *
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

IF NUMBER2 = 0.
    MESSAGE I004(Z_MESSAGE_B).
ELSE.
    CALL FUNCTION 'Z_FM_DIVIDE_B'
     EXPORTING
        N1 = NUMBER1
        N2 = NUMBER2
     IMPORTING
        R1 = REUSLT.

    MOVE RESULT TO P_RESULT.
    WRITE: / 'Result:', P_RESULT.
ENDIF.