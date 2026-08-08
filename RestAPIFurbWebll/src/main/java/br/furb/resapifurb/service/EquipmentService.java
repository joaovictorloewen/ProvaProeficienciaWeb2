package br.furb.resapifurb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.furb.resapifurb.dto.EquipmentRequest;
import br.furb.resapifurb.entity.Equipment;
import br.furb.resapifurb.entity.EquipmentType;
import br.furb.resapifurb.exception.ResourceNotFoundException;
import br.furb.resapifurb.repository.EquipmentRepository;
import br.furb.resapifurb.repository.EquipmentTypeRepository;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentTypeRepository typeRepository;

    public EquipmentService(EquipmentRepository equipmentRepository, EquipmentTypeRepository typeRepository) {
        this.equipmentRepository = equipmentRepository;
        this.typeRepository = typeRepository;
    }

    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

    public Equipment findById(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipamento não encontrado com id: " + id));
    }

    @Transactional
    public Equipment create(EquipmentRequest request) {
        EquipmentType tipo = findOrCreateType(request.getTipo().getNome());
        Equipment equipment = new Equipment();
        equipment.setNome(request.getNome());
        equipment.setTipo(tipo);
        return equipmentRepository.save(equipment);
    }

    @Transactional
    public Equipment update(Long id, EquipmentRequest request) {
        Equipment existing = findById(id);
        if (request.getNome() != null && !request.getNome().isBlank()) {
            existing.setNome(request.getNome());
        }
        if (request.getTipo() != null && request.getTipo().getNome() != null) {
            EquipmentType tipo = findOrCreateType(request.getTipo().getNome());
            existing.setTipo(tipo);
        }
        return equipmentRepository.save(existing);
    }

    public void delete(Long id) {
        Equipment existing = findById(id);
        equipmentRepository.delete(existing);
    }

    private EquipmentType findOrCreateType(String nome) {
        return typeRepository.findByNomeIgnoreCase(nome).orElseGet(() -> typeRepository.save(new EquipmentType(null, nome)));
    }
}
