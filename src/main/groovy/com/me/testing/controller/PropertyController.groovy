package com.me.testing.controller

import com.me.testing.domain.Property
import com.me.testing.service.PropertyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController("/")
class PropertyController {
    PropertyService propertyService
    @Autowired
    PropertyController(PropertyService propertyService){
        this.propertyService = propertyService

    }
    @RequestMapping(value='/propertiesbyestrato/', method = RequestMethod.GET, produces = "application/json")
    def getPropertiesByEstrato(@RequestParam int estrato){
        try {
            List<Property> properties = propertyService.getPropertiesByEstrato(estrato)?:[]
            if (properties.size() == 0) {
                return new ResponseEntity(properties, HttpStatus.NO_CONTENT)
            } else {
                return new ResponseEntity(properties, HttpStatus.OK)
            }
        } catch (Exception e){
            e.printStackTrace()
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}
