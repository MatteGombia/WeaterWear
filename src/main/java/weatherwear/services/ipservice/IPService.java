package weatherwear.services.ipservice;

import org.json.JSONObject;

import java.io.IOException;

public interface IPService {
    public JSONObject catchIp() throws IOException, InterruptedException, RuntimeException;
    public JSONObject catchIpBackup() throws InterruptedException;
}
