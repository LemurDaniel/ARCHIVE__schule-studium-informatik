package stack.test4;


import java.util.stream.Stream;


public interface StackR<T> {
	public final static String RPtest = "- x / 15 - 7 + 1 1 3 + 2 + 1 1 ";  // erg = 5
	public final static String RPNtest = "15 7 1 1 + - / 3 x 2 1 1 + + - "; // erg = 5
	public final static String LoopTest = "1#((1+) 5pushloop push) 5loop pop";
	public final static String EndifLoopTest = "0 (vara) map 0 (1+ push push (varA) map) (varA 100 <) endifloop";
	public final static String SaveAllTest = "( (save) (size 0 !=) endifloop ) (SaveAll) map";
	public final static int RPtestErg = 5;
	public final static int RPNtestErg = 5;
	public final static int MaxInfo = 0;
	public final static int MinInfo = 1;
	
	public final static String[] Operators = {"+", "-", "*", "x", "/", ":", "%", "&", "|", "^", "log", "ln", "lg", "pot", "int", "long", "double", "bigint", "shrink", "push", "pop", "pushn", "popn", "loop", "loopall", "pushloop", "cto", "save", "load", "clear", "sclear", "evloop", };
	public final static String[] Other = {"size", "sizes", "e", "pi", "tau", "maxint", "minint", "maxlong", "minlong", "rpntest", "looptest", "rptest", "test" };
	public final static String[] Tests = {"-1 (=1+) 20 pushn", "",
			"0 else 1 (1 1 ==) elseif 2 (2 2 ==) elseif 3 (3 3 ==)  if ",
			"0 else 1 (1 1 ==) elseif 2 (2 2 ==) elseif 3 (3 3 !=)  if ",
			"0 else 1 (1 1 ==) elseif 2 (2 2 !=) elseif 3 (3 3 !=)  if ",
			"0 else 1 (1 1 !=) elseif 2 (2 2 !=) elseif 3 (3 3 !=)  if ",
			"1(.1+) (size 20 <=) endifloop",
			" ((Nope)) else ((Yes)) (((12 12+) 25<) (1 1 +) ==) if",
			"(size) 50 pushn",
			"50 (maxloopsize) map",
	};
	
	
	public final static String[] OperatorsHelp = {"+", "-", "*", "x", "/", ":", "%", "&", "|", "^", "log", "ln", "lg", "pot", "int", "long", "double", "bigint", "shrink", "n push", "pop", "n n pushn", "n popn", "loop", "loopall", "pushloop", "cto", "save", "load", "clear", "sclear", "evloop"};
	public void calcExp(String s)	throws Exception ;
	
	public int getSize();
	public int getSizeS();
	public void printStack();
	public void clearStack();
	public T pop();
	public void push(String item)	throws Exception;
	public T peek();
	public Stream<T> streamStack();
	public Stream<T> streamSaveStack();
	public void setInfo(int i);
	public void saveLog();
}
