package edu.guilford;

public class Main {
    public static void main(String[] args) {

        Tile testTile = new Tile('b', 1);
        System.out.println(testTile);

        TileSet tileSet = new TileSet(TileSet.Language.ENGLISH);

        tileSet.addTile('a');
        tileSet.addTile('b');
        tileSet.removeTile('a');
        System.out.println(tileSet.getTileCount('a'));

    }
}