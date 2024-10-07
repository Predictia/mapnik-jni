FROM debian:bookworm-slim

ENV DEBIAN_FRONTEND=noninteractive

# Install dependencies (mapnik and libmapnik)
RUN apt update && \
    apt install -y libmapnik-dev mapnik-utils make g++ maven openjdk-17-jdk && \
    apt clean \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY . /app

RUN mvn clean package
