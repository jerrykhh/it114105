*&---------------------------------------------------------------------*
*& Report  Z_06_SELECT_SCR_3                                           *
*&                                                                     *
*&---------------------------------------------------------------------*
*&                                                                     *
*&                                                                     *
*&---------------------------------------------------------------------*

REPORT Z_06_SELECT_SCR_3

TABLES SBOOK.
TABLES SPFLI
SELECTION-SCREEN BEGIN OF BLOCK OPTIONS WITH FRAME TITLE OP1.
    PARAMETERS: R1 RADIOBUTTON GROUP G1.
                R2 RADIOBUTTON GROUP G1 DEFAULT 'X'.
                R3 RADIOBUTTON GROUP G1.
    SELECT-OPTIONS: S_CARRID FOR SBOOK-CARRID,
                    S_CONNID FOR SBOOK-CONNID NO INTERVALS.
                    S_FLDATE FOR SBOOK-FLDATE NO TNTERVALS DEFAULT SY-DATUM.
SELECTION-SCREEN END OF BLOCK OPTIONS.

TYPES: BEGIN OF REC,
       CARRID   TYPE SBOOK-CARRID,
       CONNID   TYPE SBOOK-CONNID,
       CITYFROM TYPE SPFLI-CITYFROM,
       CITYTO   TYPE SPFLI-CITYTO,
       FLDATE   TYPE SBOOK-FLDATE,
       CLASS    TYPE SBOOK-CLASS,
       END OF REC.

DATA: WA TYPE REC,
      ITAB TYPE TABLE OF REC.
      CLS TYPE SBOOK-CLASS.

IF R1 = 'X'.
    CLS = 'B'.
ELSEIF R2 = 'X'.
    CLS = 'Y'.
ELSE.
    CLS = 'F'.
ENDIF.

SELECT SBOOK~CARRID SBOOK~CONNID CITYFROM CITYTO FLDATE CLASS
    INTO TABLE ITAB
    FROM SBOOK INNER JOIN SPFLI
            ON SBOOK~CARRID = SPFLI~CARRID
            AND SBOOK~CONNID = SPFLI~CONNID
    WHERE SBOOK~CARRID IN S_CARRID
        AND SBOOK~CONNID IN S_CONNID
*       AND FLDATE IN S_FLDATE
        AND CLASS = CLS.

LOOP AT ITAB INTO WA.
    WRITE: / WA-CARRID, WA-CONNID, WA-CITYFROM, WA-CITYTO, WA-FLDATE, WA-CLASS.
ENDLOOP.

INITIALIZATION.
    OP1 = 'Options'.