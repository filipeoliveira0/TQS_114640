package ex2_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ProductFinderServiceTest
{
   
@Mock
private ISimpleHttpClient httpClient;

@InjectMocks
private ProductFinderService productFinderService;

@BeforeEach
public void setUp() {
    productFinderService = new ProductFinderService(httpClient);
}

@Test
void findProductDetailsTest(){

    String jsonResponse = """
          {
        "id": 3,
        "title": "Mens Cotton Jacket",
        "price": 55.99,
        "description": "great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.",
        "category": "men's clothing",
        "image": "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg"
        }
        """;

        when(httpClient.doHttpGet(anyString())).thenReturn(jsonResponse);

        Optional<Product> product = productFinderService.findProductDetails(3);

        assertTrue(product.isPresent());
        assertEquals(3, product.get().getId());
        assertEquals("Mens Cotton Jacket", product.get().getTitle()); 
}
@Test
void findProductDetailsFailTest(){

    when(httpClient.doHttpGet(anyString())).thenReturn("{}");

    Optional<Product> product = productFinderService.findProductDetails(300);

    assertTrue(product.isEmpty());
}
} 