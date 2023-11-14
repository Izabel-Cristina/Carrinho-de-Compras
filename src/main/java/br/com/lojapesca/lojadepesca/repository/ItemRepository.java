package br.com.lojapesca.lojadepesca.repository;

import br.com.lojapesca.lojadepesca.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
