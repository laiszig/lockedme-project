import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class FileService {

    static String DIRECTORY;
    File folderName;

    public FileService() {
        DIRECTORY = System.getProperty("user.dir");
        folderName = new File(DIRECTORY+"/files");
        if(!folderName.exists())
            folderName.mkdirs();
        System.out.println("DIRECTORY : "+ folderName.getAbsolutePath());
    }

    public void openMainMenu() {
        System.out.println(ApplicationMessages.MAIN_MENU_PROMPT);

        try {
            Scanner scanner = new Scanner(System.in);
            int menuOption = scanner.nextInt();
            switch (menuOption) {
                case 1: {
                    openAllFiles();
                    openMainMenu();
                }
                case 2: {
                    openSecondMenu();
                }
                case 3: {
                    System.out.println("Thank you for using LockedMe.com");
                    System.exit(0);
                }
                default:
                    openMainMenu();
            }
        } catch (Exception exception) {
            System.out.println("Plese type 1, 2 or 3.");
            openMainMenu();
        }
    }

    public void openSecondMenu() {
        System.out.println(ApplicationMessages.SECOND_MENU_PROMPT);

        try {
            Scanner scanner = new Scanner(System.in);
            char[] input = scanner.nextLine().toLowerCase().trim().toCharArray();
            char menuOption = input[0];
            switch (menuOption) {
                case 'a' : {
                    System.out.println("Adding new file... Please write the file name: ");
                    String fileName = scanner.next().trim().toLowerCase();
                    addfile(fileName);
                    break;
                }
                case 'b' : {
                    System.out.println("Deleting file... Please write the file name: ");
                    String fileName = scanner.next().trim().toLowerCase();
                    deletefile(fileName);
                    break;
                }
                case 'c' : {
                    System.out.println("Searching for file... Please write the file name: ");
                    String fileName = scanner.next().trim();
                    searchfile(fileName);
                    break;
                }
                case 'd' : {
                    System.out.println("Back to Main Menu");
                    openMainMenu();
                    break;
                }
            }
        }
        catch (Exception exception) {
            System.out.println("Please type a, b, c or d");
            openSecondMenu();
        }
    }

    private void openAllFiles() {
        if(folderName.list().length==0)
            System.out.println("There are no files here");
        else {
            String[] list = folderName.list();
            System.out.println("These are the files in " + folderName + " :");
            Arrays.sort(list);
            for (String str:list) {
                System.out.println(str);
            }
        }
    }

    public void addfile(String fileName) throws IOException {
        File filePath = new File(folderName + "/" + fileName);
        String[] list = folderName.list();
        for(String file: list) {
            if (fileName.equalsIgnoreCase(file)) {
                System.out.println("File " + fileName + " already exists at " + folderName);
                return;
            }
        }
        filePath.createNewFile();
        System.out.println("File " + fileName+ " added to " + folderName);
    }

    public void deletefile(String fileName) {
        File filePath = new File (folderName + "/" + fileName);
        String[] list = folderName.list();
        for(String file: list) {
            if(fileName.equals(file) && filePath.delete()) {
                System.out.println("File " + fileName + " deleted from " + folderName);
                return;
            }
        }
    }

    public void searchfile(String fileName) {
        String[] list = folderName.list();
        for(String file: list) {
            if (fileName.equals(file)) {
                System.out.println("File " + fileName + " is located at "+ folderName);
                return;
            }
        }
        System.out.println("File not found");

    }
}
