# jeegit-TFA105G3
[main]branch是直接在github網站網頁上的操作。例如直接在網頁改README.md。

## 先試試Pull
sourceTree 相關設定，要pull成功拉為自己電腦。

Project成功拉回Eclipse後，記得要先右鍵做Maven_F5。
做Maven_F5或run project之後可能會多出一些檔案(例如:target資料夾)。
這些資料夾可能是：
- 跟個人環境有關。(例:context.xml)
- 執行後自動產生。(例如:target資料夾)
- 有些是Eclipse、Maven的自動記錄。
- 
因此已經在 .gitignore設定，這些不會上傳，所以不會出現在sourceTree也不會上傳正常。

## 確認sql、tomcat連線
- sql: 要注意 jeeweb-TFA105G3\src\main\webapp\META-INF\context.xml 裡面的設定。

```xml=
<!-- webapp\META-INF\context.xml 的 <Context>內新增 -->
<!-- 確認name、password、localhost:3306/DataBaseName -->

<Resource auth="Container" driverClassName="com.mysql.cj.jdbc.Driver" maxIdle="10" maxTotal="20" maxWaitMillis="-1" name="jdbc/TFA105G3TestDB" password="password" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei" username="root"/>
```

- tomcat: 開一個.jsp 去撈db資料試看看。
