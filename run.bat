   @echo off
   chcp 65001
   echo [1/2] Компіляція коду...
   javac -encoding UTF-8 StudentAccounting.java 
   javac Student.java 

   if %errorlevel% neq 0 (
     echo Помилка компіляції
     pause
     exit /b %errorlevel%
   )

   echo [2/2] Запуск програми...
   java -Dfile.encoding=UTF-8 StudentAccounting

   pause