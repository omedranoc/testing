package com.me.testing.controller

import com.me.testing.domain.Property
import com.me.testing.service.PropertyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
class PropertyController {
    PropertyService propertyService
    @Autowired
    PropertyController(PropertyService propertyService){
        this.propertyService = propertyService

    }
    @RequestMapping(value='/propertiesbyestrato/{estrato}', method = RequestMethod.GET, produces = "application/json")
    def getPropertiesByEstrato(@RequestParam int estrato){
        List<Property> properties = propertyService.getPropertiesByEstrato(estrato)
        ResponseEntity responseEntity = new ResponseEntity(properties, HttpStatus.OK)
        responseEntity
    }

}
