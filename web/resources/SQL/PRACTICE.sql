

-- ========= ************* 전체 테이블 드랍 

DROP TABLE PRACTICE_MEMBER;
DROP TABLE PRACTICE_NOTICE;
DROP TABLE PRACTICE_BOARD;


---------------- 회원 테이블 -------------------

CREATE TABLE PRACTICE_MEMBER (
    USER_NO NUMBER,
    USER_ID VARCHAR2(30) UNIQUE, 
    USER_PWD VARCHAR2(100) NOT NULL,
    USER_NAME VARCHAR2(15) NOT NULL, 
    USER_PHONE VARCHAR2(20),
    USER_ADDRESS VARCHAR2(300), 
    USER_AGE NUMBER(3), 
    USER_ENDATE DATE DEFAULT SYSDATE,
    USER_STATUS VARCHAR2(10) DEFAULT 'Y' CHECK (USER_STATUS IN ('Y', 'N')),
    USER_ROLES NUMBER DEFAULT 0
); 

COMMIT;

CREATE SEQUENCE SEQ_MNO
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

INSERT INTO PRACTICE_MEMBER VALUES (SEQ_MNO.NEXTVAL,'admin','x61Ey612Kl2gpFL56FT9weDnpSo4AV8j8+qx2AuTHdRyY036xxzTTrw10Wq3+4qQyB+XURPWx1ONxp3Y3pB37A=='
                        ,'admin','010-0000-0000','서울시',100, default, default, default);

commit;


----------------- 공지사항 -------------------


CREATE TABLE PRACTICE_NOTICE (
    N_NO NUMBER UNIQUE, 
    N_TITLE VARCHAR2(300) NOT NULL,
    N_USERNAME VARCHAR(15) NOT NULL,
    N_USERID VARCHAR(30) NOT NULL,
    N_CONTENT VARCHAR2(2000) NOT NULL, 
    N_COUNT NUMBER,
    N_DATE DATE DEFAULT SYSDATE,
    N_STATUS VARCHAR2(3) DEFAULT 'Y' CHECK (N_STATUS IN('Y','N'))
); 

COMMIT;

CREATE SEQUENCE SEQ_NNO
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

COMMIT;








------------- 게시글 --------------------


CREATE TABLE PRACTICE_BOARD (
      B_NO NUMBER PRIMARY KEY,
      B_TYPE NUMBER NOT NULL,
      B_TITLE VARCHAR2(500) NOT NULL,
      B_USERNAME VARCHAR2(15) NOT NULL,
      B_USERID VARCHAR2(30),
      B_CONTENT VARCHAR2(3000) NOT NULL,
      B_COUNT NUMBER DEFAULT 0,
      B_BOARDFILE VARCHAR2(600),
      B_DATE DATE DEFAULT SYSDATE,
      B_STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (B_STATUS IN ('Y', 'N'))
);

COMMIT;

CREATE SEQUENCE SEQ_BNO
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;


