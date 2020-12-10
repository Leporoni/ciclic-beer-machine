package br.com.ciclic.beermachine.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Beer {

    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Getter
    @Setter
    @Column(name = "BEER_STYLE", unique = true)
    private String beerStyle;

    @Getter
    @Setter
    @Column(name = "TEMP_MIN")
    private Integer temperatureMin;

    @Getter
    @Setter
    @Column(name = "TEMP_MAX")
    private Integer temperatureMax;

    @Getter
    @Setter
    @Transient
    private int targetTemperature;

    public int getDifference() {
        return (targetTemperature >= temperatureMin && targetTemperature <= temperatureMax) ? 0 : lessDifference(targetTemperature);
    }

    private int lessDifference(int temperature) {
        int min = Math.abs(temperatureMin - temperature);
        int max = Math.abs(temperatureMax - temperature);
        return Math.min(min, max);
    }
}
