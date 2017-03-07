import java.awt.*;
import hsa.Console;
import java.util.Random;
import java.awt.image.*;
public class Cpt
{
    static Console d;

    static Console e;       // The output console
    static int[] [] map = new int [10] [10];
    static int n = 0;
    static int i, j, count = 0, row, col, choice, row2, col2, value;
    static int gamestate = 1;
    public static Image image1, image2, image3, image4;


    static class Observer1 implements ImageObserver
    {

        public boolean imageUpdate (Image img, int infoflags, int x, int y, int width, int height)
        {

            if (infoflags == 32)
            {
                d.drawImage (img, 0, 0, null);
            }
            return true;
        }
    }


    private static void displayImage (int x)  //for the gameover,menu,instructions, and victory screen.
    {
        Toolkit tk = Toolkit.getDefaultToolkit ();
        switch (x)
        {
            case 1:

                if (image1 == null)
                {

                    image1 = tk.getImage ("Menu.png");
                    tk.prepareImage (image1, -1, -1, new Observer1 ());
                }
                else
                {
                    d.drawImage (image1, 0, 0, null);
                }
                break;


            case 2:
                if (image2 == null)
                {

                    image2 = tk.getImage ("instructions.png");
                    tk.prepareImage (image2, -1, -1, new Observer1 ());
                }
                else
                {
                    d.drawImage (image2, 0, 0, null);
                }
                break;
            case 3:
                if (image3 == null)
                {

                    image3 = tk.getImage ("Gameover.png");
                    tk.prepareImage (image3, -1, -1, new Observer1 ());
                }
                else
                {
                    d.drawImage (image3, 0, 0, null);
                }
                break;
            case 4:

                if (image4 == null)
                {

                    image4 = tk.getImage ("Victoryscreech.png");
                    tk.prepareImage (image4, -1, -1, new Observer1 ());
                }
                else
                {
                    d.drawImage (image4, 0, 0, null);
                }
                break;

        }

    }





    public static void uncovered ()  //used for tiles with nothing on it and the backround to all other tiles
    {

        Font ok = new Font ("Tyler", 4, 35);
        d.setFont (ok);
        d.setColor (Color.black);
        d.drawRect ((j * 60) + 80, (i * 60) + 50, 50, 40);

        d.setColor (Color.gray);
        d.fillRect ((j * 60) + 80, (i * 60) + 50, 50, 40);



    }


    public static void bomb ()  //used to check if the player lost and to check the vaules for other tiles
    {

        uncovered ();
        d.setColor (Color.black);
        d.fillOval ((j * 60) + 90, (i * 60) + 65, 23, 23);
        d.drawArc ((j * 60) - 97, (i * 60) + 50, 200, 40, 0, 30);
        d.setColor (Color.red);
        d.fillOval ((j * 60) + 86, (i * 60) + 58, 4, 4);

    }


    public static void covered ()  //covered tile for every square at the start
    {
        int r = 0, g = 0, b = 0;
        Color me = new Color (192, 192, 192);
        d.setColor (Color.black);
        d.drawRect ((j * 60) + 80, (i * 60) + 50, 50, 40);

        d.setColor (me);
        d.fillRect ((j * 60) + 80, (i * 60) + 50, 50, 40);
    }


    public static void unOne ()  //Method for drawing the numbered tiles 1-8
    {
        uncovered ();
        d.setColor (Color.blue);

        d.drawString (" 1", (j * 60) + 85, (i * 60) + 80);
    }


    public static void unTwo ()
    {
        uncovered ();
        d.setColor (Color.yellow);

        d.drawString (" 2", (j * 60) + 85, (i * 60) + 80);
    }


    public static void unThree ()
    {
        uncovered ();
        d.setColor (Color.green);

        d.drawString (" 3", (j * 60) + 85, (i * 60) + 80);
    }


    public static void unFour ()
    {
        uncovered ();
        d.setColor (Color.black);

        d.drawString (" 4", (j * 60) + 85, (i * 60) + 80);
    }


    public static void unFive ()
    {
        uncovered ();
        d.setColor (Color.orange);

        d.drawString (" 5", (j * 60) + 85, (i * 60) + 80);
    }


    public static void unSix ()
    {
        uncovered ();
        d.setColor (Color.red);

        d.drawString (" 6", (j * 60) + 85, (i * 60) + 80);
    }


    public static void unSeven ()
    {
        uncovered ();
        d.setColor (Color.white);

        d.drawString (" 7", (j * 60) + 85, (i * 60) + 80);
    }


    public static void unEight ()
    {
        uncovered ();
        d.setColor (Color.magenta);

        d.drawString (" 8", (j * 60) + 85, (i * 60) + 80);
    }


    public static void flag ()//the flag to let you know where you think tiles are
    {
        d.setColor (Color.red);
        d.fillRect ((col * 60) + 90, (row * 60) + 60, 20, 15);
        d.setColor (Color.black);
        d.fillRect ((col * 60) + 85, (row * 60) + 60, 5, 28);
    }


