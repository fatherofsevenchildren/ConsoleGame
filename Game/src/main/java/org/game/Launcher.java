package org.game;

import java.util.Scanner;

public class Launcher {
    private Profile profile;
    private final GameMap gameMap;

    public Launcher(String profile, int enemiesCount, int wallsCount, int size) {
        this.profile = profile.equals("development") ? Profile.DEVELOP : Profile.PRODUCTION;
        this.gameMap = new GameMap(this.profile, enemiesCount, wallsCount, size);
    }

    public final void launch() throws InterruptedException {
        gameMap.printMap();
        runGameMap();
    }

    private void runGameMap() {
        Scanner scanner = new Scanner(System.in);
        String inputString = "";
        while (true) {
            inputString = scanner.nextLine();
            int gameStatus = gameMap.changeMap(inputString, scanner);
            if (gameStatus == 1) {
                gameMap.printMap();
                System.out.println("You loose!\nGame over");
                break;
            }
            if (gameStatus == 2) {
                gameMap.printMap();
                System.out.println("You Win!\nGame over");
                break;
            }
            gameMap.printMap();
            if(inputString.equals("9")){
                System.out.println("You loose!\nGame over");
                break;
            }
        }
    }
}

