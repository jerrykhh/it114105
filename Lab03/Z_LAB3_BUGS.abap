*&---------------------------------------------------------------------*
*& Report  Z_LAB3_BUGS                                                 *
*&                                                                     *
*&---------------------------------------------------------------------*
*&                                                                     *
*&                                                                     *
*&---------------------------------------------------------------------*

REPORT Z_LAB3_BUGS
DATA:   price1(4) TYPE p VALUE '5678.9',
        pos TYPE i VALUE 5,
        len TYPE i VALUE 8.
WRITE:  / '----+----1----+--',
        / '12345678901234567'.
SKIP.
WRITE /(1) 'Accounting'.
WRITE /(5) 'HR'.
WRITE /    'Management'.
WRITE /2(2) price1.
WRITE AT /5(len) price1.
ULINE /(11).