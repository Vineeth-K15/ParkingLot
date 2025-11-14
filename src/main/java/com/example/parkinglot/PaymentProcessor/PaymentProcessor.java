package PaymentProcessor;

public class PaymentProcessor {
    private double totalAmount;
    private Payment_Method paymentMode;

    public PaymentProcessor() {
        this.totalAmount = 0.0;
    }

    public void processPayment(double amount) {
        this.totalAmount = amount;
        System.out.println("Payment processed: ₹" + amount);
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Payment_Method getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(Payment_Method paymentMode) {
        this.paymentMode = paymentMode;
    }
}
