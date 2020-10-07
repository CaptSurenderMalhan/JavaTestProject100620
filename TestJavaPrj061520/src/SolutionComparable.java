import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Sort Persons based on Age and if same Age then based on Name.
public class SolutionComparable {
	static ArrayList <Person> arrayPersons = new ArrayList();
    public static void main(String[] args) {
    	initPersons();
    	PersonComparator pc = new PersonComparator();
    	Collections.sort(arrayPersons, pc);
    	for (int i = 0 ; i < arrayPersons.size() ; i++)
    	{
	    	System.out.println( arrayPersons.get(i).age + "    " + arrayPersons.get(i).name );
    	}
    }
    static void  initPersons()
    {
    	arrayPersons.add(new Person( "Joe", 20));
    	arrayPersons.add(new Person( "Mary", 21));
    	arrayPersons.add(new Person( "Tom", 23));
    	arrayPersons.add(new Person( "Ann", 20));
    	arrayPersons.add(new Person( "Sam", 20));
    	arrayPersons.add(new Person( "Sandy", 23));
    }
}
class Person
{
	String name;
	int age;
	Person(String name, int age)
	{
		this.name = name;
		this.age = age;
	}
}
class PersonComparator implements Comparator
{
	@Override
	public int compare(Object o1, Object o2) {
		Person p1 = (Person) o1;
		Person p2 = (Person) o2;
		if (p1.age == p2.age)
			return p1.name.compareTo(p2.name);
		return p1.age - p2.age;
	}

	
}
