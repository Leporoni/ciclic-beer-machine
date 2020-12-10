package br.com.ciclic.beermachine.dto;

public class BeerDTO {

    private String beerStyle;

    public BeerDTO() {
    }

    public BeerDTO(String beerStyle) {
        this.beerStyle = beerStyle;
    }

    public String getBeerStyle() {
        return beerStyle;
    }

    public void setBeerStyle(String beerStyle) {
        this.beerStyle = beerStyle;
    }
}