package multi;


public interface AccountService {

	public abstract void create(Account account);

	public Account findAccountCached(long id);
	
	
}