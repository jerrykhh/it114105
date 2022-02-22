*&---------------------------------------------------------------------*
*& Report  ZASG_YOURNAME_UP                                            *
*&                                                                     *
*&---------------------------------------------------------------------*
*&                                                                     *
*&                                                                     *
*&---------------------------------------------------------------------*

REPORT ZASG_YOURNAME_UP MESSAGE-ID Z_MESSAGE_YOURNAME
TYPES:  BEGIN OF ZASG_DB,
            MANDT TYPE MANDT,
            CARRID TYPE S_CARRID,
            CARRNAME TYPE S_CARRNAME,
            CONNID TYPE S_CONN_ID,
            FLTIME TYPE S_FLTIME,
            FLDATE TYPE S_DATE,
            CUSTOMID TYPE S_CUSTOMER,
            LUGGWEIGHT TYPE S_LUGWEIGH,
            WUNIT TYPE GEWEI,
            PASSNAME TYPE S_PASSNAME,
            LOCCURAM TYPE S_L_CUR_PR,
            PRICE TYPE S_PRICE,
            CURRENCY TYPE S_CURRCODE,
            PLANETYPE TYPE S_PLANETYE,
        END OF ZASG_DB.

DATA ITAB TYPE TABLE OF ZASG_DB.
DATA WA TYPE ZASG_DB.
DATA ZASGWA TYPE ZASG_YOURNAME.
DATA MAXTIME LIKE WA-FLTIME VALUE '660'.

SELECT SBOOK~MANDT SBOOK~CARRID SCARR~CARRNAME SBOOK~CONNID
SPFLI~FLTIME
SBOOK~FLDATE SBOOK~CUSTOMID SBOOK~LUGGWEIGHT SBOOK~WUNIT
SBOOK~PASSNAME
SBOOK~LOCCURAM SFLIGHT~PRICE SBOOK~LOCCURKEY SFLIGHT~PLANETYPE
    FROM SBOOK
        INNER JOIN SCARR
                ON SBOOK~CARRID = SCARR~CARRID
    INNER JOIN SPFLI
                ON SBOOK~CONNID = SPFLI~CONNID
                AND SPFLI~CARRID = SCARR~CARRID
    INNER JOIN SFLIGHT
                ON SBOOK~FLDATE = SFLIGHT~FLDATE
                AND SFLIGHT~CARRID = SCARR~CARRID
                AND SFLIGHT~CONNID = SPFLI~CONNID

    INTO TABLE ITAB.

LOOP AT ITAB INTO WA.
      DATA: EARNING TYPE P DECIMALS 2,
            HKEARNING TYPE P DECIMALS 2.
      EARNING = WA-LOCCURAM - WA-PRICE.
      CALL FUNCTION 'Z_YOURNAME_FN1'
        EXPORTING
          CURRENCY          = WA-CURRENCY
          EARNING           = EARNING
        IMPORTING
          RESULT            = HKEARNING
        EXCEPTIONS
          NO_CURRENCY       = 1
          OTHERS            = 2

    IF SY-SUBRC <> 0.
        MESSAGE I001(Z_MESSAGE_A).
    ELSE.
            DATA: RESULT_LGWEIGHT TYPE S_LUGWEIGH,
                  H_FLAG TYPE FLAG.
            CALL FUNCTION 'Z_YOURNAME_FN2'
              EXPORTING
                WTUNIT         = WA-WUNIT
                LWEIGHT        = WA-LUGGWEIGHT
              IMPORTING
                LGWEIGHT       = RESULT_LGWEIGHT
                      .
            IF SY-SUBRC <> 0.
              MESSAGE I002(Z_MESSAGE_A).
            ELSE.
              IF RESULT_LGWEIGHT > 14 OR WA-FLTIME > MAXTIME.
                H_FLAG = 'X'.
              ELSE.
                H_FLAG = ' '.
              ENDIF.
              ZASGWA-MANDT = WA-MANDT.
              ZASGWA-CARRID = WA-CARRID.
              ZASGWA-CONNID = WA-CONNID.
              ZASGWA-CUSTOMID = WA-CUSTOMID.
              ZASGWA-FLDATE = WA-FLDATE.
              ZASGWA-FLTIME = WA-FLTIME.
              ZASGWA-CARRNAME = WA-CARRNAME.
              ZASGWA-LUGGWEIGHT = RESULT_LGWEIGHT.
              ZASGWA-WUNIT = WA-WUNIT.
              ZASGWA-PASSNAME = WA-PASSNAME.
              ZASGWA-H_FLAG = H_FLAG.
              ZASGWA-HKEARNING = HKEARNING.
              ZASGWA-CURRENCY = WA-CURRENCY.
              ZASGWA-PLANETYPE = WA-PLANETYPE.
              INSERT INTO ZASG_YOURNAME VALUES ZASGWA.
            ENDIF.
    ENDIF.
ENDLOOP.

IF SY-SUBRC = 4.
    MESSAGE i002.
ELSEIF SY-SUBRC <> 0.
    MESSAGE i000.
ELSE.
    WRITE: /(5)  'MANDT',
            (5)  'CARRID',
            (20) 'CARRNAME',
            (6)  'CONNID',
            (10) 'FLTIME';,
            (12) 'FLDATE',
            (12) 'CUSTOMID',
            (15) 'LUGGWEIGHT',
            (5)  'WUNIT',
            (25) 'PASSNAME',
            (6)  'H_FLAG',
            (12) 'HKEARNING',
            (8)  'CURRENCY',
            (10) 'PLANETYPE'.

    SELECT * FROM ZASG_YOURNAME INTO ZASGWA.
        WRITE: / ZASGWA-MANDT UNDER 'MANDT',
                ZASGWA-CARRID UNDER 'CARRID',
                ZASGWA-CARRNAME UNDER 'CARRNAME',
                ZASGWA-CONNID UNDER 'CONNID',
                ZASGWA-FLTIME UNDER 'FLTIME',
                ZASGWA-FLDATE UNDER 'FLDATE',
                ZASGWA-CUSTOMID UNDER 'CUSTOMID',
                ZASGWA-LUGGWEIGHT UNDER 'LUGGWEIGHT',
                ZASGWA-WUNIT UNDER 'WUNIT',
                ZASGWA-PASSNAME UNDER 'PASSNAME',
                ZASGWA-H_FLAG UNDER 'H_FLAG',
                ZASGWA-HKEARNING UNDER  'HKEARNING' LEFT-JUSTIFIED,
                ZASGWA-CURRENCY UNDER 'CURRENCY',
                ZASGWA-PLANETYPE UNDER 'PLANETYPE'.
    ENDSELECT.
ENDIF.

