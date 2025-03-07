## A

    assertThat(found).isNotNull().
            extracting(Employee::getName).isEqualTo(persistedAlex.getName());

    the chains extracting, isEqualTo, isNotNull are all methods of the AssertJ library. The extracting is used to extract a field from an object, the isEqualTo is used to compare the extracted field with the expected value and the isNOtNull is used to check if the object is not null.



## B

    @DataJpaTest is used in the A_EmployeeRepositoryTest class
    It includes the next transitive annotations:
    - @AutoConfigureTestDatabase
    - @Transactional
    - @ExtendWith(SpringExtension.class)


## C

    The mocking of the repository is shown in the B_EmployeeService_UnitTest class.

    @Mock 
    private EmployeeRepository employeeRepository;

    instead of using the database to test the service, a mock is used so that no operations are performed on the database, only on the mock object.


## D


    The difference between @Mock and @Mockbean is that @Mock is used to create a mock of a class, while @Mockbean is used to create a mock of a bean in the Spring context. @Mock is used for unit tests and @MockBean is used for integration tests.


## E

    The role of application-integrationtest.properties is to provide the properties for the integration tests. It is used to configure the database connection and other properties that are needed for the integration tests.


## F

    Strategy C focuses on testing the REST controller in isolation using MockMvc to simulate HTTP requests. The @MockBean annotation is used to create mock service layer dependencies, eliminating the need for a real database.
    Strategy D takes a broader approach by using @SpringBootTest, which loads the entire application context. While MockMvc is still used to perform HTTP requests, the @AutoConfigureTestDatabase annotation configures an in-memory database for testing, automatically resetting it after each run. 
    Strategy E is similar to Strategy D, but instead of using MockMvc, it employs TestRestTemplate to send real HTTP requests to the API.

