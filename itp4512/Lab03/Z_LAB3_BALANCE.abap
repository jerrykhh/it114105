*&---------------------------------------------------------------------*
*& Report  Z_LAB3_BALANCE                                              *
*&                                                                     *
*&---------------------------------------------------------------------*
*&                                                                     *
*&                                                                     *
*&---------------------------------------------------------------------*

REPORT Z_LAB3_BALANCE
WRITE (22) 'Trial Balance' CENTERED.
ULINE   /(22).
WRITE:  /(10) 'Accounts', (5) 'Dr', (5) 'Cr'.
WRITE:  /'Cash', '1000' UNDER 'Dr'.
WRITE:  /'Payable', '1000' UNDER 'Cr'.
ULINE   /(22).
WRITE:  /'TOTAL', '1000' UNDER 'Dr', '1000' UNDER 'Cr'.
SKIP.
WRITE:  /(22) SY-DATUM RIGHT-JUSTIFIED, 1 SY-UNAME.