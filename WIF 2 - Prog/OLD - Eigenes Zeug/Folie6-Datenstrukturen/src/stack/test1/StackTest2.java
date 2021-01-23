package stack.test1;

import java.util.Stack;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class StackTest2 implements StackR<Object> {

	private final static String[] Operators = {"+", "-", "*", "/", "%", "&", "|", "^", "log", "ln", "lg", "pot"};
	private Stack<Object> stack = new Stack<>();

	@Override
	public Number calcExp(String s, Consumer<String> cr) throws Exception {
		if(stack.isEmpty()) throw new Exception("Stack ist Leer");
		
		while(stack.size()>1) {
			Object o = stack.pop();
			if(o.getClass().equals(Integer.class)) {
				//cr.accept("Pop -> "+o);
				throw new Exception(o+"ist kein Operator | Rechnung endet hier");
			}
			cr.accept(calc(o.toString()));
		}
		return (Number)stack.pop();
	}
	
	@SuppressWarnings("unused")
	private void parseExp2(String s) throws Exception{
		String objekt = "";
		boolean flagNumber = false;
		
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);

			if(Character.isDigit(c)) {
				// Bei Wechsel von Zeichen auf Zahl
				if(objekt.length()>0 && !flagNumber) {
					if(matchesOP(objekt))	stack.push(objekt);
					else throw new Exception("Operator '"+objekt+"' existiert nicht");
					objekt = "";
				}
				flagNumber = true;
				objekt += c;						
			// Nach Abtrennnung durch Leerzeichen und Objekt vorhanden
			}else if(c == ' ') {
				if(objekt.length()==0)	continue;
				
				// Wenn keine Zahl und somit operator
				if(!flagNumber) {
					if(matchesOP(objekt))	stack.push(objekt);
					else throw new Exception("Operator '"+objekt+"' existiert nicht");
				// Wenn Zahl
				}else {
					stack.push(Integer.parseInt(objekt));
				}
				objekt = "";
				flagNumber = false;
			
			}else {
				if(flagNumber) {
					stack.push(Integer.parseInt(objekt));			
					objekt = "";
				}
				flagNumber = false;
				if(matchesOP(objekt)) {
					stack.push(objekt);
					objekt = "";
				}else
					objekt += c;
			}	
		}
		
		if(objekt.length()>0) {
			if(objekt.matches("\\d+")) stack.push(Integer.parseInt(objekt));
			else if(matchesOP(objekt)) stack.push(objekt);
			else throw new Exception("Operator '"+objekt+"' existiert nicht");
		}
	}
	
	private void parseExp(String s) throws Exception{
		s += " ";
		String objekt="";
		boolean numberFlag = false;
		char c;
		
		for(int i=0; i<s.length(); i++) {	
			c = s.charAt(i);
			
			if(Character.isDigit(c)) numberFlag = true;
			else if(numberFlag) {
				stack.push(Integer.parseInt(objekt));
				objekt = "";
				numberFlag = false;
			}
			
			if(c!=' ')	objekt += c;	
			if(matchesOP(objekt)) objekt = "";
		}
	}
	
	private String calc(String o) throws Exception{
		int z1 = ((Number)stack.pop()).intValue(), 
				z2 = 0;
		if(!(o.equals("ln") && o.equals("lg")))
			z2 = ((Number)stack.pop()).intValue();
				
		String s = null;
		switch(o) {
		case "+":	stack.push(z1+z2); break;
		case "-": 	stack.push(z1-z2); break;
		case "/": 	stack.push(z1/z2); break;
		case "*": 	stack.push(z1*z2); break;
		case "%": 	stack.push(z1%z2); break;
		case "&": 	stack.push(z1&z2); break;
		case "|": 	stack.push(z1|z2); break;
		case "^": 	stack.push(z1^z2); break;
		case "ln":		stack.push(Math.log(z1)); 
						s = "ln("+z1+") = "+stack.peek(); break;
						
		case "lg":		stack.push(Math.log10(z1));
						s = "lg("+z1+") = "+stack.peek(); break;
						
		case "log":  	stack.push(Math.log(z2)/Math.log(z1)); //log identity: log_n(b) = log_x(n)/log_x(b)
						s = "log_"+z1+"("+z2+") = "+stack.peek(); break;
						
		case "pot": 	stack.push(Math.pow(z2, z1));
						s = "pow("+z2+z1+") = "+stack.peek(); break;
		default:
			throw new Exception("Operator '"+o+"' nicht vorhanden");
		}
		if(s==null)
			s = String.format("%d%s%d = %d", z1, o, z2,stack.peek());
		
		System.out.println(s);
		return s+"\n";
	}
	
	private boolean matchesOP(String s) {
		s.toLowerCase();
		for(String op:Operators)
			if(s.equals(op.toLowerCase())) {
				stack.push(s);
				return true;
			}
		switch(s) {
		case "e": stack.push(Math.E); break;
		case "pi": stack.push(Math.PI); break;
		default:
			return false;
		}
		return true;
	}

	@Override
	public int getSize() {
		return stack.size();
	}

	@Override
	public void printStack() {
		System.out.println("Top of Stack:");
		stack.forEach(e-> System.out.println(e +"->"+ e.getClass().getSimpleName()));
	}

	@Override
	public void clearStack() {
		stack.clear();
	}

	@Override
	public Object pop() {
		return stack.pop();
	}

	@Override
	public void push(String item) throws Exception{
		parseExp(item);
	}

	@Override
	public Object peek() {
		return stack.peek();
	}

	@Override
	public Stream<Object> stream() {
		return stack.stream();
	}
	
}
