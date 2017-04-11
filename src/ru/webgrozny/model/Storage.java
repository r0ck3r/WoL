package ru.webgrozny.model;

import java.io.*;

public class Storage implements Serializable {
    public Host[] hosts;
    
    public Storage(){
        if(!load()) {
            hosts = new Host[2];
            hosts[0] = new Host("[No selection]", new byte[0]);
        }
    }
    
    public void addHost(Host host){
        int len = hosts.length;
        Host[] newHost = new Host[len + 1];
        for(int i = 0; i < len; i++){
            newHost[i] = hosts[i];
        }
        newHost[len] = host;
        hosts = newHost;
        save();
    }
    
    public void editHost(Host host, int index){
        if(index < hosts.length) {
            hosts[index] = host;
            save();
        }
    }
    
    public void remove(int index){
        int len = hosts.length;
        if(index < len) {
            Host[] newHost = new Host[len - 1];
            int j = 0;
            for(int i = 0; i < len; i++){
                if(i != index) {
                    newHost[j++] = hosts[i];
                }
            }
            hosts = newHost;
            save();
        }
    }
    
    private void save(){
        try(FileOutputStream fs = new FileOutputStream("hosts.dat")){
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(this);
        }catch (IOException e){
            System.out.println("Can't save options");
        }
    }
    
    private boolean load(){
        boolean ret = false;
        try(FileInputStream is = new FileInputStream("hosts.dat")){
            ObjectInputStream os = new ObjectInputStream(is);
            Storage storage = (Storage) os.readObject();
            this.hosts = storage.hosts;
            ret = true;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Can't load options");
        }
        return ret;
    }
}
