package br.com.ciclic.beermachine.service;

import br.com.ciclic.beermachine.dto.BeerDTO;
import br.com.ciclic.beermachine.entity.Beer;
import br.com.ciclic.beermachine.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class BeerService {

    @Autowired
    private BeerRepository beerRepository;

    public BeerDTO retrieveBeer(int temperature) {
        List<Beer> beers = beerRepository.findAll();
        beers.stream().forEach(beer -> {beer.setTargetTemperature(temperature);});

        Beer beer = beers.stream()
                .sorted(Comparator.comparing(Beer::getBeerStyle))
                .min(Comparator.comparing(Beer::getDifference))
                .get();

        return new BeerDTO(beer.getBeerStyle());
    }
}
