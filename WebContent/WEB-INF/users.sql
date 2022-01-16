select * from users;

--users 테이블 삭제
drop table users;

--users 시퀀스 삭제, seq_users_no
drop sequence seq_users_no;

--users 테이블 생성
create table    users (
                no number(10), 
                id varchar2(20) UNIQUE, 
                password varchar2(20) NOT NULL, 
                name varchar2(20), 
                gender varchar2(10), 
                primary key (no) 
);
    
--users 시퀀스 생성
create sequence seq_users_no
increment by 1
start with 1
nocache;

--insert문 2개
insert into users
values(seq_users_no.nextval, 'aaa', '1234', '이산', 'male');

insert into users
values(seq_users_no.nextval, 'bbb', '1234', '기보배', 'female');


--commit
commit;

--select
select *
from users;