package simplewolapp.model;

import java.io.Serializable;
import java.util.StringTokenizer;

public class Host implements Serializable{
    public String name;
    public byte[] mac;
    public byte[] ip;
    
    public Host(String name, byte[] mac, byte[] ip){
        this.name = name;
        this.mac = mac;
        this.ip = ip;
    }
    
    public Host(String name, String mac, String ip) throws MacException, IPException {
        StringTokenizer ipTokenizer = new StringTokenizer(ip.trim(), ".");
        byte[] ipAddress = null;
        if (ipTokenizer.countTokens() == 4) {
            try {
                byte[] ipAddressTmp = new byte[4];
                for (int i = 0; i < 4; i++) {
                    ipAddressTmp[i] = (byte) Integer.parseInt(ipTokenizer.nextToken());
                }
                ipAddress = ipAddressTmp;
            } catch (NumberFormatException e) {
                throw new IPException();
            }
        }
        StringTokenizer tokenizer = new StringTokenizer(mac.trim(),":");
        byte[] macAddress = new byte[6];
        if(tokenizer.countTokens() == 6){
            try {
                for (int i = 0; i < 6; i++) {
                    macAddress[i] = (byte) Integer.parseInt(tokenizer.nextToken(), 16);
                }
            }catch (NumberFormatException e){
                throw new MacException();
            }
            this.name = name;
            this.mac = macAddress;
            this.ip = ipAddress;
        }else{
            throw new MacException();
        }
    }
    
    public String getHumanMac(){
        StringBuilder macString = new StringBuilder();
        for(byte b : mac){
            String c = Integer.toString( (b < 0 ? 256 + b : b), 16);
            macString.append(":" + (c.length() < 2 ? "0" + c : c).toUpperCase());
        }
        return macString.substring(1).toString();
    }
    
    public String toString(){
        return name;
    }

    public String getHumanIP() {
        String ret = "";
        if (ip != null) {
            StringBuilder ipString = new StringBuilder();
            for (byte b : ip) {
                String c = Integer.toString(b < 0 ? 256 + b : b);
                ipString.append("." + c);
            }
            ret = ipString.toString().substring(1);
        }
        return ret;
    }
}
