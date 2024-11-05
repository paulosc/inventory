-- Create the roles table
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Create the users table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

-- Create the join table for user roles
CREATE TABLE user_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parent_id INT,
    FOREIGN KEY (parent_id) REFERENCES categories(id) ON DELETE SET NULL
);

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    category_id INT,
    available BOOLEAN DEFAULT true,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL
);

-- Insert roles
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

-- Insert users
INSERT INTO users (username, password, active) VALUES ('admin', 'admin', TRUE);
INSERT INTO users (username, password, active) VALUES ('user', 'admin', TRUE);

-- Associate users with roles
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);

-- Inserting categories into the database
INSERT INTO categories (name, parent_id) VALUES ('Food', NULL);
INSERT INTO categories (name, parent_id) VALUES ('Beverages', 1);
INSERT INTO categories (name, parent_id) VALUES ('Sodas', 2);
INSERT INTO categories (name, parent_id) VALUES ('Juices', 2);
INSERT INTO categories (name, parent_id) VALUES ('Snacks', 1);
INSERT INTO categories (name, parent_id) VALUES ('Sandwiches', 5);
INSERT INTO categories (name, parent_id) VALUES ('Burgers', 5);
INSERT INTO categories (name, parent_id) VALUES ('Sweets', NULL);
INSERT INTO categories (name, parent_id) VALUES ('Cakes', 8);
INSERT INTO categories (name, parent_id) VALUES ('Cookies', 8);

INSERT INTO products (name, description, price, category_id, available) VALUES
('Coca-Cola', 'Refreshing soft drink', 1.99, 3, true),
('Orange Juice', 'Freshly squeezed orange juice', 2.50, 4, true),
('Cheeseburger', 'Delicious cheeseburger with cheddar cheese', 5.99, 7, false),
('Veggie Sandwich', 'Healthy sandwich with mixed veggies', 4.50, 6, true),
('Chocolate Cake', 'Rich chocolate cake with dark chocolate frosting', 3.75, 9, false),
('Lemonade', 'Homemade lemonade with fresh lemons', 1.75, 4, true),
('Potato Chips', 'Crispy and lightly salted potato chips', 1.25, 5, true),
('Ice Cream', 'Vanilla ice cream with chocolate chips', 2.99, 8, false),
('Latte', 'Creamy coffee latte', 3.00, 2, true),
('Apple Pie', 'Traditional apple pie with cinnamon', 4.00, 9, true),
('Chicken Caesar Salad', 'Grilled chicken on a bed of romaine lettuce with Caesar dressing', 6.50, 6, true),
('Margarita Pizza', 'Classic pizza with fresh mozzarella and basil', 8.99, 7, true),
('Tacos', 'Soft tacos with seasoned beef and fresh toppings', 4.50, 5, true),
('Spaghetti Bolognese', 'Pasta with a rich meat sauce', 7.25, 8, false),
('Mango Smoothie', 'Smooth and creamy mango smoothie', 3.50, 4, true);
