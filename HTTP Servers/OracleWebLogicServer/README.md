# WebLogic Server

WEBLOGIC_VERSION=14.1.1.0.0

Install WebLogic Generic — `fmw_${WEBLOGIC_VERSION}_wls_lite_Disk1_1of1.zip`.  
Install WebLogic Slim — `fmw_${WEBLOGIC_VERSION}_wls_lite_slim_Disk1_1of1.zip`.   
Install from https://www.oracle.com/middleware/technologies/weblogic-server-downloads.html and save to `./WebLogicFMW` folder (my copy stored on Yandex.Disk).  

Build & Run:

```
docker compose build
docker compose up
```

And go to admin console with password from `domain-properties/domain.properties`: http://127.0.0.1:7001/console

Если выставить `ADMINISTRATION_PORT_ENABLED=true`, то сервер не поднимется, тк там надо куда-то прокинуть ssl сертификаты и keystore. Возможно, надо сначала без шифрования запустить, а потом уже где-то в консоли что поднастроить.