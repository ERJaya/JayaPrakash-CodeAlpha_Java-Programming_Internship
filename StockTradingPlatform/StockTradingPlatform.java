package StockTradingPlatform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Stock {
    String symbol;
    double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String toString() {
        return "Stock: " + symbol + ", Price: $" + price;
    }
}

class Portfolio {
    private Map<String, Integer> holdings;
    private double balance;

    public Portfolio(double initialBalance) {
        holdings = new HashMap<>();
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void buyStock(String symbol, int quantity, double price) {
        double cost = price * quantity;
        if (cost <= balance) {
            balance -= cost;
            holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
            System.out.println("Bought " + quantity + " shares of " + symbol);
        } else {
            System.out.println("Insufficient balance to buy " + quantity + " shares of " + symbol);
        }
    }

    public void sellStock(String symbol, int quantity, double price) {
        int currentQuantity = holdings.getOrDefault(symbol, 0);
        if (currentQuantity >= quantity) {
            balance += price * quantity;
            holdings.put(symbol, currentQuantity - quantity);
            System.out.println("Sold " + quantity + " shares of " + symbol);
        } else {
            System.out.println("Insufficient shares to sell " + quantity + " shares of " + symbol);
        }
    }

    public void viewPortfolio() {
        System.out.println("Portfolio Holdings:");
        for (String symbol : holdings.keySet()) {
            System.out.println(symbol + ": " + holdings.get(symbol) + " shares");
        }
        System.out.println("Balance: $" + balance);
    }
}

public class StockTradingPlatform {
    private static List<Stock> market;
    private static Portfolio portfolio;

    public static void main(String[] args) {
        market = new ArrayList<>();
        initializeMarket();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter initial balance for your portfolio: ");
        double initialBalance = scanner.nextDouble();
        portfolio = new Portfolio(initialBalance);

        int choice;
        do {
            System.out.println("\nStock Trading Platform");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewMarketData();
                    break;
                case 2:
                    buyStock(scanner);
                    break;
                case 3:
                    sellStock(scanner);
                    break;
                case 4:
                    portfolio.viewPortfolio();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void initializeMarket() {
        market.add(new Stock("AAPL", 150.0));
        market.add(new Stock("GOOGL", 2800.0));
        market.add(new Stock("AMZN", 3500.0));
        market.add(new Stock("TSLA", 700.0));
        market.add(new Stock("MSFT", 300.0));
    }

    private static void viewMarketData() {
        System.out.println("Market Data:");
        for (Stock stock : market) {
            System.out.println(stock);
        }
    }

    private static void buyStock(Scanner scanner) {
        System.out.print("Enter stock symbol to buy: ");
        String symbol = scanner.nextLine();
        System.out.print("Enter quantity to buy: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Stock selectedStock = null;
        for (Stock stock : market) {
            if (stock.symbol.equalsIgnoreCase(symbol)) {
                selectedStock = stock;
                break;
            }
        }

        if (selectedStock != null) {
            portfolio.buyStock(symbol, quantity, selectedStock.price);
        } else {
            System.out.println("Stock not found.");
        }
    }

    private static void sellStock(Scanner scanner) {
        System.out.print("Enter stock symbol to sell: ");
        String symbol = scanner.nextLine();
        System.out.print("Enter quantity to sell: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Stock selectedStock = null;
        for (Stock stock : market) {
            if (stock.symbol.equalsIgnoreCase(symbol)) {
                selectedStock = stock;
                break;
            }
        }

        if (selectedStock != null) {
            portfolio.sellStock(symbol, quantity, selectedStock.price);
        } else {
            System.out.println("Stock not found.");
        }
    }
}
