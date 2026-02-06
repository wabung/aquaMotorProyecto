DROP DATABASE IF EXISTS dealership;
CREATE DATABASE dealership;
USE dealership;

CREATE TABLE user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(16) NOT NULL
);

CREATE TABLE vehicle (
    vehicle_id INT AUTO_INCREMENT PRIMARY KEY,
    registration VARCHAR(50) UNIQUE NOT NULL,
    model VARCHAR(50) NOT NULL,
    type ENUM('motorbike', 'car', 'truck', 'van', 'moped') NOT NULL,
    entry_date DATE NOT NULL,
    estimated_price DECIMAL(10,2) NOT NULL
);

CREATE TABLE parts (
    part_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

CREATE TABLE mechanic (
    mechanic_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    speciality_type ENUM('motorbike', 'car', 'truck', 'van', 'moped') NOT NULL,
    isBoss BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);

CREATE TABLE boss (
    boss_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);

CREATE TABLE salesperson (
    salesperson_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);

CREATE TABLE clients (
    client_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20)
);

CREATE TABLE repairment (
    repairment_id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT NOT NULL,
    mechanic_id INT NOT NULL,
    description VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    status VARCHAR(50),
    estimated_budget DECIMAL(10,2),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id),
    FOREIGN KEY (mechanic_id) REFERENCES mechanic(mechanic_id)
);

CREATE TABLE repairment_requires (
    part_id INT NOT NULL,
    repairment_id INT NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (part_id, repairment_id),
    FOREIGN KEY (part_id) REFERENCES parts(part_id),
    FOREIGN KEY (repairment_id) REFERENCES repairment(repairment_id)
);

CREATE TABLE offer (
    offer_id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT NOT NULL,
    client_id INT NOT NULL,
    salesperson_id INT NOT NULL,
    deadline DATE NOT NULL,
    price_offer DECIMAL(15,2) NOT NULL,
    UNIQUE (vehicle_id, client_id, salesperson_id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id),
    FOREIGN KEY (client_id) REFERENCES clients(client_id),
    FOREIGN KEY (salesperson_id) REFERENCES salesperson(salesperson_id)
);

CREATE TABLE sold (
    sold_id INT AUTO_INCREMENT PRIMARY KEY,
    offer_id INT NOT NULL,
    final_price DECIMAL(15,2) NOT NULL,
    final_date DATE NOT NULL,
    UNIQUE (offer_id),
    FOREIGN KEY (offer_id) REFERENCES offer(offer_id)
);

INSERT INTO user (name, email, password) VALUES 
('John Doe', 'john.doe@dealership.com', 'pass123'),
('Alice Smith', 'alice.smith@dealership.com', 'boss456'),
('Charles Vance', 'charles.vance@dealership.com', 'sales789'),
('Martha Low', 'marta.low@dealership.com', 'mech101'),
('Robert Saint', 'robert.saint@dealership.com', 'sales202'),
('UserMec', 'mechanic@dealership.com', 'mec123'),
('UserBoss', 'boss@dealership.com', 'boss123'),
('UserSales', 'sales@dealership.com', 'sales123'),
('Carlos Sainz', 'carlos.sainz@dealership.com', 'mec001'),
('Fernando Alonso', 'fernando.alo@dealership.com', 'mec002'),
('Lewis Hamilton', 'lewis.ham@dealership.com', 'mec003'),
('Max Verstappen', 'max.ver@dealership.com', 'mec004'),
('Lando Norris', 'lando.nor@dealership.com', 'mec005'),
('Oscar Piastri', 'oscar.pia@dealership.com', 'mec006'),
('George Russell', 'george.rus@dealership.com', 'mec007'),
('Sergio Perez', 'checo.per@dealership.com', 'mec008'),
('Charles Leclerc', 'charles.lec@dealership.com', 'mec009'),
('Valtteri Bottas', 'valtteri.bot@dealership.com', 'mec010'),
('Harry Potter', 'harry.p@magic.com', 'user001'),
('Hermione Granger', 'hermione.g@magic.com', 'user002'),
('Ron Weasley', 'ron.w@magic.com', 'user003');

