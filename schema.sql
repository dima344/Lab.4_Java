CREATE TABLE IF NOT EXISTS phones_db (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    brand VARCHAR(100),
    model VARCHAR(100),
    price NUMERIC(10, 2),
    memory_gb INT,
    battery_capacity INT,
    os_type VARCHAR(50),
    -- Поля для нащадків (nullable)
    screen_size NUMERIC(4, 1),
    has_nfc BOOLEAN,
    has_flashlight BOOLEAN,
    has_cooling BOOLEAN,
    trigger_count INT,
    satellite_network VARCHAR(100),
    is_waterproof BOOLEAN
);