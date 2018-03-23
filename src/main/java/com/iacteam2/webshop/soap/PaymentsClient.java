
package com.iacteam2.webshop.soap;

import com.iacteam2.generated.Address;
import com.iacteam2.webshop.Payments;
import com.iacteam2.webshop.PaymentsService;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class PaymentsClient {
    public int makePayment(String name, Address address, float amount) throws MalformedURLException {
        QName qName = new QName("http://webshop.iacteam2.com/",
                "PaymentsService");
        URL url = new URL("http://localhost:9999/ws/payments");
        Service service = PaymentsService.create(url, qName);
        Payments port = (Payments) service.getPort(Payments.class);

        int paymentCode = port.makePayment(name, address, amount);

        return paymentCode;
    }
}