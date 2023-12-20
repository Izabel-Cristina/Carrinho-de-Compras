package br.com.lojapesca.lojadepesca.repository;

import br.com.lojapesca.lojadepesca.domain.Produto;
import org.springframework.data.jpa.mapping.JpaPersistentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
