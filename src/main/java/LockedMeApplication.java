public class LockedMeApplication {

    public static void main(String[] args) {

        System.out.println(ApplicationMessages.OPENING_PROMPT);
        LockedMenu menu = new LockedMenu();
        menu.openMainMenu();
    }
}