    public static void coverFlag ()  //This is to remove placed flags
    {

        Color me = new Color (192, 192, 192);
        d.setColor (Color.black);
        d.drawRect ((col2 * 60) + 80, (row2 * 60) + 50, 50, 40);

        d.setColor (me);
        d.fillRect ((col2 * 60) + 80, (row2 * 60) + 50, 50, 40);
    }


    public static void main (String[] args)  //the start of the game
    {
        char key_entered; //used to make an event so you can switch gamestates for different parts of the games
        d = new Console (38, 100);
        e = new Console (7, 13);

       
        while (true)
        {

            while (gamestate == 1) //the menu screen where you pick if you want to start the game or go to instructions, game comes back here after victory or defeat.
            {
                displayImage (1);
                key_entered = e.getChar ();

                if (key_entered == 'i') //goes to instruction screen
                {
                    d.clear ();
                    gamestate = 3;
                    break;
                }
                else //anything otehr then i starts the game
                {
                    d.clear ();
                    gamestate = 2;
                    break;
                }


            }
            while (gamestate == 2) //the main game
            {
                int count = 0;

                Randomizer (map); //places the bombs everythign else is set to empty
                checks (map); //sets values for tiles around the bomb and gives them numbers

                drawBoard (map); //draws the board with covered squares
                d.setColor (Color.blue);
                Font ok = new Font ("Tyler", 4, 45); //used to let you know the map cords
                d.setFont (ok);
                d.drawString ("1", 90, 40);
                d.drawString ("2", 150, 40);
                d.drawString ("3", 210, 40);
                d.drawString ("4", 270, 40);
                d.drawString ("5", 330, 40);
                d.drawString ("6", 390, 40);
                d.drawString ("7", 450, 40);
                d.drawString ("8", 510, 40);
                d.drawString ("9", 570, 40);
                d.drawString ("10", 620, 40);
                d.setColor (Color.red);
                d.drawString ("1", 34, 86);
                d.drawString ("2", 34, 146);
                d.drawString ("3", 34, 206);
                d.drawString ("4", 34, 266);
                d.drawString ("5", 34, 326);
                d.drawString ("6", 34, 386);
                d.drawString ("7", 34, 446);
                d.drawString ("8", 34, 506);
                d.drawString ("9", 34, 566);
                d.drawString ("10", 20, 626);
                while (true)
                {
                    e.print ("Open a tile  (1)or place a flag (2)or  remove flag  (3) : "); //checks if you want to place a flag, remove a flag, or a tile
                    choice = e.readInt ();
                    sleep (500);
                    e.clear ();
                    if (choice == 1)
                    {
                        e.print ("Row : ");
                        i = e.readInt ();
                        e.print ("Col: ");
                        j = e.readInt ();
                        e.clear ();
                        i = i - 1; //used so tiles arent 0-9, 1-10 is easier to understand and think of
                        j = j - 1;

                        if (i < 0 | i > 9 | j < 0 | j > 9) // checks if they enter a number outside the map
                        {
                            e.println ("Invalid");

                        }
                        else if (map [i] [j] != 10 && map [i] [j] < 10) //keeps track of how many turns you used so far
                        {
                            count += 1;
                            updateBoard (map);
                            e.println ("Turns: " + count);
                        }
                        else if (map [i] [j] == 10) // used if they pick they same tile again
                        {
                            e.println ("Invalid");

                        }


                        if (i > -1 && i < 10 && j > -1 && j < 10) // checks if the tile you picked is a bomb or not
                        {
                            if (map [i] [j] == 9) //if the tile is a bomb it goes to the gameover gamestate
                            {
                                sleep (3000);
                                gamestate = 4;
                                break;

                            }
                            else //sets the tile to ten so if you pick this tile again its invaild
                            {
                                map [i] [j] = 10;
                            }
                        }




                        if (count == 90) //checks if you uncovered all the tiles and goes to the victory gamestate
                        {

                            sleep (3000);
                            gamestate = 5;

                            break;
                        }


                    }

                    else if (choice == 2) //if you want to place a flag
                    {
                        int value = 0;

                        e.print ("Row:  ");
                        row = e.readInt ();
                        e.print ("Col:  ");
                        col = e.readInt ();
                        row = row - 1;
                        col = col - 1;
                        sleep (1000);
                        e.clear();
                        if (row < 0 | row > 9 | col < 0 | col > 9)  //checks if you picked cords outside the map

                            {
                                e.println ("Invalid");

                            }


                        else if (map [row] [col] != 10)//otherwise it adds to the number +11 so it can be removed later to remove the flag if the player wants
                        {
                            value = map [row] [col];
                            flag ();
                            map [row] [col] += 11;
                        }






                    }
                    else if (choice == 3)//if you want ot remove the flag
                    {
                        e.print ("Row:  ");
                        row2 = e.readInt ();
                        e.print ("Col:  ");
                        col2 = e.readInt ();
                        row2 = row2 - 1;
                        col2 = col2 - 1;
                        sleep (1000);
                        e.clear();
                        if (row2 < 0 | row2 > 9 | col2 < 0 | col2 > 9)//checks if they picked outside the map

                            {
                                e.println ("Invalid");

                            }
                        else if (map [row2] [col2] > 10)//subtracts the number given if the tile had a flag to return to the orignal value
                        {
                            coverFlag ();
                            map [row2] [col2] -= 11;

                        }


                    }
                    else//if it didnt have the extra numbers it wont subtract and tell them they picked an invaild tile
                    {
                        e.println("Invalid");

                    }


                }
                break;



            }
            while (gamestate == 3)//the instruction screen in case you dont know how to play
            {
                displayImage (2);
                key_entered = e.getChar ();
                if (key_entered == 'm')//goes back to the menu
                {

                    gamestate = 1;
                }
            }
            while (gamestate == 4)//gameover try not to get this gamestate
            {
                displayImage (3);
                sleep (3000);


                d.clear ();
                e.clear ();
                break;

            }
            while (gamestate == 5)//victory gamestate
            {
                displayImage (4);
                sleep (3000);


                d.clear ();
                e.clear ();
                break;
            }
            gamestate = 1;
            sleep (2000);



        }


    }


