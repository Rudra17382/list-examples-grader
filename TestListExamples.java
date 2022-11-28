import static org.junit.Assert.*;
import org.junit.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class TestListExamples {
  // Write your grading tests here!
  @Test
  public void filterCorrectly() {
    List<String> i = new ArrayList<String>();
    List<String> endList = new ArrayList<String>();
    i.add("1Hello");
    i.add("We");
    i.add("1Are");
    i.add("Here");
    StringChecker wc = new ObjChecker();
    ListExamples.filter(i, wc);
    endList.add("1Hello");
    endList.add("1Are");
    assertEquals(endList,ListExamples.filter(i,wc));
  }

    @Test
    public void correctOrderTest(){
      List<String> list1 = new ArrayList<String>();
      List<String> list2 = new ArrayList<String>();
      list1.add("brownies");
      list1.add("cakes");
      list1.add("cookies");
      list2.add("broccoli");
      list2.add("carrots");
      list2.add("spinach");
      List<String> list3 = ListExamples.merge(list1, list2);
      String[] expected = {"broccoli", "brownies","cakes","carrots","cookies","spinach"};
      assertArrayEquals(expected, list3.toArray());
  }

  @Test
    public void correctOrderTest2(){
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        list1.add("brownies");
        list1.add("cakes");
        list1.add("cookies");      //duplicate entry
        list2.add("broccoli");
        list2.add("carrots");
        list2.add("cookies");     //duplicate entry
        list2.add("spinach");
        List<String> list3 = ListExamples.merge(list1, list2);
        String[] expected = {"broccoli", "brownies", "cakes", "carrots", "cookies", "cookies", "spinach"};
        assertArrayEquals(expected, list3.toArray());
    }
}

class ObjChecker implements StringChecker{

  public boolean checkString(String s){
    if(Character.isDigit(s.charAt(0))){
      return true;
    }else{
    return false;
    }  
  }
}