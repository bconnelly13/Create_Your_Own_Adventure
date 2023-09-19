import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // Declare and initialize variables
        Scanner getInput = new Scanner(System.in);
        int strikes = 0;
        int balls = 0;
        int outs = 0;
        int runs = 0;
        int hitOption;
        boolean Runner1 = false;
        boolean Runner2 = false;
        boolean Runner3 = false;
        boolean rightGuess;
        boolean swing = false;
        boolean keepPlaying = true;
        String hitSpot;
        String continueInput;


        // Intro, Rules, Explanation
        System.out.println("\nCongratulations! You've made it to game 7 of the World Series. Your team is down by 2 runs in the bottom of the ninth inning. Make the right decisions to win the game and go down in history!");
        System.out.println("You will have to first decide whether to swing, either enter {y} or {n}, invalid answers default to no swing. If you swing you will have to guess the pitch, either enter {fastball}, {changeup}, or {curveball}. Invalid answers will automatically be wrong. Get 3 runs before 3 outs to win!\n");
        System.out.println("Batter up!");

        // Runs until user says to stop at end of game
        while (keepPlaying == true) {

            // Keeps game going until user wins or loses
            while (runs < 3 && outs < 3) {
                System.out.println("\nNew at-bat");
                
                // Keeps at-bat going until user makes contact
                while (swing != true) {

                    // Prints scoreboard before each pitch
                    System.out.println("\nBalls: " + balls + "\tStrikes: " + strikes + "\t\tOuts: " + outs + "\t\tRuns Scored: " + runs);

                    // Batter decides whether to swing
                    System.out.print("Here comes the pitch! Do you swing? (y/n) - ");
                    String batterAction = getInput.nextLine();

                    // Batter swung
                    if (batterAction.equals("y")) {
                        swing = true;
                        // Calls method for user to guess pitch and determines if guess is correct
                        rightGuess = guessPitch(getInput);
                        // Says they missed if pitch was not correctly guessed
                        if (rightGuess != true) {
                            System.out.println("Swing and a miss! The pitcher fooled you on that one.");
                            strikes += 1;
                            swing = false;
                        }
                    } else {    // Batter did not swing
                        // Random num generator to determine if pitch is ball or strike
                        int pitchSpot = (int) (Math.random() * 3 + 1);
                        if (pitchSpot == 3) {   // Ball
                            balls += 1;
                            System.out.println("That pitch was a ball. Good eye!");
                        } else {    // Strike
                            strikes += 1;
                            System.out.println("Oooh, that pitch was a strike. You should've swung!");
                        }
                    }
                    // Checks for strikeout, if so count resets
                    if (strikes == 3) {
                        System.out.println("That's strike 3! The batter is out. Better luck next at-bat.");
                        outs += 1;
                        strikes = 0;
                        balls = 0;
                        break;
                    }
                    // Checks for walk, if so count resets and runners advance 1 base
                    else if (balls == 4) {
                        System.out.println("That's ball 4, which is a walk. The batter advances to first base. Maybe they need to think about getting a better pitcher!");

                        // Advances all previous runners
                        if (Runner3) {
                            runs += 1;
                            Runner3 = false;
                        }
                        if (Runner2) {
                            Runner3 = true;
                            Runner2 = false;
                        }
                        if (Runner1) {
                            Runner2 = true;
                            Runner1 = false;
                        }
                        // Puts batter on first base
                        Runner1 = true;

                        // Resets count
                        strikes = 0;
                        balls = 0;
                        break;
                    }
                }// End of at-bat

                // Goes to next iteration of game loop (ignores hitting if strikeout or walk)
                if (swing == false) {
                    continue;
                }

                // Resets variables for next at-bat
                swing = false;
                balls = 0;
                strikes = 0;

                // Determines and prints location of hit, whether it was an out, and number of bases (if a hit)
                hitOption = (int) (Math.random() * 10 + 1);
                if (hitOption <= 3) {   // Single

                    // Advances all previous runners
                    if (Runner3) {
                        runs += 1;
                        Runner3 = false;
                    }
                    if (Runner2) {
                        Runner3 = true;
                        Runner2 = false;
                    }
                    if (Runner1) {
                        Runner2 = true;
                        Runner1 = false;
                    }

                    // Puts batter on first
                    Runner1 = true;

                    // Multiple options for where ball is hit
                    if (hitOption == 1) {
                        hitSpot = "right";
                    } else if (hitOption == 2) {
                        hitSpot = "center";
                    } else {
                        hitSpot = "left";
                    }
                    System.out.println("A nice piece of hitting there as the ball is lined into " + hitSpot + " field. The batter rounds first and will be on with a single.");
                } else if (hitOption <= 5) {    // Double

                    // Advances all previous runners
                    if (Runner3) {
                        runs += 1;
                        Runner3 = false;
                    }
                    if (Runner2) {
                        runs += 1;
                        Runner2 = false;
                    }
                    if (Runner1) {
                        Runner3 = true;
                        Runner1 = false;
                    }

                    // Advances batter to second
                    Runner2 = true;
                    // Multiple options for where ball is hit
                    if (hitOption == 4) {
                        hitSpot = "left center";
                    } else {
                        hitSpot = "right center";
                    }
                    System.out.println("And that ball is driven deep into " + hitSpot + ", and the fielder will not be able to cut it off. The batter jogs into second base and is aboard with a double.");
                } else if (hitOption == 6) {    // Triple

                    // Advances all previous runners
                    if (Runner3) {
                        runs += 1;
                        Runner3 = false;
                    }
                    if (Runner2) {
                        runs += 1;
                        Runner2 = false;
                    }
                    if (Runner1) {
                        runs += 1;
                        Runner1 = false;
                    }

                    // Advances batter to third
                    Runner3 = true;

                    // Prints out hit explanation
                    hitSpot = "right field line";
                    System.out.println("That ball is rocketed over the first baseman's head and is headed down the " + hitSpot + ", it's going to roll all the way to the wall. The batter takes an aggressive turn around first base, and yes he's rounding second and headed for third. Here comes the throw and he's .... SAFE!!!");
                } else if (hitOption == 7) {    // Home run

                    // Scores run for all previous runners
                    if (Runner3) {
                        runs += 1;
                        Runner3 = false;
                    }
                    if (Runner2) {
                        runs += 1;
                        Runner2 = false;
                    }
                    if (Runner1) {
                        runs += 1;
                        Runner1 = false;
                    }

                    // Scores run for batter
                    runs += 1;

                    // Prints out hit explanation
                    hitSpot = "left center";
                    System.out.println("Oh my, and that ball is skied into deep " + hitSpot + " field. The center fielder is sprinting back but I think he might run out of room! Indeed he does, and that is a HOME RUN!!!");
                } else if (hitOption <= 9) {    // Ground out
                    outs += 1;
                    // Multiple options for where ball is hit
                    if (hitOption == 8) {
                        hitSpot = "shortstop";
                    } else {
                        hitSpot = "second baseman";
                    }
                    // Prints out hit explanation
                    System.out.println("Not great contact on that hit, as it's grounded to the " + hitSpot + ". He fields it cleanly and makes the throw over to first for the out. ");
                } else if (hitOption == 10) {    // Flyout
                    outs += 1;

                    // Prints out hit explanation
                    hitSpot = "center";
                    System.out.println("That ball is popped into the air into " + hitSpot + " field. It looks like the center fielder will be able to get there, and yes he does get there to catch the ball for an out.");
                }
                // Prints runs and location of runners after contact is made (hit, groundout, flyout)
                System.out.println("\nTotal Runs Scored: " + runs + "\nBase Runners: \n  On First - " + Runner1 + "\n  On Second - " + Runner2 + "\n  On Third - " + Runner3);


            }// End of game

            // Ending message after game ends
            if (outs == 3) {    // Ends in loss
                System.out.println("\nThat's three outs, you lost the game. Better luck next time!");
            } else {    // Ends in win
                System.out.println("\nThe winning run comes in to score! Congratulations, you just won the World Series!!! Your name will forever go down in history and will not be forgotten. Great job!!!.");
            }

            // Gets user input on whether to play again
            System.out.print("\nWould you like play again? (y/n) - ");
            continueInput = getInput.nextLine();
            // Stops loop of game if user says no
            if(continueInput.equals("n")){
                keepPlaying = false;
            }
            // Resets variables for user to play again
            else if(continueInput.equals("y")){
                strikes = 0;
                balls = 0;
                outs = 0;
                runs = 0;
                Runner1 = false;
                Runner2 = false;
                Runner3 = false;
                System.out.println("\n\nNew Game");
            }

        }//End of keepPlaying
    }// End of main method

    // User guesses pitch, returns boolean of whether pitch was correctly guessed
    public static boolean guessPitch(Scanner getInput){
        String pitch;
        // User guesses pitch
        System.out.print("What do you think the pitch will be? - ");
        String pitchGuess = getInput.nextLine();

        // Random num generator to determine pitch
        int pitchNum = (int)(Math.random()*10+1);
        if (pitchNum <= 5){
            pitch = "fastball";
        }
        else if (pitchNum <=8){
            pitch = "changeup";
        }
        else{
            pitch = "curveball";
        }

        return pitchGuess.equals(pitch);

    }


}
