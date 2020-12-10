#### ZCUST table

Delivery and Maintenance
| Delivery Class                | A                           |
|-------------------------------|-----------------------------|
| Data Browser/Table View Main. | Display/Maintenance Allowed |

Fields
| Field     | Key | Initi... | Data element | DTyp | Length | Decimal | Short Description                 |
|-----------|-----|----------|--------------|------|--------|---------|-----------------------------------|
| MAND      | V   | V        | MANDT        | CLNT | 3      | 0       | Client                            |
| KUNNR     | V   | V        | KUNNR        | CHAR | 10     | 0       | Customer Number 1                 |
| MOD_DATE  |     |          | EPSMODDAT    | DATS | 8      | 0       | Modification date                 |
| LNAME     |     |          | AD_LNAME     | CHAR | 35     | 0       | A person's last name (Addresses)  |
| FNAME     |     |          | AD_FNAME     | CHAR | 35     | 0       | A person's first name (Addresses) |
| VIP_FLAG  |     |          | FLAG         | CHAR | 1      | 0       | General Flag                      |
| DEL_FLAG  |     |          | FLAG         | CHAR | 1      | 0       | General FLag                      |
| SALES_VAL |     |          | W_PSALM      | CURR | 13     | 2       | Planned sales at retail price     |
| WAERS     |     |          | WAERS        | CUKY | 5      | 0       | Currency Key                      |


Currency/Quantity Fields
| Field     | Data element | DTyp | REference table | Ref.field | Short Description             |
|-----------|--------------|------|-----------------|-----------|-------------------------------|
| SALES_VAL | W_PSALW      | CURR | ZCUST           | WAERS     | Planned sales at retail price |


Table Data
| Customer | Modif.     | Lastname | Firstname | Flag(Del) | FLag(VIP) | Plnd sales SP | Crcy |
|----------|------------|----------|-----------|-----------|-----------|---------------|------|
| 12345    | 23.07.2018 | Porter   | John      | X         |           | 0.00          | HKD  |
| 12346    | 23.07.2018 | Porter   | May       |           |           | 0.00          | HKD  |
| 12347    | 23.07.2018 | Carter   | John      |           | X         | 9,876,543.21  | HKD  |
| 12348    | 23.07.2018 | Carter   | Peter     |           |           | 9,876,543.21  | JPY  |