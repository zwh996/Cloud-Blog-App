FROM openjdk:8-jdk-alpine
# 指定工作目录
WORKDIR /app
# 将 jar 包添加到工作目录，比如 target/yuoj-backend-user-service-0.0.1-SNAPSHOT.jar
COPY target/Comment-Service-0.0.1-SNAPSHOT.jar .
# 露端口
EXPOSE 8083
# 启动命令
ENTRYPOINT ["java","-jar","/app/Comment-Service-0.0.1-SNAPSHOT.jar"]
