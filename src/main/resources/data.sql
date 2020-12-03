DROP TABLE IF EXISTS contacts;

CREATE TABLE contacts (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(100) DEFAULT NULL,
  phone_number VARCHAR(10) NOT NULL,
  status VARCHAR(10) DEFAULT 'ACTIVE'
);

INSERT INTO contacts (first_name, last_name, email, phone_number, status) VALUES
  ('Aman', 'Mishra', 'aman.mishra@random.mail', 02234792734, 'Inactive'),
  ('Sahrukh', 'Khan', 'king_khan@random.mail', 3410809213, 'Active'),
  ('Priyanka', 'Chopra', 'priyanka@random.mail', 4123125152, 'Active');