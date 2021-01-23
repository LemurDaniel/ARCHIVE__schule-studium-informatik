package stack.test1;

import java.math.BigDecimal;
import java.util.Stack;
import java.util.function.*;
import java.util.stream.Stream;
import java.math.BigInteger;

public class StackTest3 implements StackR<Object> {

	private final static String[] Operators = {"+", "-", "*", "/", "%", "&", "|", "^", "log", "ln", "lg", "pot", "int", "long", "double", "BigInt"};
	private Stack<Object> stack = new Stack<>();
	// Für real time Simulation
	private Stack<Object> temp = new Stack<>();

	@Override
	public Number calcExp(String s, Consumer<String> cr) throws Exception {
		if(stack.isEmpty()) throw new Exception("Stack ist Leer");
		
		while(!stack.empty()) {
			temp.push(stack.pop());
		}
		
		while(!temp.empty()) {
			Object o = temp.pop();
			
			if(o.getClass().getSuperclass().equals(Number.class))
				stack.push(o);
			else
				cr.accept(calc(o.toString()));
		}
		return (Number)stack.pop();
	}

	private void parseExp(String s) throws Exception{
		s += " ";
		s = s.toLowerCase();
		System.out.println(s);
		String objekt="";
		boolean numberFlag = false;
		
		for(int i=0; i<s.length(); i++) {	
			char c = s.charAt(i);
			
			if(Character.isDigit(c)) numberFlag = true;
			else if(numberFlag) {
				//Wenn double
				if(c=='.' || c=='e' ) {
					int i2=i+1;
					innerLoop: for(; i2<s.length(); i2++) if(!Character.isDigit(s.charAt(i2))&&!(s.charAt(i2)=='e'&&c=='.')) break innerLoop;	// Zahlenreihe nach . und wenn . und folgendes e -> Zahlenreihe bis nach e
					objekt += c+s.substring(i+1, i2);
					stack.push(new Double(objekt));
//					Double d = Double.parseDouble(objekt);
//					if(d.isInfinite())	stack.push(new BigDecimal(objekt));
//					else 	stack.push(d);
					i = i2;
					c = s.charAt(i2);
				// Wenn Integer
				}else {
					try {
						stack.push(new Integer(objekt));
					}catch(NumberFormatException e) {
						try {
							stack.push(new Long(objekt));
						}catch(NumberFormatException e1) {
							stack.push(new BigInteger(objekt));
						}
					}
				}
				objekt = "";
				numberFlag = false;
			}
			
			if(c!=' ')	objekt += c;	
			if(matchesOP(objekt)) objekt = "";
		}
	}
	
	private String calc(String o) throws Exception{
		String s=null;	
		Number z1 = (Number)stack.pop(), 
				z2 = 0;
		
		// Konversion
		switch(o) {
		case "int": 	stack.push(z1.intValue()); s= "Int("+z1+") "; break;
		case "long": 	stack.push(z1.intValue()); s= "long("+z1+") "; break;
		case "double": 	stack.push(z1.intValue()); s= "double("+z1+") "; break;
		case "BigInt": 	stack.push(z1.intValue()); s= "BigInt("+z1+") "; break;
		}
		if(s!=null)
			return s+" = "+stack.peek()+" | --> "+stack.peek().getClass().getSimpleName()+"\n";
			
		if(!(o.equals("ln") || o.equals("lg")))
			z2 = (Number)stack.pop();
			
		Class<?> cZ1 = z1.getClass(),
					cZ2 = z2.getClass();
		if(cZ1.equals(Integer.class) && cZ2.equals(Integer.class)) 						s= calcInt(o, z1.intValue(), z2.intValue());
		else if(cZ1.equals(Long.class) || cZ2.equals(Long.class)
				&& !cZ1.equals(Double.class) && !cZ2.equals(Double.class)
				&& !cZ1.equals(BigInteger.class) && !cZ2.equals(BigInteger.class))		s= calcLong(o, z1.longValue(), z2.longValue());
			
		else if(cZ1.equals(BigInteger.class) || cZ2.equals(BigInteger.class)			
				&& !cZ1.equals(BigDecimal.class) && !cZ2.equals(BigDecimal.class)
				&& !cZ1.equals(Double.class) && !cZ2.equals(Double.class))				s= calcBigInt(o, new BigInteger(z1+""), new BigInteger(z2+""));
		
		else s= calcDouble(o, z1.doubleValue(), z2.doubleValue());	
	
		shrinkNumber();	
		return s + " | --> "+stack.peek().getClass().getSimpleName()+"\n";
	}
	
