import java.util.Scanner;

public class LockedMenu {

    private FileService fileService = new FileService();

    public void openMainMenu() {
        System.out.println(ApplicationMessages.MAIN_MENU_PROMPT);

        try {
            Scanner scanner = new Scanner(System.in);
            int menuOption = scanner.nextInt();
            switch (menuOption) {
                case 1: {
                    String[] files = fileService.getAllFiles();

                    if(files.length==0)
                        System.out.println("There are no files here");
                    else {
                        System.out.println("These are the files in " + files + " :");

                        for (String file : files) {
                            System.out.println(file);
                        }
                    }
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
                    boolean fileCreated = fileService.addfile(fileName);
                    if(fileCreated) {
                        System.out.println("File " + fileName+ " added to " + fileService.getBasePath());
                    } else {
                        System.out.println("File " + fileName + " already exists at " + fileService.getBasePath());
                    }
                    break;
                }
                case 'b' : {
                    System.out.println("Deleting file... Please write the file name: ");
                    String fileName = scanner.next().trim().toLowerCase();
                    var deleted = fileService.deletefile(fileName);
                    if(deleted) {
                        System.out.println("File " + fileName + " deleted from " + fileService.getBasePath());
                    } else {
                        System.out.println("This file does not exist. Try again.");
                    }
                    break;
                }
                case 'c' : {
                    System.out.println("Searching for file... Please write the file name: ");
                    String fileName = scanner.next().trim();
                    String fileNameFound = fileService.searchfile(fileName);
                    if(fileNameFound == null) {
                        System.out.println("File not found");
                    } else {
                        System.out.println("File " + fileNameFound + " is located at "+ fileService.getBasePath());
                    }
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
        openSecondMenu();
    }
}
