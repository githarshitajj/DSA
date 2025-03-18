package linkedListQuestions;

class Movie {
    String title;
    String director;
    int yearOfRelease;
    double rating;

    public Movie(String title, String director, int yearOfRelease, double rating) {
        this.title = title;
        this.director = director;
        this.yearOfRelease = yearOfRelease;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Director: " + director +
                ", Year: " + yearOfRelease + ", Rating: " + rating;
    }
}

class MovieNode {
    Movie data;
    MovieNode prev;
    MovieNode next;

    public MovieNode(Movie data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

class MovieDoublyLinkedList {
    MovieNode head;
    MovieNode tail;

    public MovieDoublyLinkedList() {
        head = null;
        tail = null;
    }

    // 1. Add Operations

    // Add at the beginning
    public void addFirst(Movie movie) {
        MovieNode newNode = new MovieNode(movie);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    // Add at the end
    public void addLast(Movie movie) {
        MovieNode newNode = new MovieNode(movie);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
    }

    // Add at a specific position (0-indexed)
    public void addAtPosition(Movie movie, int position) {
        if (position < 0) {
            System.out.println("Invalid position. Position must be non-negative.");
            return;
        }

        if (position == 0) {
            addFirst(movie);
            return;
        }

        MovieNode newNode = new MovieNode(movie);
        MovieNode current = head;
        int count = 0;

        while (current != null && count < position) {
            current = current.next;
            count++;
        }

        if (current == null && count == position) { //Adding at the end
            addLast(movie);
            return;
        }
        else if (current == null) {
            System.out.println("Invalid position. Position exceeds list length.");
            return;
        }


        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;
    }


    // 2. Remove Operation

    // Remove by Movie Title
    public void removeByTitle(String title) {
        if (head == null) {
            System.out.println("List is empty. Cannot remove.");
            return;
        }

        MovieNode current = head;
        while (current != null && !current.data.title.equals(title)) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Movie with title '" + title + "' not found.");
            return;
        }

        // If removing the head
        if (current == head) {
            head = current.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null; // List becomes empty
            }
        }
        // If removing the tail
        else if (current == tail) {
            tail = current.prev;
            tail.next = null;
        }
        // If removing from the middle
        else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        System.out.println("Movie with title '" + title + "' removed.");
    }


    // 3. Search Operations

    // Search by Director
    public void searchByDirector(String director) {
        MovieNode current = head;
        boolean found = false;
        System.out.println("Movies directed by " + director + ":");
        while (current != null) {
            if (current.data.director.equals(director)) {
                System.out.println(current.data);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No movies found for director: " + director);
        }
    }

    // Search by Rating
    public void searchByRating(double rating) {
        MovieNode current = head;
        boolean found = false;
        System.out.println("Movies with rating " + rating + ":");
        while (current != null) {
            if (current.data.rating == rating) {
                System.out.println(current.data);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No movies found with rating: " + rating);
        }
    }


    // 4. Display Operations

    // Display forward
    public void displayForward() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        MovieNode current = head;
        System.out.println("Movies (Forward):");
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    // Display reverse
    public void displayReverse() {
        if (tail == null) {
            System.out.println("List is empty.");
            return;
        }

        MovieNode current = tail;
        System.out.println("Movies (Reverse):");
        while (current != null) {
            System.out.println(current.data);
            current = current.prev;
        }
    }


    // 5. Update Operation

    // Update rating by Movie Title
    public void updateRating(String title, double newRating) {
        MovieNode current = head;
        while (current != null) {
            if (current.data.title.equals(title)) {
                current.data.rating = newRating;
                System.out.println("Rating updated for movie '" + title + "'.");
                return;
            }
            current = current.next;
        }
        System.out.println("Movie with title '" + title + "' not found.");
    }
}

public class MovieManagementSystem {
    public static void main(String[] args) {
        MovieDoublyLinkedList movieList = new MovieDoublyLinkedList();

        // Adding movies
        movieList.addLast(new Movie("Inception", "Christopher Nolan", 2010, 8.8));
        movieList.addLast(new Movie("The Shawshank Redemption", "Frank Darabont", 1994, 9.3));
        movieList.addFirst(new Movie("The Godfather", "Francis Ford Coppola", 1972, 9.2));
        movieList.addAtPosition(new Movie("Pulp Fiction", "Quentin Tarantino", 1994, 8.9), 2);

        // Displaying movies
        movieList.displayForward();
        movieList.displayReverse();

        // Removing a movie
        movieList.removeByTitle("Pulp Fiction");
        System.out.println("\nAfter removing 'Pulp Fiction':");
        movieList.displayForward();

        // Searching for movies
        System.out.println("\nSearching by director 'Christopher Nolan':");
        movieList.searchByDirector("Christopher Nolan");

        System.out.println("\nSearching by rating 9.2:");
        movieList.searchByRating(9.2);


        // Updating a movie's rating
        movieList.updateRating("Inception", 9.0);
        System.out.println("\nAfter updating 'Inception' rating:");
        movieList.displayForward();

        // Add at invalid positions
        movieList.addAtPosition(new Movie("Invalid Movie 1", "Director A", 2023, 7.5), -1); // Invalid position
        movieList.addAtPosition(new Movie("Invalid Movie 2", "Director B", 2022, 6.8), 10);  // Invalid position
        System.out.println("\nMovies after attempting invalid add operations:");
        movieList.displayForward();


        // Remove a movie that doesn't exist
    }
}