	private String calcInt(String o, Integer z1, Integer z2) throws Exception {
		String s = null;
		method<String> c = () ->{
			if(z1>0 && z2>0 && (int)stack.peek()<0 || z1<0 && z2<0 && (int)stack.peek()>0) {
				stack.pop();
				return calcLong(o, z1.longValue(), z2.longValue());
			}else return null;
		};
		switch(o) {
		case "+":	stack.push(z2+z1); s=c.apply();	break;
		case "-": 	stack.push(z2-z1); s=c.apply();	break;
		case "/": 	stack.push(z2/z1); s=c.apply();	break;
		case "*": 	stack.push(z2*z1); s=c.apply();	break;
		case "%": 	stack.push(z2%z1); break;
		case "&": 	stack.push(z2&z1); break;
		case "|": 	stack.push(z2|z1); break;
		case "^": 	stack.push(z2^z1); break;
		case "ln":		stack.push(Math.log(z1)); 
						s = "ln("+z1+") = "+stack.peek(); break;
						
		case "lg":		stack.push(Math.log10(z1));
						s = "lg("+z1+") = "+stack.peek(); break;
						
		case "log":  	stack.push(Math.log(z1)/Math.log(z2)); //log identity: log_n(b) = log_x(n)/log_x(b)
						s = "log_"+z2+"("+z1+") = "+stack.peek(); break;
						
		case "pot": 	stack.push(Math.pow(z1, z2));
						s = "pow("+z1+","+z2+") = "+stack.peek();
						String s2 = c.apply(); 
						s = s2==null ? s:s2; break;
		default:
			throw new Exception("Operator '"+o+"' nicht vorhanden für "+z1.getClass().getSimpleName());
		}
		if(s==null)
			s = String.format("%d%s%d = %d", z2, o, z1,stack.peek());
		
		
		return s;
	}

	private String calcBigInt(String o, BigInteger z1, BigInteger z2) throws Exception {
		String s = null;
		switch(o) {
		case "+":	stack.push(z2.add(z1)); break;
		case "-": 	stack.push(z2.subtract(z1)); break;
		case "/": 	stack.push(z2.divide(z1)); break;
		case "*": 	stack.push(z2.multiply(z1)); break;
		case "%": 	stack.push(z2.remainder(z1)); break;
		case "&": 	stack.push(z2.and(z1)); break;
		case "|": 	stack.push(z2.or(z1)); break;
		case "^": 	stack.push(z2.xor(z1)); break;
//		case "ln":		stack.push(Math.log(z1)); 
//						s = "ln("+z1+") = "+stack.peek(); break;
//						
//		case "lg":		stack.push(Math.log10(z1));
//						s = "lg("+z1+") = "+stack.peek(); break;
//						
//		case "log":  	stack.push(Math.log(z2)/Math.log(z1)); //log identity: log_n(b) = log_x(n)/log_x(b)
//						s = "log_"+z1+"("+z2+") = "+stack.peek(); break;
//						
		case "pot": 	stack.push(z2.pow(z1.intValue()));
						s = "pow("+z1+","+z2+") = "+stack.peek(); break;
		default:
			throw new Exception("Operator '"+o+"' nicht vorhanden für "+z1.getClass().getSimpleName());
		}
		if(s==null)
			s = String.format("%d%s%d = %d", z2, o, z1,stack.peek());
		
		try {
			Long i = new Long(stack.peek().toString());
			stack.pop();
			stack.push(i);
		}catch(NumberFormatException e) {
			
		}
		
		return s;
	}
	
	private String calcLong(String o, Long z1, Long z2) throws Exception {
		String s = null;
		method<String> c = () ->{
			if(z1>0 && z2>0 && (long)stack.peek()<0 || z1<0 && z2<0 && (long)stack.peek()>0) {
				stack.pop();
				return calcBigInt(o, new BigInteger(z1+""), new BigInteger(z2+""));
			}else return null;
		};
		switch(o) {
		case "+":	stack.push(z2+z1); s = c.apply(); break;
		case "-": 	stack.push(z2-z1); s = c.apply(); break;
		case "/": 	stack.push(z2/z1); s = c.apply(); break;
		case "*": 	stack.push(z2*z1); s = c.apply(); break;
		case "%": 	stack.push(z2%z1); break;
		case "&": 	stack.push(z2&z1); break;
		case "|": 	stack.push(z2|z1); break;
		case "^": 	stack.push(z2^z1); break;
		case "ln":		stack.push(Math.log(z1)); 
						s = "ln("+z1+") = "+stack.peek(); break;
						
		case "lg":		stack.push(Math.log10(z1));
						s = "lg("+z1+") = "+stack.peek(); break;
						
		case "log":  	stack.push(Math.log(z1)/Math.log(z2)); //log identity: log_n(b) = log_x(n)/log_x(b)
						s = "log_"+z2+"("+z1+") = "+stack.peek(); break;
						
		case "pot": 	stack.push(Math.pow(z1, z2));
						s = "pow("+z1+","+z2+") = "+stack.peek();
						String s2 = c.apply(); 
						s = s2==null ? s:s2; break;
		default:
			throw new Exception("Operator '"+o+"' nicht vorhanden für "+z1.getClass().getSimpleName());
		}
		if(s==null)
			s = String.format("%d%s%d = %d", z2, o, z1,stack.peek());
		
		return s;
	}
		
