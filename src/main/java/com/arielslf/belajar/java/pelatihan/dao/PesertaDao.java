package com.arielslf.belajar.java.pelatihan.dao;

import com.arielslf.belajar.java.pelatihan.entity.Peserta;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PesertaDao extends PagingAndSortingRepository<Peserta, String>{
    
}
