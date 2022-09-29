create table datn.categories
(
    id     bigint auto_increment
        primary key,
    name   varchar(255) charset utf8 not null,
    active bit default b'1'          not null,
    constraint categories_id_uindex
        unique (id)
);

create table datn.colors
(
    id     bigint auto_increment
        primary key,
    name   varchar(255) charset utf8 not null,
    hex    varchar(255) charset utf8 not null,
    active bit default b'1'          not null,
    constraint colors_id_uindex
        unique (id)
);

create table datn.events
(
    id         bigint auto_increment
        primary key,
    name       varchar(255) charset utf8 not null,
    start_date date                      not null,
    end_date   date                      not null,
    active     bit default b'1'          not null,
    constraint events_id_uindex
        unique (id)
);

create table datn.materials
(
    id     bigint auto_increment
        primary key,
    name   varchar(255) charset utf8 not null,
    code   varchar(50)               not null,
    active bit default b'1'          not null,
    constraint materials_id_uindex
        unique (id)
);

create table datn.products
(
    id          bigint auto_increment
        primary key,
    name        varchar(255) charset utf8 not null,
    category_id bigint                    not null,
    description text                      null,
    active      bit default b'1'          not null,
    material_id bigint                    not null,
    constraint products_id_uindex
        unique (id),
    constraint products___fk1
        foreign key (category_id) references datn.categories (id),
    constraint products___fk2
        foreign key (material_id) references datn.materials (id)
);

create table datn.sizes
(
    id     bigint auto_increment
        primary key,
    name   varchar(255) charset utf8 not null,
    code   varchar(50)               not null,
    active bit default b'1'          not null,
    constraint sizes_id_uindex
        unique (id)
);

create table datn.product_options
(
    id             bigint auto_increment
        primary key,
    product_id     bigint           not null,
    color_id       bigint           not null,
    size_id        bigint           not null,
    price          double           not null,
    quantity       int              not null,
    discount_price double           not null,
    weight         double           not null,
    height         double           not null,
    active         bit default b'1' not null,
    constraint product_options_id_uindex
        unique (id),
    constraint product_options___fk1
        foreign key (product_id) references datn.products (id),
    constraint product_options___fk2
        foreign key (size_id) references datn.sizes (id),
    constraint product_options___fk4
        foreign key (color_id) references datn.colors (id)
);

create index product_options___fk3
    on datn.product_options (size_id);

create table datn.users
(
    id        bigint auto_increment
        primary key,
    active    bit          default b'1'       not null,
    email     varchar(255)                    null,
    password  varchar(255)                    null,
    role      varchar(255) default 'CUSTOMER' null comment 'CUSTOMER, ADMIN, EMPLOYEE',
    user_name varchar(255)                    null,
    constraint users_id_uindex
        unique (id)
);

create table datn.address
(
    id             bigint auto_increment
        primary key,
    user_id        bigint                    not null,
    province_id    int                       not null,
    province_name  varchar(255) charset utf8 not null,
    distric_id     int                       not null,
    distric_name   varchar(155) charset utf8 not null,
    ward_code      int                       not null,
    ward_name      varchar(155) charset utf8 not null,
    phone_number   char(15)                  not null,
    address_detail varchar(255) charset utf8 null,
    is_default     bit default b'1'          null,
    active         bit default b'1'          not null,
    constraint address_id_uindex
        unique (id),
    constraint address___fk1
        foreign key (user_id) references datn.users (id)
);

create table datn.carts
(
    id                bigint auto_increment
        primary key,
    product_option_id bigint        not null,
    user_id           bigint        not null,
    quantity          int default 1 not null,
    constraint carts_id_uindex
        unique (id),
    constraint carts___fk1
        foreign key (user_id) references datn.users (id),
    constraint carts___fk2
        foreign key (product_option_id) references datn.product_options (id)
);

create table datn.vouchers
(
    id        bigint auto_increment
        primary key,
    event_id  bigint           not null,
    name      varchar(100)     not null,
    min_total double           not null,
    amount    int              null,
    discount  double           not null,
    active    bit default b'1' not null,
    constraint vouchers_id_uindex
        unique (id),
    constraint vouchers___fk1
        foreign key (event_id) references datn.events (id)
);

create table datn.orders
(
    id             bigint auto_increment
        primary key,
    user_id        bigint                         not null,
    address_id     bigint                         not null,
    note           varchar(255) charset utf8      null,
    payed          bit          default b'0'      not null comment 'đã thanh toán chưa ? yes = true, no = false',
    payment        varchar(255) default 'COD'     not null comment 'COD, VN-PAY',
    ship_price     double                         not null,
    total_discount double                         not null,
    code           varchar(255)                   not null comment 'Mã đơn hàng - gen to uuid',
    total          double                         not null,
    status         varchar(255) default 'ORDERED' null comment 'Trạng thái đơn hàng : đã đặt(ORDERED),đã duyệt(APPROVED), đang giao hàng(delivery)
.....',
    voucher_id     bigint                         not null,
    constraint orders_id_uindex
        unique (id),
    constraint orders___fk1
        foreign key (user_id) references datn.users (id),
    constraint orders___fk2
        foreign key (address_id) references datn.address (id),
    constraint orders___fk3
        foreign key (voucher_id) references datn.vouchers (id)
);

create table datn.order_details
(
    id                bigint auto_increment
        primary key,
    product_option_id bigint not null,
    order_id          bigint not null,
    quantity          int    not null,
    price             double not null,
    constraint order_details_id_uindex
        unique (id),
    constraint order_details___fk1
        foreign key (product_option_id) references datn.product_options (id),
    constraint order_details___fk2
        foreign key (order_id) references datn.orders (id)
);

create table datn.order_logs
(
    id         bigint auto_increment
        primary key,
    order_id   bigint       not null,
    note       int          null,
    created_by bigint       null,
    status     varchar(255) not null comment 'Trạng thái chuyển đổi của đơn hàng.',
    constraint order_logs_id_uindex
        unique (id),
    constraint order_logs___fk1
        foreign key (order_id) references datn.orders (id)
);


