@echo off
cd ..\static\gdpu-seizing-courses-nginx
nginx -s stop
echo Nginx Initializing...
start nginx
echo Nginx Running...
cd ..\..\gdpu-seizing-courses
echo Server Running...
java -jar gdpu-seizing-courses-0.0.1-SNAPSHOT.jar
cd ..\static\gdpu-seizing-courses-nginx
echo Nginx Stopping...
nginx -s stop
echo Nginx Stopped!
pause