create table manufacturers (
       id bigint generated by default as identity,
        address varchar(255),
        display_name varchar(255),
        formal_name varchar(255),
        status varchar(255),
        primary key (id)
    );