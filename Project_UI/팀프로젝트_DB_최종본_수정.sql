/*회원정보*/
create table ui_member(
	member_idn		NUMBER			default 1				/*회원번호*/
    ,member_id 		varchar2(50) 	PRIMARY KEY			    /*회원ID*/
    ,member_nick 	varchar2(50) 	UNIQUE NOT NULL			/*회원닉네임*/
    ,member_pw 		varchar2(500) 	not null				/*회원비밀번호*/
    ,member_name 	varchar2(50) 	not null				/*회원명*/
	,email          varchar2(100)   UNIQUE					/*이메일*/
	,joindate		TIMESTAMP		default SYSTIMESTAMP	/*회원가입날짜*/
	,role			varchar(20)		default 'user'			/*회원권한(user,admin)*/
);

create sequence member_id_seq;

select * from ui_member;                                                                                 

/***********************************************************************/

/*상품정보*/
create table ui_item(
	item_id				NUMBER			default 1 primary key	/*상품ID*/
    ,seller_id          varchar2(50)                            /*판매자ID*/
	,buyer_id			varchar2(50)      						/*구매자ID*/
	,item_category      varchar2(100)							/*카테고리(남성의류, 여성의류, 아동의류, 신발, 가방, 액세서리*/
	,item_name			varchar2(100)	not null				/*상품명*/
	,item_status		varchar2(20)	not null				/*상품상태(sealed(미개봉), unsealed(중고))*/
	,item_price			NUMBER			not null				/*상품가격*/
	,item_hit			NUMBER			default 0				/*상품조회수*/
	,item_des			varchar2(4000)	not null				/*상품설명*/
	,item_inputdate		TIMESTAMP		default SYSTIMESTAMP	/*상품등록일자*/
	,item_updatedate	TIMESTAMP		default SYSTIMESTAMP	/*상품수정일자*/
	,deal_status		varchar2(20)	default 'newitem'		/*거래진행현황(newitem(신상),reserved(거래중),soldout(거래완료))*/
    ,rating			    NUMBER			default 0				/*회원평가(0~5)*/
    ,review             varchar2(4000)                          /*리뷰란*/
    ,review_inputdate   TIMESTAMP                               /*리뷰 작성날짜*/
    ,foreign key (seller_id) references ui_member(member_id) on delete cascade
);	

create SEQUENCE item_id_seq;

select * from ui_item;

/***********************************************************************/

/*이미지*/
create table ui_image(
	image_id		 	NUMBER			default 1 primary key	/*이미지ID*/
	,item_id			NUMBER									/*상품ID*/
	,image_name_ori	 	varchar2(4000)							/*이미지원본명*/
	,image_name_saved 	varchar2(4000)							/*이미지저장명*/
	,FOREIGN KEY (item_id) REFERENCES ui_item(item_id) ON delete CASCADE
);

create sequence image_id_seq;

select * from ui_image;

/***********************************************************************/

/*관심상품*/
create table ui_wish(
	wish_id			NUMBER			default 1 primary key	/*관심상품*/
	,item_id		NUMBER									/*상품ID*/
	,buyer_id		varchar2(50)							/*회원ID*/
	,FOREIGN KEY (item_id) REFERENCES ui_item(item_id)
	,FOREIGN KEY (buyer_id) REFERENCES ui_member(member_id) on delete cascade
);

create sequence wish_id_seq;

select * from ui_wish;

/***********************************************************************/

/*최근본상품*/
create table ui_recent(
	recent_id			NUMBER			default 1 PRIMARY KEY  	/*최근 본 상품 ID*/
	,buyer_id			varchar2(50)						  	/*회원ID*/
	,item_id			NUMBER								    /*상품ID*/
	,recent_inputdate	TIMESTAMP		default SYSTIMESTAMP	/*최근상품 조회날짜*/
	,FOREIGN key (item_id) references ui_item(item_id)
	,foreign KEY (buyer_id) references ui_member(member_id) on delete cascade
);

create sequence recent_id_seq;

select * from ui_recent;

/***********************************************************************/

/*채팅*/
create table ui_chat(
	chat_id				NUMBER			default 1 primary key	/*채팅ID*/
	,item_id			NUMBER									/*상품ID*/
	,seller_id			varchar2(50)							/*판매자ID*/
	,buyer_id			varchar2(50)							/*구매자ID*/
	,image_id			NUMBER									/*이미지ID*/
	,chat_inputdate		TIMESTAMP		default SYSTIMESTAMP	/*채팅생성일*/
	,FOREIGN key (item_id) references ui_item(item_id)
);

create sequence chat_id_seq;

select * from ui_chat;

/***********************************************************************/

delete from ui_member;
delete from ui_item;
delete from ui_image;
delete from ui_wish;
delete FROM ui_recent;
delete FROM ui_chat;

/*밑에서 위로 하나씩 삭제할것*/
drop table ui_member;
drop SEQUENCE member_id_seq;

drop table ui_item;
drop SEQUENCE item_id_seq;

drop table ui_image;
drop SEQUENCE image_id_seq;

drop table ui_wish;
drop SEQUENCE wish_id_seq;

drop table ui_recent;
drop SEQUENCE recent_id_seq;

drop table ui_chat;
drop SEQUENCE chat_id_seq;

/***********************************************************************/