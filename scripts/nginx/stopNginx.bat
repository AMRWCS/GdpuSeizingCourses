@echo off
cd ..\..\static\gdpu-seizing-courses-nginx
echo Nginx Stopping...
nginx -s stop
echo Nginx Stopped!
pause