package org.game;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.List;

@Parameters(separators = "=")
public class ArgsRider {

    public ArgsRider(String[] args) {
        JCommander jCommander = JCommander.newBuilder().addObject(this).build();
        jCommander.parse(args);
        if (!checkValidParam()) throw new IllegalParametersException("Invalid parameters");
    }

    @Parameter(names = "--enemiesCount", description = "Number of enemies")
    private int enemiesCount;

    @Parameter(names = "--wallsCount", description = "Number of walls")
    private int wallsCount;

    @Parameter(names = "--size", description = "Size of the game field")
    private int size;

    @Parameter(names = "--profile", description = "Game profile")
    private String profile;

    @Parameter(description = "Main parameters")
    private List<String> mainParams;

    public int getEnemiesCount() {
        return enemiesCount;
    }

    public int getWallsCount() {
        return wallsCount;
    }

    public int getSize() {
        return size;
    }

    public String getProfile() {
        return profile;
    }

    public boolean checkValidParam() {
        return (size * size - (wallsCount + enemiesCount)) > 10;
    }

}
