#!/usr/bin/env bash

# 기존 Eginx엔스에 연결되어 있지 않지만, 실행 중이던 스프링 부트 종료
#ABSPATH=$(readlink -f $0)
#ABSDIR=$(dirname $ABSPATH) # 현재 stop.sh가 속해있는 경로
#source ${ABSDIR}/profile.sh # 해당 코드로 profile.sh 내의 함수 사용

#IDLE_PORT=$(find_idle_port)

#echo ">>> $IDLE_PORT 에서 구동중인 애플리케이션 PID 확인"
#IDLE_PID=$(sudo lsof -ti tcp:${IDLE_PORT})

#if [ -z ${IDLE_PID} ]
#then
  #echo ">>> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
#else
  # # Nginx에 연결되어 있지는 않지만 현재 실행 중인 jar 를 Kill 합니다.
  #echo ">>> kill -15 $IDLE_PID"
  #kill -15 ${IDLE_PID}
  #sleep 5
#fi


PROJECT_ROOT="/home/ubuntu/MajorYard"
JAR_FILE="$PROJECT_ROOT/build/libs/majorYard-0.0.1-SNAPSHOT.jar"

DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

# 현재 구동 중인 애플리케이션 pid 확인
CURRENT_PID=$(sudo lsof -ti tcp:8080)

# 프로세스가 켜져 있으면 종료
if [ -z $CURRENT_PID ]; then
  echo "$TIME_NOW > 현재 실행중인 애플리케이션이 없습니다" >> $DEPLOY_LOG
else
  echo "$TIME_NOW > 실행중인 $CURRENT_PID 애플리케이션 종료 " >> $DEPLOY_LOG
  kill -15 $CURRENT_PID
fi
