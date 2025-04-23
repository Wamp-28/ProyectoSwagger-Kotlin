package com.example.SpringSwagger1.controller;

import com.example.SpringSwagger1.model.Estudiante;
import com.example.SpringSwagger1.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EstudianteController {

    @Autowired
    public EstudianteService estudianteService;

    @GetMapping("/listar")
    public ResponseEntity<List<Estudiante>> listarEstudiante() {
        List<Estudiante> estudiantes = estudianteService.listar();
        return ResponseEntity.ok(estudiantes);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Estudiante> guardarEstudiane(@RequestBody Estudiante estudiante) {
        Estudiante estudianteGuardado = estudianteService.guardar(estudiante);
        return ResponseEntity.status(201).body(estudianteGuardado); // Devuelve 201 Created con el estudiante guardado
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Estudiante> listarPorId(@PathVariable Long id) {
        return estudianteService.buscarporId(id)
                .map(estudiante -> ResponseEntity.ok(estudiante)) // Si el estudiante existe, devuelve 200 OK con el estudiante
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si no existe, devuelve 404 Not Found
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarEstudiante(@PathVariable Long id) {
        if (estudianteService.buscarporId(id).isPresent()) {
            estudianteService.eliminar(id);
            return ResponseEntity.noContent().build(); // Devuelve 204 No Content si la eliminación es exitosa
        }
        // Devuelve 404 Not Found con un mensaje personalizado
        return ResponseEntity
                .status(404) // Establece el código de estado 404
                .body("Código no existe"); // El mensaje que se incluirá en el cuerpo de la respuesta
    }


    @GetMapping("/telefono/{telefono}")
    public ResponseEntity<Estudiante> buscarPorTelefono(@PathVariable String telefono) {
        Optional<Estudiante> estudiante = estudianteService.buscarporTelefono(telefono);

        if (estudiante.isPresent()) {
            return ResponseEntity.ok(estudiante.get()); // Devuelve el estudiante con estado 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Devuelve 404 Not Found si no existe
        }
    }
}
