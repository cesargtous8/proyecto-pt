-- Base de datos para el microservicio de Productos
CREATE DATABASE product_db;
\c product_db;

CREATE TABLE products (
    id VARCHAR(36) PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE DATABASE inventory_db;
\c inventory_db;

CREATE TABLE inventory (
    id VARCHAR(36) PRIMARY KEY DEFAULT gen_random_uuid(),
    product_id VARCHAR(36) NOT NULL,
    quantity INTEGER NOT NULL DEFAULT 0,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Datos de ejemplo
\c product_db;
INSERT INTO products (id, name, description, price) VALUES 
('prod-001', 'Laptop Gaming', 'High performance gaming laptop', 1299.99),
('prod-002', 'Wireless Mouse', 'Ergonomic wireless mouse', 49.99),
('prod-003', 'Mechanical Keyboard', 'RGB mechanical keyboard', 89.99);

\c inventory_db;
INSERT INTO inventory (product_id, quantity) VALUES 
('prod-001', 10),
('prod-002', 25),
('prod-003', 15);