package com.me.testing.integrationTest

import com.me.testing.TestingApplication
import com.me.testing.dao.PropertyDao
import com.me.testing.domain.Property
import com.me.testing.service.PropertyService
import groovy.sql.Sql
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import spock.lang.Specification

import javax.sql.DataSource
import java.sql.Connection
import java.sql.SQLException
import java.sql.SQLFeatureNotSupportedException
import java.util.logging.Logger

import static org.mockito.BDDMockito.given

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class PropertyIntegrationTest {

    @Autowired private TestRestTemplate testRestTemplate
    @SpyBean private PropertyDao propertyDao
    @SpyBean private PropertyService propertyService


    @Before
    public void setup(){

        Property property1 = new Property()
        Property property2 = new Property()
        property1.estrato =1
        property2.estrato =1
        property1.propertyType = "casa"
        property2.propertyType = "apartment"
        given(this.propertyService.getPropertiesByEstrato(1)).willReturn([property1,property2]);
        given(this.propertyDao.selectAllApartments()).willReturn([property1,property2]);
    }
    @Test
    public void test(){
        def result =  this.testRestTemplate.getForObject("/propertiesbyestrato/1", String.class)
        println(result.toString())

    }






}
