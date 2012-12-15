create table comment(id int auto_increment,commodity_id  int not null,annoy  int ,buyer_name varchar(100) not null,credit int ,`date`  timestamp ,deal varchar(100),rate_id bigint ,text varchar(3000),`type` int, primary key (id)) 
ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

create table commodity_category( id int auto_increment,cid bigint,name varchar(100) not null,is_parent tinyint ,parent_id bigint,primary key (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table commodity(id int auto_increment,cid bigint,click_url varchar(100) not null,commission varchar(10),commission_num varchar(30),
   commission_rate varchar(10),commission_volume varchar(10),coupon_end_time varchar(10),coupon_price varchar(10),coupon_rate varchar(10),
   coupon_start_time varchar(10),item_location varchar(100),keyword_click_url varchar(300),nick varchar(100),num_iid bigint,pic_url varchar(100),
   price varchar(10),seller_credit_score bigint,seller_id bigint,shop_click_url varchar(300),shop_type varchar(10),taobaoke_cat_click_url varchar(300),
   title varchar(100),volume bigint,primary key (id))  ENGINE=InnoDB DEFAULT CHARSET=utf8;