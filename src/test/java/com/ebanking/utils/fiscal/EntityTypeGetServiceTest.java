package com.ebanking.utils.fiscal;

import org.junit.jupiter.api.Test;

import static com.ebanking.utils.fiscal.EntityType.UNIPERSONAL;
import static org.junit.jupiter.api.Assertions.*;

class EntityTypeGetServiceTest {
    @Test
    void getTest(){
        var entityType = new EntityTypeGetService().get("20");

        assertEquals(UNIPERSONAL, entityType);
    }

    @Test
    void getTestOutOfRange(){
        var entityType = new EntityTypeGetService().get("100");

        assertNull(entityType);
    }

}
