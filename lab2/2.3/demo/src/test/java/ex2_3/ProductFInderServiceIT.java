package ex2_3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class ProductFInderServiceIT {

    private final ProductFinderService productFinderService = new ProductFinderService(new SimpleHttpClient());

    @Test
    void testFindProductDetails_validProduct() throws IOException {
        
        Optional<Product> product = productFinderService.findProductDetails(3);

        System.out.println("Parse product:"+ product);

        
        assertTrue(product.isPresent());
        assertEquals(3, product.get().getId());
        assertEquals("Mens Cotton Jacket", product.get().getTitle());
    }

}
