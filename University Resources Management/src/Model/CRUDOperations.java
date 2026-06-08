package Model;

public abstract class CRUDOperations<T> {
    public abstract void create(T object);
    public abstract void delete(String id);
    public abstract void update(T object);;
    
}
