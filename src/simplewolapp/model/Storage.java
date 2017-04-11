package simplewolapp.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage implements Serializable {
    public Host[] hosts;
    
    public Storage(){
        if(!load()) {
            hosts = new Host[2];
            hosts[0] = new Host("[No selection]", new byte[0]);
        }
    }
    
    public String getHomedir(){
        String homedir = System.getProperty("user.home");
        boolean writable = Files.isWritable(Paths.get(homedir));
        return writable ? homedir : null;
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
        String hd = getHomedir();
        if(hd != null) {
            try (FileOutputStream fs = new FileOutputStream(hd + "/.config/wolhosts.dat")) {
                ObjectOutputStream os = new ObjectOutputStream(fs);
                os.writeObject(this);

            } catch (FileNotFoundException e){
                try {
                    Files.createDirectories(Paths.get(hd + "/.config/"));
                    save();
                } catch (IOException e1) {
                }
            } catch (IOException e) {
                System.out.println("Can't save options");
            }
        }
    }
    
    private boolean load(){
        String hd = getHomedir();
        boolean ret = false;
        if(hd != null) {
            try (FileInputStream is = new FileInputStream(hd + "/.config/wolhosts.dat")) {
                ObjectInputStream os = new ObjectInputStream(is);
                Storage storage = (Storage) os.readObject();
                this.hosts = storage.hosts;
                ret = true;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Can't load options");
            }
        }
        return ret;
    }
}
