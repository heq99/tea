drop table CUSTOMER if exists;
drop table PRODUCT if exists;
drop table PRODUCT_LEVEL if exists;
drop table PRODUCT_SPEC if exists;
drop table PRODUCT_UNIT if exists;
drop table USERS if exists;
drop sequence HIBERNATE_SEQUENCE;


create table CUSTOMER (
    ID bigint not null,
    MEMBER_ID varchar(64),
    NAME varchar(128),
    EMAIL varchar(128),
    PHONE_NUMBER varchar(15),
    ADDRESS varchar(512),
    primary key (ID)
);

create table PRODUCT (
    ID bigint not null,
    ACTIVE boolean not null,
    PRODUCT_NUMBER varchar(64),
    SHORT_NAME varchar(128),
    LONG_NAME varchar(1024),
    PRICE double,
    PRODUCT_LEVEL_ID bigint,
    PRODUCT_SPEC_ID bigint,
    PRODUCT_UNIT_ID bigint,
    primary key (ID)
);

create table PRODUCT_LEVEL (
    ID bigint not null,
    NAME varchar(32),
    primary key (ID)
);

create table PRODUCT_UNIT (
    ID bigint not null,
    NAME varchar(32),
    primary key (ID)
);

create table PRODUCT_SPEC (
    ID bigint not null,
    NAME varchar(32),
    primary key (ID)
);

create table USERS (
    ID bigint not null,
    LOGIN varchar(64),
    PASSWORD varchar(128),
    NAME varchar(32),
    ROLE varchar(20),
    primary key (ID)
);

create sequence HIBERNATE_SEQUENCE start with 1 increment by 1;


alter table PRODUCT add constraint FK_PRODUCT_TO_LEVEL foreign key (PRODUCT_LEVEL_ID) references PRODUCT_LEVEL;
alter table PRODUCT add constraint FK_PRODUCT_TO_UNIT foreign key (PRODUCT_UNIT_ID) references PRODUCT_UNIT;
alter table PRODUCT add constraint FK_PRODUCT_TO_SPEC foreign key (PRODUCT_SPEC_ID) references PRODUCT_SPEC;