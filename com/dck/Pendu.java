package com.dck;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Pendu {
  private String mWord;
  private List<String> wordGuessed = new ArrayList<String>();
  private int miss = 0;
  private int MAX_MISS = 5;
  private Scanner sc = new Scanner(System.in);
  private List<String> mWords = new ArrayList<String>();

  public Pendu() {
    Random rand = new Random();
    String line = null;

    System.out.printf("%n Chargement des mots provenant de words.txt... %n %n");

    try {
      BufferedReader bufferedReader = new BufferedReader(new FileReader("words.txt"));

      while((line = bufferedReader.readLine()) != null) {
        mWords.add(line);
      }
    } catch(IOException ioe) {
      System.out.printf("%n /!\\ Impossible de lire words.txt %n");
    } finally {
      int randomIndex = rand.nextInt(mWords.size()) + 1;
      
      mWord = mWords.get(randomIndex).toLowerCase().trim();
    }
  }

  private String showProgress() {
    String progress = "";

    for (char letter: mWord.toCharArray()) {
      if (wordGuessed.contains(String.valueOf(letter))) {
        progress += letter;
      } else {
        progress += "-";
      }
    }

    return progress;
  }

  private boolean checkMatching (String letter)  {
    boolean isLetterFound = mWord.contains(letter);
    
    if (!isLetterFound) {
      miss++;
    }

    wordGuessed.add(letter);

    return isLetterFound;
  }

   public void start() {
    System.out.println("/ JEU DU PENDU /");

    do {
      System.out.printf("%nMot à deviner : %s %n--> ", showProgress());
      String input = sc.nextLine();
      
      if (wordGuessed.contains(input)) {
        System.out.printf("Vous avez déjà essayé la lettre : %s %n %n", input);
      } else {
        if (checkMatching(input)) {
          System.out.printf("Touché !%n");
        } else {
          System.out.printf("Loupé ! Coups restants : %d %n", MAX_MISS - miss);
        }
      }

    } while(miss < MAX_MISS && !showProgress().equals(mWord));

    if (miss == MAX_MISS) {
      System.out.printf("%nPERDU ! Le mot mystère était : %s %n", mWord);
    } else {
      System.out.printf("%nBravo ! Vous avez gagné :) %n");
    }
  }
}