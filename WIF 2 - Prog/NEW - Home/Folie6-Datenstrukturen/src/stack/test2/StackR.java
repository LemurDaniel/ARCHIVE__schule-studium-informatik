package stack.test2;


import java.util.function.Consumer;
import java.util.stream.Stream;


public interface StackR<T> {
	public final static String RPtest = "- x / 15 - 7 + 1 1 3 + 2 + 1 1 ";  // erg = 5
	public final static String RPNtest = "15 7 1 1 + - / 3 x 2 1 1 + + - "; // erg = 5
	public final static String LoopTest = "#1((1+) 5pushloop push) 5loop pop";
	public final static int RPtestErg = 5;
	public final static int RPNtestErg = 5;
	public final static int ReversePolishNotation = 0;
	public final static int PolishNotation = 1;
	public final static int MaxInfo = 0;
	public final static int MinInfo = 1;
	
	public final static String[] Operators = {"+", "-", "*", "x", "/", ":", "%", "&", "|", "^", "log", "ln", "lg", "pot", "int", "long", "double", "bigint", "shrink", "push", "pop", "pushn", "popn", "loop", "pushloop", "cto", "addall"};
	public final static String[] Other = {"size", "e", "pi", "tau", "maxint", "minint", "maxlong", "minlong", "rpntest", "looptest", "rptest" };
	
	default public void calcExp(String s)	throws Exception { calcExp(s, e->{}); };
	public void calcExp(String s, Consumer<String> cr)	throws Exception ;
	
	public int getSize();
	public void printStack();
	public void clearStack();
	public T pop();
	public void push(String item)	throws Exception;
	public T peek();
	public Stream<T> stream();
	public void setModus(int i);
	public void setInfo(int i);
}