	private String calcDouble(String o, Double z1, Double z2) throws Exception {
		String s = null;
		switch(o) {
		case "+":	stack.push(z2+z1); break;
		case "-": 	stack.push(z2-z1); break;
		case "/": 	stack.push(z2/z1); break;
		case "*": 	stack.push(z2*z1); break;
		case "%": 	stack.push(z2%z1); break;

		case "ln":		stack.push(Math.log(z1)); 
						s = "ln("+z1+") = "+stack.peek(); break;
							
		case "lg":		stack.push(Math.log10(z1));
						s = "lg("+z1+") = "+stack.peek(); break;
							
		case "log":  	stack.push(Math.log(z1)/Math.log(z2)); //log identity: log_n(b) = log_x(n)/log_x(b)
						s = "log_"+z2+"("+z1+") = "+stack.peek(); break;
							
		case "pot": 	stack.push(Math.pow(z1, z2));
						s = "pow("+z1+","+z2+") = "+stack.peek(); break;
		default:
			throw new Exception("Operator '"+o+"' nicht vorhanden für "+z1.getClass().getSimpleName());
		}
		if(s==null)
			s = String.format("%f%s%f = %f", z2, o, z1,stack.peek());
			
		return s;
	}
	
	@SuppressWarnings("unused")
	private String calcBigD(String o, BigDecimal z1, BigDecimal z2) throws Exception {
		String s = null;
		switch(o) {
		case "+":	stack.push(z1.add(z2)); break;
		case "-": 	stack.push(z1.subtract(z2)); break;
		case "/": 	stack.push(z1.divide(z2)); break;
		case "*": 	stack.push(z1.multiply(z2)); break;
		case "%": 	stack.push(z1.remainder(z2)); break;
		//case "&": 	stack.push(z1&z2); break;
		//case "|": 	stack.push(z1|z2); break;
		//case "^": 	stack.push(z1^z2); break;
		//case "ln":		stack.push(Math.log(z1)); 
		//				s = "ln("+z1+") = "+stack.peek(); break;
							
		//case "lg":		stack.push(Math.log10(z1));
		//				s = "lg("+z1+") = "+stack.peek(); break;
							
		//case "log":  	stack.push(Math.log(z2)/Math.log(z1)); //log identity: log_n(b) = log_x(n)/log_x(b)
		//				s = "log_"+z1+"("+z2+") = "+stack.peek(); break;
							
		//case "pot": 	stack.push(z2.pow(z1.intValue()));
		//				s = "pow("+z2+z1+") = "+stack.peek(); break;
		default:
			throw new Exception("Operator '"+o+"' nicht vorhanden für "+z1.getClass().getSimpleName());
		}
		if(s==null)
			s = String.format("%d%s%d = %d", z1, o, z2,stack.peek());
			
		return s+"\n";
	}
	
	private void shrinkNumber() {
		if(stack.peek().getClass().equals(BigDecimal.class)) {
			try {
				Long i = new Long(stack.peek().toString());
				stack.pop();
				stack.push(i);
			}catch(NumberFormatException e) {			
			}
		}
		if(stack.peek().getClass().equals(Long.class)) {
			try {
				Integer i = new Integer(stack.peek().toString());
				stack.pop();
				stack.push(i);
			}catch(NumberFormatException e) {	
			}
		}
		
	}
	
	private boolean matchesOP(String s) {
		for(String op:Operators)
			if(s.equals(op.toLowerCase())) {
				stack.push(s);
				return true;
			}
		switch(s) {
		case "e": stack.push(Math.E); break;
		case "pi": stack.push(Math.PI); break;
		case "maxint": stack.push(Integer.MAX_VALUE); break;
		case "minint": stack.push(Integer.MIN_VALUE); break;
		case "maxlong": stack.push(Long.MAX_VALUE); break;
		case "minlong": stack.push(Long.MIN_VALUE); break;
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

	private interface method<T> {
		public T apply() throws Exception;
	}
}
