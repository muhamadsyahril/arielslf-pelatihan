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

### Test Kode Program Akses Database ###

* Siapkan sample data dalam file SQL

```SQL
delete from peserta;

insert into peserta (id, nama, email, tanggal_lahir)
values ('aa1', 'Peserta Test 001', 'peserta.test.001@gmail.com', '2011-10-01');

insert into peserta (id, nama, email, tanggal_lahir)
values ('aa2', 'Peserta Test 002', 'peserta.test.002@gmail.com', '2011-10-02');

insert into peserta (id, nama, email, tanggal_lahir)
values ('aa3', 'Peserta Test 003', 'peserta.test.003@gmail.com', '2011-10-03');

```

* Panggil file SQL dari dalam JUnit

```java
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PelatihanApplication.class)
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "/data/peserta.sql"
)
public class PesertaDaoTest {

}
```

* Lakukan pengetesan dalam method `@Test`

	* Contoh test insert
	```java

	 @Test
    public void testInsert() throws SQLException{
        Peserta p = new Peserta();
        p.setNama("Peserta 001");
        p.setEmail("peserta001@gmail.com");
        p.setTanggalLahir(new Date());
        
        pd.save(p);
        
      
        
        String sql = "select count(*) as jumlah"
                + " from peserta "
                + " where email = 'peserta001@gmail.com'";
        
        try (java.sql.Connection c = ds.getConnection()) {
            ResultSet rs = c.createStatement().executeQuery(sql);
            Assert.assertTrue(rs.next());
            
            
            Long jumlahRow = rs.getLong("jumlah");
            Assert.assertEquals(1L, jumlahRow.longValue());
        }
        
    }

	```

	* Contoh test select by id
	```java
	@Test
    public void testCariById(){
        Peserta p = pd.findOne("aa1");
        
        Assert.assertNotNull(p);
        Assert.assertEquals("Peserta Test 001", p.getNama());
        Assert.assertEquals("peserta.test.001@gmail.com", p.getEmail());
        
        Peserta px = pd.findOne("xx");
        Assert.assertNull(px);
        
    }

	```

	* Contoh test select count
	```java
	@Test
    public void testHitung(){
        Long jumlah = pd.count();
        Assert.assertEquals(3L, jumlah.longValue());
    }

	```

	* Bersihkan sisa test insert dengan method `@After`

	```java
	 @After
    public void hapusData() throws SQLException{
        String sql = "delete from peserta where email = 'peserta001@gmail.com'";
             try (java.sql.Connection c = ds.getConnection()) {
                 c.createStatement().executeUpdate(sql);
             }
        
    }

	```



