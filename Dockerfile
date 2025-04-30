FROM openjdk:17-jdk

WORKDIR /app

COPY . .

CMD ["/bin/bash"]
