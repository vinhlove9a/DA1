@echo off
REM Lưu đường dẫn thư mục chứa file .bat
set CURRENT_DIR=%~dp0

REM Chuyển đến thư mục chứa file .bat
cd /d "%CURRENT_DIR%"

REM Kiểm tra nếu tệp JAR tồn tại trong thư mục hiện tại
if exist "5aesieunhan.jar" (
    java -jar "5aesieunhan.jar"
) else (
    echo Khong tim thay file JAR! Vui long kiem tra lai!
    pause
)
