import java.util.*;
import java.util.stream.Collectors;
public class UBSTest092520WM { // * * * * * WALTER MARIN * * * * * *
	private Map<String, Integer> accountsMap;
	public UBSTest092520WM() {
		accountsMap = new HashMap<String, Integer>();
	}
	void deposit(String accountId, int amount) {
		Integer totalAmount = accountsMap.get(accountId);
		if(totalAmount == null) {
			totalAmount = new Integer(0);
		}
		totalAmount += amount;
		this.accountsMap.put(accountId, totalAmount);
	}
	Collection<String> getTopAccounts(int limit) {
		Comparator<Map.Entry<String, Integer>> amtComparator = (entry1, entry2) -> Integer.compare(entry2.getValue(),entry1.getValue());
		List<Map.Entry<String, Integer>> entryList = new ArrayList<>(accountsMap.entrySet());
		Collections.sort(entryList, amtComparator);
		int size = entryList.size();
		if (limit < size) {
			size = limit;
		}
		Collection<String> topAccountsCollection = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			topAccountsCollection.add(entryList.get(i).getKey());
		}
		return topAccountsCollection;
	}
	Collection<String> getTopAccounts1(int nLimit) { // accountsMap = new HashMap<String, Integer>();
		Collection<String> topAccountsCollection = accountsMap.entrySet().stream()
				.sorted((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()))
				.limit(nLimit)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
		return topAccountsCollection;
	}
	public static void main(String[] args) {
		UBSTest092520WM service = new UBSTest092520WM();
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