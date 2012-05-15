package transaction;

public interface AccountServiceInterface {

	public abstract void create(Account account);

	public abstract Account find(long id);
	
	
}