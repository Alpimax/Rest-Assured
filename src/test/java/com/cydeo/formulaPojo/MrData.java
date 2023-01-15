package com.cydeo.formulaPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.Test;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class MrData {

    private String xmlns;
    private String series;
    private String url;
    private String limit;
    @JsonProperty("")
    private String offset;
    @JsonProperty("total")
    private String total;
    private String DriverTable;


}

