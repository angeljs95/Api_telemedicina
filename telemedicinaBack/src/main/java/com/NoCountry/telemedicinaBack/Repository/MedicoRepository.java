package com.NoCountry.telemedicinaBack.Repository;

import com.NoCountry.telemedicinaBack.Entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico,Long> {

    public List<Medico> findByEspecialidad(String especialidad);

    @Query("SELECT m FROM Medico m " +
            "WHERE (LOWER(m.nombre) LIKE LOWER(CONCAT('%', :filtroBusqueda, '%')) " +
            "OR LOWER(m.pais) LIKE LOWER(CONCAT('%', :filtroBusqueda, '%')) " +
            "OR LOWER(m.especialidad) LIKE LOWER(CONCAT('%', :filtroBusqueda, '%')))")
    List<Medico> buscarPorCriterioPersonalizado(@Param("filtroBusqueda") String filtroBusqueda);


}
