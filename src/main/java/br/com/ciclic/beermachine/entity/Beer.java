package br.com.ciclic.beermachine.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BEER")
public class Beer {

    public Beer() {
        super();
    }

    public Beer(String beerStyle, Integer temperatureMin, Integer temperatureMax) {
        this.beerStyle = beerStyle;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotNull(message = "Estilo de cerveja é obrigatório")
    @Column(name = "BEER_STYLE")
    private String beerStyle;

    @NotNull(message = "Temperatura mínima é obrigatório")
    @Column(name = "TEMP_MIN")
    private Integer temperatureMin;

    @NotNull(message = "Temperatura máxima é obrigatório")
    @Column(name = "TEMP_MAX")
    private Integer temperatureMax;

    @Transient
    private int targetTemperature;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeerStyle() {
        return beerStyle;
    }

    public void setBeerStyle(String beerStyle) {
        this.beerStyle = beerStyle;
    }

    public Integer getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Integer temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Integer getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Integer temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public int getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(int targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public int getDifference() {
        return (targetTemperature >= temperatureMin && targetTemperature <= temperatureMax) ? 0 : lessDifference(targetTemperature);
    }

    private int lessDifference(int temperature) {
        int min = Math.abs(temperatureMin - temperature);
        int max = Math.abs(temperatureMax - temperature);
        return Math.min(min, max);
    }
}
