/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author hp
 */
public class SendPhoneController {
    
    private static final String Account_SID = "ACaac996ea1f542ac8850e2129da267adc";
    private static final String Account_Token = "65de5c9133800c42d298696047160184";
    private static final String SMS_Center = "+12292672607";
    private static final String Status_Callback_URL = "http://localhost:9999/SE1709_G3_FLM/view/userAccess/profile.jsp";
    private static final TwilioRestClient client;
    
    static {
        client = new TwilioRestClient(Account_SID, Account_Token);
    }
    public void sendMessage(String toNumber, String code) throws TwilioRestException{
        List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        params.add(new BasicNameValuePair("Body", code));
        params.add(new BasicNameValuePair("To", toNumber));
        params.add(new BasicNameValuePair("From", SMS_Center));
        params.add(new BasicNameValuePair("StatusCallBack", Status_Callback_URL));
        
        client.getAccount().getMessageFactory().create(params);
    }
   
}
