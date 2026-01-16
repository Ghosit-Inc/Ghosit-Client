@echo off
setlocal enabledelayedexpansion

:: Get the folder of this script
set "SCRIPT_DIR=%~dp0"

:: Set paths relative to this script
set "LIBS_DIR=%SCRIPT_DIR%..\build\libs"
set "OUTPUT=%SCRIPT_DIR%checksums.txt"

:: Delete old output
if exist "%OUTPUT%" del "%OUTPUT%"

:: Files to process
set FILES=ghosit_client-0.0.1.jar ghosit_client-0.0.1-sources.jar

for %%F in (%FILES%) do (
    set "FULLPATH=%LIBS_DIR%\%%F"

    :: Reset hashes
    set "MD5="
    set "SHA="

    :: Get MD5
    for /f "skip=1 tokens=1" %%M in ('certutil -hashfile "!FULLPATH!" MD5') do (
        if defined MD5 (
            rem do nothing, already captured
        ) else (
            if not "%%M"=="CertUtil:" if not "%%M"=="" set "MD5=%%M"
        )
    )

    :: Get SHA256
    for /f "skip=1 tokens=1" %%S in ('certutil -hashfile "!FULLPATH!" SHA256') do (
        if defined SHA (
            rem already captured
        ) else (
            if not "%%S"=="CertUtil:" if not "%%S"=="" set "SHA=%%S"
        )
    )

    :: Write to output
    echo `%%F`>> "%OUTPUT%"
    echo MD5: `!MD5!`>> "%OUTPUT%"
    echo SHA256: `!SHA!`>> "%OUTPUT%"
    echo.>> "%OUTPUT%"
)

echo Checksums generated at %OUTPUT%
pause
