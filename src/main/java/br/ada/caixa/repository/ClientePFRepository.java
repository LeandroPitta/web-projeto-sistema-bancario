package br.ada.caixa.repository;

import br.ada.caixa.entity.ClientePF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientePFRepository extends JpaRepository<ClientePF, String> {
}
