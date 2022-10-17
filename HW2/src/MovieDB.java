import java.util.Iterator;

/**
 * Genre, Title 을 관리하는 영화 데이터베이스.
 * 
 * MyLinkedList 를 사용해 각각 Genre와 Title에 따라 내부적으로 정렬된 상태를  
 * 유지하는 데이터베이스이다. 
 */
public class MovieDB {
		MyLinkedList<MyLinkedList<String>> list;
		static final String[] genres = {"ACTION", "DRAMA", "HORROR"};

    public MovieDB() {
    	// HINT: MovieDBGenre 클래스를 정렬된 상태로 유지하기 위한 
    	// MyLinkedList 타입의 멤버 변수를 초기화 한다.

			MyLinkedList<String>[] arr = new MyLinkedList[3];

			for (int i = 0; i< genres.length; i++) {
				arr[i] = new MyLinkedList<String>();
			}

			list = new MyLinkedList(arr);
    }

		private int getGenreIndex(String genre) {
			for (int i = 0; i < genres.length; i++) {
				if (genres[i] == genre) {
					return i;
				}
			}

			return -1;
		}

    public void insert(MovieDBItem item) {
			// FIXME implement this
			// Insert the given item to the MovieDB.
			int genreIdx = this.getGenreIndex(item.getGenre());
			String title = item.getTitle();
			Node<String> insertData = new Node<String>(title);

			Node<MyLinkedList<String>> target = list.head.getNext();

			while(genreIdx > 0) {
				target = target.getNext();
				genreIdx--;
			}

			MyLinkedList<String> list = target.getItem();


			Node<String> cursor = list.head;
			while (cursor.getNext() != null && cursor.getNext().getItem().compareTo(title) <= 0) {
				cursor = cursor.getNext();
			}

			if (cursor.getNext() != null && cursor.getNext().getItem().compareTo(title) == 0) {
				return;
			}

			insertData.setNext(cursor.getNext());
			cursor.setNext(insertData);

    	// Printing functionality is provided for the sake of debugging.
			// This code should be removed before submitting your work.
			System.err.printf("[trace] MovieDB: INSERT [%s] [%s]\n", item.getGenre(), item.getTitle());
    }

    public void delete(MovieDBItem item) {
			// FIXME implement this
			// Remove the given item from the MovieDB.
			int genreIdx = this.getGenreIndex(item.getGenre());
			String title = item.getTitle();

			Node<MyLinkedList<String>> target = list.head.getNext();

			while(genreIdx > 0) {
				target = target.getNext();
			}

			MyLinkedList<String> list = target.getItem();

			Node<String> cursor = list.head;
			while (cursor.getNext() != null && cursor.getNext().getItem().compareTo(title) == 0) {
				cursor = cursor.getNext();
			}

			if (cursor.getNext() == null) {
				return;
			}

			cursor.setNext(cursor.getNext().getNext());
    	
    	// Printing functionality is provided for the sake of debugging.
			// This code should be removed before submitting your work.
			System.err.printf("[trace] MovieDB: DELETE [%s] [%s]\n", item.getGenre(), item.getTitle());
    }

    public MyLinkedList<MovieDBItem> search(String term) {
        // FIXME implement this
        // Search the given term from the MovieDB.
        // You should return a linked list of MovieDBItem.
        // The search command is handled at SearchCmd class.
    	
    	// Printing search results is the responsibility of SearchCmd class. 
    	// So you must not use System.out in this method to achieve specs of the assignment.
    	
        // This tracing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
    	System.err.printf("[trace] MovieDB: SEARCH [%s]\n", term);
    	
    	// FIXME remove this code and return an appropriate MyLinkedList<MovieDBItem> instance.
    	// This code is supplied for avoiding compilation error.   
        MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();

        return results;
    }
    
    public MyLinkedList<MovieDBItem> items() {
        // FIXME implement this
        // Search the given term from the MovieDatabase.
        // You should return a linked list of QueryResult.
        // The print command is handled at PrintCmd class.

    	// Printing movie items is the responsibility of PrintCmd class. 
    	// So you must not use System.out in this method to achieve specs of the assignment.

    	// Printing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
        System.err.printf("[trace] MovieDB: ITEMS\n");

    	// FIXME remove this code and return an appropriate MyLinkedList<MovieDBItem> instance.
    	// This code is supplied for avoiding compilation error.   
        MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
        
    	return results;
    }
}

class Genre extends Node<String> implements Comparable<Genre> {
	public Genre(String name) {
		super(name);
		throw new UnsupportedOperationException("not implemented yet");
	}
	
	@Override
	public int compareTo(Genre o) {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public int hashCode() {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public boolean equals(Object obj) {
		throw new UnsupportedOperationException("not implemented yet");
	}
}

class MovieList implements ListInterface<String> {	
	public MovieList() {
	}

	@Override
	public Iterator<String> iterator() {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public void add(String item) {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public String first() {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public void removeAll() {
		throw new UnsupportedOperationException("not implemented yet");
	}
}