package br.furb.resapifurb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.furb.resapifurb.dto.EquipmentRequest;
import br.furb.resapifurb.entity.Equipment;
import br.furb.resapifurb.service.EquipmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/equipamentos")
@Validated
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping
    public ResponseEntity<Map<String, List<Equipment>>> getAll() {
        return ResponseEntity.ok(Map.of("equipamentos", equipmentService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getById(@PathVariable Long id) {
        return ResponseEntity.ok(equipmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Equipment> create(@Valid @RequestBody EquipmentRequest request) {
        Equipment created = equipmentService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> update(@PathVariable Long id, @Valid @RequestBody EquipmentRequest request) {
        Equipment updated = equipmentService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        equipmentService.delete(id);
        return ResponseEntity.ok(Map.of("success", Map.of("text", "equipamento removido")));
    }
}
