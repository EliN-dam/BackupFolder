/**
 * https://www.baeldung.com/java-copy-file
 */
package backupfolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * @author zelda
 */
public class CopyFolder {
    
    private Path source;
    private Path destination;
    
    public CopyFolder(String[] files, String newPath){
        if (this.check(newPath)) {
            this.source = Paths.get(files[0]);
            this.destination = Paths.get(newPath).resolve(source.getFileName());
            this.copy(files);
        }
    }
    
    public void copy(String[] files){
        try {
            for (String file : files){
                File temp = new File(file);
                Path in = Paths.get(file);
                Path target = this.destination.resolve(this.source.relativize(in));
                if (temp.isDirectory()){
                    Files.createDirectories(target);
                } else {
                    Files.copy(in, target);
                }
            }
        } catch(IOException e){
            this.undo(files);
            System.out.println("Ha ocurrido un error: " + e.getMessage() + "\nSe han restaurado los cambios.");
        }
    }
    
    public void undo(String[] files){
        try {
            if (Files.exists(this.destination)){
                for (int i = files.length - 1; i >= 0; i--){
                    this.deleteFile(files[i]);
                }
            }   
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public boolean check(String newPath){
        Path dest = Paths.get(newPath);
        if (Files.exists(dest)){
            if (Files.isWritable(dest)){
                return true;
            } else {
                System.out.println("No tiene permisos para escribir en la ruta de destino.");
            }
        } else {
            System.out.println("La ruta de destino no existe.");
        }
        return false;
    }
    
    public void deleteFile(String path) throws IOException {
        Path target = this.destination.resolve(this.source.relativize(Paths.get(path)));
        if (Files.exists(target))
            Files.delete(target);
    }
}