@echo off
setlocal
cd /d %~dp0

echo ==============================================
echo   COLASANTO - BUILD APP CLIENTE ANDROID TEST
echo ==============================================
echo.
where node >nul 2>nul || (echo ERRORE: Node.js non trovato.& pause & exit /b 1)
where java >nul 2>nul || (echo ERRORE: Java non trovato.& pause & exit /b 1)

call npm install --no-audit --no-fund || goto :err
if not exist android call npx cap add android || goto :err
call npx cap sync android || goto :err
call npx capacitor-assets generate --android || goto :err
cd android
call gradlew.bat assembleDebug || goto :err
cd ..
copy /Y android\app\build\outputs\apk\debug\app-debug.apk COLASANTO_CLIENT_ANDROID_TEST.apk >nul

echo.
echo OK: creato COLASANTO_CLIENT_ANDROID_TEST.apk
pause
exit /b 0
:err
echo.
echo BUILD NON RIUSCITA. Verifica Android Studio/SDK e riprova.
pause
exit /b 1
