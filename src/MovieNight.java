import java.util.ArrayList;
import java.util.HashMap;

public class MovieNight {

    public static void main(String[] args) {

        String[] titles = {
                "The Shawshank Redemption", "The Godfather", "The Dark Knight", "Schindler's List",
                "Pulp Fiction", "The Lord of the Rings: The Return of the King", "Forrest Gump",
                "Inception", "Fight Club", "The Matrix",
                "Goodfellas", "Se7en", "The Silence of the Lambs", "Interstellar", "Saving Private Ryan",
                "The Green Mile", "Gladiator", "Whiplash", "Parasite", "The Departed",
                "Spirited Away", "The Lion King", "Amélie", "La La Land", "Her",
                "Eternal Sunshine of the Spotless Mind", "The Truman Show", "Memento", "Requiem for a Dream", "Black Swan",
                "12 Angry Men", "Casablanca", "Vertigo", "Rear Window", "Psycho",
                "Some Like It Hot", "Sunset Boulevard", "All About Eve", "Rebecca", "Lawrence of Arabia",
                "Alien", "Blade Runner", "2001: A Space Odyssey", "Star Wars", "The Empire Strikes Back",
                "Jurassic Park", "E.T.", "Back to the Future", "Terminator 2", "Die Hard",
                "Heat", "No Country for Old Men", "There Will Be Blood", "Fargo", "The Big Lebowski",
                "O Brother Where Art Thou", "True Grit", "Burn After Reading", "Barton Fink", "Blood Simple",
                "Oldboy", "City of God", "Pan's Labyrinth", "The Lives of Others", "Y Tu Mamá También",
                "Amores Perros", "Crouching Tiger Hidden Dragon", "In the Mood for Love", "Ikiru", "Tokyo Story",
                "Seven Samurai", "Rashomon", "Bicycle Thieves", "La Strada", "8½",
                "The 400 Blows", "Breathless", "Jules and Jim", "Au Revoir Les Enfants", "Cleo from 5 to 7",
                "Stalker", "Andrei Rublev", "Mirror", "Come and See", "Ivan's Childhood",
                "A Clockwork Orange", "Full Metal Jacket", "Dr. Strangelove", "The Shining", "Barry Lyndon",
                "Taxi Driver", "Raging Bull", "Mean Streets", "The King of Comedy", "After Hours",
                "Dune", "Everything Everywhere All at Once", "The Whale", "Tár", "Oppenheimer"
        };

        double[] ratings = {
                9.3, 9.2, 9.0, 8.9, 8.9, 8.9, 8.8, 8.8, 8.8, 8.7,
                8.7, 8.6, 8.6, 8.6, 8.5, 8.5, 8.5, 8.5, 8.5, 8.5,
                8.6, 8.5, 8.3, 8.0, 8.0, 8.3, 8.1, 8.4, 8.3, 8.0,
                9.0, 8.5, 8.3, 8.5, 8.5, 8.2, 8.4, 8.2, 8.1, 8.3,
                8.5, 8.1, 8.3, 8.6, 8.7, 7.9, 7.9, 8.5, 8.5, 8.2,
                8.3, 8.2, 8.1, 8.1, 8.1, 7.7, 7.6, 6.6, 7.4, 7.4,
                8.4, 8.6, 8.2, 8.4, 7.7, 8.1, 7.9, 8.1, 8.3, 8.2,
                8.6, 8.2, 8.3, 8.0, 8.0, 8.0, 7.7, 7.8, 7.9, 7.3,
                8.1, 8.1, 8.5, 8.2, 8.0, 8.5, 8.2, 7.5, 7.7, 7.5,
                8.3, 7.8, 7.6, 7.5, 8.4, 6.9, 7.8, 7.9, 7.2, 7.8
        };

        System.out.printf("The Movie length is %s ", titles.length);
        System.out.printf("\nThe Rating length is %s \n ", ratings.length);

        // ── PART 1: Print highly rated movies ────────────────────────────────────
        // Print only movies with a rating >= 8.5 using a for loop and continue.
        // Number the results starting from 1.
        // Format: "1. Inception - Rating: 8.8"
        System.out.println("=== Highly Rated Movies (8.5+) ===");

        // TODO: Write your Part 1 code here
        int count = 1;
        for (int i = 0; i < titles.length; i++) { //use a normal `for` loop to go through all 100 movies
            if (ratings[i] < 8.5) { // if (ratings[i] < 8.5) : if the rating is less than 8.5, use continue
                continue; //skipping movies that has lower scores than 8.5
            }
            System.out.printf("%d. %s - Rating: %.1f\n", count, titles[i], ratings[i]);
            count++;


            // only movies with rating 8.5 or higher will be shown
        }


        // ── PART 2: Movie search ──────────────────────────────────────────────────
        // Search the titles array for "Parasite" and then "Avatar".
        // Use a for loop and break when the movie is found.
        // Use .equals() to compare Strings (NOT ==).
        // Print: "Found! Rating: X.X"  or  "Not found in catalog."
        System.out.println("\n=== Movie Search ===");

        // TODO: Write your Part 2 code here
        // Tip: declare a boolean found = false; before the loop

        // Search for parasites
        boolean found = false; // use to track whether we found the movie
        for (int i = 0; i < titles.length; i++) {
            if (titles[i].equals("Parasite")) { // compare 2 strings
                System.out.printf("Searching for \"Parasite\" ... Found! Rating: %.1f\n", ratings[i]);
                found = true;
                break; // stop loop immediately
            }
        }
        if (!found) { // if not found
            System.out.println("Searching for \"Parasite\" ... Not found in catalog.");
        }

        // Search for Avatar
        found = false;
        for (int i = 0; i < titles.length; i++) {
            if (titles[i].equals("Avatar")) {
                System.out.printf("Searching for \"Avatar\" ... Found! Rating: %.1f\n", ratings[i]);
                found = true; // stop searching once found
                break;
            }
        }
        if (!found) {
            System.out.println("Searching for \"Avatar\" ... Not found in catalog.");
        }


        // ── PART 3: Watchlist ─────────────────────────────────────────────────────
        // Create an ArrayList<String>.
        // Add "Inception", "Parasite", "Dune" — then print the list.
        // Remove "Dune", add "The Matrix" — then print the list and its size.
        System.out.println("\n=== Watchlist ===");

        // TODO: Write your Part 3 code here

        ArrayList<String> watchlist = new ArrayList<>(); //dynamic list (size can change)

        // add movies
        watchlist.add("Inception");
        watchlist.add("Parasite");
        watchlist.add("Dune");
        System.out.println("After adding: " + watchlist);

        // remove dune then add matrix
        watchlist.remove("Dune");
        watchlist.add("The Matrix");
        System.out.println("After changes: " + watchlist);
        System.out.println("Watchlist has " + watchlist.size() + " movies."); // how many movies are in the list


        // ── PART 4: Genre tally ───────────────────────────────────────────────────
        // Hardcode a HashMap<String, String> mapping each watchlist movie to its genre:
        //   "Inception"  -> "Sci-Fi"
        //   "Parasite"   -> "Thriller"
        //   "The Matrix" -> "Sci-Fi"
        //
        // Loop over the watchlist and count genres in a HashMap<String, Integer>.
        // Tip: use containsKey() — if the genre is already in the tally map, increment
        //      its count; if not, add it with count 1.
        //
        // Print each genre and its count, then print the most popular genre.
        System.out.println("\n=== Genre Tally ===");

        // TODO: Write your Part 4 code here
        //genre mapping
        HashMap<String, String> genreMap = new HashMap<>(); // which genre each movie belongs to (like a dictionary)
        genreMap.put("Inception", "Sci-Fi");
        genreMap.put("Parasite", "Thriller");
        genreMap.put("The Matrix", "Sci-Fi");

        // count genres
        HashMap<String, Integer> genreCount = new HashMap<>(); // this counts how many movies belong to each genre

        for (String movie : watchlist) {
            String genre = genreMap.get(movie);
            if (genre != null) {
                if (genreCount.containsKey(genre)) {
                    genreCount.put(genre, genreCount.get(genre) + 1); // if the genre is already in genreCount, increase the number by 1
                } else { // if not, add the genre with count = 1
                    genreCount.put(genre, 1);
                }
            }
        }

        // prints count
        for (String genre : genreCount.keySet()) {
            System.out.println(genre + ": " + genreCount.get(genre));
        }

        // find most popular genre
        String mostPopular = "";
        int maxCount = 0;
        for (String genre : genreCount.keySet()) {
            if (genreCount.get(genre) > maxCount) {
                maxCount = genreCount.get(genre);
                mostPopular = genre;
            }
        }
        System.out.println("Most popular genre tonight: " +  mostPopular);
    }
}
