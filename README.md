# Spring-JavaJWTv4.4
Explicacion de la libreria java-Jwt:4.4 para la generación de token, uso de spring security 6, spring boot 3.0.7.
# Uso de spring Security + jwt (libreria Java-jwt:4.4.0)

Si estas trabajando con mapstruct y lombok, tienes que agregar un plugin en tu gestor de repositorio maven.
Con el fin de que no origine problemas, ya que a veces mapstruct carga antes que lombok y le es dificil encontrar los Getter and setter

![portfolio](https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white) 
![java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white)
![jwt](https://img.shields.io/badge/json%20web%20tokens-323330?style=for-the-badge&logo=json-web-tokens&logoColor=pink)

```xml
  <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>17</source> <!-- depending on your project -->
                    <target>17</target> <!-- depending on your project -->
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.mapstruct</groupId>
                            <artifactId>mapstruct-processor</artifactId>
                            <version>${org.mapstruct.version}</version>
                        </path>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok-mapstruct-binding</artifactId>
                            <version>0.2.0</version>
                        </path>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>${lombok.version}</version>
                        </path>
                        <!-- other annotation processors -->
                    </annotationProcessorPaths>
                    <!--CARACTERISTICA DE MAPSTRUCT EVITAR EL ANOTAR LA CLASE MAPEADORA CON @Component y ACEPTA SU PROPIEDAD @Mapper(componentModel = "spring")-->
                    <compilerArgs>
                        <compilerArg>
                            -Amapstruct.defaultComponentModel=spring
                        </compilerArg>
                    </compilerArgs>
                </configuration>
            </plugin>
```
