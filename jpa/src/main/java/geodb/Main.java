package geodb;

import geodb.repository.OceanRepository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        OceanRepository ocean1 = new OceanRepository();

        ocean1.insertToTable();
        ocean1.displayTable();

    }
}
