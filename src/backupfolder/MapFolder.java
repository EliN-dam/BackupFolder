/**
 * https://www.baeldung.com/java-path
 */
package backupfolder;

import java.io.File;
import java.util.ArrayList;


/**
 * @author zelda
 */
public class MapFolder {
    
    private File path;
    private ArrayList<String> files = new ArrayList();
    private long size;
    
    public MapFolder(String path){
        this.path = new File(path);
        this.files.add(this.path.getPath());
        this.size = 0;
        this.map(this.path);      
    }
    
    public String getPath(){
        return this.path.getAbsolutePath();
    }
    
    public String[] getMap(){
        return this.files.toArray(new String[this.files.size()]);
    }

    public long getSize() {
        return size;
    }
       
    public void map(File path){
        if (path.exists()){
            File[] temp = path.listFiles();
            for(File file : temp){
                this.files.add(file.getPath());
                this.size += file.length();
                if (file.isDirectory()){
                    this.map(file);
                }
            }
        }
    }
}