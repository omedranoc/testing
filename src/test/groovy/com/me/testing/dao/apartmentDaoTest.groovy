package com.me.testing.dao

import com.me.testing.domain.Apartment
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import spock.lang.Specification
import groovy.sql.Sql

class apartmentDaoTest extends Specification {

   static EmbeddedDatabase embeddedDatabase
   Sql sql = new Sql(embeddedDatabase)
   final String APARTMENT_DESCRIPTION = "Se vende amplio apartamento de 118.4 M2, ubicado en tranquilo sector residencial muy cerca a TM. Con espacios muy bien distribuidos, este apartamento de tres alcobas, tiene sala con chimenea y balcón, comedor independiente, cómoda cocina integral, baño social, y cuarto y baño de servicio. La alcoba principal cuenta con chimenea, vestier, y baño privado. Buena luz natural. Un parqueadero independiente cubierto y depósit"
   ApartmentDao apartmentDao = new ApartmentDao(sql)
   Apartment apartmentRow = new Apartment(propertyType: "casa", price: new BigDecimal(300000000), privateArea: 60, builtArea: 55, estrato: 4,
           address: "carrera 148 b 132d 15", description: APARTMENT_DESCRIPTION,
           updateDate: new Date(117, 7, 8), url: "https://www.fincaraiz.com.co/apartamento-en-venta/bogota/puente_largo-det-2375791.aspx")
   Apartment apartmentRow1 = new Apartment(propertyType: "apartment", price: new BigDecimal(200000000), privateArea: 69, builtArea: 55, estrato: 3,
           address: "carrera 148 b 132d 15", description: APARTMENT_DESCRIPTION,
           updateDate: new Date(117, 7, 8), url: "https://www.fincaraiz.com.co/apartamento-en-venta/bogota/puente_largo-det-2375791.aspx")

   def setupSpec() {
      embeddedDatabase = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
              .addScript("finca_raiz.sql").build();
   }

   def cleanupSpec() {
      embeddedDatabase.shutdown()
      embeddedDatabase = null
   }

   def cleanup() {
      sql.execute('delete from finca_raiz')
   }

   def 'insert a row  in the database'() {

      when:
      apartmentDao.insertApartmentRow(apartmentRow)
      then:
      List rows = sql.rows("select * from finca_raiz")
      rows[0].property_type == apartmentRow.propertyType
      rows[0].price == apartmentRow.price
      rows[0].private_area == apartmentRow.privateArea
      rows[0].built_area == apartmentRow.builtArea
      rows[0].estrato == apartmentRow.estrato
      rows[0].address == apartmentRow.address
      rows[0].description == apartmentRow.description
      rows[0].updated_date == apartmentRow.updateDate
      rows[0].url == apartmentRow.url
   }

   def "select all the aparmentRows from the database"(){
      given:
      apartmentDao.insertApartmentRow(apartmentRow)
      apartmentDao.insertApartmentRow(apartmentRow1)
      when:
      List result =apartmentDao.selectAllApartments()
      then:
      result.size() == 2
      result.contains(apartmentRow)
      result.contains(apartmentRow1)

   }

}