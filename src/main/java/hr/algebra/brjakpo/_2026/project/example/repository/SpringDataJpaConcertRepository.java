package hr.algebra.brjakpo._2026.project.example.repository;

import hr.algebra.brjakpo._2026.project.example.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaConcertRepository extends JpaRepository<Concert, Long> {
}
