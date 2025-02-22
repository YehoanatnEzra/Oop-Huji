# ASCII Art Generator

This project is an implementation of an algorithm that converts images into ASCII art.
The program processes an image, calculates its brightness, and maps it to characters based on the brightness levels.
It supports output in both the console and HTML formats, and it allows the user to interact with the application via a command-line interface.

## Installation and Usage

1. Clone this repository to your local machine.
2. Make sure you have Java 21 installed.
3. Compile and run the `Shell` class to start the application.
4. Use the command-line interface to interact with the program and generate ASCII art from your images.

## Commands
The program accepts the following commands via the command-line interface:

- **image <path_to_image>**: Sets the image for ASCII art generation.
- **output html**: Outputs the ASCII art to an HTML file.
- **output console**: Outputs the ASCII art to the console.
- **chars**: Displays the current character set used for mapping image brightness.
- **add <character>**: Adds a character to the character set.
- **remove <character>**: Removes a character from the character set.
- **res up**: Increases the resolution.
- **res down**: Decreases the resolution.
- **asciiArt**: Generates and displays the ASCII art based on the current settings.
- **exit**: Exits the program.


Example of running the program:
1. Set the image:
   image C:\path\to\your\image.jpg
2. Output to HTML/console:
   output html/console
3. Generate ASCII art:
   asciiArt

