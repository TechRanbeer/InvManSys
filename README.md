# Inventory Management System 🗃️

A desktop-based inventory management application developed in Java using **Swing GUI** and **MySQL**.  
Built with **Eclipse IDE**, this system allows users to manage products, including:

- ✅ Add new items to inventory  
- 🔍 Search items by ID  
- 🔄 Update and delete existing records  
- 🛒 Sell items and update stock automatically  
- 📋 View live inventory in a table

---

## 🛠️ Tech Stack

- **Java (Swing GUI)**
- **MySQL (JDBC)**
- **Eclipse IDE**

---

## 💾 Database Setup

1. Create a MySQL database named "invmng".
2. Create the table:

sql

CREATE TABLE InventoryTable (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  ProductName VARCHAR(100),
  Quantity INT,
  PricePerItem DOUBLE
);
