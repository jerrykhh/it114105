#### ZASG_YOURNAME TABLE

| Field      | Key | Initi | Data element | DTyp | Length | Decimal | Short Description         |
|------------|-----|-------|--------------|------|--------|---------|---------------------------|
| MANDT      | V   | V     | MANDT        | CLNT | 3      | 0       | Client                    |
| CARRID     | V   | V     | S_CARRID     | CHAR | 3      | 0       | Airline Code              |
| CONNID     | V   | V     | S_CONN_ID    | NUMC | 4      | 0       | Flight Connection Number  |
| CUSTOMID   | V   | V     | S_CUSTOMER   | NUMC | 8      | 0       | Customer Number           |
| FLDATE     | V   | V     | S_DATE       | DATS | 8      | 0       | Flight date               |
| FLTIME     |     |       | SFLTIME      | INT4 | 10     | 0       | Flight time               |
| CARRNAME   |     |       | S_CARRNAME   | CHAR | 20     | 0       | Airline name              |
| LUGGWEIGHT |     |       | S_LUGWEIGH   | QUAN | 8      | 4       | Weight of Luggage         |
| WUNIT      |     |       | GEWEI        | UNIT | 3      | 0       | Weight Unit               |
| PASSNAME   |     |       | S_PASSNAME   | CHAR | 25     | 0       | Name of the Passenger     |
| H_FLAG     |     |       | FLAG         | CHAR | 1      | 0       | General Flag              |
| HKEARNING  |     |       | AWKGR        | CURR | 15     | 2       | Amount                    |
| CURRENCY   |     |       | S_CURRCODE   | CUKY | 5      | 0       | Local currency of airline |
| PLANETYPE  |     |       | S_PLANETYE   | CHAR | 10     | 0       | PLANE TYPE                |


#### Message class Z_MESSAGE_YOURNAME 

| MessageId | Message short text         |
|-----------|----------------------------|
| 000       | System Erro                |
| 001       | Data not found in table &1 |
| 002       | Insert Data Error          |

#### Function module Z_YOURNAME_FN1

Import 
| Parameter Name | Type | Associated Type | Default value | Opt.. | Pa.. | Short text                |
|----------------|------|-----------------|---------------|-------|------|---------------------------|
| CURRENCY       | TYPE | S_CURRCODE      |               |       |      | Local currency of airline |
| EARNING        | TYPE | AWKGR           |               |       |      | Amount                    |


Export
| Parameter Name | Type | Associated Type | Pass Va.. | Short text          |
|----------------|------|-----------------|-----------|---------------------|
| RESULT         | TYPE | AWKGR           |           | ABAP Book Customers |

Exceptions
| Parameter Name | Type spec.. | Associated Type | Pass Va.. | Short text          |
|----------------|-------------|-----------------|-----------|---------------------|
| RESULT         | TYPE        | AWKGR           |           | ABAP Book Customers |


#### Function module Z_YOURNAME_FN2
Import
| Parameter Name | Type | Associated Type | Default value | Opt.. | Pa.. | Short text        |
|----------------|------|-----------------|---------------|-------|------|-------------------|
| WTUNIT         | TYPE | UNIT            |               |       |      | Unit              |
| LWEIGHT        | TYPE | S_LUGWEIGH      |               |       |      | Weight of Luggage |

Export
| Parameter Name | Type | Associated Type | Pass Va.. | Short text        |
|----------------|------|-----------------|-----------|-------------------|
| LGWEIGHT       | TYPE | S_LUGWEIGH      |           | Weight of Luggage |