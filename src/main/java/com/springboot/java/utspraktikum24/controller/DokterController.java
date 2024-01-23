package com.springboot.java.utspraktikum24.controller;

import com.springboot.java.utspraktikum24.model.Dokter;
import com.springboot.java.utspraktikum24.repositories.DokterRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dokters")
public class DokterController {

    @Autowired
    private DokterRepositories dokterRepositories;

    @GetMapping
    public String listDokter(Model model) {
        model.addAttribute("dokters", dokterRepositories.findAll());
        return "dokter";
    }

    @GetMapping("/create")
    public String createDokterForm(Model model) {
        model.addAttribute("dokter", new Dokter());
        return "create";
    }

    @PostMapping("/create")
    public String createDokter(@ModelAttribute Dokter dokter) {
        dokterRepositories.save(dokter);
        return "redirect:/dokters";
    }

    @GetMapping("/edit/{id}")
    public String editDokterForm(@PathVariable Long id, Model model) {
        Dokter dokter = dokterRepositories.findById(id).orElse(null);
        model.addAttribute("dokter", dokter);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute Dokter updatedDokter) {
        Dokter dokter = dokterRepositories.findById(id).orElse(null);
        if (dokter != null) {
            dokter.setName(updatedDokter.getName());
            dokter.setAlamat(updatedDokter.getAlamat());
            dokter.setTelepon(updatedDokter.getTelepon());
            dokterRepositories.save(dokter);
        }
        return "redirect:/dokters";
    }

    @GetMapping("/delete/{id}")
    public String deleteDokter(@PathVariable Long id) {
        dokterRepositories.deleteById(id);
        return "redirect:/dokters";
    }
}
