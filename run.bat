   @echo off
   chcp 65001 >nul
   
   echo [1/3] Компіляція коду...
   javac -encoding UTF-8 *.java 

   echo [2/3] Компіляція класу...
   javac Student.java 

   if %errorlevel% neq 0 (
     echo Помилка компіляції
     pause
     exit /b %errorlevel%
   )

   echo [3/3] Запуск програми...
   java -Dfile.encoding=UTF-8 StudentAccounting

   pause
   