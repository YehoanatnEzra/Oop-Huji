# 2D Game
This project is a simulation of a dynamic game world, where objects such as logs, leaves, and fruits interact with the player's avatar. The environment reacts to the avatar’s actions, implementing key design patterns to manage behaviors and interactions efficiently.

## **Installation & running**
- Clone the repository (if not already done).
- Navigate to the Bricker project.
- This project requires an external library, so download DanoGameLab 1.1.0 - 17.10.23 (https://danthe1st.itch.io/danogamelab).
- Run pepse.PepseGameManager.

## Classes and Design Patterns
**Log Class:**
- Role: Represents a log in the game world that changes color based on
the state of the avatar.
- Design Pattern: Observer pattern is indirectly used here.
The BooleanSupplier (avatarState) acts as the subject, and the Log class
observes changes in the avatar's state to adjust its color accordingly.

**Leaf Class:**
- Role: Represents a leaf object in the game world with
animations triggered by the avatar's state.
- Design Pattern: Observer pattern is used here as well. The BooleanSupplier (avatarState) acts as the subject, and the Leaf class observes changes in the avatar's state to trigger animations.

**Fruit Class:**
- Role: Represents a fruit object in the game world that changes color based
on the avatar's
state and triggers actions when eaten.
- Design Pattern: Observer pattern is again used here.
The BooleanSupplier (avatarState) acts as the subject, and the Fruit class
observes changes in the
avatar's state to change its color.

**Flora Class**
- Role: Represents the flora in the game world and generates trees, logs, leaves,
and fruits within a specified range.
Overall Design Patterns:
- Observer Pattern: Used in Log, Leaf, and Fruit classes to observe changes in the avatar's
state and trigger actions/animations accordingly.
Factory Method Pattern: Used in Flora class with the createInRange method serving as a
factory method to create different types of game objects within a specified range.
These classes and their interactions demonstrate a structured
approach to modeling elements
in a game world while utilizing common design patterns to manage
behaviors and relationships
effectively.


