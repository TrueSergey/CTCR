# Инструкции по развертыванию REST Service

## Предварительные требования

- Java Development Kit (JDK) 8 или выше ✓ (установлена версия 11)
- Apache Maven (установку смотрите ниже) ✓
- MySQL Server ✓
- Apache Tomcat 8.x или выше (для развертывания WAR файла)
- или используйте встроенный сервер

## Подготовка окружения

### 1. Проверка Java
```bash
java -version
# Должна быть версия 8 или выше
```

### 2. Maven (уже установлен)
```bash
/tmp/apache-maven-3.8.1/bin/mvn --version
# Версия 3.8.1 совместима с Java 11
```

### 3. Подготовка базы данных

#### Создание схемы (если её нет)
```sql
CREATE DATABASE IF NOT EXISTS gr;
USE gr;
```

#### Создание таблиц (Hibernate создаст автоматически)
или вручную:

```sql
CREATE TABLE gruppyi (
    Shifr INT PRIMARY KEY AUTO_INCREMENT,
    Nazvanie VARCHAR(50) NOT NULL UNIQUE,
    DataFormir DATE NOT NULL,
    KodPlana INT NOT NULL,
    status VARCHAR(20),
    statusDate DATETIME
);

CREATE TABLE studentyi (
    NomerZachetki BIGINT PRIMARY KEY,
    ShifrGruppyi INT NOT NULL,
    Familiya VARCHAR(30) NOT NULL,
    Imya VARCHAR(30) NOT NULL,
    Otchestvo VARCHAR(30),
    gorod VARCHAR(30),
    adres VARCHAR(50),
    tel VARCHAR(20),
    status VARCHAR(20),
    statusDate DATETIME,
    FOREIGN KEY (ShifrGruppyi) REFERENCES gruppyi(Shifr)
);
```

#### Загрузка тестовых данных
```sql
USE gr;
SOURCE /workspaces/CTCR/JavaApplication1/src/main/resources/init_data.sql;
```

## Сборка проекта

### Maven сборка
```bash
cd /workspaces/CTCR/JavaApplication1

# Сборка на компиляцию
/tmp/apache-maven-3.8.1/bin/mvn clean compile

# Полная сборка с тестами
/tmp/apache-maven-3.8.1/bin/mvn clean package

# Сборка без тестов (быстрее)
/tmp/apache-maven-3.8.1/bin/mvn clean package -DskipTests
```

### Результат
- WAR файл: `/workspaces/CTCR/JavaApplication1/target/REST.war`

## Развертывание

### Вариант 1: Apache Tomcat

1. **Скачайте и распакуйте Tomcat** (если не установлен)
```bash
cd /tmp
wget https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.50/bin/apache-tomcat-9.0.50.tar.gz
tar -xzf apache-tomcat-9.0.50.tar.gz
export CATALINA_HOME=/tmp/apache-tomcat-9.0.50
```

2. **Скопируйте WAR файл**
```bash
cp /workspaces/CTCR/JavaApplication1/target/REST.war $CATALINA_HOME/webapps/
```

3. **Обновите конфигурацию БД** (убедитесь, что БД параметры правильные в `spring-jpa.xml`)

4. **Запустите Tomcat**
```bash
$CATALINA_HOME/bin/startup.sh
```

5. **Проверьте логи**
```bash
tail -f $CATALINA_HOME/logs/catalina.out
```

6. **Приложение доступно**
```
http://localhost:8080/REST/api/group
```

### Вариант 2: Встроенный сервер (для тестирования)

Создайте класс в `src/main/java` для запуска с встроенным Tomcat:
```java
// TomcatStarter.java
import org.apache.catalina.startup.Tomcat;

public class TomcatStarter {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
    }
}
```

(Требует дополнительных зависимостей в pom.xml)

## Варианты запуска для разработки

### Быстрый запуск через Maven (с Cargo плагином)

```bash
# Скачайте Cargo плагин через Maven и запустите локально
/tmp/apache-maven-3.8.1/bin/mvn tomcat7:run
```

(Требует конфигурации плагина в pom.xml)

## Проверка приложения

### 1. Проверка здоровья
```bash
curl -X GET http://localhost:8080/REST/api/group
```

### 2. Через Postman
- Импортируйте коллекцию (см. POSTMAN_GUIDE.md)
- Запустите тестовые запросы

### 3. Проверка логов
```bash
# Tomcat логи
tail -f /tmp/apache-tomcat-9.0.50/logs/catalina.out
tail -f /tmp/apache-tomcat-9.0.50/logs/localhost.log
```

## Конфигурация для различных окружений

### Development
```xml
<!-- spring-jpa.xml -->
<property name="url" value="jdbc:mysql://localhost:3306/gr" />
<property name="username" value="root" />
<property name="password" value="" />
```

### Production
Используйте переменные окружения:
```bash
export DB_URL=jdbc:mysql://prod-server:3306/gr
export DB_USER=prod_user
export DB_PASSWORD=prod_pass
```

Обновите spring-jpa.xml для использования переменных.

## Решение проблем

### Ошибка "Cannot load JDBC driver class 'com.mysql.jdbc.Driver'"
- Проверьте, что MySQL connector jar есть в classpath
- Убедитесь, что зависимость `mysql-connector-java` есть в pom.xml

### Port 8080 уже занят
```bash
# Найдите процесс
lsof -i :8080

# Измените порт в конфигурации Tomcat
```

### Ошибки БД подключения
- Проверьте, что MySQL сервер запущен
- Проверьте URL БД, username, password в spring-jpa.xml
- Проверьте, что база `gr` существует

### Ошибка "Table 'gr.gruppyi' doesn't exist"
```bash
# Создайте таблицы через SQL скрипт
mysql -u root < init_data.sql
```

## Остановка приложения

### Tomcat
```bash
$CATALINA_HOME/bin/shutdown.sh
```

### Через Process
```bash
pkill -f tomcat
```

## Производительность и масштабирование

1. **Connection Pool**: Отрегулируйте параметры в spring-jpa.xml
```xml
<property name="initialSize" value="5" />
<property name="maxActive" value="20" />
<property name="maxIdle" value="10" />
```

2. **Кеширование**: Добавьте Redis или Memcached слой

3. **Индексы БД**: Создайте индексы на часто используемых полях
```sql
CREATE INDEX idx_surname ON studentyi(Familiya);
CREATE INDEX idx_name ON gruppyi(Nazvanie);
```

## Следующие шаги

1. ✅ Проверьте, что приложение запущено и доступно
2. ✅ Протестируйте через Postman (смотрите POSTMAN_GUIDE.md)
3. ✅ Проверьте логи на ошибки
4. ✅ Загрузите тестовые данные
5. ✅ Убедитесь, что все API endpoints работают

## Дополнительная информация

- [Spring Framework документация](https://spring.io/projects/spring-framework)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Tomcat документация](https://tomcat.apache.org/)
- [Hibernate документация](https://hibernate.org/)
- [MySQL JDBC Driver](https://dev.mysql.com/downloads/connector/j/)
