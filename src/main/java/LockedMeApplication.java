public class LockedMeApplication {

    public static void main(String[] args) {

        System.out.println(ApplicationMessages.OPENING_PROMPT);
        FileService menu = new FileService();
        menu.openMainMenu();
    }
}
