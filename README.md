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

---

## Estructura del proyecto

---

## Configuracion local

---

## Endpoints REST

---

## Pruebas y calidad

---

## CI/CD

