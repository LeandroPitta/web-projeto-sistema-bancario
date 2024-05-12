package br.ada.caixa.repository;

import br.ada.caixa.entity.ClientePJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientePJRepository extends JpaRepository<ClientePJ, String> {
}
