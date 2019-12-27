-- 게시판 테이블
create table board (
bno 	int not null auto_increment, 		-- 게시물 번호
title 	varchar(50) not null,				-- 게시물 제목
content	text not null,						-- 게시물 내용
writer	varchar(30) not null,				-- 게시물 작성자
regDate timestamp not null default now(),	-- 등록일
viewCnt int default 0,						-- 조회수
primary key(bno)
);

commit;

desc board;

insert into board(title, content, writer)
values ("게시물1", "게시물 내용1", "작성자");

insert into board(title, content, writer)
values ("게시물2", "게시물 내용2", "작성자");

insert into board(title, content, writer)
values ("게시물2", "게시물 내용3", "작성자");

insert into board(title, content, writer)
	select title, content, writer from board;
    
select * from board;

select bno, title, writer, regDate, viewCnt
from board
order by bno desc limit 30, 10;
-- Limit 구문은 select 문에 의해 반환되는 행 수를 제한하는 데 사용된다.
-- 두 인자값 중에 첫번째 인자값은 반환 될 첫번째 행의 오프셋을 가리키며,
-- 두번째는 반환 될 최대 행 수를 가리킨다.
