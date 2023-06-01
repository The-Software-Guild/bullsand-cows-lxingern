# C-352-Excercises
solve the following 
Basic Programming Concepts
--------------------------
Dog Genetics
Ever heard of those places that you can mail in some of your dog’s hair, and they’ll send back a genetic analysis report to tell you what kind of dogs are in your most beloved pet’s ancestry?

Well, we don’t know how to do that. But we DO know how to generate random numbers. And half the time, that’s good enough. Especially for the Internet.

What You Should Do
**Program Name: DogGenetics
Write a program that asks the user for the name of their dog, and then generates a fake DNA background report on the pet dog.
It should assign a random percentage using five dog breeds. The total percentage should be 100%.
What You Should See
The following is an example of what the output might look like. You are welcome to use different text or dog breeds than what is shown here.

What is your dog's name? Sir Fluffy McFlufferkins Esquire
Well then, I have this highly reliable report on Sir Fluffy McFlufferkins Esquire's prestigious background right here.

Sir Fluffy McFlufferkins Esquire is:

61% St. Bernard
2% Chihuahua
29% Dramatic RedNosed Asian Pug
1% Common Cur
7% King Doberman

Wow, that's QUITE the dog!
Healthy Hearts
What You Should Do

**Program name: HealthyHearts
Create a simple calculator that asks the user for their age, calculates the healthy heart rate range for that age, and displays the result.

Their maximum heart rate should be 220 - their age.
The target heart rate zone is the 50 - 85% of the maximum.
What You Should See
The following is an example of what the output might look like.

What is your age? 50
Your maximum heart rate should be 170 beats per minute
Your target HR Zone is 85 - 145 beats per minute

**Summative Sums
What You Should Do

Program Name: SummativeSums
Write a static method that takes in an array of ints, adds them together, and returns the result.

Call your new method inside a main method and print out the results for the following three example arrays:

{ 1, 90, -33, -55, 67, -16, 28, -55, 15 }
{ 999, -60, -77, 14, 160, 301 }
{ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130,
140, 150, 160, 170, 180, 190, 200, -99 }
What You Should See
The following is an example of what the output might look like.

#1 Array Sum: 42
#2 Array Sum: 1337
#3 Array Sum: 2001
Lucky Seven
------------
Overview
In this lab, you will write a program that plays Lucky Sevens. The rules of the game are:

Each round, the program rolls a virtual pair of dice for the user.
If the sum of the two dice is equal to 7, the player wins $4; otherwise, the player loses $1.
Your job is to write a program that plays this game, which will also demonstrate the futility of playing Lucky Sevens.

This exercise is provided to give you practice applying the skills you have learned in this course, but you will not submit it to your instructor nor will it be graded, unless otherwise directed by your instructor.

You should complete this exercise before continuing on with the course.
If you have problems completing this exercise, review the course content up to this point and try again.
Contact an instructor or TA if you have questions about this exercise or cannot resolve problems on your own.
Remember to sync your code with your classwork repository after you have completed the exercise.
Requirements

Your program must include the following features:

This program will be a Java Console Application called LuckySevens.
The program first asks the user how many dollars they have to bet.
The program then rolls the dice repeatedly until all the money is gone.

Hint: Use a loop construct to keep playing until the money is gone.
The program keeps track of how many rolls were taken before the money ran out.
The program keeps track of the maximum amount of money held by the player.
The program keeps track of how many rolls were taken at the point when the user held the most money.


Sample Program Output:
How many dollars do you have? 100
You are broke after 543 rolls.
You should have quit after 47 rolls when you had $113.
For Factorizer
------------------
Overview
In this exercise, you will write a program that receives an integer value from a user and then calculates and prints out a list that includes all of the factors of that number, whether or not the number is perfect, and whether or not the number is prime.

A perfect number is a number where all the factors of the number, excluding the number itself, add up to that number. For example, the first perfect number is 6 because its factors 1, 2, and 3 add up to 6.

A prime number is defined as a number greater than 1 that has only itself and 1 as factors. For example, 3 is a prime number, but 4 is not.

This exercise is provided to give you practice applying the skills you have learned in this course, but you will not submit it to your instructor nor will it be graded.

