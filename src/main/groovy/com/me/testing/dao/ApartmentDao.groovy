package com.me.testing.dao

import com.me.testing.domain.Apartment
import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ApartmentDao {
    Sql sql

    @Autowired
    ApartmentDao(Sql sql){
        this.sql = sql

    }

    def insertApartmentRow(Apartment apartment){
        sql.execute("insert into finca_raiz (property_type, price, private_area, built_area, estrato, address, description" +
                ", updaded_date, url) values (?,?,?,?,?,?,?,?,?)", apartment.propertyType, apartment.price, apartment.privateArea, apartment.builtArea
                , apartment.estrato, apartment.address, apartment.description, apartment.updateDate, apartment.url)

    }
}
