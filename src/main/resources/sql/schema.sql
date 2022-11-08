create table customers(
    id bigserial primary key,
    code varchar(50),
    fullname varchar(100) not null,
    phone_number varchar(14) not null,
    image text,
    email varchar(100) not null,
    adress varchar(100) not null,
    dob date not null,
    created_by varchar(100),
    updated_by varchar(100),
    created_time timestamp,
    updated_time timestamp
);

create table customers_documents(
    id bigserial primary key,
    code varchar(50),
    customer_code varchar(50),
    document_name varchar(100),
    document_type varchar(20),
    document_file text,
    created_by varchar(100),
    updated_by varchar(100),
    created_time timestamp,
    updated_time timestamp
);

--- table log--
create table audit_log(
    id bigserial primary key,
    code varchar(255)not null,
    activity_time timestamp,
    remarks text,
    current_thread varchar(255)
);