package reflection.method;

public class PaymentServiceTest {
    private PaymentService service;

    public void beforeClass() {
        // Called in the beginning of the test suite only once
        // Used for all tests need to share computationally expensive setup
        this.service =  new PaymentService("test");
        System.out.println("=========== beforeClass ===========");
    }

    public void setupTest() {
        // Called before every test
        // Used for setting up resource before every test
        System.out.println("=========== setupTest ===========");
    }

    public void testCreditCardPayment() {
        // Test case 1
        System.out.println("=========== testCreditCardPayment ===========");
    }

    public void testWireTransfer() {
        // Test case 2
        System.out.println("=========== testWireTransfer ===========");
    }

    public void testInsufficientFunds() {
        // Test case 3
        System.out.println("=========== testInsufficientFunds ===========");
    }

    public static void afterClass() {
        // Called once in the end of the entire test suite
        // Used for closing and cleaning up common resources
        System.out.println("=========== afterClass ===========");
    }
}

