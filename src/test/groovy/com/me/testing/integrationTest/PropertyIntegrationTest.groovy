package com.me.testing.integrationTest

import com.me.testing.dao.PropertyDao
import com.me.testing.domain.Property
import com.me.testing.service.PropertyService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner
import static org.mockito.BDDMockito.given

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class PropertyIntegrationTest {
    final String  RESPONSE = """[{"propertyType":"casa","privateArea":0,"builtArea":0,"estrato":1},{"propertyType":"apartment","privateArea":0,"builtArea":0,"estrato":1}]"""
    @Autowired private TestRestTemplate testRestTemplate
    @SpyBean private PropertyDao propertyDao
    @SpyBean private PropertyService propertyService


    @Test
    public  void shouldReturnBadRequestIfNotParameterIsPassed(){

        Property property1 = new Property(estrato: 1, propertyType: "casa")
        Property property2 = new Property(estrato: 1, propertyType: "apartment")
        List properties = [property1, property2]
        given(this.propertyService.getPropertiesByEstrato(1)).willReturn(properties);
        def result = this.testRestTemplate.getForObject("/propertiesbyestrato?estrato={estrato}", String.class, 1)
        Assert.assertEquals(RESPONSE, result);

    }






}
