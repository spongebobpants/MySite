create table    
    board (
    no number(10),              
    title varchar2(500),
    content varchar2(4000),
    hit number (10),
    reg_date date NOT NULL,
    user_no number(10) NOT NULL,
    primary key (no),
    CONSTRAINT board_fk FOREIGN KEY (user_no)
    REFERENCES users(no)
);

create sequence seq_board_no
increment by 1
start with 1
nocache;

insert into board
values(seq_board_no.nextval, '제목', '내용', '10', sysdate, 1);

insert into board
values(seq_board_no.nextval, '제목', '내용', '11', sysdate, 2);

insert into board
values(seq_board_no.nextval, '제목', '내용', '12', sysdate, 3);

--commit
commit;

--rollback
rollback;

--update
update board
set title 'title'
where no =10;

--select all
select *
from board bd, users us
where bd.user_no =us.no;

select * from board;
select * from users;
select * from guestbook;

--delete
delete from board where no =2;

--users
select  no,
        id,
        password,
        name,
        gender
from users;


--board
select bd.no,
    title,
    content,
    hit,
    to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') regDate,
    user_no,
    id,
    password,
    name
from board bd, users us
where bd.user_no = us.no
order by regDate desc;


delete from board where no =x;



