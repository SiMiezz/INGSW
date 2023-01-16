package com.ingsw.backend.Model.Enumerations;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum User_Type {
    @JsonProperty("admin")
    admin,
    @JsonProperty("supervisor")
    supervisor,
    @JsonProperty("waiter")
    waiter,
    @JsonProperty("chef")
    chef
}
