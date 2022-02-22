*&---------------------------------------------------------------------*
*& Report  Z_06_SELECT_SCR                                             *
*&                                                                     *
*&---------------------------------------------------------------------*
*&                                                                     *
*&                                                                     *
*&---------------------------------------------------------------------*

REPORT Z_06_SELECT_SCR
PARAMETERS: P_CARRID TYPE SBOOK-CARRID OBLIGATORY,
            P-FLDATE TYPE SBOOK-FLDATE DEFAULT SY-DATUM,
            P-SMOKER AS CHECKBOX.