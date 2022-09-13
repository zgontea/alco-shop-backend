insert into users (name, surname, email, password, phone, is_admin) values('Mr.', 'Admin', 'admin@admin.com', '$2a$10$32IDW.SWwZ64BTvaxy02oONQFoa4SmhcKAAUuYxm77ena7uS9lEpO', '793130773', true);

insert into categories (category_id, name) values(1, 'Wódki');
insert into categories (category_id, name) values(2, 'Wina');
insert into categories (category_id, name) values(3, 'Piwa');
insert into categories (category_id, name) values(4, 'Whiskey');
insert into categories (category_id, name) values(5, 'Likiery');

insert into products (name, image, unit_price, description, size, concentration, category_id) values('Absolut Lime', 'absolut-lime.jpg', 54.99, 'Absolut o smaku limonki', 1000, 40, 1);
insert into products (name, image, unit_price, description, size, concentration, category_id) values('Absolut Grapefruit', 'absolut-grapefruit.jpg', 53.99, 'Absolut o smaku grejfrutowym', 1000, 40, 1);
insert into products (name, image, unit_price, description, size, concentration, category_id) values('Absolut Raspberry', 'absolut-raspberri.jpg', 52.99, 'Absolut o smaku malinowym', 1000, 40, 1);
insert into products (name, image, unit_price, description, size, concentration, category_id) values('Absolut', 'absolut-vodka.jpg', 57.99, 'Wódka czysta Absolut', 1000, 40, 1);
insert into products (name, image, unit_price, description, size, concentration, category_id) values('Absolut Watermelon', 'absolut-watermelon.jpg', 52.99, 'Absolut o smaku arbuzowym', 1000, 40, 1);
insert into products (name, image, unit_price, description, size, concentration, category_id) values('Los Pinguinos', 'los-pinguinos.jpg', 82.99, 'Wino z pingwina', 750, 11, 2);
insert into products (name, image, unit_price, description, size, concentration, category_id) values('Fresco', 'fresco.jpg', 22.99, 'Różowe pół słodkie', 750, 10, 2);
insert into products (name, image, unit_price, description, size, concentration, category_id) values('El Sol', 'el-sol.jpg', 32.99, 'Hiszpańskie różowe pół słodkie', 750, 12, 2);
insert into products (name, image, unit_price, description, size, concentration, category_id) values('Somersby Pina Colada', 'piwo-somersby-pina-colada.jpg', 4.99, 'Smaki lata', 400, 4.5, 3);
