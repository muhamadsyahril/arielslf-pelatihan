/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arielslf.belajar.java.pelatihan.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author arielslf
 */
@Entity
public class Peserta {
    
    @Id
    private String id;
    
    @Column(nullable = false)
    private String nama;
    
    @Column( nullable = false, unique = true)
    private String email;
    
    @Column(name = "tanggal_lahir", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;
    
}
