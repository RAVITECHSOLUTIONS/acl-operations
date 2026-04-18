# # ─────────────────────────────────────────────────────────────────────────────
# # Dockerfile — acl-operations
# # Base: rnc internal OpenJDK 17 on UBI9 (Red Hat Universal Base Image)
# # This exact base image is used by all 3 Java Spring Boot acl apps.
# # ─────────────────────────────────────────────────────────────────────────────
# #
# # Build context: repo root (where pom.xml lives)
# # Before docker build runs, buildMaven() has already produced:
# #   target/acl-operations-<version>.jar
# #
# # Spring config is loaded from /app/config/ at runtime.
# # This folder is mounted as a ConfigMap volume in OCP via Helm chart.
# # So you can change application.properties without rebuilding the image.
# #
# # Port 8080 is the Spring Boot default — matched in Helm service.yaml.
# # acl-operations has NO external OCP Route — it is ClusterIP only.
# # Only acl-outer-api and acl-workflow-api call it, via internal K8s DNS:
# #   http://acl-operations:8080
# # ─────────────────────────────────────────────────────────────────────────────
#
# FROM docker-release.docker.rncint.net/rnc/baseimages/openjdk17-ubi9:17-1-0210261546
#
# WORKDIR /app
#
# COPY target/acl-operations*.jar acl-operations.jar
#
# EXPOSE 8080
#
# ENTRYPOINT java $JAVA_OPTS -jar \
#     -Dspring.config.additional-location=/app/config/ \
#     acl-operations.jar



# Build stage
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy built jar
COPY target/acl-operations-1.0.0.jar acl-operations.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","acl-operations.jar"]