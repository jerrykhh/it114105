*&---------------------------------------------------------------------*
*& Report  Z_03_LOOP                                                   *
*&                                                                     *
*&---------------------------------------------------------------------*
*&                                                                     *
*&                                                                     *
*&---------------------------------------------------------------------*

REPORT Z_03_LOOP
WRITE 'LOOP'.
DATA CO TYPE I.
ULINE /.
DO 10 TIMES.
    CO = SY-INDEX MOD 4.
    IF CO EQ 8.
        CONTINUE.
    ELSEIF CO EQ 2.
        WRITE / SY-INDEX.
    ELSE.
        WRITE / 'This is odd'.
    ENDIF.
ENDDO.