package org.softuni.residentevil.models.binding;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class VirusAddDto {
    private String name;

    private String description;

    private String sideEffect;

    private String creator;

    private Boolean isCurable;

    private Boolean isDeadly;

    private String mutation;

    private Integer turnOverRate;

    private Integer hoursUntilTurn;

    private String magnitude;

    private LocalDate releaseDate;

    private List<String> affectedCapitals;

    public VirusAddDto() {
        this.affectedCapitals = new ArrayList<>();
    }

    @NotNull
    @Size(min = 5, max = 10, message = "Invalid name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Size(min = 5, max = 10, message = "Invalid description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @Size(min = 1, max = 50, message = "Invalid side effects")
    public String getSideEffect() {
        return this.sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    @NotNull
    @Pattern(regexp = "[C|c]orp", message = "Invalid creator")
    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Boolean getCurable() {
        return this.isCurable;
    }

    public void setCurable(Boolean deadly) {
        this.isCurable = deadly;
    }

    public Boolean getDeadly() {
        return this.isDeadly;
    }

    public void setDeadly(Boolean deadly) {
        this.isDeadly = deadly;
    }

    @NotNull(message = "Mutation cannot be null")
    public String getMutation() {
        return this.mutation;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    @NotNull(message = "Invalid turnover rate")
    @Min(value = 0, message = "Invalid turnover rate")
    @Max(value = 100, message = "Invalid turnover rate")
    public Integer getTurnOverRate() {
        return this.turnOverRate;
    }

    public void setTurnOverRate(Integer turnOverRate) {
        this.turnOverRate = turnOverRate;
    }

    @NotNull
    @Min(value = 1, message = "Invalid hours")
    @Max(value = 12, message = "Invalid hours")
    public Integer getHoursUntilTurn() {
        return this.hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    public String getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    @NotNull(message = "Invalid date")
    @Past(message = "Invalid date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @NotEmpty(message = "You must select capitals")
    public List<String> getAffectedCapitals() {
        return this.affectedCapitals;
    }

    public void setAffectedCapitals(List<String> affectedCapitals) {
        this.affectedCapitals = affectedCapitals;
    }
}
