import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.function.Function;
import java.io.BufferedReader;

public class Main {
    private static <T> T get_valid_input(String prompt, Function<String, T> parser) {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print(prompt);
            try {
                String input = scan.readLine();
                return parser.apply(input);
            } catch (NumberFormatException e) {
                System.err.println(e); // TODO: Use logger instead of sys err
                System.out.println("Error parsing input. Please try again.");
            } catch (Exception e) {
                System.err.println("Error occurred: " + e.getMessage());
            }
        }
    }

    private static double round(double input) {
        DecimalFormat format = new DecimalFormat("#.##");
        return Double.parseDouble(format.format(input));
    }

    public static void main(String[] args) {
        double gallons_in_tank = get_valid_input("How many gallons in your tank? ", Double::parseDouble);
        double fuel_efficiency = get_valid_input("What is your miles per gallon? ", Double::parseDouble);
        double price_per_gallon = get_valid_input("What is the price per gallon? ", Double::parseDouble);
        double price_per_100_miles = (100. / fuel_efficiency) * price_per_gallon;
        double range = gallons_in_tank * fuel_efficiency;
        System.out.println("Cost per 100 miles: $" + round(price_per_100_miles));
        System.out.println("Range: " + range + " miles");
    }
}