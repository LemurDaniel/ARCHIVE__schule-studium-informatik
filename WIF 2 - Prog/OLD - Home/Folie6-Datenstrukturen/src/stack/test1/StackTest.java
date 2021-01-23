package stack.test1;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class StackTest implements StackR<Integer>{
	
	private Stack<Integer> stack = new Stack<>();
		
	public Number calcExp(String string, Consumer<String> cr) throws Exception{
		String op[] = string.split("\\s");
		for(int i=0; i<op.length; i++) System.out.println(op[i]);
		
		for(int i=0; i<op.length; i++) {
			// Push Zahlen in stack
			if(op[i].matches("\\d+"))
				stack.push( Integer.parseInt(op[i]) );
			
			//Rechnoperationen ausführen
			else 
				for(int i2=0; i2<op[i].length(); i2++) cr.accept( calc(op[i].charAt(i2)) );		
			}
		
			if(stack.size()>1)
				throw new Exception("Es sind noch "+stack.size() +" Elemente im Stack");
			return stack.pop();
		}
		
		private String calc(char c) throws Exception {
			if(stack.size()<2)
				throw new Exception("Um eine Operation auszuführen müssen min. 2 Elemente im Stack sein");
			int z1 = stack.pop(), z2 = stack.pop();
			switch(c) {
			case '+': 	stack.push(z1+z2); 	break;
			case '-': 	stack.push(z1-z2);	break;
			case '/':	stack.push(z1/z2);	break;
			case '*':	stack.push(z1*z2);	break;
			case '%': 	stack.push(z1%z2);	break;
			case '&': 	stack.push(z1&z2);	break;
			case '|': 	stack.push(z1|z2);	break;
			case '^': 	stack.push(z1^z2);	break;
			default:
				throw new Exception("Operator "+c+" nicht vorhanden");
			}
			return String.format("%d %s %d = %d \n", z1, c , z2, stack.peek());
		}
		
		public void clearStack() {
			stack.clear();
		}
		public int getSize() {
			return stack.size();
		}
		public void printStack() {
			@SuppressWarnings("unchecked")
			Stack<Integer> sclone = (Stack<Integer>)stack.clone();
			while(!sclone.isEmpty())	System.out.print(sclone.pop().toString()+", ");
		}

		@Override
		public Integer pop() {
			return stack.pop();
		}

		@Override
		public void push(String item) throws Exception {
			stack.push(Integer.parseInt(item));
		}

		@Override
		public Integer peek() {
			return stack.peek();
		}

		@Override
		public Stream<Integer> stream() {
			return stack.stream();
		}

}