INSERT INTO mechanic (user_id, speciality_type, isBoss) VALUES 
(1, 'car', TRUE),
(4, 'motorbike', FALSE),
(6, 'van', FALSE),
(9, 'car', FALSE),
(10, 'car', FALSE),
(11, 'car', FALSE),
(12, 'car', FALSE),
(13, 'moped', FALSE),
(14, 'truck', FALSE),
(15, 'truck', FALSE),
(16, 'car', FALSE),
(17, 'car', FALSE),
(18, 'motorbike', FALSE);

INSERT INTO boss (user_id) VALUES (2), (7);
INSERT INTO salesperson (user_id) VALUES (3), (5), (8);

INSERT INTO vehicle (registration, model, type, entry_date, estimated_price) VALUES 
('1234-ABC', 'Golf', 'car', '2024-01-15', 25000.00),
('5678-DEF', 'Z650', 'motorbike', '2024-02-01', 7000.00),
('9012-GHI', 'Sprinter', 'van', '2024-02-10', 35000.00),
('3456-JKL', 'FH16', 'truck', '2024-02-15', 120000.00),
('7890-MNP', 'Zip 50', 'moped', '2024-02-20', 2500.00),
('1001-KRT', 'Civic', 'car', '2023-12-05', 22000.00),
('2002-LMS', 'Corolla', 'car', '2023-12-20', 21000.00),
('3003-NQA', 'Mustang', 'car', '2024-01-05', 45000.00),
('4004-OPD', 'Camry', 'car', '2024-01-08', 26000.00),
('5005-RST', 'Accord', 'car', '2024-01-12', 28000.00),
('6006-UVW', 'Model 3', 'car', '2024-01-25', 42000.00),
('7007-XYZ', 'Model Y', 'car', '2024-02-02', 48000.00),
('8008-ABC', 'X5', 'car', '2024-02-05', 65000.00),
('9009-DEF', 'Q7', 'car', '2024-02-10', 68000.00),
('1111-GHI', 'C-Class', 'car', '2024-02-15', 55000.00),
('2222-JKL', '3 Series', 'car', '2024-02-20', 52000.00),
('3333-MNO', 'A4', 'car', '2024-03-01', 49000.00),
('4444-PQR', 'Fiesta', 'car', '2024-03-05', 18000.00),
('5555-STU', 'Focus', 'car', '2024-03-10', 23000.00),
('6666-VWX', 'Polo', 'car', '2024-03-15', 19000.00),
('7777-YZA', 'Ibiza', 'car', '2024-03-20', 17000.00),
('8888-BCD', 'Leon', 'car', '2024-03-25', 24000.00),
('9999-EFG', 'Megane', 'car', '2024-04-01', 22500.00),
('1212-HIJ', 'Clio', 'car', '2024-04-05', 16500.00),
('3434-KLM', 'Duke 390', 'motorbike', '2024-01-10', 5500.00),
('5656-NOP', 'MT-07', 'motorbike', '2024-02-12', 7500.00),
('7878-QRS', 'CBR650R', 'motorbike', '2024-03-05', 9000.00),
('9090-TUV', 'Ninja 400', 'motorbike', '2024-03-15', 6500.00),
('1313-WXY', 'R7', 'motorbike', '2024-04-02', 9500.00),
('2424-ZAB', 'Scania S', 'truck', '2024-01-20', 130000.00),
('3535-CDE', 'Actros', 'truck', '2024-02-25', 125000.00),
('4646-FGH', 'Transport', 'van', '2024-03-10', 38000.00),
('5757-IJK', 'Vito', 'van', '2024-03-22', 36000.00),
('6868-LMN', 'Traffic', 'van', '2024-04-05', 34000.00),
('7979-OPQ', 'Berlingo', 'van', '2024-04-08', 25000.00);

INSERT INTO parts (name, price) VALUES 
('Oil Filter', 15.50), ('Front Tire', 85.00), ('Brake Pads', 45.00), 
('12V Battery', 120.00), ('Spark Plug', 12.00), ('Air Filter', 22.50), 
('Timing Belt', 150.00), ('Water Pump', 85.00), ('Alternator', 200.00), 
('Headlight Bulb', 18.00), ('Rear Bumper', 350.00), ('Side Mirror', 120.00);

INSERT INTO clients (name, email, phone_number) VALUES 
('Peter Martinez', 'peter.mtz@gmail.com', '555-0101'),
('Lucy Towers', 'lucia.t@outlook.com', '555-0202'),
('Fleet Solutions Ltd', 'info@fleetsolutions.com', '555-0303');

