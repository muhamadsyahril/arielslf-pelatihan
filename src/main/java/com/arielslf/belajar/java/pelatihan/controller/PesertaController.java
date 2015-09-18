package com.arielslf.belajar.java.pelatihan.controller;

import com.arielslf.belajar.java.pelatihan.dao.PesertaDao;
import com.arielslf.belajar.java.pelatihan.entity.Peserta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PesertaController {
    
    @Autowired
    private PesertaDao pd;
    
    @RequestMapping(value="/peserta", method = RequestMethod.GET)
    public Page<Peserta> cariPeserta(Pageable page){
        return pd.findAll(page);
        
    }
    
    @RequestMapping(value="/peserta", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void insertPesertaBaru(@RequestBody Peserta p){
        pd.save(p);
    }
    
}
