package br.ada.caixa.repository;

import br.ada.caixa.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>{

    @Query("SELECT c FROM Cliente c")
    Page<Cliente> pesquisarPage(Pageable pageable);

}
