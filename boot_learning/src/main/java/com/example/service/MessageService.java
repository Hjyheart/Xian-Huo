package com.example.service;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hongjiayong on 2016/12/22.
 */
@Service
public class MessageService {
    private TaobaoClient client;
    private AlibabaAliqinFcSmsNumSendRequest req;

    public MessageService(){
        client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23578004", "ad2593ab3d65d0d5e2518863ddd2b2b9");
        req = new AlibabaAliqinFcSmsNumSendRequest();
    }

    public void sendMessage(String name, String contact, String clubName, String location, Date date, String activity) throws ApiException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        req.setExtend( "" );
        req.setSmsType( "normal" );
        req.setSmsFreeSignName( "鲜活社团平台" );
        req.setSmsParamString( "{name:'" + name +"',clubName:'" + clubName + "',time:'" + dateString + "',location:'" + location +
                                "',activityName:'" + activity + "'}" );
        req.setRecNum( contact );
        req.setSmsTemplateCode( "SMS_35050366" );
        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

}
