FUNCTION Z_YOURNAME_FN2.
*"----------------------------------------------------------------------
*"*"Local interface:
*"  IMPORTING
*"      REFERENCE(WTUNIT) TYPE UNIT
*"      REFERENCE(LWEIGHT) TYPE S_LUGWEIGH
*"  EXPORTING
*"      REFERENCE(LGWEIGHT) TYPE S_LUGWEIGH
*"----------------------------------------------------------------------

    IF WIUNIT CS 'KG'.
        LGWEIGHT = LWEIGHT.
    ELSE. 
        LGWEIGHT = LWEIGHT / '2.2'.
    ENDIF.

ENDFUNCTION.