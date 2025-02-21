package main.java;

public class Gallows {

    public String createGallows(int mistakes) {
        return switch (mistakes) {
            case 1 -> """
                    
                      +---+
                      |   |
                      O   |
                          |
                          |
                          |
                    =========
                    
                    """;
            case 2 -> """
                    
                      +---+
                      |   |
                      O   |
                      |   |
                          |
                          |
                    =========
                    
                    """;
            case 3 -> """
                    
                      +---+
                      |   |
                      O   |
                     /|   |
                          |
                          |
                    =========
                    
                    """;
            case 4 -> """
                    
                      +---+
                      |   |
                      O   |
                     /|\\  |
                          |
                          |
                    =========
                    
                    """;
            case 5 -> """
                    
                      +---+
                      |   |
                      O   |
                     /|\\  |
                     /    |
                          |
                    =========
                    
                    """;
            case 6 -> """
                    
                      +---+
                      |   |
                      O   |
                     /|\\  |
                     / \\  |
                          |
                    =========
                    
                    """;
            default -> """
                    
                      +---+
                      |   |
                          |
                          |
                          |
                          |
                    =========
                    
                    """;
        };
    }
}