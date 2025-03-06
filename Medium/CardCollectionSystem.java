import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CardCollection {
    // HashMap to store cards with their symbols as keys
    private Map<String, List<String>> cardCollection;

    public CardCollection() {
        cardCollection = new HashMap<>();
        initializeCards();
    }

    // Method to initialize the card collection
    private void initializeCards() {
        // Adding cards to the collection
        addCard("Hearts", "2 of Hearts");
        addCard("Hearts", "3 of Hearts");
        addCard("Hearts", "4 of Hearts");
        addCard("Diamonds", "2 of Diamonds");
        addCard("Diamonds", "3 of Diamonds");
        addCard("Clubs", "2 of Clubs");
        addCard("Clubs", "3 of Clubs");
        addCard("Spades", "2 of Spades");
        addCard("Spades", "3 of Spades");
    }

    // Method to add a card to the collection
    private void addCard(String symbol, String card) {
        cardCollection.putIfAbsent(symbol, new ArrayList<>());
        cardCollection.get(symbol).add(card);
    }

    // Method to find all cards of a given symbol
    public List<String> findCardsBySymbol(String symbol) {
        return cardCollection.getOrDefault(symbol, new ArrayList<>());
    }

    public static void main(String[] args) {
        CardCollection cardCollection = new CardCollection();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the symbol (e.g., Hearts, Spades, Diamonds, Clubs) to find all cards: ");
        String symbol = scanner.nextLine();

        List<String> cards = cardCollection.findCardsBySymbol(symbol);
        if (cards.isEmpty()) {
            System.out.println("No cards found for the symbol: " + symbol);
        } else {
            System.out.println("Cards of symbol " + symbol + ": " + cards);
        }

        // Close the scanner
        scanner.close();
    }
}
