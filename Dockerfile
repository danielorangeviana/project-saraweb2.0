# Etapa 1: build da aplicação com Maven e JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copia arquivos do projeto
COPY pom.xml .
COPY src ./src

# Build do projeto (sem rodar testes)
RUN mvn clean package -DskipTests

# Etapa 2: imagem final somente com JDK
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copia o JAR gerado da etapa de build
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
