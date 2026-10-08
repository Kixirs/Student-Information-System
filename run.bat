@echo off
   chcp 65001 >nul
   
   echo [1/2] Компіляція коду...
   javac -encoding UTF-8 -sourcepath src -d out ^
      src/exception/*.java ^
      src/model/*.java ^
      src/formatter/*.java ^
      src/strategy/*.java ^
      src/template/*.java ^
      src/StudentAccounting.java

   if %errorlevel% neq 0 (
     echo Помилка компіляції. Перевірте повідомлення вище.
     pause
     exit /b %errorlevel%
   )

   echo [2/2] Запуск програми...
   java -Dfile.encoding=UTF-8 -cp out StudentAccounting

   pause