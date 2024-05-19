package br.ada.caixa.repository;

import br.ada.caixa.entity.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {


    @Query("SELECT c FROM Conta c " +
            "WHERE :tipoConta IS NULL OR c.tipoConta = :tipoConta " +
            "AND :numeroConta IS NULL OR c.numeroConta = :numeroConta")
    Page<Conta> pesquisarPage(@Param("tipoConta") String tipoConta,
                              @Param("numeroConta") String numeroConta,
                              Pageable pageable);

}
