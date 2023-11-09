package weatherwear.services;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weatherwear.services.ipservice.IPService;
import weatherwear.services.ipservice.IPServiceAPI;

import java.io.IOException;
import java.net.http.HttpTimeoutException;
import java.time.LocalDate;

public class IPServicesTest {
    public IPService ipService;

    @BeforeEach
    public void setup(){
        ipService = new IPServiceAPI();
    }

    @Test
    public void testCatchIp(){
        //setup
        JSONObject result;

        //exercise
        try {
            result = ipService.catchIp();
        } catch (HttpTimeoutException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(result.toString());
        //
        Assertions.assertEquals("success", result.getString("status"));
    }

    @Test
    public void testCatchIpBackup(){
        //setup
        JSONObject result;

        //exercise
        try {
            result = ipService.catchIpBackup();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(result);
        //
        Assertions.assertEquals("success", result.getString("status"));
    }
}

