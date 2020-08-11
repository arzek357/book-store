create table books (id bigserial primary key,category varchar(255), title varchar(255), description varchar(5000), price numeric(8, 2), publish_year int);
insert into books (category,title, description, price, publish_year) values
('FANTASY','Harry Potter 1', 'Description 1', 300.0, 2000),
('FANTASY','Harry Potter 2', 'Description 2', 400.0, 2001),
('FANTASY','Harry Potter 3', 'Description 3', 500.0, 2002),
('FANTASY','Harry Potter 4', 'Description 4', 700.0, 2007),
('FANTASY','Harry Potter 5', 'Description 5', 440.0, 2004),
('FANTASY','Harry Potter 6', 'Description 6', 650.0, 2007),
('FANTASY','Harry Potter 7', 'Description 7', 200.0, 2006),
('FANTASY','LOTR 1', 'Description 8', 1200.0, 2006),
('FANTASY','LOTR 2', 'Description 9', 900.0, 2004),
('FANTASY','LOTR 3', 'Description 10', 600.0, 2001),
('FANTASTIC','Stalker Part 1', 'Description 11', 500.0, 2001),
('FANTASTIC','Stalker Part 2', 'Description 12', 600.0, 2004),
('FANTASTIC','Stalker Part 3', 'Description 13', 700.0, 2004),
('FANTASTIC','Stalker Part 4', 'Description 14', 800.0, 2008);