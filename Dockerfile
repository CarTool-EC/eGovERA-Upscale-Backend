FROM eclipse-temurin:21

WORKDIR /upscale-backend/
COPY ./upscale-webservice/target/upscale-webservice-1.0-SNAPSHOT.jar ./

ENV GRAPHDB_URL http://eiragraphdb.cudzftgkg9d0hxdv.spaincentral.azurecontainer.io:7200
ENV GRAPHDB_SPARQL_ENDPOINT /repositories/EIRA
ENV GRAPHDB_USERNAME danielB
ENV GRAPHDB_PASSWORD EiraDanielB2006

EXPOSE 9996
CMD ["java", "-jar", "upscale-webservice-1.0-SNAPSHOT.jar"]