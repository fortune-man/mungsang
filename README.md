## known issues

### 1. MySQL Database Initialization and Connection Failure

#### **Issue Description:**
- MySQL 서버가 초기화되지 않거나 정상적으로 시작되지 않아 Spring Boot 애플리케이션과의 데이터베이스 연동이 실패.
- `mysqld --initialize` 명령 실행 시 디렉토리 문제로 인해 데이터 디렉토리 초기화가 제대로 이루어지지 않음.
- MySQL 서버 실행 시 아래와 같은 오류 발생:
  ```
  ERROR 2002 (HY000): Can't connect to local MySQL server through socket '/tmp/mysql.sock'
  ```

#### **Root Cause:**
- 데이터 디렉토리가 제대로 초기화되지 않음.
- 디렉토리 권한이 올바르지 않아 MySQL 서버가 디렉토리에 접근할 수 없음.
- Homebrew MySQL 서비스가 시스템에서 충돌을 일으키며 `brew services start mysql` 실패.

#### **Steps to Reproduce:**
1. MySQL을 Homebrew로 설치:
   ```bash
   brew install mysql
   ```
2. 데이터 디렉토리를 초기화하려고 시도:
   ```bash
   mysqld --initialize --user=`whoami` --basedir="$(brew --prefix mysql)" --datadir=/opt/homebrew/var/mysql --tmpdir=/tmp
   ```
3. MySQL 서비스를 시작:
   ```bash
   brew services start mysql
   ```
4. 애플리케이션 실행 중 DB 연결 실패.

#### **Proposed Solutions:**
- **디렉토리 권한 설정:**
  ```bash
  sudo chown -R `whoami`:staff /opt/homebrew/var/mysql
  ```
- **MySQL 데이터 디렉토리 완전 초기화:**
  ```bash
  sudo rm -rf /opt/homebrew/var/mysql/*
  mysqld --initialize --user=`whoami` --basedir="$(brew --prefix mysql)" --datadir=/opt/homebrew/var/mysql --tmpdir=/tmp
  ```
- **MySQL 로그 파일 확인:**
  ```bash
  cat /opt/homebrew/var/mysql/$(hostname).err
  ```
- **Docker 기반 대체:**
  MySQL 실행을 Docker 컨테이너로 전환하여 환경 의존성 제거:
  ```bash
  docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=password -d -p 3306:3306 mysql:8
  ```

#### **Prevention:**
- 초기화 중 디렉토리 권한 및 로그 파일을 항상 확인.
- MySQL 실행 전 데이터 디렉토리가 깨끗한 상태인지 확인:
  ```bash
  ls -al /opt/homebrew/var/mysql
  ```
- MySQL 컨테이너 사용으로 복잡성을 줄이고 호환성을 확보.

---

### 2. Test Case Execution Dependent on Database

#### **Issue Description:**
- DB 연동 문제로 인해 Repository, Service, Controller의 테스트 케이스 실행 실패.

#### **Root Cause:**
- H2 또는 다른 In-Memory DB로 대체하지 않고 MySQL에 직접 의존.

#### **Proposed Solutions:**
- **H2 데이터베이스 사용:**
  테스트 환경에서 MySQL 대신 H2를 사용:
  ```properties
  spring.datasource.url=jdbc:h2:mem:testdb
  spring.datasource.driver-class-name=org.h2.Driver
  spring.datasource.username=sa
  spring.datasource.password=
  ```
- **MySQL Mocking:**
  Testcontainers를 사용하여 MySQL 컨테이너를 테스트 중 실행.

#### **Prevention:**
- 테스트 환경과 실제 환경을 분리.
- Mock 또는 In-Memory DB로 로컬 테스트 실행.

---

### Next Steps for Improvement

#### **Potential Contributions:**
- 디렉토리 초기화 및 권한 관련 오류를 자동으로 해결하는 스크립트를 작성하여 오픈소스 기여.
- Docker 기반 MySQL 설정을 문서화하여 README에 추가.
- `H2` 또는 `Testcontainers` 사용을 포함한 테스트 가이드를 작성.

---

### Future Enhancements:
- Spring Boot와 Docker Compose를 활용한 로컬 개발 환경 통합.
- CI/CD 파이프라인에서 테스트와 배포 환경 자동화.
- `Flyway` 또는 `Liquibase`를 사용하여 DB 마이그레이션 관리.
