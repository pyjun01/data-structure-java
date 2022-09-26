public interface IndexInterface<T> {
  public T search(Comparable x);
  public void insert(Comparable x);
  public void delete(Comparable x);
  public boolean isEmpty();
  public void clear();
}
