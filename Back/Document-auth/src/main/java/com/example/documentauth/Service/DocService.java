package com.example.documentauth.Service;


import com.example.documentauth.entity.Doc;
import com.example.documentauth.repository.DocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DocService {

    @Autowired
    private DocRepository docRepository;

    public Doc saveDoc(Doc doc) {
        return docRepository.save(doc);
    }

    public List<Doc> getAllDocs() {
        return docRepository.findAll();
    }

    public Doc getDocById(Long id) {
        return docRepository.findById(id).orElse(null);
    }

    public void deleteDoc(Long id) {
        docRepository.deleteById(id);
    }
}