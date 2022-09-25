import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FileService {

    private static String DIRECTORY;
    private File folder;

    public FileService() {
        DIRECTORY = System.getProperty("user.dir");
        folder = new File(DIRECTORY+"/files");
        if(!folder.exists())
            folder.mkdirs();
        System.out.println("DIRECTORY : "+ folder.getAbsolutePath());
    }

    public String getBasePath(){
        return folder.getPath();
    }

    public boolean addfile(String fileName) throws IOException {
        File filePath = new File(folder + "/" + fileName);
        String[] files = folder.list();
        for(String file: files) {
            if (fileName.equalsIgnoreCase(file)) {
                return false;
            }
        }
        filePath.createNewFile();
        return true;
    }

    public String searchfile(String fileName) {
        String[] files = folder.list();
        for(String file: files) {
            if (fileName.equals(file)) {
                return fileName;
            }
        }
        return null;
    }

    public boolean deletefile(String fileName) {
        File filePath = new File (folder + "/" + fileName);
        String[] files = folder.list();
        for(String file: files) {
            if(fileName.equals(file) && filePath.delete()) {
                return true;
            }
        }
        return false;
    }

    public  String[]  getAllFiles() {
       String[] files = folder.list();
       Arrays.sort(files);
       return files;
    }
}
