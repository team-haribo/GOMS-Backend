# openjdk:11 베이스 이미로 사용
FROM openjdk:11

# JAR 파일 변수명 선언
ARG JAR_FILE=build/libs/goms-0.0.1-SNAPSHOT.jar

# app.jar로 복사
COPY ${JAR_FILE} app.jar

# 컨테이너 시작 되었을때 스크립트 실행
ENTRYPOINT ["java", "-jar", "app.jar"]