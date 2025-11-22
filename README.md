# TodoFlow API - Gerenciamento de Tarefas

API RESTful completa para gerenciamento de tarefas desenvolvida com **Spring Boot 3** e **PostgreSQL**.

### Funcionalidades
- CRUD completo com respostas HTTP corretas (201 Created + Location, 204 No Content, etc.)
- Validação com Bean Validation + mensagens claras
- Tratamento global de exceções (404 Not Found, 409 Conflict, 400 Bad Request)
- Status da tarefa: `PENDING` | `IN_PROGRESS` | `DONE`
- Timestamps automáticos (`createdAt` / `updatedAt`)
- Título único (evita duplicatas)
- Atualização total (PUT) e parcial (PATCH)

### Tecnologias
- Java 17+
- Spring Boot 3
- Spring Data JPA + Hibernate
- PostgreSQL
- Lombok + Maven

### Como rodar
```bash
git clone https://github.com/KaykMurphy/todo-flow-api.git
cd todo-flow-api
./mvnw spring-boot:run
