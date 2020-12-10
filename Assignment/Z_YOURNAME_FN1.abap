FUNCTION Z_YOURNAME_FN1.
*"----------------------------------------------------------------------
*"*"Local interface:
*"  IMPORTING
*"      REFERENCE(CURRENCY) TYPE S_CURRCODE
*"      REFERENCE(EARNING) TYPE AWKGR
*"  EXPORTING
*"      REFERENCE(RESULT) TYPE AWKGR
*"  EXCEPTIONS
*"      NO_CURRENCY
*"----------------------------------------------------------------------

  IF CURRENCY EQ 'USD'.
    RESULT = '+7.8' * EARNING.
  ELSEIF CURRENCY EQ 'EUR'.
    RESULT = '+8.4' * EARNING.
  ELSEIF CURRENCY EQ 'AUD'.
    RESULT = '+5.2' * EARNING.
  ELSEIF CURRENCY EQ 'SGD'.
    RESULT = '+5.6' * EARNING.
  ELSEIF CURRENCY EQ 'JPY'.
    RESULT = '+0.07' * EARNING.
  ELSE.
    RAISE NO_CURRENCY.
  ENDIF.

ENDFUNCTION.