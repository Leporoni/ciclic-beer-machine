package br.com.ciclic.beermachine.repository;

import br.com.ciclic.beermachine.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, Long> {
    Beer findByBeerStyleContainingIgnoreCase(String beerStyle);
}
