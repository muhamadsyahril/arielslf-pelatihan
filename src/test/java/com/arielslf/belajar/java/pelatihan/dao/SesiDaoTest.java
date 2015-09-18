package com.arielslf.belajar.java.pelatihan.dao;

import com.arielslf.belajar.java.pelatihan.PelatihanApplication;
import com.arielslf.belajar.java.pelatihan.entity.Materi;
import com.arielslf.belajar.java.pelatihan.entity.Peserta;
import com.arielslf.belajar.java.pelatihan.entity.Sesi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PelatihanApplication.class)
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"/data/peserta.sql", "/data/materi.sql", "/data/sesi.sql"}
)
public class SesiDaoTest {
    
    @Autowired
    private SesiDao sd;
    
    @Test
    public void testCariByMateri(){
        Materi m = new Materi();
        m.setId("aa6");
        
        PageRequest page = new PageRequest(0, 5);
        
        Page<Sesi> hasilQuery = sd.findByMateri(m, page);
        Assert.assertEquals(2L, hasilQuery.getTotalElements());
        
        Assert.assertFalse(hasilQuery.getContent().isEmpty());
        Sesi s = hasilQuery.getContent().get(0);
        Assert.assertNotNull(s);
        Assert.assertEquals("Java Fundamental", s.getMateri().getNama());
        
        
    }

    @Test
    public void testCariBerdasarkanTanggalMulaiDanKodeMateri() throws Exception {
        PageRequest page = new PageRequest(0, 5);
        
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Date sejak = formater.parse("2015-01-01");
        Date sampai = formater.parse("2015-01-03");
        
        Page<Sesi> hasil = sd.cariBerdasarkanTanggalMulaiDanKodeMateri(sejak, sampai, "JF-002", page);
        Assert.assertEquals(1L, hasil.getTotalElements());
        Assert.assertFalse(hasil.getContent().isEmpty());
        
        Sesi s = hasil.getContent().get(0);
        Assert.assertEquals("Java Web", s.getMateri().getNama());
        
    }
    


    
    

    
    
    
}
