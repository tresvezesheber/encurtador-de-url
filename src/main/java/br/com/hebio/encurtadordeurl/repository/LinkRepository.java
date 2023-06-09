package br.com.hebio.encurtadordeurl.repository;

import br.com.hebio.encurtadordeurl.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends PagingAndSortingRepository<Link, Long> {

    boolean existsByUrl(String url);
}
