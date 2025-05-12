# File Hider App (Java Swing + JDBC + JavaMail)

## 📌 Features
- Register/Login using Email OTP
- File hiding by moving content to DB (CLOB)
- File unhide and restore from DB
- GUI using Java Swing

## 🛠️ Tech Stack
- Java (Swing, JDBC, JavaMail)
- MySQL Database

## ⚙️ Environment Variables
Create a `.env` file with:
```
EMAIL_SENDER=your_email@gmail.com
EMAIL_PASSWORD=your_16_char_app_password
DB_URL=jdbc:mysql://localhost:3306/student
DB_USER=your_db_user
DB_PASS=your_db_password
```

## 🧪 Sample SQL Setup
```sql
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE
);

CREATE TABLE data (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    path TEXT,
    email VARCHAR(100),
    bin_data LONGTEXT
);
```

## 🚀 How to Run
1. Clone the repo
2. Setup environment variables or config file
3. Compile and run `Main.java`
