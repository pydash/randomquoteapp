# randomquoteapp

Android Studio Version Used: Android Studio Narwhal 2025.1.1 Patch 1

[versions]
AGP: 8.9.1
Kotlin: 2.0.21
ktor: 3.2.2

How to run in local machine:
1. In Android Studio home, select Clone
2. Enter repo URL https://github.com/pydash/randomquoteapp
3. Sync with Gradle
4. Run
5. move folder 'randomquoteapp' found in this repo to xampp\htdocs\
6. Open xampp; start apache and mysql
7. Create database 'quotedb' and table 'quotes'
use this query to make the table
CREATE TABLE quotes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    text VARCHAR(255) NOT NULL,
    author VARCHAR(100)
); 

Things to change in code:
1. AddQuoteActivity.kt --> suspend fun addQuote --> client.get("[your ip address]/randomquoteapp/REST/addquote.php")
