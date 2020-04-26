/**
 * https://stackoverflow.com/questions/29076439/java-8-copy-directory-recursively
 */
package backupfolder;

import java.io.File;

/**
 * @author zelda
 */
public class BackupFolder {

    private String path;
    private String newPath;
    private String[] files;
    
    public BackupFolder(String path, String newPath) {
        this.path = this.formatPath(path);
        this.newPath = this.formatPath(newPath);
        this.mapPath(this.path);
        this.Backup();
    }
    
    public void mapPath(String path){
        this.files = new MapFolder(path).getMap();
    }
    
    public void Backup(){
        new CopyFolder(this.files, this.newPath);
    }
    
    public static String formatPath(String path){
        String[] temp = path.split("\\/");
        String formatted = "";
        for (int i = 0; i < temp.length; i++){
            formatted += temp[i] + File.separator;
        }
        formatted += File.separator;
        return formatted;
    }

    public String[] getFiles() {
        return files;
    }
    
    public static void main(String[] args) {
        new BackupFolder("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\MiDir", "G:\\Pendrive\\DAM");
    }   
}