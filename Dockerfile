# 1. JDK 17 베이스 이미지
FROM openjdk:17-alpine
VOLUME /tmp
# 2. JAR 파일을 컨테이너 내부로 복사
ADD /target/*.jar ./app.jar
# 3. 애플리케이션 실행 명령어
#ENTRYPOINT ["java", "-jar", "/app/app.jar"]
ENTRYPOINT ["java","-jar","/app.jar"]