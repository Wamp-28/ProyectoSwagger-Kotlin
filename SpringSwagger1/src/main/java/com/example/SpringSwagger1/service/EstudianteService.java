package com.example.SpringSwagger1.service;

import com.example.SpringSwagger1.model.Estudiante;
import com.example.SpringSwagger1.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;


    public List<Estudiante> listar(){
        return estudianteRepository.findAll();
    }

    public Estudiante guardar(Estudiante estudiante){
        return estudianteRepository.save(estudiante);
    }

    public Optional<Estudiante> buscarporId(Long id){
       return estudianteRepository.findById(id);
    }

    public void eliminar(Long id){
        estudianteRepository.deleteById(id);
    }

    public Optional<Estudiante> buscarporTelefono(String telefono){
        return  estudianteRepository.findByTelefono(telefono);
    }





}
