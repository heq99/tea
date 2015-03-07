
    create table CUSTOMER (
        ID bigint not null auto_increment,
        ADDRESS varchar(512),
        EMAIL varchar(128),
        MEMBER_ID varchar(64),
        NAME varchar(128),
        PHONE_NUMBER varchar(15),
        primary key (ID)
    );

    create table PRODUCT (
        ID bigint not null auto_increment,
        ACTIVE bit,
        LONG_NAME varchar(1024),
        PRICE double precision,
        PRODUCT_NUMBER varchar(64),
        SHORT_NAME varchar(128),
        PRODUCT_LEVEL_ID bigint,
        PRODUCT_SPEC_ID bigint,
        PRODUCT_UNIT_ID bigint,
        primary key (ID)
    );

    create table PRODUCT_LEVEL (
        ID bigint not null auto_increment,
        NAME varchar(32),
        primary key (ID)
    );

    create table PRODUCT_SPEC (
        ID bigint not null auto_increment,
        NAME varchar(32),
        primary key (ID)
    );

    create table PRODUCT_UNIT (
        ID bigint not null auto_increment,
        NAME varchar(32),
        primary key (ID)
    );

    create table USERS (
        ID bigint not null auto_increment,
        LOGIN varchar(64),
        NAME varchar(32),
        PASSWORD varchar(128),
        ROLE varchar(20),
        primary key (ID)
    );

    alter table PRODUCT 
        add constraint FK_icpk817cixk9dyhp6itcxe0m 
        foreign key (PRODUCT_LEVEL_ID) 
        references PRODUCT_LEVEL (ID);

    alter table PRODUCT 
        add constraint FK_d31uo6idqqqq39jmjj2jy5ntv 
        foreign key (PRODUCT_SPEC_ID) 
        references PRODUCT_SPEC (ID);

    alter table PRODUCT 
        add constraint FK_2extg00qlg2su1duhn5ghoxw2 
        foreign key (PRODUCT_UNIT_ID) 
        references PRODUCT_UNIT (ID);
