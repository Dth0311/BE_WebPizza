Create database osahaneat;
use osahaneat;

create table Roles(
	id int auto_increment,
    role_name varchar(20),
    create_date timestamp,
    
    primary key(id)
);

create table Users(
	id int auto_increment,
    user_name varchar(100),
    password varchar(100),
    fullname varchar(50),
    create_date timestamp,
    role_id int,
    
    primary key(id)
);

create table RatingFood(
	id int auto_increment,
    user_id int,
    food_id int,
    content text,
    rate_food int(5),
    
    primary key(id)
);

create table Category(
	id int auto_increment,
    name_cate varchar(100),
    create_date timestamp,
    
    primary key(id)
);

create table Food(
	id int auto_increment,
    title varchar(255),
    image text,
    time_ship varchar(10),
    price decimal,
    cate_id int,
    
    primary key(id)
);

create table rating_restaurant(
	id int auto_increment,
    user_id int,
    res_id int,
    content text,
    rate_point int(5),
    
    primary key(id)
);

create table Orders(
	id int auto_increment,
    user_id int,
    res_id int,
    create_date timestamp,
    
    primary key(id)
);

create table MenuRestaurant(
	cate_id int,
    res_id int,
    create_date timestamp,
    primary key(cate_id,res_id)
);

create table Restaurant(
	id int auto_increment,
    title varchar(255),
    subtitle varchar(255),
    description text,
    image text,
    is_freeship boolean,
    address varchar(255),
    open_date timestamp,
    
    primary key(id)
);

create table Promo(
	id int auto_increment,
    res_id int,
    percent int,
    start_date timestamp,
    end_date timestamp default current_timestamp,
    
    primary key(id)
);

create table OrderItem(
	order_id int,
    food_id int,
    create_date timestamp,
    
    primary key(order_id,food_id)
);

alter table Users add constraint fk_users_role_id foreign key (role_id) references Roles(id);
alter table RatingFood add constraint fk_ratingfood_user_id foreign key (user_id) references Users(id);
alter table RatingFood add constraint fk_ratingfood_food_id foreign key (food_id) references Food(id);
alter table Food add constraint fk_food_cate_id foreign key (cate_id) references Category(id);
alter table rating_restaurant add constraint fk_ratingrestaurant_user_id foreign key (user_id) references Users(id);
alter table rating_restaurant add constraint fk_ratingrestaurant_res_id foreign key (res_id) references Restaurant(id);
alter table Orders add constraint fk_orders_user_id foreign key (user_id) references Users(id);
alter table Orders add constraint fk_orders_res_id foreign key (res_id) references Restaurant(id);
alter table OrderItem add constraint fk_orderitem_order_id foreign key (order_id) references Orders(id);
alter table OrderItem add constraint fk_orderitem_food_id foreign key (food_id) references Food(id);
alter table MenuRestaurant add constraint fk_menurestaurant_cate_id foreign key (cate_id) references Category(id);
alter table MenuRestaurant add constraint fk_menurestaurant_res_id foreign key (res_id) references Restaurant(id);
alter table Promo add constraint fk_promo_res_id foreign key (res_id) references Restaurant(id);

insert into Roles(role_name) Values ("ROLE_ADMIN"),("ROLE_USER")

Insert into Users(user_name,password,fullname,role_id) values ("nguyenvana@gmail.com","123456","Nguyen Van A",1),
("nguyenvanb@gmail.com","123","Nguyen Van B",2)

select * from users
select * from Restaurant
select * from rating_restaurant