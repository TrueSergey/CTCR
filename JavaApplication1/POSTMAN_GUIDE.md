# Гайд отправки REST запросов (Postman)

## Базовый URL
```
http://localhost:8080
```

## Тестовые сценарии

### 1. Получить все группы
```
GET /api/group
```
**Ответ (200 OK):**
```json
[
  {
    "id": 1,
    "name": "ПИ-20"
  },
  {
    "id": 2,
    "name": "ПИ-21"
  }
]
```

### 2. Получить группу по ID
```
GET /api/group/1
```
**Ответ (200 OK):**
```json
{
  "id": 1,
  "name": "ПИ-20"
}
```

### 3. Получить группу по названию
```
GET /api/group/name/ПИ-20
```
**Ответ (200 OK):**
```json
{
  "id": 1,
  "name": "ПИ-20"
}
```

### 4. Создать новую группу (PUT)
```
PUT /api/group
Content-Type: application/json

{
  "name": "ПМ-22"
}
```
**Ответ (201 Created):**
```
```

### 5. Обновить группу (POST)
```
POST /api/group
Content-Type: application/json

{
  "id": 1,
  "name": "ПИ-20 (обновлено)"
}
```
**Ответ (200 OK):**
```
```

### 6. Удалить группу
```
DELETE /api/group/1
```
**Ответ (200 OK):**
```
```

---

### 7. Получить всех студентов
```
GET /api/student
```
**Ответ (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Иван",
    "surname": "Иванов"
  },
  {
    "id": 2,
    "name": "Петр",
    "surname": "Петров"
  }
]
```

### 8. Получить студента по ID
```
GET /api/student/1
```
**Ответ (200 OK):**
```json
{
  "id": 1,
  "name": "Иван",
  "surname": "Иванов"
}
```

### 9. Получить студентов группы
```
GET /api/student/group/1
```
**Ответ (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Иван",
    "surname": "Иванов"
  },
  {
    "id": 2,
    "name": "Петр",
    "surname": "Петров"
  }
]
```

### 10. Получить студентов по фамилии
```
GET /api/student/surname/Иванов
```
**Ответ (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Иван",
    "surname": "Иванов"
  }
]
```

### 11. Создать студента (PUT)
```
PUT /api/student
Content-Type: application/json

{
  "name": "Сергей",
  "surname": "Сергеев",
  "group": {
    "id": 1
  }
}
```
**Ответ (201 Created):**
```
```

### 12. Обновить студента (POST)
```
POST /api/student
Content-Type: application/json

{
  "id": 1,
  "name": "Иван Обновленный",
  "surname": "Иванов",
  "group": {
    "id": 1
  }
}
```
**Ответ (200 OK):**
```
```

### 13. Удалить студента
```
DELETE /api/student/1
```
**Ответ (200 OK):**
```
```

---

## Коды ответов

| Код | Описание |
|-----|---------|
| 200 OK | Успешная операция |
| 201 Created | Ресурс успешно создан |
| 404 Not Found | Ресурс не найден |
| 400 Bad Request | Неверные параметры запроса |
| 500 Internal Server Error | Ошибка сервера |

---

## Примечания

1. **JSON Format**: Все запросы и ответы используют формат JSON
2. **Content-Type**: Для PUT и POST запросов установите `Content-Type: application/json`
3. **Group при создании Student**: При создании студента обязательно передайте группу с её ID
4. **Response Body**: Методы delete и другие операции обновления могут возвращать пустой body

## Использование Postman

1. Откройте Postman
2. Создайте новый запрос (New > Request)
3. Установите метод (GET, POST, PUT, DELETE)
4. Введите URL
5. Если необходимо, установите Headers и Body
6. Нажмите Send
