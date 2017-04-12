package simplewolapp.model;

import java.io.Serializable;
import java.util.StringTokenizer;

public class Host implements Serializable{
    public String name;
    public byte[] mac;
    
    public Host(String name, byte[] mac){
        this.name = name;
        this.mac = mac;
    }
    
    public Host(String name, String mac) throws MacException{
        StringTokenizer tokenizer = new StringTokenizer(mac,":");
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
}
