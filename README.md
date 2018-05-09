# 19. Система Періодичні видання
**Адміністратор** здійснює ведення каталогу періодичних **видань**. **Читач** може оформити **Підписку**, попередньо вибравши періодичні **Видання** із списку. Система розраховує суму для оплати та реєструє **Платіж**.

## Технології
- JSP + JSTL
- Servlets + Filters
- JDBC
- Log4J2
- JUnit
- Mockito
- Pagination
- custom tags

## Для запуску мають бути наявні
1. JDK и JRE
2. MySQL Server
3. Apache Maven
4. Apache Tomcat

## Інструкції по запуску
1. Завантажити і розпакувати архів з проектом або завантажити за допомогою клієнта Git
2. Підключитися до MySQL Server за допомогою MySQL Workbench
3. Створити схему бази даних і необхідні таблиці за допомогою скрипта `%PROJECT%/src/main/resources/create_db.sql`
4. Відкрити командний рядок
5. Перейти до кореневої директорії (з `pom.xml`) 
6. Виконати команди: `mvn clean`, `mvn compile`, `mvn package`, `mvn tomcat7:run` або всі одразу `mvn clean compile package tomcat7:run`
7. Відкрити у браузері: http://localhost:8888/

## Вхід
- **Адміністратор:** логін - admin@ukr.net; пароль - adminadmin
- **Читач:** створити нового або логін - vlada@gmail.com; пароль - vladaclient
