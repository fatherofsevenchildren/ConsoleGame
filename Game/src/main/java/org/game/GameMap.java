package org.game;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi.Attribute;
import com.diogonunes.jcdp.color.api.Ansi.BColor;
import com.diogonunes.jcdp.color.api.Ansi.FColor;
import org.GameObjects.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class GameMap {

    private final ArrayList<GameObject> gameObjects = new ArrayList<>();
    private final Stack<Position> positionStack = new Stack<>();
    private final Profile profile;
    private final int size;
    private final int enemiesCount;
    private final int wallsCount;


    public GameMap(Profile profile, int enemiesCount, int wallsCount, int size) {
        this.profile = profile;
        this.enemiesCount = enemiesCount;
        this.wallsCount = wallsCount;
        this.size = size;
        generateStackOfPositions(this.positionStack, size);
        initGameMap();
    }

    private void generateStackOfPositions(Stack<Position> positionStack, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                positionStack.push(new Position(i, j));
            }
        }
        Collections.shuffle(positionStack);
    }

    private Position getRandomPosition() {
        if (!positionStack.empty()) return positionStack.pop();
        else {
            return new Position(0, 0);
        }
    }

    private void createHero() {
        Hero hero = new Hero(getRandomPosition());
        gameObjects.add(hero);
    }

    private void createGoal() {
        Goal goal = new Goal(getRandomPosition());
        gameObjects.add(goal);
    }

    private void createEnemies() {
        for (int i = 0; i < enemiesCount; i++) {
            Enemy enemy = new Enemy(getRandomPosition());
            gameObjects.add(enemy);
        }
    }

    private void createObstacles() {
        for (int i = 0; i < wallsCount; i++) {
            Obstacle obstacle = new Obstacle(getRandomPosition());
            gameObjects.add(obstacle);
        }
    }

    private void createEmptyObjects() {
        while (!positionStack.empty()) {
            EmptyObject emptyObject = new EmptyObject(getRandomPosition());
            gameObjects.add(emptyObject);
        }
    }

    public void initGameMap() {
        createHero();
        createGoal();
        createEnemies();
        createObstacles();
        createEmptyObjects();
    }

    public static Direction mapInputToDirection(char character) {
        switch (character) {
            case 'W':
            case 'w':
            case '8':
                return Direction.UP;
            case 'S':
            case 's':
            case '2':
                return Direction.DOWN;
            case 'A':
            case 'a':
            case '4':
                return Direction.LEFT;
            case 'D':
            case 'd':
            case '6':
                return Direction.RIGHT;
            default:
                return null;
        }
    }


    /**
     * Обработка входа командной строки
     *
     * @param input Вход командной строки
     * @return Результат обработки:
     * 0 - игра продолжается
     * 1 - проигрыш
     * 2 - победа
     */
    public int changeMap(String input, Scanner scanner) {
        int isGameOverStatus = 0;
        boolean isHeroMadeMove = false;
        char character = ' ';
        if (!input.isEmpty()) character = input.charAt(0);
        Direction direction = mapInputToDirection(character);
        Hero hero = null;

        for (GameObject gameObject : gameObjects) {
            if (gameObject.getName().equals("Hero")) {
                hero = (Hero) gameObject;
                break;
            }
        }

        if (hero != null) {
            if(direction != null)
                hero.move(direction, this);
            isHeroMadeMove = true;
        }

        for (GameObject gameObject : gameObjects) {
            if (gameObject.getName().equals("Enemy")) {
                Enemy enemy = (Enemy) gameObject;
                
                if (profile == Profile.DEVELOP && isHeroMadeMove) {
                    if(direction != null)
                        printMap();
                    System.out.println("Press 8 to confirm enemy move.");
                    while (!scanner.nextLine().equals("8")) {
                        System.out.println("Invalid input. Press 8 to confirm enemy move.");
                    }
                }
                
                enemy.move(direction, this);
            }
        }

        for (GameObject gameObject : gameObjects) {
            if (gameObject.getName().equals("Goal")) {
                Goal goal = (Goal) gameObject;
                if (hero != null && goal.getPosition().equals(hero.getPosition())) {
                    isGameOverStatus = 2;
                }
                break;
            }
        }

        if (hero != null && !hero.isAlive()) {
            isGameOverStatus = 1;
        }

        return isGameOverStatus;
    }


    public void printMap() {

        if (profile == Profile.PRODUCTION) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        try {
            ColoredPrinter cp = new ColoredPrinter.Builder(1, false).foreground(FColor.WHITE).background(BColor.BLUE).build();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    Position currentPosition = new Position(i, j);
                    GameObject gameObject = getObjectByPosition(currentPosition);

                    char character = ' ';
                    Attribute attribute = Attribute.LIGHT;
                    FColor fColor = FColor.BLACK;
                    BColor bColor = BColor.YELLOW;

                    if (gameObject != null) {
                        character = gameObject.getChar();
                        fColor = gameObject.getfColor();
                        bColor = gameObject.getbColor();

                        if (gameObject instanceof Hero) {
                            Hero hero = (Hero) gameObject;
                            if (!hero.isAlive()) {
                                character = 'X';
                                fColor = FColor.BLACK;
                                bColor = BColor.RED;
                            }
                        }
                    }

                    cp.print(character, attribute, fColor, bColor);
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

   
    public GameObject getObjectByPosition(Position position) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.getPosition().equals(position)) {
                return gameObject;
            }
        }
        return null;
    }

    public GameObject getObjectByName(String name) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.getName().equals(name)) {
                return gameObject;
            }
        }
        return null;
    }

    public int getMapSize() {
        return size;
    }
}


