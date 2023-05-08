REPOSITORY=/home/ec2-user/goms-app
cd $REPOSITORY

APP_NAME=goms-app
JAR_NAME=$(ls -tr $JAR_REPOSITORY/$APP_NAME-0.0.1-SNAPSHOT.jar | tail -n 1)

CURRENT_PID=$(pgrep -f $APP_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 실행중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $CURRENT_PID"
  sudo kill -15 $CURRENT_PID
  sleep 5
fi

echo "> $JAR_NAME 애플리케이션 실행"
nohup java -jar $JAR_NAME >> $REPOSITORY/nohup.out 2>&1 &