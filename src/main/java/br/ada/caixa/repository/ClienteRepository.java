package br.ada.caixa.repository;

import br.ada.caixa.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>{

    @Query("SELECT c FROM Cliente c" +
            " WHERE (((TYPE(c) = ClientePF AND :tipoCliente = 'PF')" +
            " OR (TYPE(c) = ClientePJ AND :tipoCliente = 'PJ') OR :tipoCliente IS NULL)) " +
            " AND (:nome IS NULL OR UPPER(c.nome) LIKE UPPER(CONCAT('%', :nome, '%')) " +
            " OR :nome IS NULL OR UPPER(c.razaoSocial) LIKE UPPER(CONCAT('%', :nome, '%'))) " +
            " AND (:documentoCliente IS NULL OR c.documentoCliente = :documentoCliente) ")
    Page<Cliente> pesquisarPage(@Param("tipoCliente") String tipoCliente,
                                @Param("nome") String nome,
                                @Param("documentoCliente") String documentoCliente,
                                Pageable pageable);

}
