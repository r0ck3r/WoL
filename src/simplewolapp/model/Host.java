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
    
    public String toString(){
        return name;
    }
}
