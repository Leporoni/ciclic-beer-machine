package br.com.ciclic.beermachine.repository;

import br.com.ciclic.beermachine.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
    Beer findByBeerStyleContainingIgnoreCase(String beerStyle);

    Optional<Beer> findById(Integer id);

    void deleteById(Integer id);
}
