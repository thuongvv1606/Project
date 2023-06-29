/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Message;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Trung Quan
 */
public class SendPhone{

    private static final String Account_SID = "AC02e25764f06194abaa9c2e9fb10c47cb";
    private static final String Account_Token = "153518ff02e35d5852e78e21bcc44168";
    private static final String SMS_Center = "+13159247593";
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
        //params.add(new BasicNameValuePair("StatusCallBack", Status_Callback_URL));
        
        client.getAccount().getMessageFactory().create(params);
    }
    public static void main(String[] args) throws TwilioRestException {
        //sendMessage("+84888160699", "conto");
    }
}
