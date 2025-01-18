## Known Issues

### 1. MySQL 서버 실행 및 접속 문제
#### 발생 상황
- MySQL 서버는 실행되지만 `root` 계정으로 접속 불가.
- 초기화 단계에서 임시 비밀번호가 로그에 출력되지 않음.

#### 원인
- MySQL 초기화 과정에서 임시 비밀번호 출력 누락.
- 초기 비밀번호 설정 및 계정 관리 미흡.

#### 해결 방법
1. MySQL 초기화 후 반드시 로그 파일 확인:
   ```bash
   cat /path/to/mysql/error.log | grep 'temporary password'

2.	임시 비밀번호가 없을 경우 비밀번호 강제 재설정:
```bash
ALTER USER 'root'@'localhost' IDENTIFIED BY 'YourNewPassword!';
FLUSH PRIVILEGES;
```
3.	MySQL 서버 재시작 및 접속 테스트:
```bash
brew services restart mysql
mysql -u root -p
```

---

재발 방지 대안
•	초기화 단계에서 로그 파일 확인 및 비밀번호 재설정 절차 준수.
•	프로젝트 초기 설정에 MySQL 관련 주요 절차 추가.

```bash
---

### **5. 다음 작업**
- README 업데이트 후 Git에 커밋:
```bash
git add README.md
git commit -m "Update README with MySQL blocking issue and resolution steps"
git push
 ```
•	개선된 절차를 적용하여 다시 테스트.