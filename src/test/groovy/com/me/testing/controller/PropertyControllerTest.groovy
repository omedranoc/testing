package com.me.testing.controller

import com.me.testing.domain.Property
import com.me.testing.service.PropertyService
import org.springframework.http.HttpStatus
import spock.lang.Specification
import org.springframework.http.ResponseEntity
class PropertyControllerTest extends Specification {
    PropertyService propertyService = Mock()
    PropertyController propertyController = new PropertyController(propertyService)

    def "should return  200  and the properties filtered by estrato"(){
        given:
        int estrato = 4
        Property property1 = new Property()
        Property property2 = new Property()
        property1.estrato = estrato
        property1.price = 3000000
        property2.estrato = estrato
        property2.price = 45000000
        propertyService.getPropertiesByEstrato(estrato) >> [property1, property2]

        when:
        ResponseEntity responseEntity = propertyController.getPropertiesByEstrato(estrato)

        then:
        responseEntity.statusCode == HttpStatus.OK
        responseEntity.body == [property1, property2]

    }

}
