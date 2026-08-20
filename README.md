# 📚 Bookshop Management System — Java Client-Server Desktop Application

[![Java](https://img.shields.io/badge/Language-Java%20%2F%20Swing-ED8B00?logo=openjdk)](https://www.java.com/)
[![IDE](https://img.shields.io/badge/IDE-Apache%20NetBeans-1B6AC6?logo=apache-netbeans)](https://netbeans.apache.org/)
[![Database](https://img.shields.io/badge/Database-MySQL%20%2F%20MariaDB-4169E1?logo=mysql)](https://www.mysql.com/)
[![XAMPP](https://img.shields.io/badge/Environment-XAMPP-FB7A24?logo=apache)](https://www.apachefriends.org/)

A desktop application built with Java Swing and NetBeans, designed to streamline bookshop sales, inventory tracking, and invoice management. Built on a Client-Server architecture using socket communication, a custom generic database repository layer, and a local MySQL database via XAMPP.

---

## 🌟 Key Features

### 💻 Client & UI Application
- Interactive graphical UI built with Java Swing for managing books, sellers, and customer invoices
- Invoice and order generation — calculate totals and link sales directly to active inventory
- Search and filter books by title, author, category, or stock availability

### 🖥️ Server & Database Layer
- TCP/IP socket communication — dedicated server handling concurrent client requests
- Generic repository pattern mapping MySQL result sets to domain entities
- Relational database storage ensuring consistency for books, sellers, and transactions

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language & Framework | Java (JDK 8+), Java Swing (GUI) |
| Development Environment | Apache NetBeans IDE |
| Backend / Server | Multi-threaded TCP/IP Java socket server |
| Database | MySQL / MariaDB |
| Environment | XAMPP Control Panel |

---


## 🚀 Getting Started

### Prerequisites
- [Apache NetBeans IDE](https://netbeans.apache.org/)
- Java Development Kit (JDK 8 or higher)
- [XAMPP](https://www.apachefriends.org/)
- Git

### 1. Clone the repository
```bash
git clone https://github.com/nadjamladenovic/bookshop-system-java.git
```

### 2. Start XAMPP & import the database
1. Open the **XAMPP Control Panel** and start **MySQL** (and **Apache** for phpMyAdmin access)
2. Open [phpMyAdmin](http://localhost/phpmyadmin/) in your browser
3. Create a new database named `bookshop` (or as set in the project's connection config)
4. Select the database → **Import** tab → choose the `.sql` file from the `database/` folder → **Go**

### 3. Open both projects in NetBeans
1. Open Apache NetBeans
2. **File → Open Project...**
3. Navigate to the cloned repository and open **both** projects:
   - `BookshopServer`
   - `BookshopClient`

### 4. Run the application

> ⚠️ **Run order matters** — the server must be started before the client, so the socket connection is open and ready to accept it.

**Step 1 — Launch the server**
1. Right-click on `BookshopServer` → **Run** (or press `F6`)
2. On the Server GUI, click **Start Server** to begin listening for connections

**Step 2 — Launch the client**
1. Right-click on `BookshopClient` → **Run** (or press `F6`)
2. The Client GUI will open, ready for login and bookshop management

---

## 👩‍💻 Author

[@nadjamladenovic](https://github.com/nadjamladenovic)


