SET MODE = MySQL;

create table finca_raiz (
  finca_raiz_id bigint not null auto_increment
, property_type varchar(17)
, price decimal(20, 2)
, private_area int
, built_area int
, estrato int
, address VARCHAR (30)
, description VARCHAR (40000)
, updaded_date date
, url VARCHAR (500)
, PRIMARY KEY (finca_raiz_id)
);
