#!bin/bash
sudo systemctl stop user_service.service --no-pager
sudo systemctl status user_service.service --no-pager
sudo chmod u+x /app/gazapp-api/deploy
mvn clean install
sudo systemctl start user_service.service --no-pager
sudo systemctl status user_service.service --no-pager