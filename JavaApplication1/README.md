# REST Service - Лабораторная работа 5

## Описание
Это Spring-based REST сервис для управления студентами и группами. Проект использует:
- Spring Framework 4.3.10
- Spring Data JPA  
- Hibernate 5.2.11
- Maven для сборки
- MySQL база данных

## Структура проекта

```
src/main/java/by/vstu/isap/zamok/lab5/
├── entity/          # JPA сущности (Group, Student, AbstractEntity)
├── dao/             # Интерфейсы DAO слоя
├── dao/impl/        # Реализация DAO (включая JpaRepository)
├── service/         # Интерфейсы сервисов
├── service/impl/    # Реализация бизнес-логики
├── controller/      # REST контроллеры
└── config/          # Spring конфигурация
```

## Установка и запуск

### Требования
- Java 8+
- Maven 3.6+ (или используйте предоставленный скрипт)
- MySQL сервер

### Конфигурация БД

Обновите параметры подключения в `src/main/resources/spring-jpa.xml`:
```xml
<property name="url" value="jdbc:mysql://localhost:3306/gr" />
<property name="username" value="root" />
<property name="password" value="" />
```

### Сборка
```bash
cd /workspaces/CTCR/JavaApplication1
/tmp/apache-maven-3.8.1/bin/mvn clean compile
```

### Сборка WAR
```bash
/tmp/apache-maven-3.8.1/bin/mvn clean package -DskipTests
```

## REST API Endpoints

### Группы (Group)
- `GET /api/group` - получить все группы
- `GET /api/group/{id}` - получить группу по ID
- `GET /api/group/name/{name}` - получить группу по имени
- `PUT /api/group` - создать новую группу
- `POST /api/group` - обновить группу
- `DELETE /api/group/{id}` - удалить группу

### Студенты (Student)
- `GET /api/student` - получить всех студентов
- `GET /api/student/{id}` - получить студента по ID
- `GET /api/student/group/{id}` - получить студентов группы
- `GET /api/student/surname/{surname}` - получить студентов по фамилии
- `PUT /api/student` - создать нового студента
- `POST /api/student` - обновить студента
- `DELETE /api/student/{id}` - удалить студента

## Тестирование с Postman

1. Получить все группы:
```
GET http://localhost:8080/api/group
```

2. Создать новую группу:
```
PUT http://localhost:8080/api/group
Header: Content-Type: application/json
Body: {"name": "ПИ-20"}
```

3. Получить студентов группы:
```
GET http://localhost:8080/api/student/group/1
```

## Архитектура

### Entity Layer
- `AbstractEntity` - базовый класс с ID
- `Group` - сущность группы (один-ко-многим со Student)
- `Student` - сущность студента (много-к-одному с Group)

### DAO Layer
- `Dao<T>` - generic интерфейс для CRUD операций
- `GroupDao`, `StudentDao` - специализированные интерфейсы
- `GroupJpaRepository`, `StudentJpaRepository` - Spring Data репозитории
- `GroupDaoImpl`, `StudentDaoImpl` - реализация через репозитории

### Service Layer
- `Service<T>` - generic интерфейс бизнес-логики
- `GroupService`, `StudentService` - специализированные интерфейсы
- `GroupServiceImpl`, `StudentServiceImpl` - реализация сервисов

### Controller Layer
- `AbstractController<T>` - базовый контроллер с типовыми CRUD операциями
- `GroupController`, `StudentController` - специализированные контроллеры

## Jackson конфигурация

Для предотвращения рекурсивных ссылок при сериализации JSON:
- На поле `Student.group` установлена аннотация `@JsonProperty(access = Access.WRITE_ONLY)`
- Это позволяет читать группу при создании студента, но не включает её в ответ

## Примечания

- Проект использует Hibernate для ORM
- Spring Data JPA упрощает работу с БД
- REST контроллеры используют аннотации (`@RestController`, `@GetMapping`, и т.д.)
- Все ошибки обработаны с использованием `ResponseEntity` и HTTP статус кодов

## Дальнейшее развитие

Необходимо добавить:
1. Аутентификацию и авторизацию
2. Логирование
3. Обработку исключений
4. Валидацию данных
5. Unit/Integration тесты
6. Документацию Swagger/OpenAPI
