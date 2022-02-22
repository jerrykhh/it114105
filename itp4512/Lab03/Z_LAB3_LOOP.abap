*&---------------------------------------------------------------------*
*& Report  Z_LAB3_LOOP                                                 *
*&                                                                     *
*&---------------------------------------------------------------------*
*&                                                                     *
*&                                                                     *
*&---------------------------------------------------------------------*

REPORT Z_LAB3_LOOP
DATA:   N1 TYPE I,
        N2 TYPE I VALUE 1,
        N3 TYPE I.
WRITE: / 'Mathematical Statment', 
        30 'Value'.
ULINE.
DO 10 TIMES.
    CHECK SY-INDEX < 0.
    N3 = SY-INDEX.
    CASE N3.
        WHEN 2.
            WRITE: / 'Square of', 15(7) n3, 'is'.
            N1 = SY-INDEX ** 2.
        WHEN 4.
            WRITE: / 'Square root of', 15(7) n3, 'is'.
            N1 = SQRT(N3).
        WHEN 6.
            WRITE: / 'Sum of 1 to ', 15(7) n3, 'is'.
                N1 = 0.
                WHILE N2 <= N3.
                    ADD N2 TO N1.
                    ADD 1 TO N2.
                ENDWHILE.
        WHEN OTHERS.
            WRITE: / 'Factorial of', 15(7) n3, 'is'.
            N1 = 1.
            DO N3 TIMES.
                MULTIPLY N1 BY SY-INDEX.
            ENDDO.
    ENDCASE.
    WRITE (8) N1 UNDER 'Value'.
ENDDO.

        