📚 Library Management System

This project is a Library Management System implemented using **Java/Spring Boot** for the backend and **Angular** for the frontend. The system allows for the management of books, authors, genres, and library members, supporting CRUD operations, advanced searches, and multi-user interactions through a modern web interface.

🛠️ Technologies Used
- **Backend**: Java 17, Spring Boot 3.x, MySQL
- **Frontend**: Angular 19+, TypeScript, Bootstrap 5
- **Tools**: Maven, Git, GitHub Actions (CI/CD)
- **Deployment**: Kubernetes with Helm

⚙️ Pipeline CI/CD (GitHub Actions)

The CI/CD pipeline is structured into multiple automated jobs that ensure integrity, quality, and continuous deployment:

🔨 Build & Test Backend:

- **Builds** the Spring Boot project
- Runs tests via Maven (**mvnw verify**)
- Uses a **MySQL service** for end-to-end testing

✅ Code Analysis:

- **Checkstyle**: for checking Java style
- **SpotBugs**: for detecting bugs and vulnerabilities
- **SonarQube**: for in-depth code quality review (uses tokens and hosts from GitHub Secrets)
- **Jacoco**: for test coverage

🎨 Build & Test Frontend:

- Uses **Node.js** 19 and npm ci to install dependencies
- **Runs tests** with Chrome Headless
- Linting code with **ESLint**
- Compiling in production with **npm run build**

🐳 Docker Build & Push:

- Create and publish frontend and backend Docker images on Docker Hub
- Use the **DOCKERHUB_USERNAME** and **DOCKERHUB_TOKEN** secrets

🚀 Prerequisites
Make sure you have installed:

- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- [Java Development Kit (JDK 17+)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [MySQL 8.x](https://www.mysql.com/)
- [Node.js 19.x & npm 9.x](https://nodejs.org/)
- [Angular CLI 15+](https://angular.io/cli)
- [Docker](https://www.docker.com/)
- [Kubernetes](https://kubernetes.io/)
- [Helm](https://helm.sh/)

👨‍💻 Contributors

This project is maintained by a team of dedicated developers. Contributions are welcome. Please fork the repository and submit a pull request with your changes.

🔒 License

This project is licensed under the MIT License.