    public static void drawBoard (int[] [] x)//draws the board with the covered tiles  to cover the map
    {

        for (i = 0 ; i < x.length ; i++)
        {

            for (j = 0 ; j < x [i].length ; j++)
            {

                if (map [i] [j] >= 0)
                {
                    covered ();
                }



            }

        }
    }


    public static void Randomizer (int[] [] x)//gives each tile a random number. Keeps generating new maps untill one with 10 bombs is made
    {

        int dog = 1;




        while (dog == 1)//when dog is 1 it gives a random number to each tile
        {
            int count = 0;
            Random number = new Random ();
            for (int i = 0 ; i < x.length ; i++)
            {



                for (int j = 0 ; j < x [i].length ; j++)
                {

                    n = number.nextInt (10);
                    map [i] [j] = n;
                    if (map [i] [j] == map [0] [j] || map [i] [j] == map [i] [0] || map [i] [j] == map [9] [j] || map [i] [j] == map [i] [9])//to stop the game from bugging out with bombs on the border this stops bombs from spawning on it
                    {
                        map [i] [j] = 0;
                    }
                    else if (map [i] [j] == 9)//when a bomb is placed it increases count to know when to stop placing bombs
                    {
                        count += 1;
                    }
                    else//all tiles that are not 9 are set to 0
                    {
                        map [i] [j] = 0;
                    }
                }



            }
            if (count == 10)//after 10 bombs are placed it changes dog to 2 to stop making bombs
            {
                dog = 2;

            }

        }


    }


    public static void checks (int[] [] x)//checks the tiles if its attached to a bomb
    {
        int i = 0, j = 0;

        for (i = 0 ; i < x.length ; i++)
        {


            for (j = 0 ; j < x [i].length ; j++)
            {




                if (map [i] [j] == 9)//checks all tiles around a bomb if the tile attached is not another bomb it adds 1 to its value
                {

                    try
                    {


                        if (map [i + 1] [j] < 9)
                        {
                            map [i + 1] [j] += 1;
                        }
                        if (map [i] [j + 1] < 9)
                        {
                            map [i] [j + 1] += 1;
                        }
                        if (map [i - 1] [j] < 9)
                        {
                            map [i - 1] [j] += 1;
                        }
                        if (map [i] [j - 1] < 9)
                        {
                            map [i] [j - 1] += 1;
                        }
                        if (map [i + 1] [j + 1] < 9)
                        {
                            map [i + 1] [j + 1] += 1;
                        }
                        if (map [i - 1] [j + 1] < 9)
                        {
                            map [i - 1] [j + 1] += 1;
                        }
                        if (map [i + 1] [j - 1] < 9)
                        {
                            map [i + 1] [j - 1] += 1;
                        }
                        if (map [i - 1] [j - 1] < 9)
                        {
                            map [i - 1] [j - 1] += 1;
                        }


                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                        d.println (map [i] [j]);
                    }
                }


                if (map [i] [j] >= 10)//if a bomb is next to another bomb it makes its value to 10 and this sets the number back to 9

                    {
                        map [i] [j] = 9;
                    }







            }

        }



    }


    public static void updateBoard (int[] [] x)//when you pick a tile this checks the value of the tile and changes the image according to the tiles value
    {

        if (map [i] [j] == 1)
        {

            unOne ();
        }
        else if (map [i] [j] == 2)
        {

            unTwo ();
        }
        else if (map [i] [j] == 3)
        {

            unThree ();
        }
        else if (map [i] [j] == 4)
        {

            unFour ();
        }
        else if (map [i] [j] == 5)
        {

            unFive ();
        }
        else if (map [i] [j] == 6)
        {

            unSix ();
        }
        else if (map [i] [j] == 7)
        {

            unSeven ();
        }
        else if (map [i] [j] == 8)
        {

            unEight ();
        }
        else if (map [i] [j] == 9)
        {

            bomb ();

        }
        else //if the tiles is not 1-9 it draws a empty tiles
        {
            uncovered ();
        }


    }





    public static void sleep (int x)
    {
        try
        {
            Thread.sleep (x);

        }
        catch (InterruptedException ie)
        {

        }
    }
}
