# **Exercise 2 - Tic Tac Toe Tournament**

This project is the second exercise I completed in the OOP course at Hebrew University of Jerusalem.  
The main objective of this exercise is to practice implementing basic design patterns and interfaces while improving strategic decision-making in a Tic Tac Toe game.

## **Installation & running**
- Clone the repository (if not already done)
- Navigate to the Tic Tac Toe Tournament project
- Open the project in your IDE (IntelliJ IDEA, Eclipse, or VS Code).
- Use JDK 11 or higher to compile and run the program.
- Run the Tournament class with the following parameters:  
`<Number of tournament> <Size of the board> <Number of marks needed to win> <Display mode (console or none)> <First player's strategy (human, clever, whatever, genius)> <Second player's strategy>`.



## **Exercise Description**  
This project implements a Tic Tac Toe Tournament, where multiple player strategies compete against each other.
The primary focus is on different AI strategies and their effectiveness in winning against a random player.

### **Implemented Strategies**  

#### **Clever Strategy**  
The Clever player systematically scans the board in a structured manner:  
- It starts from the top-left corner (row 0, column 0) and moves row by row, searching for an available space.  
- Once it finds an empty square, it places its mark and continues.  
- This method ensures a logical and structured gameplay approach.  
- The Clever player wins against a random player except the Genius approximately 65% of the time, which meets the exercise requirements.

#### **Genius Strategy**  
The Genius player is an improved version of the Clever strategy, designed to counter it effectively:  
- Instead of starting from row 0, column 0, it begins from row 0, column 1.  
- Unlike the Clever player, which moves to the next row when encountering an empty space, the Genius player stays on the same row but moves one column to the right.
- This approach blocks the Clever player's strategy, reducing its chances of winning.  
- Since the Genius player still follows a structured method, it maintains an advantage over random players.  
- The Genius player wins against random players with a success rate of over 55%, similar to how the Clever player outperforms them.

## **Design Principles**  

The project follows fundamental Object-Oriented Design principles, ensuring scalability, maintainability, and modularity:

- **Polymorphism** – Different player strategies share a common interface, allowing easy extension.  
- **Encapsulation** – Data and logic are well-structured within classes, preventing unnecessary dependencies.  
- **Open/Closed Principle (OCP)** – The design allows new player strategies to be added without modifying existing code.  
- **Interface Segregation Principle** – Each class implements only the methods it truly needs, promoting clean architecture.
- **Single Responsibility Principle (SRP)** – Each class has a well-defined responsibility, making debugging and enhancements more manageable.

Thanks to this structured approach, we can easily add new AI players and rendering options in the future without modifying all
classes — only the relevant factories will need updates.
This reduces the risk of bugs and makes debugging more efficient by isolating potential issues.


