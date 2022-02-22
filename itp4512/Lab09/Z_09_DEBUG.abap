*&---------------------------------------------------------------------*
*& Report  Z_09_DEBUG                                                  *
*&                                                                     *
*&---------------------------------------------------------------------*
*&                                                                     *
*&                                                                     *
*&---------------------------------------------------------------------*

REPORT  Z_09_DEBUG

TABLES SFLIGHT.
TYPES: BEGIN OF REC,
        CARRID LIKE SFLIGHT-CARRID,
        CONNID LIKE SFLIGHT-CONNID,
        FLDATE LIKE FLIGHT-FLDATE,
        PLANETYPE LIKE SFLIGHT-PLANETYPE,
       END OF REC.

DATA WA TYPE REC.
DATA ITABLE TYPE TABLE OF REC.

SELECT-OPTIONS S_CARRID FOR SFLIGHT-CARRID.
SELECT-OPTIONS S_CONNID FOR SFLIGHT-CONNID.
SELECT-OPTIONS S_FLDATE FOR SLIGHT-FLDATE.

WRITE: 11 'Airline', 23 'Connection No', 38 'Date', 53 'Plane Type'.
SELECT CARRID CONNID FLDATE PLANETYPE FROM SFLIGHT
    INTO TABLE ITABLE
    WHERE CARRID IN S_CARRID
    AND CONNID IN S_CONNID
    AND FLDATE IN S_FLDATE.

LOOP AT ITABLE INTO WA.
    WRITE: / WA-CARRID UNDER 'Airline', WA-CONNID UNDER 'Connection No',
            WA-FLDATE UNDER 'Date', WA-PLANETYPE UNDER 'Plane Type'.
ENDLOOP.