INSERT INTO repairment (vehicle_id, mechanic_id, description, start_date, end_date, status, estimated_budget) VALUES 
(1, 1, 'Oil change', '2024-01-05', '2024-01-06', 'Completed', 150.00),
(6, 2, 'Brake replacement', '2024-01-08', '2024-01-10', 'Completed', 200.00),
(20, 3, 'Chain adjustment', '2024-01-12', '2024-01-12', 'Completed', 50.00),
(25, 4, 'Engine check', '2024-01-22', '2024-01-25', 'Completed', 500.00),
(7, 1, 'Battery replacement', '2024-01-25', '2024-01-26', 'Completed', 180.00),
(8, 5, 'Tire change', '2024-01-28', '2024-01-28', 'Completed', 350.00),
(2, 3, 'Electrical check', '2024-02-02', '2024-02-03', 'Completed', 200.00),
(9, 6, 'Suspension fix', '2024-02-05', '2024-02-07', 'Completed', 450.00),
(10, 1, 'General Service', '2024-02-10', '2024-02-11', 'Completed', 300.00),
(21, 2, 'Oil & Filter', '2024-02-12', '2024-02-12', 'Completed', 120.00),
(26, 7, 'Transmission fluid', '2024-02-15', '2024-02-16', 'Completed', 250.00),
(11, 4, 'Spark plugs', '2024-02-18', '2024-02-18', 'Completed', 100.00),
(12, 8, 'Coolant leak', '2024-02-20', '2024-02-22', 'Completed', 180.00),
(3, 1, 'Clutch replacement', '2024-02-22', '2024-02-25', 'Completed', 800.00),
(13, 9, 'Diagnostic', '2024-02-25', '2024-02-25', 'Completed', 80.00),
(14, 3, 'Paint scratch repair', '2024-02-28', '2024-02-29', 'Completed', 150.00),
(15, 5, 'Brakes & Discs', '2024-03-01', '2024-03-02', 'Completed', 400.00),
(22, 10, 'Chain & Sprockets', '2024-03-05', '2024-03-06', 'Completed', 220.00),
(27, 11, 'Turbo check', '2024-03-08', NULL, 'In Progress', 1200.00),
(16, 1, 'AC Recharge', '2024-03-10', '2024-03-10', 'Completed', 90.00),
(17, 2, 'Headlight fix', '2024-03-12', '2024-03-12', 'Completed', 60.00),
(18, 6, 'Suspension noise', '2024-03-15', NULL, 'In Progress', 300.00),
(28, 7, 'Gearbox issue', '2024-03-18', NULL, 'Pending Parts', 1500.00),
(19, 4, 'Timing belt', '2024-03-20', '2024-03-22', 'Completed', 600.00),
(23, 12, 'ECU Remap', '2024-03-25', '2024-03-25', 'Completed', 350.00),
(4, 1, 'Oil leak repair', '2024-03-28', NULL, 'In Progress', 250.00),
(24, 8, 'Routine Service', '2024-04-01', NULL, 'In Progress', 200.00),
(29, 9, 'Door lock fix', '2024-04-02', NULL, 'In Progress', 150.00),
(30, 3, 'Windshield replace', '2024-04-03', NULL, 'Pending Approval', 400.00),
(5, 5, 'Flat tire', '2024-04-05', '2024-04-05', 'Completed', 40.00),
(31, 13, 'Hydraulic pump', '2024-04-06', NULL, 'In Progress', 900.00);

INSERT INTO repairment_requires (part_id, repairment_id, quantity) VALUES 
(1, 1, 1), (5, 1, 4), (3, 2, 2), (4, 5, 1), (2, 6, 2), 
(12, 11, 1), (7, 12, 1), (6, 10, 1), (2, 9, 4), 
(3, 15, 2), (7, 22, 1), (5, 23, 4);

INSERT INTO offer (vehicle_id, client_id, salesperson_id, deadline, price_offer) VALUES 
(3, 1, 1, '2024-03-01', 34000.00),
(5, 2, 3, '2024-03-15', 2300.00),
(32, 3, 2, '2024-04-10', 16000.00);

INSERT INTO sold (offer_id, final_price, final_date) VALUES 
(1, 33500.00, '2024-02-25'),
(3, 15800.00, '2024-04-02');