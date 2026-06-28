# raceflow-room-service
Microservicio de salas de RaceFlow. Ciclo de vida de salas: creación, ingreso por código, participantes.

**Stack:** Java 21 · Spring Boot · PostgreSQL (Room DB) · RabbitMQ (productor)
**Responsabilidad:** Crear sala · Generar código · Gestionar participantes · Publicar eventos en RabbitMQ
**Escala:** ×2
