### Spring Data ###



### Akses Database dengan Spring Data JPA ###

### Setup Project ###

* `pom.xml` : tambahkan parent project ke spring boot

```xml

<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>1.2.5.RELEASE</version>
	<relativePath/> <!-- lookup parent from repository -->
</parent>

```

* `pom.xml` : tambahkan dependensi Spring Data JPA

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

```

* `pom.xml` : tambahkan dependensi database (misal : MtSQL)

```xml
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
</dependency>

```

* `src/main/resources/application.properties : Konfigurasi koneksi database

````
spring.datasource.url=jdbc:mysql://localhost/pelatihan
spring.datasource.username=pelatihanuser
spring.datasource.password=pelatihanpasswd

spring.jpa.generate-ddl=true

````

* Entity class dengan annotation `@Entity`. Contoh:

```java

@Entity
public class Peserta {
    
    @Id
    private String id;
    
    @Column(nullable = false)
    private String nama;
    
    @Column( nullable = false, unique = true)
    private String email;
    
}


```

* Siapkan user dan password untuk koneksi database
```
grant all pelatihan.* to pelatihanuser@localhost identified by 'pelatihanpasswd'
```

* Buat database
```
create database pelatihan
```

* Jalankan projectnya
```
mvn clean package
```

