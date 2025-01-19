현재 반복 중인 문제들
### known issue
- 포트폴리오 완성을 위해 도메인 설계 - 엔티티 설계와 테이블 설계 - orm으로 매핑 - 로직 개발 - 전체 테스트 완료 - 배포 순서로 일정 진행하려고 계획했으나
- 전체 테스트 과정에서 데이터베이스 연동 문제로 일정 지연 중. gpt는 mysql 서비스 시작을 안내하지만 하기 문제들이 반복중
- - mysql 데이터디텍토리 초기화 반복 문제
- 임시비밀번호 미생성됨
- mysql 서비스상태 불안정으로 테스트 작업 지연
#### 시도해본 것들

pkill -9 mysqld
✘ joohyeongkim@gimjuhyeong-ui-MacBookAir  ~/Desktop/dontpanic/mungsang   main ±  brew services list
Name                  Status  User         File
emacs                 none                 
mongodb-community@5.0 none                 
mysql                 stopped joohyeongkim ~/Library/LaunchAgents/homebrew.mxcl.mysql.plist
unbound               none                 
joohyeongkim@gimjuhyeong-ui-MacBookAir  ~/Desktop/dontpanic/mungsang   main ±  sudo rm -rf /opt/homebrew/var/mysql/*
Password:
Sorry, try again.
Password:
joohyeongkim@gimjuhyeong-ui-MacBookAir  ~/Desktop/dontpanic/mungsang   main ±  sudo chown -R `whoami`:staff /opt/homebrew/var/mysql
joohyeongkim@gimjuhyeong-ui-MacBookAir  ~/Desktop/dontpanic/mungsang   main ±  sudo chmod -R 755 /opt/homebrew/var/mysql
joohyeongkim@gimjuhyeong-ui-MacBookAir  ~/Desktop/dontpanic/mungsang   main ±  mysqld --initialize --user=`whoami` --basedir="$(brew --prefix mysql)" --datadir=/opt/homebrew/var/mysql --tmpdir=/tmp
2025-01-19T01:42:30.723724Z 0 [System] [MY-015017] [Server] MySQL Server Initialization - start.
2025-01-19T01:42:30.725205Z 0 [System] [MY-013169] [Server] /opt/homebrew/Cellar/mysql/9.1.0_1/bin/mysqld (mysqld 9.1.0) initializing of server in progress as process 2296
2025-01-19T01:42:30.726535Z 0 [ERROR] [MY-010457] [Server] --initialize specified but the data directory has files in it. Aborting.
2025-01-19T01:42:30.726540Z 0 [ERROR] [MY-013236] [Server] The designated data directory /opt/homebrew/var/mysql/ is unusable. You can remove all files that the server added to it.
2025-01-19T01:42:30.726574Z 0 [ERROR] [MY-010119] [Server] Aborting
2025-01-19T01:42:30.727617Z 0 [System] [MY-015018] [Server] MySQL Server Initialization - end.
