your-project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── yourproject/
│   │   │               ├── YourProjectApplication.java
│   │   │               ├── controller/
│   │   │               ├── service/
│   │   │               ├── repository/
│   │   │               └── model/
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── yourproject/
│                       └── YourProjectApplicationTests.java
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md

he .aws folder is typically located in your user's home directory. This is a hidden directory (prefixed with a dot) used by the AWS CLI and SDKs to store configuration and credentials.

Default locations:

Windows:
C:\Users\<your-username>\.aws\

Linux/macOS:
/home/<your-username>/.aws/
or
~/.aws/

Inside the .aws directory, you usually find:

credentials — stores your AWS access key and secret key
config — contains default region and output format settings

~/.aws/credentials
~/.aws/config