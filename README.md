# MarkMePresent
MarkMePresent is a simple attendance system based on QR codes.

Generate a Class that generates a QR code, User scans the code and marks himself/herself present.

### System Includes
+ Android Application - (Kotlin)
+ REST API Server - (NodeJs & Express)
+ Basic Web Client - (Vanilla Javascript)

### Some Screenshots
Web Client Screenshot

![webclient screenshot](https://github.com/abdullahwaqar/QRAttendanceSystem/blob/dev/doc/screenshots/webclient-ss.png)

#### **Disclaimer: The *server* and the *webclient* is for demoing purposes and may not include full functionality.**

---

## Building & Running Application
Well download the source code and let *AndroidStudio* do its thing.

## Running Server
```
cd api

npm i

npm start
```

## Running WebClient
```
cd client

python -m SimpleHTTPServer 8080
```

Can be server over any generic HTTP server.
