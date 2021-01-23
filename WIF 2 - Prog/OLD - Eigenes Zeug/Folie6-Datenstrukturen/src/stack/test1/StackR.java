package stack.test1;

import java.util.function.Consumer;
import java.util.stream.Stream;

public interface StackR<T> {
	default	public Number calcExp(String s)	throws Exception {
		return calcExp(s, e -> {});
	};
	public Number calcExp(String s, Consumer<String> cr)	throws Exception ;
	
	public int getSize();
	public void printStack();
	public void clearStack();
	public T pop();
	public void push(String item)	throws Exception;
	public T peek();
	public Stream<T> stream();
	
}
