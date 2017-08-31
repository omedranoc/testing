package com.me.testing.domain

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
class Property {

    String propertyType
    BigDecimal price
    int privateArea
    int builtArea
    int estrato
    String address
    String description
    Date updateDate
    String url

}