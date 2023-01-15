package com.cydeo.formulaPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Driver {
    @JsonProperty("driverId")
    private String driverId;
    private String url;
    private String givenName;
    private String familyName;
    private String dateOfBirth;
    private String nationality;



}
//                    "driverId": "abate",
//                    "url": "http://en.wikipedia.org/wiki/Carlo_Mario_Abate",
//                    "givenName": "Carlo",
//                    "familyName": "Abate",
//                    "dateOfBirth": "1932-07-10",
//                    "nationality":