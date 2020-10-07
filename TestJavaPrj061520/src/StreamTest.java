import java.util.*; 
import java.util.stream.Collectors; 
public class StreamTest {  // Stream Test 10/06/20 -Tue
    public static void main(String[] args) 
    { 
    	test1();
    	test2();
    } 
    static void test1()
    {
        List<Student> listStudents = new ArrayList<>(); 
        listStudents.add(new Student(1, "Tom1"));   listStudents.add(new Student(1, "Tom2")); 
        listStudents.add(new Student(2, "Mary"));     listStudents.add(new Student(3, "Tom1")); 
        // create map with the help of Collectors.toMap() method 
        LinkedHashMap<Integer, String> linkedHashMap =  listStudents.stream().collect( 
        									Collectors.toMap( Student::getId, Student::getName, 
                                  				( oldVal, newVal ) -> oldVal + ", " + newVal,   LinkedHashMap::new)); 
        linkedHashMap.forEach( ( key, value ) -> System.out.println( key + " = " + value ) );     	
    } //  Output:   1 = Tom1, Tom2   ;  	2 = Mary  ; 	3 = Tom1
    static void test2()
    {
    	List<Student> listStudents = new ArrayList<Student>(); 
        listStudents.add(new Student(1, "Tom1"));  listStudents.add(new Student(1, "Tom2")); 
        listStudents.add(new Student(2, "Mary1"));  listStudents.add(new Student(2, "Mary2")); 
        Map< Integer, List< String> > mapOfListofValues = listStudents.stream()
                           				.collect( Collectors.groupingBy(  Student::getId, 
                                       					Collectors.mapping( Student::getName, Collectors.toList()))); 
        System.out.println("Map of List of Values = " + mapOfListofValues);     	
    }  // Output:   MultiMap = {  1 = [Tom1, Tom2], 2 = [Mary1, Mary2]  }
} 
class Student {  // Stream Test 10/06/20 -Tue
    private Integer id;  private String name; 
    public Student(Integer id, String name) 
    { this.id = id;  this.name = name; } 
    public Integer getId() 
    {   return id; } 
    public String getName() 
    {  return name; } 
} 