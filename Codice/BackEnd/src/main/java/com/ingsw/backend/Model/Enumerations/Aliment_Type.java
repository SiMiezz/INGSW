package com.ingsw.backend.Model.Enumerations;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Aliment_Type {
    @JsonProperty("food")
    food,
    @JsonProperty("drink")
    drink
}
