^eee1801 (Corey 2022-02-19 04:36:56 +0800  1) # jeegit-TFA105G3
012256af (Corey 2022-02-19 16:47:15 +0800  2) [main]branch是直接在github網站網頁上的操作。例如直接在網頁改README.md。
97ba2fe2 (Corey 2022-02-19 05:11:35 +0800  3) 
97ba2fe2 (Corey 2022-02-19 05:11:35 +0800  4) ## 先試試Pull
97ba2fe2 (Corey 2022-02-19 05:11:35 +0800  5) sourceTree 相關設定，要pull成功拉為自己電腦。
97ba2fe2 (Corey 2022-02-19 05:11:35 +0800  6) 
012256af (Corey 2022-02-19 16:47:15 +0800  7) Project成功拉回Eclipse後，記得要先右鍵做Maven_F5。
012256af (Corey 2022-02-19 16:47:15 +0800  8) 做Maven_F5或run project之後可能會多出一些檔案(例如:target資料夾)。
012256af (Corey 2022-02-19 16:47:15 +0800  9) 這些資料夾可能是：
012256af (Corey 2022-02-19 16:47:15 +0800 10) - 跟個人環境有關。(例:context.xml)
012256af (Corey 2022-02-19 16:47:15 +0800 11) - 執行後自動產生。(例如:target資料夾)
012256af (Corey 2022-02-19 16:47:15 +0800 12) - 有些是Eclipse、Maven的自動記錄。
29d82d9f (Corey 2022-02-19 16:48:05 +0800 13) 
012256af (Corey 2022-02-19 16:47:15 +0800 14) 因此已經在 .gitignore設定，這些不會上傳，所以不會出現在sourceTree也不會上傳正常。
012256af (Corey 2022-02-19 16:47:15 +0800 15) 
97ba2fe2 (Corey 2022-02-19 05:11:35 +0800 16) ## 確認sql、tomcat連線
97ba2fe2 (Corey 2022-02-19 05:11:35 +0800 17) - sql: 要注意 jeeweb-TFA105G3\src\main\webapp\META-INF\context.xml 裡面的設定。
7bbd57d5 (Corey 2022-02-19 16:32:10 +0800 18) 
7bbd57d5 (Corey 2022-02-19 16:32:10 +0800 19) ```xml=
7bbd57d5 (Corey 2022-02-19 16:32:10 +0800 20) <!-- webapp\META-INF\context.xml 的 <Context>內新增 -->
7bbd57d5 (Corey 2022-02-19 16:32:10 +0800 21) <!-- 確認name、password、localhost:3306/DataBaseName -->
7bbd57d5 (Corey 2022-02-19 16:32:10 +0800 22) 
7bbd57d5 (Corey 2022-02-19 16:32:10 +0800 23) <Resource auth="Container" driverClassName="com.mysql.cj.jdbc.Driver" maxIdle="10" maxTotal="20" maxWaitMillis="-1" name="jdbc/TFA105G3TestDB" password="password" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei" username="root"/>
7bbd57d5 (Corey 2022-02-19 16:32:10 +0800 24) ```
7bbd57d5 (Corey 2022-02-19 16:32:10 +0800 25) 
7bbd57d5 (Corey 2022-02-19 16:32:10 +0800 26) - tomcat: 開一個.jsp 去撈db資料試看看。
