\# 🛡️ Role-Based Access Control (RBAC) \& User Management System



!\[Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge\&logo=openjdk\&logoColor=white)

!\[MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge\&logo=mysql\&logoColor=white)

!\[Swing](https://img.shields.io/badge/GUI-Java%20Swing-blue?style=for-the-badge)



A robust and secure desktop application demonstrating \*\*Authentication\*\* and \*\*Role-Based Access Control (RBAC)\*\* built with \*\*Java Swing\*\* and \*\*MySQL\*\*.



\---



\## 📌 Project Overview \& Architecture



The system enforces strict role-based authorization:

1\. \*\*Authentication:\*\* Users log in using their credentials validated against a MySQL database.

2\. \*\*Authorization / Access Control:\*\*

&#x20;  - \*\*Normal Users:\*\* Redirected to `MainPage`. Clicking the \*Admin Page\* button prompts a \*"No admin rights"\* warning.

&#x20;  - \*\*Administrators:\*\* Granted access to the full \*\*Admin Dashboard\*\* with administrative privileges.

3\. \*\*Admin Dashboard:\*\* Complete CRUD operations, real-time ID search/filtering, multi-selection deletion with confirmation dialogs, password visibility toggles, and form reset.



```text

&#x20;      \[ Login Page ]

&#x20;            │

&#x20;     (Authenticate)

&#x20;            ▼

&#x20;      \[ Main Page ]

&#x20;            │

&#x20;    (Check userRole)

&#x20;     ┌──────┴──────┐

&#x20;     │             │

&#x20;(admin)         (normal)

&#x20;     ▼             ▼

\[Admin Panel]  \[No Admin Rights Warning]

```



\---



\## 📸 Screenshots



| 1. Login Page | 2. Main Page | 3. Admin Panel |

| :---: | :---: | :---: |

| !\[Login Page](screenshots/login.png) | !\[Main Page](screenshots/main.png) | !\[Admin Panel](screenshots/admin.png) |



\---



\## ✨ Key Features



\- \*\*Role-Based Routing:\*\* Dynamic navigation based on user permissions (`admin` vs `normal`).

\- \*\*Full CRUD Management:\*\* Create, Read, Update, and Delete users from a centralized table.

\- \*\*Search by ID:\*\* Real-time table row filtering using `TableRowSorter` and `RowFilter`.

\- \*\*Safe Multi-Row Deletion:\*\* Delete one or multiple selected rows simultaneously with a custom confirmation dialog.

\- \*\*Form-Table Synchronization:\*\* Clicking a table row instantly populates form fields for quick updates.

\- \*\*Security-First UI:\*\* Masked password input (`JPasswordField`) with an intuitive Show/Hide toggle.

\- \*\*Quick Reset:\*\* One-click clear button to reset input fields and selections.

\- \*\*Auto-Increment \& Data Integrity:\*\* Uses MySQL `AUTO\_INCREMENT` Primary Keys while preserving row index consistency.



\---



\## 🚀 Setup \& Installation



\### Prerequisites

\- Java Development Kit (JDK 17+)

\- MySQL Server \& MySQL Workbench

\- MySQL Connector/J JDBC Driver



\### 1. Database Configuration

1\. Open MySQL Workbench.

2\. Run the provided `schema.sql` script to create the `company` database, tables, and demo users.



\### 2. Configure Database Credentials

Create a `src/db.properties` file with your local MySQL credentials:

```properties

db.url=jdbc:mysql://localhost:3306/company?useSSL=false\&serverTimezone=UTC\&allowPublicKeyRetrieval=true

db.user=root

db.password=YOUR\_MYSQL\_PASSWORD

```



\### 3. Demo Credentials

| Role | Username | Password | Access Level |

| :--- | :--- | :--- | :--- |

| \*\*Administrator\*\* | `admin` | `1234` | Full Admin Dashboard |

| \*\*Standard User\*\* | `ahmet` | `1234` | Main Page Only |



\---



\## 👨‍💻 Author

\- \*\*Oğulcan\*\* - Computer Engineering Student