You should complete this exercise before continuing on with the course.
If you have problems completing this exercise, review the course content up to this point and try again.
Contact an instructor or TA if you have questions about this exercise or cannot resolve problems on your own.
Remember to sync your code with your classwork repository after you have completed the exercise.
Requirements
This program must be in a new console application named Factorizer.
This program must ask the user for the number the program will factor.
The program must print out the original number.
The program must print out each factor of the number (not including the number itself).
The program must print out the total number of factors for the number.
The program must print out whether or not the number is perfect.
The program must print out whether or not the number is prime.
Sample Program Output
What number would you like to factor? 6
The factors of 6 are:
1 2 3 6
6 has 4 factors.
6 is a perfect number. 
6 is not a prime number.


For InterestCalculator
----------------------------
Overview
In this lab, you will write an interest calculator program that works as described in this example:

John has $500 to invest. Sue knows of a mutual fund plan that pays 10% interest annually, compounded quarterly. That is, every three months, the principal is multiplied by 2.5% (the 10% annual rate divided by 4 because it is compounded 4 times per year) and the result is added to the principal.

More generally, the new amount each quarter is equal to:

CurrentBalance * (1 + (QuarterlyInterestRate / 100))
Your assignment is to write a program that will tell John how much money will be in the fund after a specified number of years.

This exercise is provided to give you practice applying the skills you have learned in this course, but you will not submit it to your instructor nor will it be graded.

You should complete this exercise before continuing on with the course.
If you have problems completing this exercise, review the course content up to this point and try again.
Contact an instructor or TA if you have questions about this exercise or cannot resolve problems on your own.
Remember to sync your code with your classwork repository after you have completed the exercise.
Requirements
Make the program general; that is, it should prompt for the following inputs and use those inputs in the calculations.

The annual interest rate
The initial amount of principal
The number of years the money is to stay in the fund
The output should include the following for each year:

The year number
The principal at the beginning of the year
The total amount of interest earned for the year
The principal at the end of the year
Sample Program Output
How much do you want to invest? 500
How many years are investing? 10
What is the annual interest rate % growth? 10

Calculating...
Year 1:
Began with $500.00
Earned $51.91
Ended with $551.91

Year 2:
Began with $551.91
Earned $57.30
Ended with $609.20

Year 3:
Began with $609.20
Earned $63.24
Ended with $672.44

...
Additional Challenges
Change the program so that interest is compounded monthly.
Change the program so that the user can choose from quarterly, monthly, or daily interest compound periods.


Mandatory Assignment : Java Basics
----------------------------------
Overview
This purpose of this activity is to demonstrate your proficiency in basic Java syntax involving console input and output, variables, flow of control statements, and expressions. These core concepts are the building blocks for more complicated code that is to come.

In this activity, you will write a program that plays the game Rock, Paper, Scissors.

Task List
Create a flowchart that describes what the program will do.
Show your flowchart to your instructor.
After receiving approval for your flowchart, begin writing code.
Write the code in stages, checking that each stage works before continuing to the next stage.
Once all parts of the code work as expected and the program meets all requirements listed below, submit the code using instructions provided by your instructor.
Rules
The rules of the game are as follows:

Each player chooses Rock, Paper, or Scissors.
If both players choose the same thing, the round is a tie.
Otherwise:
Paper wraps Rock to win
Scissors cut Paper to win
Rock breaks Scissors to win
Requirements
This program will be a Java console application named RockPaperScissors.

The program first asks the user how many rounds he/she wants to play.
Maximum number of rounds = 10, minimum number of rounds = 1.
If the user asks for something outside this range, the program prints an error message and quits.
If the number of rounds is in range, the program plays that number of rounds.
Each round is played according to the requirements below.
For each round of Rock, Paper, Scissors, the program does the following:
The computer asks the user for his/her choice (Rock, Paper, or Scissors).
Hint: 1 = Rock, 2 = Paper, 3 = Scissors
After the computer asks for the user’s input, the computer randomly chooses Rock, Paper, or Scissors and displays the result of the round (tie, user win, or computer win).
Hint: Use the Random class.
The program must keep track of how many rounds are ties, user wins, or computer wins.
Hint: Create three variables to keep track of these items and update them correctly after each round.
At the end of the last round, the program must print out the number of ties, user wins, and computer wins and declare the overall winner based on who won more rounds.
After all rounds have been played and the winner declared, the program must ask the user if he/she wants to play again.
If the user says No, the program prints out a message like, “Thanks for playing!” and then exits.
If the user says Yes, the program starts over, asking the user how many rounds he/she would like to play.
Submit Your Work
Your instructor will provide instructions on how to submit your work to a GitHub classroom
