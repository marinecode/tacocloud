create table if not exists Ingredient (
  id varchar(4) not null,
  name varchar(25) not null,
  type varchar(10) not null
);
create table if not exists Taco (
  id identity,
  name varchar(50) not null,
  created_At timestamp not null
);
create table if not exists Taco_Ingredients (
  taco_id bigint not null,
  ingredients_id varchar(4) not null
);
alter table Taco_Ingredients
    add foreign key (taco_id) references Taco(id);
alter table Taco_Ingredients
    add foreign key (ingredients_id) references Ingredient(id);
create table if not exists Taco_Order (
  id identity,
    dName varchar(50) not null,
    Street varchar(50) not null,
        City varchar(50) not null,
        State varchar(2) not null,
        Zip varchar(10) not null,
        ccNumber varchar(16) not null,
        ccExpiration varchar(5) not null,
        ccCVV varchar(3) not null,
        placed_At timestamp not null

    );
    create table if not exists Taco_Order_Tacos (
      tacoOrder bigint not null,
      taco bigint not null
    );
    alter table Taco_Order_Tacos
        add foreign key (tacoOrder) references Taco_Order(id);
    alter table Taco_Order_Tacos
        add foreign key (taco) references Taco(id);