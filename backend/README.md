# CareSync backend

Spring Boot 3 (Java 21) + PostgreSQL API, mirroring the Patient/Appointment/User
domain model and validation rules already defined in the Next.js frontend
(`types/index.d.ts`, `lib/validation.ts`).

## Run locally

```bash
docker compose up -d        # starts Postgres on localhost:5432
./mvnw spring-boot:run       # or: mvn spring-boot:run
```

Flyway runs the migrations in `src/main/resources/db/migration` on startup.

## Access control & rate limiting

- Every `/api/**` request needs an `X-API-Key` header matching one of the
  comma-separated keys in `API_KEYS`. `/actuator/health` is open.
- Requests are rate-limited per API key (falling back to remote IP) using a
  fixed in-memory window: `RATE_LIMIT_MAX_REQUESTS` per `RATE_LIMIT_WINDOW_SECONDS`
  (defaults: 60 req / 60s). Over the limit returns `429`.

## Config (env vars)

| Var | Default | Purpose |
|---|---|---|
| `DB_URL` | `jdbc:postgresql://localhost:5432/caresync` | Postgres connection |
| `DB_USER` / `DB_PASSWORD` | `caresync` / `caresync` | Postgres credentials |
| `API_KEYS` | empty (all `/api/**` calls rejected) | comma-separated valid keys |
| `RATE_LIMIT_MAX_REQUESTS` | `60` | requests allowed per window |
| `RATE_LIMIT_WINDOW_SECONDS` | `60` | window size |

## Endpoints

- `POST /api/users`, `GET /api/users/{id}`
- `POST /api/patients`, `GET /api/patients/{id}`
- `POST /api/appointments`, `GET /api/appointments/{id}`, `GET /api/appointments?userId=`, `PATCH /api/appointments/{id}`
