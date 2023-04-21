package uz.tafakkoor.easyorder.services.auth;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TwilioService {
    @Value("${twilio.account.sid}")
    private String ACCOUNT_SID;
    @Value("${twilio.auth.token}")
    private String AUTH_TOKEN;
    @Value("${twilio.from.number}")
    private String FROM_NUMBER;

    public void sendOtp(String toNumber, String otp) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String messageBody = "Your EasyOrder verification code is " + otp;

        Message message = Message.creator(
                        new PhoneNumber(toNumber),
                        new PhoneNumber(FROM_NUMBER),
                        messageBody)
                .create();

        String sid = message.getSid();
        System.out.println(sid);
    }
}
