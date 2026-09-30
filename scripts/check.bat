@echo off
setlocal enabledelayedexpansion

echo === Jornada Android: Setup Checker ===
echo.

set ERRORS=0

REM 1. JDK 17
echo Checking JDK 17...
java -version 2>&1 | findstr "17" >nul
if %errorlevel% equ 0 (
    echo   - JDK 17 found
) else (
    echo   - JDK 17 not found or wrong version
    set /a ERRORS=!ERRORS! + 1
)

REM 2. adb devices
echo Checking Android Debug Bridge...
for /f "tokens=*" %%A in ('adb devices ^| findstr "device$"') do (
    if not "%%A"=="" (
        echo   - Emulator/device connected
        goto adb_ok
    )
)
echo   - No emulator/device online. Start emulator first.
set /a ERRORS=!ERRORS! + 1
:adb_ok

REM 3. Appium
echo Checking Appium...
where appium >nul 2>&1
if %errorlevel% equ 0 (
    echo   - Appium CLI installed
) else (
    echo   - Appium CLI not found. Run: npm i -g appium
    set /a ERRORS=!ERRORS! + 1
)

REM 4. Appium UiAutomator2 driver
echo Checking Appium UiAutomator2 driver...
if exist "%USERPROFILE%\.appium\node_modules\appium-uiautomator2-driver" (
    echo   - UiAutomator2 driver installed
) else (
    echo   - UiAutomator2 driver not found. Run: appium driver install uiautomator2
    set /a ERRORS=!ERRORS! + 1
)

REM 5. Gradle cache
echo Checking Gradle cache...
if exist "%USERPROFILE%\.gradle\caches" (
    echo   - Gradle cache found
) else (
    echo   - Gradle cache not warmed. First build will be slow.
)

REM 6. AnkiDroid instalado no emulador
echo Checking AnkiDroid installed on emulator...
adb shell pm path com.ichi2.anki >nul 2>&1
if %errorlevel% equ 0 (
    echo   - AnkiDroid installed
) else (
    echo   - AnkiDroid not installed. Drag the APK onto the emulator screen (see README step 6^)
    set /a ERRORS=!ERRORS! + 1
)

echo.
echo === Summary ===
if %ERRORS% equ 0 (
    echo - All checks passed! Ready to go.
    exit /b 0
) else (
    echo - %ERRORS% check(s^) failed. Fix above and retry.
    exit /b 1
)
