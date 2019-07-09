流水號

SELECT 'A' || TO_CHAR(TO_NUMBER(RPAD(SUBSTR (NVL(MAX (NO),'A' || TO_CHAR (SYSDATE, 'YYYYMMDD')), 2, 13),12,'0'))+1) NO
FROM dsrf10
WHERE NO LIKE 'A' || TO_CHAR (SYSDATE, 'YYYYMMDD') || '%'

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

LPAD用法( 將字串右靠，不足n長度，則左補string)

SELECT LPAD('Oracle', 10, 'X') "LPAD 範例" FROM dual;

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

RPAD用法( 將字串左靠，不足n長度，則右補string)

SELECT RPAD('Oracle', 10, 'X') "RPAD 範例" FROM dual;

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

LTRIM用法( 從字串最左邊去除所有set字元，set預設為空白)

SELECT LTRIM('XXXXOracle', 'X') "LTRIM 範例" FROM dual;

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

RTRIM用法( 從字串最右邊去除所有set字元，set預設為空白)

SELECT RTRIM('OracleXXXX', 'X') "RTRIM 範例" FROM dual;

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

REPLACE用法( 替換字串)

SELECT REPLACE('O1234e', '1234', 'racl') "REPLACE 範例" FROM dual;

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

LENGTH用法(字串長度)

SELECT LENGTH('abcedfg') "LENGTH 範例" FROM dual;

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

VM_CONCAT用法(相同的資料以,隔開結合在一個欄位上)

to_char(WM_CONCAT(DISTINCT(欄位)))

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

UNION用法 (查詢出兩個TABLE聯集的資料)

SELECT PO_NO FROM TABLE_A
union
SELECT PO_NO FROM TABLE_B

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

MINUS用法 (查詢出TABLE_A減去TABLE_B的資料)

SELECT PO_NO FROM TABLE_A
minus
SELECT PO_NO FROM TABLE_B

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

INTERSECT用法 (查詢兩個TABLE交集的資料)

SELECT PO_NO FROM TABLE_A
intersect
SELECT PO_NO FROM TABLE_B

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

between用法

WHERE TABLE between 110 and 118

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

EXISTS & NOT EXISTS 比對TABLE資料存在不存在

SELECT  ID, empl   FROM 表一 
WHERE  NOT EXISTS 
(SELECT  ID, empl  FROM 表二)

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

計算同一種類的數量各有多少

SELECT   mt_stockno, COUNT (*)
FROM fcmc23
GROUP BY mt_stockno

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

找到含有『特定字串』的所有Stored Procedure

SELECT DISTINCT s.NAME, s.TYPE
FROM all_source s
WHERE text LIKE '%fcmt17%'

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

搜尋欄位的資料長度

SELECT *
FROM FCEL08
WHERE LENGTH(EL_COLORNO) = 2;

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

查詢資料庫中所有TABLE設定資料

SELECT
C.TABLE_NAME, C.COLUMN_ID, C.COLUMN_NAME,
DATA_TYPE, DATA_LENGTH, DATA_PRECISION,
NULLABLE, R.COMMENTS
FROM
ALL_TAB_COLUMNS C
JOIN ALL_TABLES T ON
C.OWNER = T.OWNER AND C.TABLE_NAME = T.TABLE_NAME
JOIN ALL_TAB_COMMENTS U ON
C.OWNER = U.OWNER AND C.TABLE_NAME = U.TABLE_NAME
LEFT JOIN ALL_COL_COMMENTS R ON
C.OWNER = R.Owner AND
C.TABLE_NAME = R.TABLE_NAME AND
C.COLUMN_NAME = R.COLUMN_NAME
WHERE
C.TABLE_NAME  LIKE  'DSFA%'
ORDER BY C.TABLE_NAME, C.COLUMN_ID

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

查詢TABLE所有欄位設定資料

SELECT TABLE_NAME,COMMENTS
FROM
ALL_TAB_COMMENTS U
WHERE    U.TABLE_NAME  LIKE  'DSFA%' ORDER BY 1

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —
(+) 放置在可能沒有資料的 Table 一方

select t1.aa
     , t1.bb
     , t2.cc
     , t3.dd
  from tom1 t1
     , tom2 t2
     , tom3 t3
 where t1.aa = t2.aa
   and t2.cc = t3.cc(+)
 order by t1.aa;
 
— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —
 
DISTINCT用法(列出不重覆的欄位資料)

SELECT 
 DISTINCT Column_Name
FROM A-TABLE

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

UNION用法

select Column_Name
  from 表格名
 where Column_Name= 1
union
select Column_Name
  from 表格名
 where Column_Name= 2

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

CASE用法

select (CASE WHEN 表格名.Column_Name='T' THEN '符合' WHEN 表格名.Column_Name='F' THEN '不符合' WHEN 表格名.Column_Name='0' THEN '未檢驗' END) Column_Name FROM 表格名
取回第 n ~ m 筆資料

select *
  from (select rownum bRn
             , b.*
          from (select rownum aRn
                     , a.*
                  from DSIT000 a
                 order by a.FTY_ID
               ) b
       )
 where bRn between 5 and 10;
— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —
 
從 Table 隨機抓 N 筆資料

SELECT A.*
FROM ( SELECT * FROM 表格名 ORDER BY dbms_random.value) A
WHERE rownum <= N;

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

只建立結構相同的Table，不含資料(Where 1=1含資料)

create table 表格名 as
select *
from 表格名 where 1 = 0;

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

將B-TABLE資料倒入A-TABLE

INSERT INTO A-TABLE SELECT * FROM B-TABLE

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

把B-TABLE資料和A-TABLE資料做集合

JOIN B-TABLE ON B-TABLE.Column_Name= A-TABLE.Column_Name

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

將欄位內的字串做替換

SELECT replace(Column_Name,’Modified_Data’,’New_Data’) from A-TABLE

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

SUBSTR用法(擷取字串)

SELECT substr(Column_Name,Number_of_Start_Index,count_index)
from A-TABLE

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

NVL將資料庫為空的欄位回傳一個字串

SELECT NVL(Column_Name,’等於空要替換的字串’) from A-TABLE

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

DECODE用法(有IF的功能將資料符合條件的回傳)

SELECT  DECODE (Column_Name,'條件1','返回1','條件2','返回2','其餘返回') 
FROM A-TABLE;

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

抓系統時間

TO_DATE (TO_CHAR (SYSDATE, ‘YYYYMMDD’), ‘YYYYMMDD’)

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

群組化PK值抓項次中最新一筆資料

SELECT *
FROM A-TABLE a
WHERE EXISTS (SELECT 1
FROM (SELECT Column_NamePK, MAX (項次) 項次
FROM A-TABLE
GROUP BY Column_NamePK) b
WHERE a.Column_NamePK= b.Column_NamePK
AND a.項次= b.項次)

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

排序ASC小到大(預設) DESC大到小

SELECT * from A-TABLE ORDER BY Column_Name DESC

— — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —

將兩個欄位串起來合併顯示在同一個欄位上

SELECT Column_Name || Column_Name from A-TABLE


