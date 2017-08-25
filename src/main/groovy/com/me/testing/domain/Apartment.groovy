package com.me.testing.domain

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Apartment {

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