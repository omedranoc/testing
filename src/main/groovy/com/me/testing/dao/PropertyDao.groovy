package com.me.testing.dao

import com.me.testing.domain.Property
import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PropertyDao {
    Sql sql

    @Autowired
    PropertyDao(Sql sql){
        this.sql = sql

    }

    def insertApartmentRow(Property apartment){
        sql.execute("insert into property (property_type, price, private_area, built_area, estrato, address, description" +
                ", updated_date, url) values (?,?,?,?,?,?,?,?,?)", apartment.propertyType, apartment.price, apartment.privateArea, apartment.builtArea
                , apartment.estrato, apartment.address, apartment.description, apartment.updateDate, apartment.url)

    }

    def selectAllApartments() {
        List<Property> apartments = []
        sql.eachRow("select * from property") {
            row ->
                apartments << new Property(propertyType: row.property_type, price: row.price, privateArea: row.private_area,
                        builtArea: row.built_area, estrato: row.estrato, address: row.address, description: row.description,
                        updateDate: row.updated_date, url: row.url)
        }
        return apartments
    }

}
