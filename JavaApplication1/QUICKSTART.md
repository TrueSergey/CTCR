# 🚀 Быстрый старт

## За 5 минут до первого теста

### Шаг 1: Сборка проекта ✅
```bash
cd /workspaces/CTCR/JavaApplication1
/tmp/apache-maven-3.8.1/bin/mvn clean package -DskipTests
```
**Результат**: `target/REST.war`

### Шаг 2: Подготовка БД
```bash
# Убедитесь, что MySQL запущен
mysql -u root -e "CREATE DATABASE IF NOT EXISTS gr;"

# Загрузите тестовые данные
mysql -u root gr < src/main/resources/init_data.sql
```

### Шаг 3: Запуск на Tomcat
```bash
# Если Tomcat не установлен:
cd /tmp
wget -q https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.50/bin/apache-tomcat-9.0.50.tar.gz
tar -xzf apache-tomcat-9.0.50.tar.gz

# Скопируйте WAR
cp /workspaces/CTCR/JavaApplication1/target/REST.war /tmp/apache-tomcat-9.0.50/webapps/

# Запустите
/tmp/apache-tomcat-9.0.50/bin/startup.sh

# Проверьте логи
tail -f /tmp/apache-tomcat-9.0.50/logs/catalina.out
```

### Шаг 4: Первый тест в browser или Postman
```
GET http://localhost:8080/REST/api/group
```

**Ожидаемый результат**: JSON массив с группами

---

## Основные URLs для тестирования

```bash
# Получить все группы
curl http://localhost:8080/REST/api/group

# Получить всех студентов
curl http://localhost:8080/REST/api/student

# Получить студентов группы
curl http://localhost:8080/REST/api/student/group/1

# Получить студентов по фамилии
curl http://localhost:8080/REST/api/student/surname/Иванов
```

---

## Если что-то не работает

### Ошибка: Port 8080 in use
```bash
pkill -f tomcat
# Или измените порт в конфигурации
```

### Ошибка: Cannot connect to MySQL
```bash
# Проверьте подключение
mysql -u root -e "SELECT 1"

# Проверьте параметры в spring-jpa.xml
# url, username, password должны совпадать с вашей БД
```

### Ошибка при загрузке WAR
```bash
# Посмотрите логи
tail -50 /tmp/apache-tomcat-9.0.50/logs/localhost.log
tail -50 /tmp/apache-tomcat-9.0.50/logs/catalina.out
```

---

## Документация

- **README.md** - полная информация о проекте
- **POSTMAN_GUIDE.md** - все примеры запросов с response
- **DEPLOYMENT.md** - расширенные инструкции развертывания
- **SUMMARY.md** - резюме и статистика проекта

---

## Примеры запросов в Postman

### 1. Создать группу
```
PUT http://localhost:8080/REST/api/group
Header: Content-Type: application/json
Body: {"name": "ПИ-25"}
```

### 2. Создать студента
```
PUT http://localhost:8080/REST/api/student
Header: Content-Type: application/json
Body: {
  "name": "Петр",
  "surname": "Петров",
  "group": {"id": 1}
}
```

### 3. Получить студента
```
GET http://localhost:8080/REST/api/student/1
```

---

## Полезные команды

```bash
# Проверить логи Maven сборки
/tmp/apache-maven-3.8.1/bin/mvn clean compile -X 2>&1 | grep ERROR

# Проверить размер WAR
ls -lh /workspaces/CTCR/JavaApplication1/target/REST.war

# Остановить Tomcat
/tmp/apache-tomcat-9.0.50/bin/shutdown.sh

# Удалить WAR (для переразвертывания)
rm /tmp/apache-tomcat-9.0.50/webapps/REST.war
rm -rf /tmp/apache-tomcat-9.0.50/webapps/REST/
```

---

## Структура приложения

```
http://localhost:8080/REST/
├── /api/
│   ├── /group         - операции с группами
│   │   ├── GET    - получить все
│   │   ├── GET/{id} - получить по ID
│   │   ├── GET/name/{name} - поиск по названию
│   │   ├── PUT    - создать новую
│   │   ├── POST   - обновить
│   │   └── DELETE/{id} - удалить
│   └── /student       - операции со студентами
│       ├── GET    - получить всех
│       ├── GET/{id} - получить по ID
│       ├── GET/group/{id} - студенты группы
│       ├── GET/surname/{surname} - поиск по фамилии
│       ├── PUT    - создать нового
│       ├── POST   - обновить
│       └── DELETE/{id} - удалить
```

---

## Проверочный список

- [ ] Maven построил успешно (BUILD SUCCESS)
- [ ] WAR файл создан (target/REST.war)
- [ ] MySQL база создана (gr база)
- [ ] Тестовые данные загружены
- [ ] Tomcat запущен
- [ ] Приложение развернуто (появилось в webapps)
- [ ] Первый GET запрос вернул группы
- [ ] Postman протестировал основные endpoints

---

## Следующие улучшения

1. Добавить логирование (Log4j/SLF4j)
2. Добавить обработку исключений (@ExceptionHandler)
3. Добавить JWT аутентификацию
4. Добавить unit тесты (JUnit)
5. Добавить Swagger документацию
6. Кеширование через Redis
7. Собрать несколько примеров интеграционных тестов

---

**Всё готово! Приложение должно быть доступно на `http://localhost:8080/REST/`** ✨
