ALTER TABLE examen_visual
    ADD COLUMN IF NOT EXISTS fecha_modificacion TIMESTAMP;

UPDATE examen_visual
SET fecha_modificacion = CURRENT_TIMESTAMP
WHERE fecha_modificacion IS NULL;

ALTER TABLE orden_pedido
    ADD COLUMN IF NOT EXISTS fecha_modificacion TIMESTAMP;

UPDATE orden_pedido
SET fecha_modificacion = COALESCE(fecha_registro, CURRENT_TIMESTAMP)
WHERE fecha_modificacion IS NULL;

ALTER TABLE orden_entrega
    ADD COLUMN IF NOT EXISTS fecha_modificacion TIMESTAMP;

UPDATE orden_entrega
SET fecha_modificacion = COALESCE(fecha_registro, CURRENT_TIMESTAMP)
WHERE fecha_modificacion IS NULL;

CREATE TABLE IF NOT EXISTS proveedor (
    id_proveedor SERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL UNIQUE,
    identificacion VARCHAR(13) NOT NULL UNIQUE,
    correo VARCHAR(150),
    telefono VARCHAR(30),
    direccion VARCHAR(250),
    estado BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS producto (
    id_producto SERIAL PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    nombre VARCHAR(150) NOT NULL,
    descripcion VARCHAR(300),
    precio NUMERIC(10,2) NOT NULL CHECK (precio >= 0),
    id_proveedor INTEGER NOT NULL REFERENCES proveedor(id_proveedor),
    estado BOOLEAN NOT NULL DEFAULT TRUE
);

ALTER TABLE detalle_orden
    ADD COLUMN IF NOT EXISTS id_producto INTEGER REFERENCES producto(id_producto);
