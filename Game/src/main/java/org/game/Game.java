package org.game;

public class Game {
    public static void main(String[] args) {
        try {
            ArgsRider arguments = new ArgsRider(args);
            Launcher launcher = new Launcher(arguments.getProfile(), arguments.getEnemiesCount(), arguments.getWallsCount(), arguments.getSize());
            launcher.launch();
        } catch (IllegalParametersException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}