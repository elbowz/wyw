## How to invoke wsdl2java.

```bash 
cd microservices\person
mvn clean package org.apache.cxf:cxf-codegen-plugin:wsdl2java
```
The generated files will be under: `person\target\generated-sources\cxf`.