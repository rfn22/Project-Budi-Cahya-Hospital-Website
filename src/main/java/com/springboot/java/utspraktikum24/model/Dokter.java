package com.springboot.java.utspraktikum24.model;

import javax.persistence.*;

@Entity
@Table(name = "dokter")
public class Dokter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String alamat;
    private String telepon;

    public Dokter(Long id, String name, String alamat, String telepon) {
        super();
        this.id = id;
        this.name = name;
        this.alamat = alamat;
        this.telepon = telepon;
    }

    public Dokter() { super();}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
}
