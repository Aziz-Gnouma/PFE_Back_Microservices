package com.example.cvai.Controller;

import com.example.cvai.Entity.OffreEmploi;
import com.example.cvai.service.OffreEmploiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offres")
public class OffreEmploiController {

    @Autowired
    private OffreEmploiService offreEmploiService;

    @PostMapping("/add")
    public OffreEmploi addOffre(@RequestBody OffreEmploi offreEmploi) {
        return offreEmploiService.addOffre(offreEmploi);
    }

    @PutMapping("/edit/{id}")
    public OffreEmploi editOffre(@PathVariable Long id, @RequestBody OffreEmploi updatedOffre) {
        return offreEmploiService.editOffre(id, updatedOffre);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOffre(@PathVariable Long id) {
        offreEmploiService.deleteOffre(id);
    }

    @GetMapping("/all")
    public List<OffreEmploi> getAllOffres() {
        return offreEmploiService.getAllOffres();
    }

    @GetMapping("/{id}")
    public OffreEmploi getOffreById(@PathVariable Long id) {
        return offreEmploiService.getOffreById(id);
    }
}
