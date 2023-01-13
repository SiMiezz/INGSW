
    create table allergen (
       name varchar(50) not null,
        primary key (name)
    ) engine=InnoDB;

    create table category (
       id_category integer not null auto_increment,
        name varchar(50) not null,
        menu_code varchar(255),
        primary key (id_category)
    ) engine=InnoDB;

    create table client (
       id integer not null auto_increment,
        table_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table composed (
       order_id integer not null,
        element_id integer not null
    ) engine=InnoDB;

    create table contains (
       allergen_name varchar(50) not null,
        element_id integer not null
    ) engine=InnoDB;

    create table element (
       id_element integer not null auto_increment,
        aliment_type smallint not null,
        description varchar(255),
        name varchar(50) not null,
        prepackaged bit not null,
        price float(53) not null,
        category_id integer,
        menu_code varchar(255),
        primary key (id_element)
    ) engine=InnoDB;

    create table menu (
       qr_code varchar(255) not null,
        restaurant_id varchar(50),
        primary key (qr_code)
    ) engine=InnoDB;

    create table order (
       id_order integer not null auto_increment,
        date datetime(6),
        price float(53) not null,
        table_id integer,
        primary key (id_order)
    ) engine=InnoDB;

    create table restaurant (
       name varchar(50) not null,
        description varchar(255),
        locality varchar(50),
        seats integer not null,
        tables integer not null,
        touristic bit not null,
        primary key (name)
    ) engine=InnoDB;

    create table stats (
       id integer not null auto_increment,
        restaurant_name varchar(50),
        primary key (id)
    ) engine=InnoDB;

    create table tablerestaurant (
       id_table integer not null auto_increment,
        seats_number integer,
        restaurant_name varchar(50),
        primary key (id_table)
    ) engine=InnoDB;

    create table user (
       id_user varchar(50) not null,
        user_type smallint not null,
        name varchar(50),
        password varchar(50) not null,
        surname varchar(50),
        restaurant_name varchar(50),
        primary key (id_user)
    ) engine=InnoDB;

    alter table category 
       add constraint FK11p6cjathgyeh1wse92jboudm 
       foreign key (menu_code) 
       references menu (qr_code) 
       on delete cascade;

    alter table client 
       add constraint FK9lkljqjkmqdv2oauvd1am0pi 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table composed 
       add constraint FKohqoyvcs52f7a1whg2k2hj0tw 
       foreign key (element_id) 
       references element (id_element);

    alter table composed 
       add constraint FK7pim0q8yvaalixmt757n287lh 
       foreign key (order_id) 
       references order (id_order);

    alter table contains 
       add constraint FK3pl5xdqdbgeveviucjjwkmv18 
       foreign key (element_id) 
       references element (id_element);

    alter table contains 
       add constraint FK4h3e9q0id3p4evl6a3jgewaaf 
       foreign key (allergen_name) 
       references allergen (name);

    alter table element 
       add constraint FK753tiyls81arkdp90rx1t35he 
       foreign key (category_id) 
       references category (id_category) 
       on delete cascade;

    alter table element 
       add constraint FKrqwpleb6f1n5j8dm6ws41pgpm 
       foreign key (menu_code) 
       references menu (qr_code) 
       on delete cascade;

    alter table menu 
       add constraint FKblwdtxevpl4mrds8a12q0ohu6 
       foreign key (restaurant_id) 
       references restaurant (name);

    alter table order 
       add constraint FK734iym51ncvd1ylkx0y1tlt06 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table stats 
       add constraint FKf8509t0nhodqsdrym5iw38bws 
       foreign key (restaurant_name) 
       references restaurant (name);

    alter table tablerestaurant 
       add constraint FKj7mp42c85p7pdjpqnghnpy2fv 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    alter table user 
       add constraint FKdgwkno9qmm8qldyn0hq1mmk2a 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    create table allergen (
       name varchar(50) not null,
        primary key (name)
    ) engine=InnoDB;

    create table category (
       id_category integer not null auto_increment,
        name varchar(50) not null,
        menu_code varchar(255),
        primary key (id_category)
    ) engine=InnoDB;

    create table client (
       id integer not null auto_increment,
        table_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table composed (
       order_id integer not null,
        element_id integer not null
    ) engine=InnoDB;

    create table contains (
       allergen_name varchar(50) not null,
        element_id integer not null
    ) engine=InnoDB;

    create table element (
       id_element integer not null auto_increment,
        aliment_type smallint not null,
        description varchar(255),
        name varchar(50) not null,
        prepackaged bit not null,
        price float(53) not null,
        category_id integer,
        primary key (id_element)
    ) engine=InnoDB;

    create table menu (
       qr_code varchar(255) not null,
        restaurant_id varchar(50),
        primary key (qr_code)
    ) engine=InnoDB;

    create table order (
       id_order integer not null auto_increment,
        date datetime(6),
        price float(53) not null,
        table_id integer,
        primary key (id_order)
    ) engine=InnoDB;

    create table restaurant (
       name varchar(50) not null,
        description varchar(255),
        locality varchar(50),
        seats integer not null,
        tables integer not null,
        touristic bit not null,
        primary key (name)
    ) engine=InnoDB;

    create table stats (
       id integer not null auto_increment,
        restaurant_name varchar(50),
        primary key (id)
    ) engine=InnoDB;

    create table tablerestaurant (
       id_table integer not null auto_increment,
        seats_number integer,
        restaurant_name varchar(50),
        primary key (id_table)
    ) engine=InnoDB;

    create table user (
       id_user varchar(50) not null,
        user_type smallint not null,
        name varchar(50),
        password varchar(50) not null,
        surname varchar(50),
        restaurant_name varchar(50),
        primary key (id_user)
    ) engine=InnoDB;

    alter table category 
       add constraint FK11p6cjathgyeh1wse92jboudm 
       foreign key (menu_code) 
       references menu (qr_code) 
       on delete cascade;

    alter table client 
       add constraint FK9lkljqjkmqdv2oauvd1am0pi 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table composed 
       add constraint FKohqoyvcs52f7a1whg2k2hj0tw 
       foreign key (element_id) 
       references element (id_element);

    alter table composed 
       add constraint FK7pim0q8yvaalixmt757n287lh 
       foreign key (order_id) 
       references order (id_order);

    alter table contains 
       add constraint FK3pl5xdqdbgeveviucjjwkmv18 
       foreign key (element_id) 
       references element (id_element);

    alter table contains 
       add constraint FK4h3e9q0id3p4evl6a3jgewaaf 
       foreign key (allergen_name) 
       references allergen (name);

    alter table element 
       add constraint FK753tiyls81arkdp90rx1t35he 
       foreign key (category_id) 
       references category (id_category) 
       on delete cascade;

    alter table menu 
       add constraint FKblwdtxevpl4mrds8a12q0ohu6 
       foreign key (restaurant_id) 
       references restaurant (name);

    alter table order 
       add constraint FK734iym51ncvd1ylkx0y1tlt06 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table stats 
       add constraint FKf8509t0nhodqsdrym5iw38bws 
       foreign key (restaurant_name) 
       references restaurant (name);

    alter table tablerestaurant 
       add constraint FKj7mp42c85p7pdjpqnghnpy2fv 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    alter table user 
       add constraint FKdgwkno9qmm8qldyn0hq1mmk2a 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    create table allergen (
       name varchar(50) not null,
        primary key (name)
    ) engine=InnoDB;

    create table category (
       id_category integer not null auto_increment,
        name varchar(50) not null,
        menu_code varchar(255),
        primary key (id_category)
    ) engine=InnoDB;

    create table client (
       id integer not null auto_increment,
        table_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table composed (
       order_id integer not null,
        element_id integer not null
    ) engine=InnoDB;

    create table contains (
       allergen_name varchar(50) not null,
        element_id integer not null
    ) engine=InnoDB;

    create table element (
       id_element integer not null auto_increment,
        aliment_type smallint not null,
        description varchar(255),
        name varchar(50) not null,
        prepackaged bit not null,
        price float(53) not null,
        category_id integer,
        primary key (id_element)
    ) engine=InnoDB;

    create table menu (
       qr_code varchar(255) not null,
        restaurant_id varchar(50),
        primary key (qr_code)
    ) engine=InnoDB;

    create table order (
       id_order integer not null auto_increment,
        date datetime(6),
        price float(53) not null,
        table_id integer,
        primary key (id_order)
    ) engine=InnoDB;

    create table restaurant (
       name varchar(50) not null,
        description varchar(255),
        locality varchar(50),
        seats integer not null,
        tables integer not null,
        touristic bit not null,
        primary key (name)
    ) engine=InnoDB;

    create table stats (
       id integer not null auto_increment,
        restaurant_name varchar(50),
        primary key (id)
    ) engine=InnoDB;

    create table tablerestaurant (
       id_table integer not null auto_increment,
        seats_number integer,
        restaurant_name varchar(50),
        primary key (id_table)
    ) engine=InnoDB;

    create table user (
       id_user varchar(50) not null,
        user_type smallint not null,
        name varchar(50),
        password varchar(50) not null,
        surname varchar(50),
        restaurant_name varchar(50),
        primary key (id_user)
    ) engine=InnoDB;

    alter table category 
       add constraint FK11p6cjathgyeh1wse92jboudm 
       foreign key (menu_code) 
       references menu (qr_code) 
       on delete cascade;

    alter table client 
       add constraint FK9lkljqjkmqdv2oauvd1am0pi 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table composed 
       add constraint FKohqoyvcs52f7a1whg2k2hj0tw 
       foreign key (element_id) 
       references element (id_element);

    alter table composed 
       add constraint FK7pim0q8yvaalixmt757n287lh 
       foreign key (order_id) 
       references order (id_order);

    alter table contains 
       add constraint FK3pl5xdqdbgeveviucjjwkmv18 
       foreign key (element_id) 
       references element (id_element);

    alter table contains 
       add constraint FK4h3e9q0id3p4evl6a3jgewaaf 
       foreign key (allergen_name) 
       references allergen (name);

    alter table element 
       add constraint FK753tiyls81arkdp90rx1t35he 
       foreign key (category_id) 
       references category (id_category) 
       on delete cascade;

    alter table menu 
       add constraint FKblwdtxevpl4mrds8a12q0ohu6 
       foreign key (restaurant_id) 
       references restaurant (name);

    alter table order 
       add constraint FK734iym51ncvd1ylkx0y1tlt06 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table stats 
       add constraint FKf8509t0nhodqsdrym5iw38bws 
       foreign key (restaurant_name) 
       references restaurant (name);

    alter table tablerestaurant 
       add constraint FKj7mp42c85p7pdjpqnghnpy2fv 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    alter table user 
       add constraint FKdgwkno9qmm8qldyn0hq1mmk2a 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    create table allergen (
       name varchar(50) not null,
        primary key (name)
    ) engine=InnoDB;

    create table category (
       id_category integer not null auto_increment,
        name varchar(50) not null,
        menu_code varchar(255),
        primary key (id_category)
    ) engine=InnoDB;

    create table client (
       id integer not null auto_increment,
        table_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table composed (
       order_id integer not null,
        element_id integer not null
    ) engine=InnoDB;

    create table contains (
       allergen_name varchar(50) not null,
        element_id integer not null
    ) engine=InnoDB;

    create table element (
       id_element integer not null auto_increment,
        aliment_type smallint not null,
        description varchar(255),
        name varchar(50) not null,
        prepackaged bit not null,
        price float(53) not null,
        category_id integer,
        primary key (id_element)
    ) engine=InnoDB;

    create table menu (
       qr_code varchar(255) not null,
        restaurant_id varchar(50),
        primary key (qr_code)
    ) engine=InnoDB;

    create table order (
       id_order integer not null auto_increment,
        date datetime(6),
        price float(53) not null,
        table_id integer,
        primary key (id_order)
    ) engine=InnoDB;

    create table restaurant (
       name varchar(50) not null,
        description varchar(255),
        locality varchar(50),
        seats integer not null,
        tables integer not null,
        touristic bit not null,
        primary key (name)
    ) engine=InnoDB;

    create table stats (
       id integer not null auto_increment,
        restaurant_name varchar(50),
        primary key (id)
    ) engine=InnoDB;

    create table tablerestaurant (
       id_table integer not null auto_increment,
        seats_number integer,
        restaurant_name varchar(50),
        primary key (id_table)
    ) engine=InnoDB;

    create table user (
       id_user varchar(50) not null,
        user_type smallint not null,
        name varchar(50),
        password varchar(50) not null,
        surname varchar(50),
        restaurant_name varchar(50),
        primary key (id_user)
    ) engine=InnoDB;

    alter table category 
       add constraint FK11p6cjathgyeh1wse92jboudm 
       foreign key (menu_code) 
       references menu (qr_code) 
       on delete cascade;

    alter table client 
       add constraint FK9lkljqjkmqdv2oauvd1am0pi 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table composed 
       add constraint FKohqoyvcs52f7a1whg2k2hj0tw 
       foreign key (element_id) 
       references element (id_element);

    alter table composed 
       add constraint FK7pim0q8yvaalixmt757n287lh 
       foreign key (order_id) 
       references order (id_order);

    alter table contains 
       add constraint FK3pl5xdqdbgeveviucjjwkmv18 
       foreign key (element_id) 
       references element (id_element);

    alter table contains 
       add constraint FK4h3e9q0id3p4evl6a3jgewaaf 
       foreign key (allergen_name) 
       references allergen (name);

    alter table element 
       add constraint FK753tiyls81arkdp90rx1t35he 
       foreign key (category_id) 
       references category (id_category) 
       on delete cascade;

    alter table menu 
       add constraint FKblwdtxevpl4mrds8a12q0ohu6 
       foreign key (restaurant_id) 
       references restaurant (name);

    alter table order 
       add constraint FK734iym51ncvd1ylkx0y1tlt06 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table stats 
       add constraint FKf8509t0nhodqsdrym5iw38bws 
       foreign key (restaurant_name) 
       references restaurant (name);

    alter table tablerestaurant 
       add constraint FKj7mp42c85p7pdjpqnghnpy2fv 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    alter table user 
       add constraint FKdgwkno9qmm8qldyn0hq1mmk2a 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    create table allergen (
       name varchar(50) not null,
        primary key (name)
    ) engine=InnoDB;

    create table category (
       id_category integer not null auto_increment,
        name varchar(50) not null,
        menu_code varchar(255),
        primary key (id_category)
    ) engine=InnoDB;

    create table client (
       id integer not null auto_increment,
        table_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table composed (
       order_id integer not null,
        element_id integer not null
    ) engine=InnoDB;

    create table contains (
       allergen_name varchar(50) not null,
        element_id integer not null
    ) engine=InnoDB;

    create table element (
       id_element integer not null auto_increment,
        aliment_type smallint not null,
        description varchar(255),
        name varchar(50) not null,
        prepackaged bit not null,
        price float(53) not null,
        category_id integer,
        primary key (id_element)
    ) engine=InnoDB;

    create table menu (
       qr_code varchar(255) not null,
        restaurant_id varchar(50),
        primary key (qr_code)
    ) engine=InnoDB;

    create table order (
       id_order integer not null auto_increment,
        date datetime(6),
        price float(53) not null,
        table_id integer,
        primary key (id_order)
    ) engine=InnoDB;

    create table restaurant (
       name varchar(50) not null,
        description varchar(255),
        locality varchar(50),
        seats integer not null,
        tables integer not null,
        touristic bit not null,
        primary key (name)
    ) engine=InnoDB;

    create table stats (
       id integer not null auto_increment,
        restaurant_name varchar(50),
        primary key (id)
    ) engine=InnoDB;

    create table tablerestaurant (
       id_table integer not null auto_increment,
        seats_number integer,
        restaurant_name varchar(50),
        primary key (id_table)
    ) engine=InnoDB;

    create table user (
       id_user varchar(50) not null,
        user_type smallint not null,
        name varchar(50),
        password varchar(50) not null,
        surname varchar(50),
        restaurant_name varchar(50),
        primary key (id_user)
    ) engine=InnoDB;

    alter table category 
       add constraint FK11p6cjathgyeh1wse92jboudm 
       foreign key (menu_code) 
       references menu (qr_code) 
       on delete cascade;

    alter table client 
       add constraint FK9lkljqjkmqdv2oauvd1am0pi 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table composed 
       add constraint FKohqoyvcs52f7a1whg2k2hj0tw 
       foreign key (element_id) 
       references element (id_element);

    alter table composed 
       add constraint FK7pim0q8yvaalixmt757n287lh 
       foreign key (order_id) 
       references order (id_order);

    alter table contains 
       add constraint FK3pl5xdqdbgeveviucjjwkmv18 
       foreign key (element_id) 
       references element (id_element);

    alter table contains 
       add constraint FK4h3e9q0id3p4evl6a3jgewaaf 
       foreign key (allergen_name) 
       references allergen (name);

    alter table element 
       add constraint FK753tiyls81arkdp90rx1t35he 
       foreign key (category_id) 
       references category (id_category) 
       on delete cascade;

    alter table menu 
       add constraint FKblwdtxevpl4mrds8a12q0ohu6 
       foreign key (restaurant_id) 
       references restaurant (name);

    alter table order 
       add constraint FK734iym51ncvd1ylkx0y1tlt06 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table stats 
       add constraint FKf8509t0nhodqsdrym5iw38bws 
       foreign key (restaurant_name) 
       references restaurant (name);

    alter table tablerestaurant 
       add constraint FKj7mp42c85p7pdjpqnghnpy2fv 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    alter table user 
       add constraint FKdgwkno9qmm8qldyn0hq1mmk2a 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    create table allergen (
       name varchar(50) not null,
        primary key (name)
    ) engine=InnoDB;

    create table category (
       id_category integer not null auto_increment,
        name varchar(50) not null,
        menu_code varchar(255),
        primary key (id_category)
    ) engine=InnoDB;

    create table client (
       id integer not null auto_increment,
        table_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table composed (
       order_id integer not null,
        element_id integer not null
    ) engine=InnoDB;

    create table contains (
       allergen_name varchar(50) not null,
        element_id integer not null
    ) engine=InnoDB;

    create table element (
       id_element integer not null auto_increment,
        aliment_type smallint not null,
        description varchar(255),
        name varchar(50) not null,
        prepackaged bit not null,
        price float(53) not null,
        category_id integer,
        primary key (id_element)
    ) engine=InnoDB;

    create table menu (
       qr_code varchar(255) not null,
        restaurant_id varchar(50),
        primary key (qr_code)
    ) engine=InnoDB;

    create table order (
       id_order integer not null auto_increment,
        date datetime(6),
        price float(53) not null,
        table_id integer,
        primary key (id_order)
    ) engine=InnoDB;

    create table restaurant (
       name varchar(50) not null,
        description varchar(255),
        locality varchar(50),
        seats integer not null,
        tables integer not null,
        touristic bit not null,
        primary key (name)
    ) engine=InnoDB;

    create table stats (
       id integer not null auto_increment,
        restaurant_name varchar(50),
        primary key (id)
    ) engine=InnoDB;

    create table tablerestaurant (
       id_table integer not null auto_increment,
        seats_number integer,
        restaurant_name varchar(50),
        primary key (id_table)
    ) engine=InnoDB;

    create table user (
       id_user varchar(50) not null,
        user_type smallint not null,
        name varchar(50),
        password varchar(50) not null,
        surname varchar(50),
        restaurant_name varchar(50),
        primary key (id_user)
    ) engine=InnoDB;

    alter table category 
       add constraint FK11p6cjathgyeh1wse92jboudm 
       foreign key (menu_code) 
       references menu (qr_code) 
       on delete cascade;

    alter table client 
       add constraint FK9lkljqjkmqdv2oauvd1am0pi 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table composed 
       add constraint FKohqoyvcs52f7a1whg2k2hj0tw 
       foreign key (element_id) 
       references element (id_element);

    alter table composed 
       add constraint FK7pim0q8yvaalixmt757n287lh 
       foreign key (order_id) 
       references order (id_order);

    alter table contains 
       add constraint FK3pl5xdqdbgeveviucjjwkmv18 
       foreign key (element_id) 
       references element (id_element);

    alter table contains 
       add constraint FK4h3e9q0id3p4evl6a3jgewaaf 
       foreign key (allergen_name) 
       references allergen (name);

    alter table element 
       add constraint FK753tiyls81arkdp90rx1t35he 
       foreign key (category_id) 
       references category (id_category) 
       on delete cascade;

    alter table menu 
       add constraint FKblwdtxevpl4mrds8a12q0ohu6 
       foreign key (restaurant_id) 
       references restaurant (name);

    alter table order 
       add constraint FK734iym51ncvd1ylkx0y1tlt06 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table stats 
       add constraint FKf8509t0nhodqsdrym5iw38bws 
       foreign key (restaurant_name) 
       references restaurant (name);

    alter table tablerestaurant 
       add constraint FKj7mp42c85p7pdjpqnghnpy2fv 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    alter table user 
       add constraint FKdgwkno9qmm8qldyn0hq1mmk2a 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    create table allergen (
       name varchar(50) not null,
        primary key (name)
    ) engine=InnoDB;

    create table category (
       id_category integer not null auto_increment,
        name varchar(50) not null,
        menu_code varchar(255),
        primary key (id_category)
    ) engine=InnoDB;

    create table client (
       id integer not null auto_increment,
        table_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table composed (
       order_id integer not null,
        element_id integer not null
    ) engine=InnoDB;

    create table contains (
       allergen_name varchar(50) not null,
        element_id integer not null
    ) engine=InnoDB;

    create table element (
       id_element integer not null auto_increment,
        aliment_type smallint not null,
        description varchar(255),
        name varchar(50) not null,
        prepackaged bit not null,
        price float(53) not null,
        category_id integer,
        primary key (id_element)
    ) engine=InnoDB;

    create table menu (
       qr_code varchar(255) not null,
        restaurant_id varchar(50),
        primary key (qr_code)
    ) engine=InnoDB;

    create table order (
       id_order integer not null auto_increment,
        date datetime(6),
        price float(53) not null,
        table_id integer,
        primary key (id_order)
    ) engine=InnoDB;

    create table restaurant (
       name varchar(50) not null,
        description varchar(255),
        locality varchar(50),
        seats integer not null,
        tables integer not null,
        touristic bit not null,
        primary key (name)
    ) engine=InnoDB;

    create table stats (
       id integer not null auto_increment,
        restaurant_name varchar(50),
        primary key (id)
    ) engine=InnoDB;

    create table tablerestaurant (
       id_table integer not null auto_increment,
        seats_number integer,
        restaurant_name varchar(50),
        primary key (id_table)
    ) engine=InnoDB;

    create table user (
       id_user varchar(50) not null,
        user_type smallint not null,
        name varchar(50),
        password varchar(50) not null,
        surname varchar(50),
        restaurant_name varchar(50),
        primary key (id_user)
    ) engine=InnoDB;

    alter table category 
       add constraint FK11p6cjathgyeh1wse92jboudm 
       foreign key (menu_code) 
       references menu (qr_code) 
       on delete cascade;

    alter table client 
       add constraint FK9lkljqjkmqdv2oauvd1am0pi 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table composed 
       add constraint FKohqoyvcs52f7a1whg2k2hj0tw 
       foreign key (element_id) 
       references element (id_element);

    alter table composed 
       add constraint FK7pim0q8yvaalixmt757n287lh 
       foreign key (order_id) 
       references order (id_order);

    alter table contains 
       add constraint FK3pl5xdqdbgeveviucjjwkmv18 
       foreign key (element_id) 
       references element (id_element);

    alter table contains 
       add constraint FK4h3e9q0id3p4evl6a3jgewaaf 
       foreign key (allergen_name) 
       references allergen (name);

    alter table element 
       add constraint FK753tiyls81arkdp90rx1t35he 
       foreign key (category_id) 
       references category (id_category) 
       on delete cascade;

    alter table menu 
       add constraint FKblwdtxevpl4mrds8a12q0ohu6 
       foreign key (restaurant_id) 
       references restaurant (name);

    alter table order 
       add constraint FK734iym51ncvd1ylkx0y1tlt06 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table stats 
       add constraint FKf8509t0nhodqsdrym5iw38bws 
       foreign key (restaurant_name) 
       references restaurant (name);

    alter table tablerestaurant 
       add constraint FKj7mp42c85p7pdjpqnghnpy2fv 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    alter table user 
       add constraint FKdgwkno9qmm8qldyn0hq1mmk2a 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    create table allergen (
       name varchar(50) not null,
        primary key (name)
    ) engine=InnoDB;

    create table category (
       id_category integer not null auto_increment,
        name varchar(50) not null,
        menu_code varchar(255),
        primary key (id_category)
    ) engine=InnoDB;

    create table client (
       id integer not null auto_increment,
        table_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table composed (
       order_id integer not null,
        element_id integer not null
    ) engine=InnoDB;

    create table contains (
       allergen_name varchar(50) not null,
        element_id integer not null
    ) engine=InnoDB;

    create table element (
       id_element integer not null auto_increment,
        aliment_type smallint not null,
        description varchar(255),
        name varchar(50) not null,
        prepackaged bit not null,
        price float(53) not null,
        category_id integer,
        primary key (id_element)
    ) engine=InnoDB;

    create table menu (
       qr_code varchar(255) not null,
        restaurant_id varchar(50),
        primary key (qr_code)
    ) engine=InnoDB;

    create table order (
       id_order integer not null auto_increment,
        date datetime(6),
        price float(53) not null,
        table_id integer,
        primary key (id_order)
    ) engine=InnoDB;

    create table restaurant (
       name varchar(50) not null,
        description varchar(255),
        locality varchar(50),
        seats integer not null,
        tables integer not null,
        touristic bit not null,
        primary key (name)
    ) engine=InnoDB;

    create table stats (
       id integer not null auto_increment,
        restaurant_name varchar(50),
        primary key (id)
    ) engine=InnoDB;

    create table tablerestaurant (
       id_table integer not null auto_increment,
        seats_number integer,
        restaurant_name varchar(50),
        primary key (id_table)
    ) engine=InnoDB;

    create table user (
       id_user varchar(50) not null,
        user_type smallint not null,
        name varchar(50),
        password varchar(50) not null,
        surname varchar(50),
        restaurant_name varchar(50),
        primary key (id_user)
    ) engine=InnoDB;

    alter table category 
       add constraint FK11p6cjathgyeh1wse92jboudm 
       foreign key (menu_code) 
       references menu (qr_code) 
       on delete cascade;

    alter table client 
       add constraint FK9lkljqjkmqdv2oauvd1am0pi 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table composed 
       add constraint FKohqoyvcs52f7a1whg2k2hj0tw 
       foreign key (element_id) 
       references element (id_element);

    alter table composed 
       add constraint FK7pim0q8yvaalixmt757n287lh 
       foreign key (order_id) 
       references order (id_order);

    alter table contains 
       add constraint FK3pl5xdqdbgeveviucjjwkmv18 
       foreign key (element_id) 
       references element (id_element);

    alter table contains 
       add constraint FK4h3e9q0id3p4evl6a3jgewaaf 
       foreign key (allergen_name) 
       references allergen (name);

    alter table element 
       add constraint FK753tiyls81arkdp90rx1t35he 
       foreign key (category_id) 
       references category (id_category) 
       on delete cascade;

    alter table menu 
       add constraint FKblwdtxevpl4mrds8a12q0ohu6 
       foreign key (restaurant_id) 
       references restaurant (name);

    alter table order 
       add constraint FK734iym51ncvd1ylkx0y1tlt06 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table stats 
       add constraint FKf8509t0nhodqsdrym5iw38bws 
       foreign key (restaurant_name) 
       references restaurant (name);

    alter table tablerestaurant 
       add constraint FKj7mp42c85p7pdjpqnghnpy2fv 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    alter table user 
       add constraint FKdgwkno9qmm8qldyn0hq1mmk2a 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    create table allergen (
       name varchar(50) not null,
        primary key (name)
    ) engine=InnoDB;

    create table category (
       id_category integer not null auto_increment,
        name varchar(50) not null,
        menu_code varchar(255),
        primary key (id_category)
    ) engine=InnoDB;

    create table client (
       id integer not null auto_increment,
        table_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table composed (
       order_id integer not null,
        element_id integer not null
    ) engine=InnoDB;

    create table contains (
       allergen_name varchar(50) not null,
        element_id integer not null
    ) engine=InnoDB;

    create table element (
       id_element integer not null auto_increment,
        aliment_type smallint not null,
        description varchar(255),
        name varchar(50) not null,
        prepackaged bit not null,
        price float(53) not null,
        category_id integer,
        primary key (id_element)
    ) engine=InnoDB;

    create table menu (
       qr_code varchar(255) not null,
        restaurant_id varchar(50),
        primary key (qr_code)
    ) engine=InnoDB;

    create table order (
       id_order integer not null auto_increment,
        date datetime(6),
        price float(53) not null,
        table_id integer,
        primary key (id_order)
    ) engine=InnoDB;

    create table restaurant (
       name varchar(50) not null,
        description varchar(255),
        locality varchar(50),
        seats integer not null,
        tables integer not null,
        touristic bit not null,
        primary key (name)
    ) engine=InnoDB;

    create table stats (
       id integer not null auto_increment,
        restaurant_name varchar(50),
        primary key (id)
    ) engine=InnoDB;

    create table tablerestaurant (
       id_table integer not null auto_increment,
        seats_number integer,
        restaurant_name varchar(50),
        primary key (id_table)
    ) engine=InnoDB;

    create table user (
       id_user varchar(50) not null,
        user_type smallint not null,
        name varchar(50),
        password varchar(50) not null,
        surname varchar(50),
        restaurant_name varchar(50),
        primary key (id_user)
    ) engine=InnoDB;

    alter table category 
       add constraint FK11p6cjathgyeh1wse92jboudm 
       foreign key (menu_code) 
       references menu (qr_code) 
       on delete cascade;

    alter table client 
       add constraint FK9lkljqjkmqdv2oauvd1am0pi 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table composed 
       add constraint FKohqoyvcs52f7a1whg2k2hj0tw 
       foreign key (element_id) 
       references element (id_element);

    alter table composed 
       add constraint FK7pim0q8yvaalixmt757n287lh 
       foreign key (order_id) 
       references order (id_order);

    alter table contains 
       add constraint FK3pl5xdqdbgeveviucjjwkmv18 
       foreign key (element_id) 
       references element (id_element);

    alter table contains 
       add constraint FK4h3e9q0id3p4evl6a3jgewaaf 
       foreign key (allergen_name) 
       references allergen (name);

    alter table element 
       add constraint FK753tiyls81arkdp90rx1t35he 
       foreign key (category_id) 
       references category (id_category) 
       on delete cascade;

    alter table menu 
       add constraint FKblwdtxevpl4mrds8a12q0ohu6 
       foreign key (restaurant_id) 
       references restaurant (name);

    alter table order 
       add constraint FK734iym51ncvd1ylkx0y1tlt06 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table stats 
       add constraint FKf8509t0nhodqsdrym5iw38bws 
       foreign key (restaurant_name) 
       references restaurant (name);

    alter table tablerestaurant 
       add constraint FKj7mp42c85p7pdjpqnghnpy2fv 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    alter table user 
       add constraint FKdgwkno9qmm8qldyn0hq1mmk2a 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    create table allergen (
       name varchar(50) not null,
        primary key (name)
    ) engine=InnoDB;

    create table category (
       id_category integer not null auto_increment,
        name varchar(50) not null,
        menu_code varchar(255),
        primary key (id_category)
    ) engine=InnoDB;

    create table client (
       id integer not null auto_increment,
        table_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table composed (
       order_id integer not null,
        element_id integer not null
    ) engine=InnoDB;

    create table contains (
       allergen_name varchar(50) not null,
        element_id integer not null
    ) engine=InnoDB;

    create table element (
       id_element integer not null auto_increment,
        aliment_type smallint not null,
        description varchar(255),
        name varchar(50) not null,
        prepackaged bit not null,
        price float(53) not null,
        category_id integer,
        primary key (id_element)
    ) engine=InnoDB;

    create table menu (
       qr_code varchar(255) not null,
        restaurant_id varchar(50),
        primary key (qr_code)
    ) engine=InnoDB;

    create table order (
       id_order integer not null auto_increment,
        date datetime(6),
        price float(53) not null,
        table_id integer,
        primary key (id_order)
    ) engine=InnoDB;

    create table restaurant (
       name varchar(50) not null,
        description varchar(255),
        locality varchar(50),
        seats integer not null,
        tables integer not null,
        touristic bit not null,
        primary key (name)
    ) engine=InnoDB;

    create table stats (
       id integer not null auto_increment,
        restaurant_name varchar(50),
        primary key (id)
    ) engine=InnoDB;

    create table tablerestaurant (
       id_table integer not null auto_increment,
        seats_number integer,
        restaurant_name varchar(50),
        primary key (id_table)
    ) engine=InnoDB;

    create table user (
       id_user varchar(50) not null,
        user_type smallint not null,
        name varchar(50),
        password varchar(50) not null,
        surname varchar(50),
        restaurant_name varchar(50),
        primary key (id_user)
    ) engine=InnoDB;

    alter table category 
       add constraint FK11p6cjathgyeh1wse92jboudm 
       foreign key (menu_code) 
       references menu (qr_code) 
       on delete cascade;

    alter table client 
       add constraint FK9lkljqjkmqdv2oauvd1am0pi 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table composed 
       add constraint FKohqoyvcs52f7a1whg2k2hj0tw 
       foreign key (element_id) 
       references element (id_element);

    alter table composed 
       add constraint FK7pim0q8yvaalixmt757n287lh 
       foreign key (order_id) 
       references order (id_order);

    alter table contains 
       add constraint FK3pl5xdqdbgeveviucjjwkmv18 
       foreign key (element_id) 
       references element (id_element);

    alter table contains 
       add constraint FK4h3e9q0id3p4evl6a3jgewaaf 
       foreign key (allergen_name) 
       references allergen (name);

    alter table element 
       add constraint FK753tiyls81arkdp90rx1t35he 
       foreign key (category_id) 
       references category (id_category) 
       on delete cascade;

    alter table menu 
       add constraint FKblwdtxevpl4mrds8a12q0ohu6 
       foreign key (restaurant_id) 
       references restaurant (name);

    alter table order 
       add constraint FK734iym51ncvd1ylkx0y1tlt06 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table stats 
       add constraint FKf8509t0nhodqsdrym5iw38bws 
       foreign key (restaurant_name) 
       references restaurant (name);

    alter table tablerestaurant 
       add constraint FKj7mp42c85p7pdjpqnghnpy2fv 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    alter table user 
       add constraint FKdgwkno9qmm8qldyn0hq1mmk2a 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    create table allergen (
       name varchar(50) not null,
        primary key (name)
    ) engine=InnoDB;

    create table category (
       id_category integer not null auto_increment,
        name varchar(50) not null,
        menu_code varchar(255),
        primary key (id_category)
    ) engine=InnoDB;

    create table client (
       id integer not null auto_increment,
        table_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table composed (
       order_id integer not null,
        element_id integer not null
    ) engine=InnoDB;

    create table contains (
       allergen_name varchar(50) not null,
        element_id integer not null
    ) engine=InnoDB;

    create table element (
       id_element integer not null auto_increment,
        aliment_type smallint not null,
        description varchar(255),
        name varchar(50) not null,
        prepackaged bit not null,
        price float(53) not null,
        category_id integer,
        primary key (id_element)
    ) engine=InnoDB;

    create table menu (
       qr_code varchar(255) not null,
        restaurant_id varchar(50),
        primary key (qr_code)
    ) engine=InnoDB;

    create table order (
       id_order integer not null auto_increment,
        date datetime(6),
        price float(53) not null,
        table_id integer,
        primary key (id_order)
    ) engine=InnoDB;

    create table restaurant (
       name varchar(50) not null,
        description varchar(255),
        locality varchar(50),
        seats integer not null,
        tables integer not null,
        touristic bit not null,
        primary key (name)
    ) engine=InnoDB;

    create table stats (
       id integer not null auto_increment,
        restaurant_name varchar(50),
        primary key (id)
    ) engine=InnoDB;

    create table tablerestaurant (
       id_table integer not null auto_increment,
        seats_number integer,
        restaurant_name varchar(50),
        primary key (id_table)
    ) engine=InnoDB;

    create table user (
       id_user varchar(50) not null,
        user_type smallint not null,
        name varchar(50),
        password varchar(50) not null,
        surname varchar(50),
        restaurant_name varchar(50),
        primary key (id_user)
    ) engine=InnoDB;

    alter table category 
       add constraint FK11p6cjathgyeh1wse92jboudm 
       foreign key (menu_code) 
       references menu (qr_code) 
       on delete cascade;

    alter table client 
       add constraint FK9lkljqjkmqdv2oauvd1am0pi 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table composed 
       add constraint FKohqoyvcs52f7a1whg2k2hj0tw 
       foreign key (element_id) 
       references element (id_element);

    alter table composed 
       add constraint FK7pim0q8yvaalixmt757n287lh 
       foreign key (order_id) 
       references order (id_order);

    alter table contains 
       add constraint FK3pl5xdqdbgeveviucjjwkmv18 
       foreign key (element_id) 
       references element (id_element);

    alter table contains 
       add constraint FK4h3e9q0id3p4evl6a3jgewaaf 
       foreign key (allergen_name) 
       references allergen (name);

    alter table element 
       add constraint FK753tiyls81arkdp90rx1t35he 
       foreign key (category_id) 
       references category (id_category) 
       on delete cascade;

    alter table menu 
       add constraint FKblwdtxevpl4mrds8a12q0ohu6 
       foreign key (restaurant_id) 
       references restaurant (name);

    alter table order 
       add constraint FK734iym51ncvd1ylkx0y1tlt06 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table stats 
       add constraint FKf8509t0nhodqsdrym5iw38bws 
       foreign key (restaurant_name) 
       references restaurant (name);

    alter table tablerestaurant 
       add constraint FKj7mp42c85p7pdjpqnghnpy2fv 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    alter table user 
       add constraint FKdgwkno9qmm8qldyn0hq1mmk2a 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    create table allergen (
       name varchar(50) not null,
        primary key (name)
    ) engine=InnoDB;

    create table category (
       id_category integer not null auto_increment,
        name varchar(50) not null,
        menu_code varchar(255),
        primary key (id_category)
    ) engine=InnoDB;

    create table client (
       id integer not null auto_increment,
        table_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table composed (
       order_id integer not null,
        element_id integer not null
    ) engine=InnoDB;

    create table contains (
       allergen_name varchar(50) not null,
        element_id integer not null
    ) engine=InnoDB;

    create table element (
       id_element integer not null auto_increment,
        aliment_type smallint not null,
        description varchar(255),
        name varchar(50) not null,
        prepackaged bit not null,
        price float(53) not null,
        category_id integer,
        primary key (id_element)
    ) engine=InnoDB;

    create table menu (
       qr_code varchar(255) not null,
        restaurant_id varchar(50),
        primary key (qr_code)
    ) engine=InnoDB;

    create table order (
       id_order integer not null auto_increment,
        date datetime(6),
        price float(53) not null,
        table_id integer,
        primary key (id_order)
    ) engine=InnoDB;

    create table restaurant (
       name varchar(50) not null,
        description varchar(255),
        locality varchar(50),
        seats integer not null,
        tables integer not null,
        touristic bit not null,
        primary key (name)
    ) engine=InnoDB;

    create table stats (
       id integer not null auto_increment,
        restaurant_name varchar(50),
        primary key (id)
    ) engine=InnoDB;

    create table tablerestaurant (
       id_table integer not null auto_increment,
        seats_number integer,
        restaurant_name varchar(50),
        primary key (id_table)
    ) engine=InnoDB;

    create table user (
       id_user varchar(50) not null,
        user_type smallint not null,
        name varchar(50),
        password varchar(50) not null,
        surname varchar(50),
        restaurant_name varchar(50),
        primary key (id_user)
    ) engine=InnoDB;

    alter table category 
       add constraint FK11p6cjathgyeh1wse92jboudm 
       foreign key (menu_code) 
       references menu (qr_code) 
       on delete cascade;

    alter table client 
       add constraint FK9lkljqjkmqdv2oauvd1am0pi 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table composed 
       add constraint FKohqoyvcs52f7a1whg2k2hj0tw 
       foreign key (element_id) 
       references element (id_element);

    alter table composed 
       add constraint FK7pim0q8yvaalixmt757n287lh 
       foreign key (order_id) 
       references order (id_order);

    alter table contains 
       add constraint FK3pl5xdqdbgeveviucjjwkmv18 
       foreign key (element_id) 
       references element (id_element);

    alter table contains 
       add constraint FK4h3e9q0id3p4evl6a3jgewaaf 
       foreign key (allergen_name) 
       references allergen (name);

    alter table element 
       add constraint FK753tiyls81arkdp90rx1t35he 
       foreign key (category_id) 
       references category (id_category) 
       on delete cascade;

    alter table menu 
       add constraint FKblwdtxevpl4mrds8a12q0ohu6 
       foreign key (restaurant_id) 
       references restaurant (name);

    alter table order 
       add constraint FK734iym51ncvd1ylkx0y1tlt06 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table stats 
       add constraint FKf8509t0nhodqsdrym5iw38bws 
       foreign key (restaurant_name) 
       references restaurant (name);

    alter table tablerestaurant 
       add constraint FKj7mp42c85p7pdjpqnghnpy2fv 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    alter table user 
       add constraint FKdgwkno9qmm8qldyn0hq1mmk2a 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    create table allergen (
       name varchar(50) not null,
        primary key (name)
    ) engine=InnoDB;

    create table category (
       id_category integer not null auto_increment,
        name varchar(50) not null,
        menu_code varchar(255),
        primary key (id_category)
    ) engine=InnoDB;

    create table client (
       id integer not null auto_increment,
        table_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table composed (
       order_id integer not null,
        element_id integer not null
    ) engine=InnoDB;

    create table contains (
       allergen_name varchar(50) not null,
        element_id integer not null
    ) engine=InnoDB;

    create table element (
       id_element integer not null auto_increment,
        aliment_type smallint not null,
        description varchar(255),
        name varchar(50) not null,
        prepackaged bit not null,
        price float(53) not null,
        category_id integer,
        primary key (id_element)
    ) engine=InnoDB;

    create table menu (
       qr_code varchar(255) not null,
        restaurant_id varchar(50),
        primary key (qr_code)
    ) engine=InnoDB;

    create table order (
       id_order integer not null auto_increment,
        date datetime(6),
        price float(53) not null,
        table_id integer,
        primary key (id_order)
    ) engine=InnoDB;

    create table restaurant (
       name varchar(50) not null,
        description varchar(255),
        locality varchar(50),
        seats integer not null,
        tables integer not null,
        touristic bit not null,
        primary key (name)
    ) engine=InnoDB;

    create table stats (
       id integer not null auto_increment,
        restaurant_name varchar(50),
        primary key (id)
    ) engine=InnoDB;

    create table tablerestaurant (
       id_table integer not null auto_increment,
        seats_number integer,
        restaurant_name varchar(50),
        primary key (id_table)
    ) engine=InnoDB;

    create table user (
       id_user varchar(50) not null,
        user_type smallint not null,
        name varchar(50),
        password varchar(50) not null,
        surname varchar(50),
        restaurant_name varchar(50),
        primary key (id_user)
    ) engine=InnoDB;

    alter table category 
       add constraint FK11p6cjathgyeh1wse92jboudm 
       foreign key (menu_code) 
       references menu (qr_code) 
       on delete cascade;

    alter table client 
       add constraint FK9lkljqjkmqdv2oauvd1am0pi 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table composed 
       add constraint FKohqoyvcs52f7a1whg2k2hj0tw 
       foreign key (element_id) 
       references element (id_element);

    alter table composed 
       add constraint FK7pim0q8yvaalixmt757n287lh 
       foreign key (order_id) 
       references order (id_order);

    alter table contains 
       add constraint FK3pl5xdqdbgeveviucjjwkmv18 
       foreign key (element_id) 
       references element (id_element);

    alter table contains 
       add constraint FK4h3e9q0id3p4evl6a3jgewaaf 
       foreign key (allergen_name) 
       references allergen (name);

    alter table element 
       add constraint FK753tiyls81arkdp90rx1t35he 
       foreign key (category_id) 
       references category (id_category) 
       on delete cascade;

    alter table menu 
       add constraint FKblwdtxevpl4mrds8a12q0ohu6 
       foreign key (restaurant_id) 
       references restaurant (name);

    alter table order 
       add constraint FK734iym51ncvd1ylkx0y1tlt06 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table stats 
       add constraint FKf8509t0nhodqsdrym5iw38bws 
       foreign key (restaurant_name) 
       references restaurant (name);

    alter table tablerestaurant 
       add constraint FKj7mp42c85p7pdjpqnghnpy2fv 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    alter table user 
       add constraint FKdgwkno9qmm8qldyn0hq1mmk2a 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    create table allergen (
       name varchar(50) not null,
        primary key (name)
    ) engine=InnoDB;

    create table category (
       id_category integer not null auto_increment,
        name varchar(50) not null,
        menu_id integer,
        primary key (id_category)
    ) engine=InnoDB;

    create table client (
       id integer not null auto_increment,
        table_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table composed (
       order_id integer not null,
        element_id integer not null
    ) engine=InnoDB;

    create table contains (
       allergen_name varchar(50) not null,
        element_id integer not null
    ) engine=InnoDB;

    create table element (
       id_element integer not null auto_increment,
        aliment smallint not null,
        description varchar(255),
        name varchar(50) not null,
        prepackaged bit not null,
        price float(53) not null,
        category_id integer,
        primary key (id_element)
    ) engine=InnoDB;

    create table menu (
       id_menu integer not null auto_increment,
        qrcode varchar(255),
        restaurant_name varchar(50),
        primary key (id_menu)
    ) engine=InnoDB;

    create table order (
       id_order integer not null auto_increment,
        date datetime(6) not null,
        price float(53) not null,
        table_id integer,
        primary key (id_order)
    ) engine=InnoDB;

    create table restaurant (
       name varchar(50) not null,
        description varchar(255),
        locality varchar(50),
        seats integer not null,
        tables integer not null,
        touristic bit not null,
        primary key (name)
    ) engine=InnoDB;

    create table tablerestaurant (
       id_table integer not null auto_increment,
        seats integer,
        restaurant_name varchar(50),
        primary key (id_table)
    ) engine=InnoDB;

    create table user (
       id_user varchar(50) not null,
        job smallint not null,
        name varchar(50) not null,
        password varchar(50) not null,
        surname varchar(50),
        restaurant_name varchar(50),
        primary key (id_user)
    ) engine=InnoDB;

    alter table category 
       add constraint FK7ld4ysop2r15rbwxiue1ko5eb 
       foreign key (menu_id) 
       references menu (id_menu) 
       on delete cascade;

    alter table client 
       add constraint FK9lkljqjkmqdv2oauvd1am0pi 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table composed 
       add constraint FKohqoyvcs52f7a1whg2k2hj0tw 
       foreign key (element_id) 
       references element (id_element);

    alter table composed 
       add constraint FK7pim0q8yvaalixmt757n287lh 
       foreign key (order_id) 
       references order (id_order);

    alter table contains 
       add constraint FK3pl5xdqdbgeveviucjjwkmv18 
       foreign key (element_id) 
       references element (id_element);

    alter table contains 
       add constraint FK4h3e9q0id3p4evl6a3jgewaaf 
       foreign key (allergen_name) 
       references allergen (name);

    alter table element 
       add constraint FK753tiyls81arkdp90rx1t35he 
       foreign key (category_id) 
       references category (id_category) 
       on delete cascade;

    alter table menu 
       add constraint FK8c76fki310kx4xqaa6ub0hlcd 
       foreign key (restaurant_name) 
       references restaurant (name);

    alter table order 
       add constraint FK734iym51ncvd1ylkx0y1tlt06 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table tablerestaurant 
       add constraint FKj7mp42c85p7pdjpqnghnpy2fv 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    alter table user 
       add constraint FKdgwkno9qmm8qldyn0hq1mmk2a 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    create table allergen (
       name varchar(50) not null,
        primary key (name)
    ) engine=InnoDB;

    create table category (
       id_category integer not null auto_increment,
        name varchar(50) not null,
        menu_id integer,
        primary key (id_category)
    ) engine=InnoDB;

    create table client (
       id integer not null auto_increment,
        table_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table composed (
       order_id integer not null,
        element_id integer not null
    ) engine=InnoDB;

    create table contains (
       allergen_name varchar(50) not null,
        element_id integer not null
    ) engine=InnoDB;

    create table element (
       id_element integer not null auto_increment,
        aliment varchar(255) not null,
        description varchar(255),
        name varchar(50) not null,
        prepackaged bit not null,
        price float(53) not null,
        category_id integer,
        primary key (id_element)
    ) engine=InnoDB;

    create table menu (
       id_menu integer not null auto_increment,
        qrcode varchar(255),
        restaurant_name varchar(50),
        primary key (id_menu)
    ) engine=InnoDB;

    create table order (
       id_order integer not null auto_increment,
        date datetime(6) not null,
        price float(53) not null,
        table_id integer,
        primary key (id_order)
    ) engine=InnoDB;

    create table restaurant (
       name varchar(50) not null,
        description varchar(255),
        locality varchar(50),
        seats integer not null,
        tables integer not null,
        touristic bit not null,
        primary key (name)
    ) engine=InnoDB;

    create table tablerestaurant (
       id_table integer not null auto_increment,
        seats integer,
        restaurant_name varchar(50),
        primary key (id_table)
    ) engine=InnoDB;

    create table user (
       id_user varchar(50) not null,
        job smallint not null,
        name varchar(50) not null,
        password varchar(50) not null,
        surname varchar(50),
        restaurant_name varchar(50),
        primary key (id_user)
    ) engine=InnoDB;

    alter table category 
       add constraint FK7ld4ysop2r15rbwxiue1ko5eb 
       foreign key (menu_id) 
       references menu (id_menu) 
       on delete cascade;

    alter table client 
       add constraint FK9lkljqjkmqdv2oauvd1am0pi 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table composed 
       add constraint FKohqoyvcs52f7a1whg2k2hj0tw 
       foreign key (element_id) 
       references element (id_element);

    alter table composed 
       add constraint FK7pim0q8yvaalixmt757n287lh 
       foreign key (order_id) 
       references order (id_order);

    alter table contains 
       add constraint FK3pl5xdqdbgeveviucjjwkmv18 
       foreign key (element_id) 
       references element (id_element);

    alter table contains 
       add constraint FK4h3e9q0id3p4evl6a3jgewaaf 
       foreign key (allergen_name) 
       references allergen (name);

    alter table element 
       add constraint FK753tiyls81arkdp90rx1t35he 
       foreign key (category_id) 
       references category (id_category) 
       on delete cascade;

    alter table menu 
       add constraint FK8c76fki310kx4xqaa6ub0hlcd 
       foreign key (restaurant_name) 
       references restaurant (name);

    alter table order 
       add constraint FK734iym51ncvd1ylkx0y1tlt06 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table tablerestaurant 
       add constraint FKj7mp42c85p7pdjpqnghnpy2fv 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    alter table user 
       add constraint FKdgwkno9qmm8qldyn0hq1mmk2a 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    create table allergen (
       name varchar(50) not null,
        primary key (name)
    ) engine=InnoDB;

    create table category (
       id_category integer not null auto_increment,
        name varchar(50) not null,
        menu_id integer,
        primary key (id_category)
    ) engine=InnoDB;

    create table client (
       id integer not null auto_increment,
        table_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table composed (
       order_id integer not null,
        element_id integer not null
    ) engine=InnoDB;

    create table contains (
       allergen_name varchar(50) not null,
        element_id integer not null
    ) engine=InnoDB;

    create table element (
       id_element integer not null auto_increment,
        aliment varchar(255) not null,
        description varchar(255),
        name varchar(50) not null,
        prepackaged bit not null,
        price float(53) not null,
        category_id integer,
        primary key (id_element)
    ) engine=InnoDB;

    create table menu (
       id_menu integer not null auto_increment,
        qrcode varchar(255),
        restaurant_name varchar(50),
        primary key (id_menu)
    ) engine=InnoDB;

    create table order (
       id_order integer not null auto_increment,
        date datetime(6) not null,
        price float(53) not null,
        table_id integer,
        primary key (id_order)
    ) engine=InnoDB;

    create table restaurant (
       name varchar(50) not null,
        description varchar(255),
        locality varchar(50),
        seats integer not null,
        tables integer not null,
        touristic bit not null,
        primary key (name)
    ) engine=InnoDB;

    create table tablerestaurant (
       id_table integer not null auto_increment,
        seats integer,
        restaurant_name varchar(50),
        primary key (id_table)
    ) engine=InnoDB;

    create table user (
       id_user varchar(50) not null,
        job varchar(255) not null,
        name varchar(50) not null,
        password varchar(50) not null,
        surname varchar(50),
        restaurant_name varchar(50),
        primary key (id_user)
    ) engine=InnoDB;

    alter table category 
       add constraint FK7ld4ysop2r15rbwxiue1ko5eb 
       foreign key (menu_id) 
       references menu (id_menu) 
       on delete cascade;

    alter table client 
       add constraint FK9lkljqjkmqdv2oauvd1am0pi 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table composed 
       add constraint FKohqoyvcs52f7a1whg2k2hj0tw 
       foreign key (element_id) 
       references element (id_element);

    alter table composed 
       add constraint FK7pim0q8yvaalixmt757n287lh 
       foreign key (order_id) 
       references order (id_order);

    alter table contains 
       add constraint FK3pl5xdqdbgeveviucjjwkmv18 
       foreign key (element_id) 
       references element (id_element);

    alter table contains 
       add constraint FK4h3e9q0id3p4evl6a3jgewaaf 
       foreign key (allergen_name) 
       references allergen (name);

    alter table element 
       add constraint FK753tiyls81arkdp90rx1t35he 
       foreign key (category_id) 
       references category (id_category) 
       on delete cascade;

    alter table menu 
       add constraint FK8c76fki310kx4xqaa6ub0hlcd 
       foreign key (restaurant_name) 
       references restaurant (name);

    alter table order 
       add constraint FK734iym51ncvd1ylkx0y1tlt06 
       foreign key (table_id) 
       references tablerestaurant (id_table) 
       on delete cascade;

    alter table tablerestaurant 
       add constraint FKj7mp42c85p7pdjpqnghnpy2fv 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;

    alter table user 
       add constraint FKdgwkno9qmm8qldyn0hq1mmk2a 
       foreign key (restaurant_name) 
       references restaurant (name) 
       on delete cascade;
