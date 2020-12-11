FUNCTION Z_FM_DIVIDE_B.
*"----------------------------------------------------------------------
*"*"Local interface:
*"  IMPORTING
*"      REFERENCE(N1) TYPE I
*"      REFERENCE(N2) TYPE I
*"  EXPORTING
*"      REFERENCE(R1) TYPE F
*"  EXCEPTION
*"      ZERO_NUMBER
*"----------------------------------------------------------------------

    IF N2 <> 0.
        R1 = N1 / N2.
    ELSE.
        RAISE ZERO_NUMBER.
    ENDIF.

ENDFUNCTION.