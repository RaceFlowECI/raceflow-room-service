# RACEFLOW — Room Service

> [!IMPORTANT]
> Este repositorio contiene el **Room Service** de RaceFlow: gestion de salas de competicion.

> Para informacion general consulta el [perfil de la organizacion](https://github.com/RaceFlowECI).

---

## Tabla de contenido
- [Descripcion general](#descripcion-general)
- [Stack tecnologico](#stack-tecnologico)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Configuracion local](#configuracion-local)
- [Endpoints REST](#endpoints-rest)
- [Pruebas y calidad](#pruebas-y-calidad)
- [CI/CD](#cicd)

---

## Descripcion general

> [!NOTE]
> Microservicio de gestion de salas de competicion. Crea salas con un codigo unico de 6 caracteres, gestiona su estado (WAITING, ACTIVE, FINISHED) y publica eventos en RabbitMQ cuando una sala cambia de estado.

### Responsabilidades principales

| Responsabilidad | Descripcion |
|---|---|
| **Creacion** | Genera salas con codigo unico y las persiste en PostgreSQL. |
| **Estado** | Controla las transiciones WAITING → ACTIVE → FINISHED. |
| **Eventos** | Publica en RabbitMQ cuando una sala se activa o finaliza. |
| **Configuracion** | Permite definir deporte, duracion y numero maximo de participantes. |

---

## Stack tecnologico

### Backend
![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3.2-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

### Testing y calidad
![JUnit](https://img.shields.io/badge/JUnit_5-25A162?style=for-the-badge&logo=java&logoColor=white)
![JaCoCo](https://img.shields.io/badge/JaCoCo-BB0A30?style=for-the-badge)
![SonarQube](https://img.shields.io/badge/SonarQube-4E9BCD?style=for-the-badge&logo=sonarqube&logoColor=white)

### DevOps
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

---

## Estructura del proyecto

```text
raceflow-room-service/
├── .github/workflows/
├── .env.example
├── .gitignore
├── Dockerfile
├── pom.xml
└── src/main/java/edu/eci/arsw/raceflow/room/
    ├── RoomApplication.java
    ├── config/
    │   └── RabbitMQConfig.java
    ├── controller/
    │   └── RoomController.java
    ├── dto/
    │   ├── CreateRoomRequest.java
    │   └── RoomResponse.java
    ├── model/
    │   └── Room.java
    ├── repository/
    │   └── RoomRepository.java
    └── service/
        ├── RoomService.java
        └── RoomEventPublisher.java
```

---

## Configuracion local

### 1. Clonar el repositorio
```bash
git clone https://github.com/RaceFlowECI/raceflow-room-service.git
cd raceflow-room-service
```

### 2. Compilar
```bash
mvn clean install
```

### 3. Configurar variables de entorno
```bash
cp .env.example .env
```
```env
DB_HOST=localhost
DB_USER=raceflow
DB_PASSWORD=secret
RABBITMQ_HOST=localhost
```

### 4. Ejecutar
```bash
mvn spring-boot:run
```
> [!TIP]
> El servicio arranca en `http://localhost:8082`. Requiere PostgreSQL y RabbitMQ.

---

## Endpoints REST

---

## Pruebas y calidad

---

## CI/CD

