package com.arielslf.belajar.java.pelatihan.dao;

import com.arielslf.belajar.java.pelatihan.entity.Materi;
import com.arielslf.belajar.java.pelatihan.entity.Sesi;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface SesiDao extends PagingAndSortingRepository<Sesi, String>{
    public Page<Sesi> findByMateri(Materi m, Pageable page);
    
    /**
     *
     * @param mulai
     * @param sampai
     * @param kode
     * @param page
     * @return
     */
    @Query("select x from Sesi x where x.mulai >= :m "
            + "and x.mulai < :s "
            + "and x.materi.kode = :k "
            + "order by x.mulai desc ")
    
    public Page<Sesi> cariBerdasarkanTanggalMulaiDanKodeMateri(
            @Param("m") Date mulai, 
            @Param("s") Date sampai, 
            @Param("k") String kode,
            Pageable page);
}
