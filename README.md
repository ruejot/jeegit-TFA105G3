# jeegit-TFA105G3

## 先試試Pull
sourceTree 相關設定，要pull成功拉為自己電腦。

## 確認sql、tomcat連線
- sql: 要注意 jeeweb-TFA105G3\src\main\webapp\META-INF\context.xml 裡面的設定。

```xml=
<!-- webapp\META-INF\context.xml 的 <Context>內新增 -->
<!-- 確認name、password、localhost:3306/DataBaseName -->

<Resource auth="Container" driverClassName="com.mysql.cj.jdbc.Driver" maxIdle="10" maxTotal="20" maxWaitMillis="-1" name="jdbc/TFA105G3TestDB" password="password" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei" username="root"/>
```

- tomcat: 開一個.jsp 去撈db資料試看看。
