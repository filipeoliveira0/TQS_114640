package ex2_3;

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
            // Fetching the JSON response
            String jsonResponse = httpClient.doHttpGet(url + id);

            // Parse the JSON response to Product object
            Product product = objectMapper.readValue(jsonResponse, Product.class);

            // Log the parsed product for debugging purposes
            System.out.println("Parsed Product: " + product);

            // Return the product wrapped in Optional
            return Optional.ofNullable(product); // Return the product directly
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty(); // Return empty if there's an error
        }
    }
}

