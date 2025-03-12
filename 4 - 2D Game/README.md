# 2D Game  
This project is a simulation of a dynamic game world where objects such as logs, leaves, and fruits interact with the player's avatar. The environment responds to the avatar’s actions, using key design patterns to efficiently manage behaviors and interactions.

## Installation & Running  
1. Clone the repository (if not already done).  
2. Navigate to the Bricker project.  
3. Download DanoGameLab 1.1.0 - 17.10.23 (required external library) from [here](https://danthe1st.itch.io/danogamelab).  
4. Run pepse.PepseGameManager.  

## Classes and Design Patterns  

### Log Class  
- **Role:** Represents a log in the game world that changes color based on the avatar’s state.  
- **Design Pattern:** Uses the Observer pattern indirectly. The `BooleanSupplier` (avatarState) acts as the subject, while the Log class observes changes in the avatar's state to adjust its color accordingly.  

### Leaf Class  
- **Role:** Represents a leaf object in the game world with animations triggered by changes in the avatar's state.  
- **Design Pattern:** Implements the Observer pattern, where `BooleanSupplier` (avatarState) is the subject, and the Leaf class observes its changes to trigger animations.  

### Fruit Class  
- **Role:** Represents a fruit object that changes color based on the avatar’s state and triggers actions when eaten.  
- **Design Pattern:** Implements the Observer pattern, with `BooleanSupplier` (avatarState) as the subject. The Fruit class observes its changes to modify its color accordingly.  

### Flora Class  
- **Role:** Represents flora in the game world, generating trees, logs, leaves, and fruits within a specified range.  
- **Design Patterns Used:**  
  - **Observer Pattern:** Used in the Log, Leaf, and Fruit classes to monitor changes in the avatar's state and trigger actions or animations accordingly.  
  - **Factory Method Pattern:** Used in the Flora class, where the `createInRange` method serves as a factory to generate different types of game objects within a specified range.  


