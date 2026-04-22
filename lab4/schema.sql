CREATE TABLE IF NOT EXISTS store_items (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    storage_capacity INT NOT NULL,
    os VARCHAR(50) NOT NULL,
    camera_megapixels NUMERIC(5, 2),
    has_flashlight BOOLEAN,
    cooling_system VARCHAR(100),
    number_of_screens INT,
    quantity INT NOT NULL
);