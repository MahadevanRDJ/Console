package com.MahadevanRDJ.carparkingmanagement.Payment;

import com.MahadevanRDJ.carparkingmanagement.CarParkingRepository.CarParkingRepository;

public class PaymentModel implements PaymentModelCallBack {
    private PaymentModelControllerCallBack paymentController;

    public PaymentModel(PaymentModelControllerCallBack paymentController) {
        this.paymentController = paymentController;
    }

    @Override
    public void getAmount() {
        int amount = CarParkingRepository.getInstance().totalFare();
        paymentController.pay(amount);
    }

    public void ticketID() {
        
    }
}
interface PaymentModelControllerCallBack {

    void pay(int amount);


}
