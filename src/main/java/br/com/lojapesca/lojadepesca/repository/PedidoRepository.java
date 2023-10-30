package br.com.lojapesca.lojadepesca.repository;

import br.com.lojapesca.lojadepesca.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
