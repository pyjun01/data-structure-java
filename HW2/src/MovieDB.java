import java.util.function.Consumer;

/**
 * Genre, Title 을 관리하는 영화 데이터베이스.
 * 
 * MyLinkedList 를 사용해 각각 Genre와 Title에 따라 내부적으로 정렬된 상태를
 * 유지하는 데이터베이스이다.
 */
public class MovieDB {
	MyLinkedList<MyLinkedList<MovieDBItem>> list;
	static final String[] genres = { "ACTION", "DRAMA", "HORROR" };

	public MovieDB() {
		// HINT: MovieDBGenre 클래스를 정렬된 상태로 유지하기 위한
		// MyLinkedList 타입의 멤버 변수를 초기화 한다.

		MyLinkedList<MovieDBItem>[] arr = new MyLinkedList[3];

		for (int i = 0; i < genres.length; i++) {
			arr[i] = new MyLinkedList<MovieDBItem>();
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

	private MyLinkedList<MovieDBItem> getGenreList(String genre) {
		int index = this.getGenreIndex(genre);

		Node<MyLinkedList<MovieDBItem>> target = this.list.head.getNext();

		while (index > 0) {
			target = target.getNext();
		}

		return target.getItem();
	}

	public void insert(MovieDBItem item) {
		// Insert the given item to the MovieDB.
		MyLinkedList<MovieDBItem> list = this.getGenreList(item.getGenre());
		String title = item.getTitle();

		Node<MovieDBItem> cursor = list.head;

		while (cursor.getNext() != null && cursor.getNext().getItem().getTitle().compareTo(title) <= 0) {
			cursor = cursor.getNext();
		}

		if (cursor.getNext() != null && cursor.getNext().getItem().getTitle().compareTo(title) == 0) {
			return;
		}

		Node<MovieDBItem> insertData = new Node<MovieDBItem>(item);

		insertData.setNext(cursor.getNext());
		cursor.setNext(insertData);
	}

	public void delete(MovieDBItem item) {
		// Remove the given item from the MovieDB.
		String title = item.getTitle();
		MyLinkedList<MovieDBItem> list = this.getGenreList(item.getGenre());

		Node<MovieDBItem> cursor = list.head;
		while (cursor.getNext() != null && cursor.getNext().getItem().getTitle().compareTo(title) == 0) {
			cursor = cursor.getNext();
		}

		if (cursor.getNext() == null) {
			return;
		}

		cursor.setNext(cursor.getNext().getNext());
	}

	public MyLinkedList<MovieDBItem> search(String term) {
		MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();

		this.list.forEach(new Consumer<MyLinkedList<MovieDBItem>>() {
			@Override
			public void accept(MyLinkedList<MovieDBItem> movieList) {
				movieList.forEach(new Consumer<MovieDBItem>() {
					@Override
					public void accept(MovieDBItem item) {
						if (item.getTitle().contains(term)) {
							results.add(item);
						}
					}
				});
			}
		});

		return results;
	}

	public MyLinkedList<MovieDBItem> items() {
		MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();

		this.list.forEach(new Consumer<MyLinkedList<MovieDBItem>>() {
			@Override
			public void accept(MyLinkedList<MovieDBItem> movieList) {
				movieList.forEach(new Consumer<MovieDBItem>() {
					@Override
					public void accept(MovieDBItem item) {
						results.add(item);
					}
				});
			}
		});

		return results;
	}
}
