create table if not exists flyway_schema_history
(
    installed_rank integer                 not null
        constraint flyway_schema_history_pk
            primary key,
    version        varchar(50),
    description    varchar(200)            not null,
    type           varchar(20)             not null,
    script         varchar(1000)           not null,
    checksum       integer,
    installed_by   varchar(100)            not null,
    installed_on   timestamp default now() not null,
    execution_time integer                 not null,
    success        boolean                 not null
);

create index if not exists flyway_schema_history_s_idx
    on flyway_schema_history (success);
create table if not exists templates
(
    id   uuid not null
        constraint templates_pkey
            primary key,
    name varchar(128)
);

create table if not exists file_creation_infos
(
    id        uuid not null
        primary key,
    file_name varchar(4096),
    file_path varchar(4096)
);

create table if not exists authentication_infos
(
    id               uuid not null
        primary key,
    password         varchar(1024),
    type             varchar(255),
    application_file uuid
        constraint fk_authentication_infos_authentication_info_id
            references file_creation_infos,
    key_file         uuid
);

create table if not exists secret_values
(
    id       uuid not null
        primary key,
    notes    text,
    password text,
    title    text,
    url      text,
    username text
);

create table if not exists tree_nodes
(
    id        uuid not null
        primary key,
    depth     integer,
    node      boolean,
    value     text,
    parent_id uuid
        constraint fk_treeable_parent_id
            references tree_nodes
);


