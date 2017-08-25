package com.me.testing.service

import com.me.testing.dao.PropertyDao
import com.me.testing.domain.Property
import spock.lang.Specification

class PropertyServiceTest extends Specification {
    PropertyDao propertyDao = Mock()
    final String PROPERTY_DESCRIPTION = "Se vende amplio apartamento de 118.4 M2, ubicado en tranquilo sector residencial muy cerca a TM. Con espacios muy bien distribuidos, este apartamento de tres alcobas, tiene sala con chimenea y balcón, comedor independiente, cómoda cocina integral, baño social, y cuarto y baño de servicio. La alcoba principal cuenta con chimenea, vestier, y baño privado. Buena luz natural. Un parqueadero independiente cubierto y depósit"
    PropertyService propertyService = new PropertyService( propertyDao )
    Property apartmentRow = new Property(propertyType: "casa", price: new BigDecimal(300000000), privateArea: 60, builtArea: 55, estrato: 4,
            address: "carrera 148 b 132d 15", description: PROPERTY_DESCRIPTION,
            updateDate: new Date(117, 7, 8), url: "https://www.fincaraiz.com.co/apartamento-en-venta/bogota/puente_largo-det-2375791.aspx")
    Property houseRow = new Property(propertyType: "house", price: new BigDecimal(200000000), privateArea: 69, builtArea: 55, estrato: 3,
            address: "carrera 148 b 132d 15", description: PROPERTY_DESCRIPTION,
            updateDate: new Date(117, 7, 8), url: "https://www.fincaraiz.com.co/apartamento-en-venta/bogota/puente_largo-det-2375791.aspx")
    Property houseRow1 = new Property(propertyType: "house", price: new BigDecimal(100000000), privateArea: 45, builtArea: 55, estrato: 4,
            address: "carrera 148 b 132d 15", description: PROPERTY_DESCRIPTION,
            updateDate: new Date(117, 7, 8), url: "https://www.fincaraiz.com.co/apartamento-en-venta/bogota/puente_largo-det-2375791.aspx")


    def "get all the properties filtered by their estrato"() {
        given:
        int estrato = 4
        propertyDao.selectAllApartments() >> [apartmentRow, houseRow, houseRow1]
        when:
        List<Property> properties = propertyService.getPropertiesByEstrato(estrato)
        then:
        properties.size() == 2
        properties.contains(apartmentRow)
        properties.contains(houseRow1)
    }

}
