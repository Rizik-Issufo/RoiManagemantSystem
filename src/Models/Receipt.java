package Models;

import java.time.LocalDateTime;

public class Receipt {
    private int id;
    private Cashier idCashier;
    private LocalDateTime data;
    private ProductList idProductList;
    private PaymentMode idPayment;

}
