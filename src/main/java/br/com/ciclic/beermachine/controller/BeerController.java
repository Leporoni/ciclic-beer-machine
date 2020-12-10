package br.com.ciclic.beermachine.controller;

import br.com.ciclic.beermachine.dto.BeerDTO;
import br.com.ciclic.beermachine.entity.Beer;
import br.com.ciclic.beermachine.repository.BeerRepository;
import br.com.ciclic.beermachine.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/beer")
public class BeerController {

    @Autowired
    private BeerService beerService;

    @Autowired
    private BeerRepository beerRepository;

    @PostMapping("/beers")
    public Beer createOrUpdateBeer(@RequestBody @Validated Beer beer) {
        return beerRepository.save(beer);
    }

    @PutMapping("/beers")
    public List<Beer> createOrUpdateBeer(@RequestBody Beer[] beers) {
        List<Beer> response = new ArrayList();
        for (Beer beer : beers) {
            response.add(beerRepository.save(beer));
        }

        return response;
    }

    @DeleteMapping("/beers/{id}")
    public void deleteBeer(@PathVariable Integer id) {
        beerRepository.deleteById(id);
    }

    @GetMapping("/beers")
    public List<Beer> retrieveAllBeers() {
        return beerRepository.findAll();
    }

    @GetMapping("/beers/{id}")
    public ResponseEntity<Beer> retrieveBeer(@PathVariable Integer id) {
        Object body;
        HttpStatus status = HttpStatus.OK;

        Optional<Beer> beer = beerRepository.findById(id);

        if (!beer.isPresent()) {
            body = "ID: " + id + " não localizado";
            status = HttpStatus.NO_CONTENT;
        } else {
            body = beer.get();
        }

        return new ResponseEntity(body, status);
    }

    @GetMapping(value = "/beers", params = {"page", "size"})
    public List<Beer> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC,"beerStyle");
        Page<Beer> beers = beerRepository.findAll(pageRequest);
        return beers.getContent();
    }

    @GetMapping("/best-beers/{temperature}")
    public BeerDTO retrieveBestBeer(@PathVariable Integer temperature) {
        return beerService.retrieveBeer(temperature);
    }
}