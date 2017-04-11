package simplewolapp.model;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class WakeOnLan {

    public NetworkInterface[] getInterfaces(){
        NetworkInterface[] ret = null;
        try {
            Enumeration<NetworkInterface> ints = NetworkInterface.getNetworkInterfaces();
            ArrayList<NetworkInterface> listNet = new ArrayList<>();
            int cnt = 0;
            while(ints.hasMoreElements()){
                NetworkInterface netInt = ints.nextElement();
                Enumeration<InetAddress> addresses = netInt.getInetAddresses();
                if(!netInt.isLoopback() && addresses.hasMoreElements()) {
                    while(addresses.hasMoreElements()){
                        InetAddress ia = addresses.nextElement();
                        if(ia.getAddress().length == 4){
                            listNet.add(netInt);
                            cnt++;
                            continue;
                        }
                    }
                }
            }
            ret = new NetworkInterface[cnt];
            listNet.toArray(ret);
        } catch (SocketException e) {
            System.out.println("Can't get available network interfaces!");
        }
        return ret;
    }
    
    public void wake(Host host, NetworkInterface[] interfaces){
        for(NetworkInterface networkInterface : interfaces){
            Enumeration<InetAddress> addressEnumeration = networkInterface.getInetAddresses();
            while(addressEnumeration.hasMoreElements()){
                InetAddress ia = addressEnumeration.nextElement();
                byte[] address = ia.getAddress();
                if(address.length == 4) {
                    address[3] = (byte) 255;
                    try {
                        InetAddress senderIFace = InetAddress.getByAddress(address);
                        DatagramSocket datagramSocket = new DatagramSocket();
                        datagramSocket.send(genPacket(host, senderIFace));
                        } catch (SocketException e) {
                            System.out.println("Can't create socket");
                    } catch (IOException e) {
                        System.out.println("Can't send magic packet");
                    }
                }
            }
        }
    }
    
    private DatagramPacket genPacket(Host host, InetAddress ia){
        byte[] buf = new byte[102];
        byte[] mac = host.mac;
        for(int i = 0; i < 6; i++){
            buf[i] = (byte) 0xFF;
        }
        int iter = 6;
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 6; j++){
                buf[iter++] = mac[j];
            }
        }
        DatagramPacket dp = new DatagramPacket(buf, 102, ia, 10000);
        return dp;
    }
    
    public static void main(String[] args){
        new WakeOnLan().wake(new Host("sd", new byte[2]), new NetworkInterface[2]);
    }
}
