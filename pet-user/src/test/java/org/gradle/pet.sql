create table user_third_part_info(id int auto_increment,user_id  bigint not null,refresh_token  varchar(100) ,access_token varchar(200) not null,token_secret varchar(200) ,guid  varchar(200) ,`type` int,status int ,update_time timestamp,is_verified int,third_part_name varchar(200),expired_in bigint, primary key (id)) 
ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

create table `user`(id int auto_increment,user_name  varchar(100) not null,password  varchar(100) ,email varchar(200) not null,birthday timestamp ,
header_pic  varchar(200) ,city varchar(200),address varchar(200) ,backgroud_pic varchar(200),create_time timestamp,modify_time timestamp, primary key (id)) 
ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

create table user_device_token(id int auto_increment,user_id  int not null,device_token  varchar(200) ,device_type int not null,device_version  varchar(200) ,
create_time  timestamp,modify_time  timestamp, primary key (id)) 
ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


