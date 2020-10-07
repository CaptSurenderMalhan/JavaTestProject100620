import java.util.*;
import java.util.stream.Collectors;
public class UBSTest092520
{
    public static void main(String[] args) {
    	LinkedList l = new LinkedList();
        AccountStatisticsCollector service = new AccountStatisticsCollector();
        service.deposit("A", 1);
        service.deposit("A", 2);
        service.deposit("B", 5);
        service.deposit("C", 10);
        service.deposit("C", 12);
        service.deposit("D", 1);
        Collection<String> top = service.getTopAccounts(5);
        for (String line : top) {
          System.out.println(line);
        }
  }	
}
class AccountStatisticsCollector 
{
	HashMap <String, AccountVO>  hashMapAccountVo = new HashMap<>();
	HashMap <String, Integer>  acctHmap1 = new HashMap<>();
    void deposit(String accountId, int amount) 
    {
      AccountVO accountVo = hashMapAccountVo.get(accountId);
      if ( accountVo == null)
      {
        accountVo = new AccountVO();
        accountVo.accountId = accountId;
        accountVo.amount = amount;
        hashMapAccountVo.put(accountId, accountVo);
      }
      else
      {
        accountVo.amount += amount;
      }
    }
    Collection<String> getTopAccounts(int nLimit) {
        List <String> listTopAccts = hashMapAccountVo.values().stream()
        		.sorted(Comparator.comparingInt( AccountVO::getAmount).reversed())
        		.limit(nLimit)
        		.map(AccountVO::getAccountId)
        		.collect(Collectors.toList());
        return listTopAccts;
    }    
    Collection<String> getTopAccounts4(int nLimit) {
        List <String> listTopAccts = hashMapAccountVo.values().stream()
        		.sorted((acctVo1, acctVo2) -> (acctVo2.amount - acctVo1.amount))
        		.limit(nLimit)
        		.map(AccountVO::getAccountId)
        		.collect(Collectors.toList());
        return listTopAccts;
    }
    Collection<String> getTopAccounts3(int nLimit) {
        List <String> listTopAccts = hashMapAccountVo.values().stream()
        		.sorted( ( acctVo1, acctVo2 ) -> Integer.compare( acctVo2.amount , acctVo1.amount) )
        		.limit( nLimit )
        		.map( acctVo -> acctVo.accountId)  // OR--  .map( acctVo -> acctVo.getAccountId()) 
        		.collect( Collectors.toList() );
        return listTopAccts;
    }    
    Collection<String> getTopAccounts2(int nLimit) {
        //List <AccountVO> acctColl =  (ArrayList) acctHmap.values(); // Will throw ClassCastException.
        Collection <AccountVO> acctColl =  hashMapAccountVo.values(); // returns all Values as a Collection.
        List <AccountVO> listAcctVo = new ArrayList( acctColl );
        Collections.sort( listAcctVo, new AccountComparator() );
        List <String> listTopAccts = new ArrayList(); 
        if ( nLimit > listAcctVo.size())
        	nLimit = listAcctVo.size(); // we can as well return listAcctVo, though as per API we need to return list of Strings
        for (int i = 0; i < nLimit; i++)
        {
            listTopAccts.add(listAcctVo.get(i).accountId);
        }
        return listTopAccts;
    }
    Collection<String> getTopAccounts1(int nLimit) 
    { // Algorithm - NOT using Streams
        Collection <AccountVO> acctColl =  hashMapAccountVo.values();
        AccountVO  [ ] arr = acctColl.toArray( new AccountVO[ acctColl.size() ] ); 
        AccountVO  [ ] arr1 = acctColl.toArray( new AccountVO[ 0 ] ); // This Works Too!
        Arrays.sort( arr, new AccountComparator() );
        List <String> topAccts = new ArrayList(); 
        for (int i = 0; i < nLimit; i++) // Or >>> for (int i = 0; ( (i < nLimit) && (i < arr.length) ) ; i++) 
        {
          if (i < arr.length)
            topAccts.add(arr[i].accountId);
        }
        return topAccts;
    }    
}
class AccountComparator implements Comparator <AccountVO>
{
	@Override
	public int compare(AccountVO o1, AccountVO o2) {
		return  (o2.amount - o1.amount);
	}
}
class AccountVO
{
	 String accountId;
	 int amount;
	 String getAccountId() { return accountId; }
	 int getAmount() { return amount; }
}

