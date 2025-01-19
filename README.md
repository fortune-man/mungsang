## 1. 프로젝트 개요
- **목적**: Spring Boot와 MySQL을 활용한 CRUD 서비스 구축.
- **주요 기능**: 사용자 생성, 조회, 수정, 삭제.

## 2. 학습 내용
### 2.1 MySQL 연동
- MySQL 설치 및 초기화 문제 해결.
- `application.properties`에서 데이터베이스 설정.

### 2.2 Spring Data JPA 설계
- 엔티티와 테이블 매핑: `User`, `UserEntity`.
- Repository 설계 및 메서드 작성.

### 2.3 예외 처리
- `NotFoundException`과 `ErrorCode` 활용.

### 2.4 테스트 작성
- `Mockito`로 단위 테스트 작성.
- 사용자 생성 및 조회 기능 검증.

## 3. 테스트 결과
### 성공 사례
- 사용자 생성 테스트 성공.
- 데이터베이스 연동 확인.
### 발생한 문제
- MySQL 초기화 문제 → 디렉토리 정리로 해결.
- `any()` 메서드 타입 오류 → 클래스 매핑 문제 해결

---



### MySQL 서버 설정 과정
1.	문제:
•	초기화 실패: 데이터 디렉토리가 비어 있음에도 --initialize 과정에서 오류 발생.
•	임시 비밀번호 확인 실패: 로그 파일에서 임시 비밀번호를 찾을 수 없었음.
•	MySQL 서버 시작 오류: brew services로 서버 시작 실패, 중복 실행 프로세스 충돌.
•	MySQL 접속 실패: 비밀번호 설정 과정에서 잘못된 초기화로 인해 Access denied 오류.
해결 방법:
•	데이터 디렉토리 초기화:
•	기존 디렉토리 파일 삭제 후 초기화 재실행
```bash
sudo rm -rf /opt/homebrew/var/mysql/*
mysqld --initialize --user=`whoami` --basedir="$(brew --prefix mysql)" --datadir=/opt/homebrew/var/mysql --log-error=/opt/homebrew/var/mysql/mysql_error.log
```
•	임시 비밀번호 확인:
•	로그 파일 위치를 명시적으로 지정하고, 로그에서 임시 비밀번호 추출:
```bash
cat /opt/homebrew/var/mysql/mysql_error.log | grep 'temporary password'
```
•	서버 시작 문제 해결:
•	중복 실행된 프로세스 종료:
```bash
pkill -9 mysqld
```
•	서버 재시작
```bash
brew services restart mysql
```
비밀번호 변경 및 접속:
•	초기 임시 비밀번호 사용 후 새로운 비밀번호 설정:
```bash
ALTER USER 'root'@'localhost' IDENTIFIED BY '새로운비밀번호';
```
3.	왜 순조롭지 않았는가?:
•	데이터 디렉토리 초기화 과정에서 기존 파일 삭제가 누락되었거나, 불완전한 설정으로 인해 반복적으로 오류 발생.
•	임시 비밀번호 로그 파일의 위치를 명확히 지정하지 않음으로써 불필요한 디버깅 과정 발생.
•	MySQL 프로세스 충돌을 사전에 방지하지 못함.

향후 개선 방안
•	MySQL 초기화 및 서버 시작 과정을 스크립트로 자동화하여 반복적인 오류를 방지.
•	로그 파일 위치 및 명명 규칙을 명확히 지정.
•	프로세스 충돌 방지를 위해 서버 시작 전 상태 점검(brew services list 등) 수행.
