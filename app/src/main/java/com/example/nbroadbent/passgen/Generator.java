package com.example.nbroadbent.passgen;

import java.util.Random;

class Generator {

    Random rand = new Random();

    private int length = 8;
    private int num = 2;
    private int special = 1;
    private double likeness = 0.3;
    private String pass = "";

    public Generator(int length) {
        this.length = length;
    }

    public Generator(int length, int num, int special) {
        this.length = length;
        this.num = num;
        this.special = special;
    }

    private void addNum() {
        int  n = rand.nextInt(9) + 1;

        System.out.println("Num: "+ n);
        pass += n;
    }

    private void addChar() {
        int  n = 32;

        // Check special character;
        if (true) {
            addLetter();
        }
        else {
            addSpecial();
        }
    }

    private void addLetter() {
        int  n = 32;

        // Remove spaces.
        while (n == 32 || n > 122 || n < 41) {
            n = rand.nextInt(90) + 65;
        }
        char c = (char) n;
        System.out.println("Char: "+ n + " " + c);
        pass += c;
    }

    private void addSpecial() {
        int  n = 32;

        // Remove spaces.
        while (n > 47 || n < 33) {
            n = rand.nextInt(48) + 32;
        }
        char c = (char) n;
        System.out.println("Special: "+ n + " " + c);
        pass += c;
    }

    protected String generate() {
        int n;
        int currNum = 0;
        int fract = (int) (100*likeness);
        int likeChar = (int) (length*likeness);
        int currLike = 0;
        int common = 32;

        while (common == 32 || common > 122 || common < 41) {
            common = rand.nextInt(100) + 33;
        }

        System.out.println("Common: "+ common);

        for (int i = 0; i < length; i++) {
            // Check if we need more numbers.
            if (currNum < num) {
                // Check if the remaining number of char needs to be numbers.
                if ((length - (i + 1)) <= (num - currNum)){
                    addNum();
                    currNum++;
                } else {
                    // Choose a number or a character
                    n = rand.nextInt(100) + 1;
                    if (n % fract == 0) {
                        addNum();
                        currNum++;
                    } else {
                        // Check if it should be common.
                        n = rand.nextInt(100) + 1;
                        if ((length - (i+1) - num <= (likeChar - currLike))) {
                            pass += (char) common;
                            currLike++;
                        } else {
                            addChar();
                        }
                    }
                }
            }
            else {
                // Pick a character.
                addChar();
            }
        }

        return pass;
    }
}
