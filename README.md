# Random Quote App

## Android Studio Version
- Android Studio Narwhal 2025.1.1 Patch 1

## Versions
- AGP: 8.9.1
- Kotlin: 2.0.21
- Ktor: 3.2.2

---

## How to Run on Local Machine

1. Open Android Studio and select **Clone**.
2. Enter the repo URL: https://github.com/pydash/randomquoteapp
3. Sync the project with Gradle.
4. Run the app.
5. Move the folder `randomquoteapp` from this repo into: xampp\htdocs\
6. Open XAMPP and start **Apache** and **MySQL**.
7. Create a database named `quotedb` and a table named `quotes`.

### SQL Query to Create Table:
```sql
CREATE TABLE quotes (
 id INT PRIMARY KEY AUTO_INCREMENT,
 text VARCHAR(255) NOT NULL,
 author VARCHAR(100)
);
```

## Things to Change in Code
In AddQuoteActivity.kt, update the addQuote function:
```kotlin
suspend fun addQuote() {
    client.get("http://[your-ip-address]/randomquoteapp/REST/addquote.php")
}
```

## How to Get Your IP Address (Windows)

1. Open CMD.
2. Type:
```nginx
ipconfig
```
3. Find the line:
```css
IPv4 Address . . . . . . . . . . : 192.168.X.X
```
