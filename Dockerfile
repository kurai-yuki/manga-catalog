FROM eclipse-temurin:17-jdk-alpine

RUN mvn clean package -DskipTests

WORKDIR /app

COPY --from=build /app/target/manga_catalog-0.0.1-SNAPSHOT.jar ./

CMD ["java", "-jar", "./manga_catalog-0.0.1-SNAPSHOT.jar"]