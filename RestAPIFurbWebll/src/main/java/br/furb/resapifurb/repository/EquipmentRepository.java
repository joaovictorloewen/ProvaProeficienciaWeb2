package br.furb.resapifurb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.furb.resapifurb.entity.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
