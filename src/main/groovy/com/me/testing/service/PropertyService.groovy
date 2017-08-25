package com.me.testing.service

import com.me.testing.dao.PropertyDao
import com.me.testing.domain.Property
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PropertyService {
    PropertyDao propertyDao

    @Autowired
    PropertyService(PropertyDao propertyDao){
        this.propertyDao = propertyDao
    }

    List<Property> getPropertiesByEstrato(int estrato){
       propertyDao.selectAllApartments().findAll({property -> property.estrato == estrato})
    }


}
