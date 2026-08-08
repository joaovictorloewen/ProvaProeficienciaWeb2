package br.furb.resapifurb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.furb.resapifurb.entity.EquipmentType;

@Repository
public interface EquipmentTypeRepository extends JpaRepository<EquipmentType, Long> {
    Optional<EquipmentType> findByNomeIgnoreCase(String nome);
}
