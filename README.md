# Prerrequisitos
Software Requerido
- Node.js 18.13+ y npm 8.0+
- Java 17+ (OpenJDK recomendado)
- Maven 3.6+ 
- Git 2.20+
Opcional (Para Docker)
- Docker 20.10+
- Docker Compose 2.0+

# 1. Clonar Repositorio
- git clone https://github.com/cesargtous8/proyecto-pt.git
- cd fullstack-product-inventory
# 2. Configuracion de Backend
2.1 Product Service
Navegar al directorio del servicio
- cd backend/product-service
Compilar el proyecto
- mvn clean compile
Ejecutar en modo desarrollo
- mvn spring-boot:run
2.2 Inventory Service
Navegar al directorio del servicio
- cd backend/inventory-service
Compilar el proyecto
- mvn clean compile
Ejecutar en modo desarrollo
mvn spring-boot:run
# 3. Configuracion del Frontend en Angular
Navegar al directorio del frontend
- cd frontend/angular-app/fullstack-frontend
Instalar dependencias
- npm install
Si hay conflictos de versiones
- npm install --legacy-peer-deps
Instalar Bootstrap y dependencias UI
- npm install bootstrap @popperjs/core --legacy-peer-deps
Ejecutar en modo desarrollo
- ng serve
O con auto-apertura del navegador
- ng serve --open
# 4. Ejecución de Todo el stack
Desde la raíz del proyecto
- docker-compose up --build
Ejecutar en segundo plano
- docker-compose up -d
Ver logs
- docker-compose logs -f
Detener servicios
- docker-compose down
