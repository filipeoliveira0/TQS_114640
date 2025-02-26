package ex2_2;

import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ProductFinderService {

    private ISimpleHttpClient httpClient;
    private final String url = "https://fakestoreapi.com/products/";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ProductFinderService(ISimpleHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Optional<Product> findProductDetails(int id) {

        try {
            String jsonResponse = httpClient.doHttpGet(url + id);

            Product product = objectMapper.readValue(jsonResponse, Product.class);

           
            return Optional.ofNullable(product.getId() != null ? product : null);
        } catch (Exception e) {
            return Optional.empty(); 
        }
    }
}
