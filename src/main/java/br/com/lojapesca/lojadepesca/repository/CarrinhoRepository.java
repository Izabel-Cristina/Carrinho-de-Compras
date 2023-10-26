package br.com.lojapesca.lojadepesca.repository;

import br.com.lojapesca.lojadepesca.domain.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
}
