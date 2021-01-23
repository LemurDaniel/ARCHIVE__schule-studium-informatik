package stack.test2;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.function.*;
import java.util.stream.Stream;

import java.math.BigInteger;

public class StackTest3 implements StackR<Object> {
	
	private Stack<Object> stack = new Stack<>();
	public Set<String> set = new HashSet<>();
	private int modus = 0, info = 0;
	
	public StackTest3() {
		for(int i=0; i<Operators.length; i++)
			set.add(Operators[i]);
		for(int i=0; i<Other.length; i++)
			set.add(Other[i]);
	}
	
	@Override
	public void calcExp(String s, Consumer<String> cr) throws Exception{
		s = " "+s+" ";
		s = s.toLowerCase();
		String objekt="";
		boolean numberFlag = false;
		boolean negate = false;
		int openbrackets = 0;
		
		for(int i=modus==PolishNotation? s.length()-1:0; i>=0 && i<s.length(); i = modus==PolishNotation ? --i:++i) {
			char c = s.charAt(i);
			if(c=='#')	{
				negate = true;
				continue;
			}
			
			if(Character.isDigit(c)) numberFlag = true;
			else if(numberFlag) {
				//Wenn double
				if(c=='.' || c=='e' ) {
					int i2=i;
					boolean comma = c=='.';
					boolean e = c=='e';
					if(modus == ReversePolishNotation) {
						innerLoop: 
						for(i2++; i2<s.length(); i2++) {
							char c2 = s.charAt(i2);
							if(!Character.isDigit(c2)) 
								if(c2=='.' && !e) {
									if(comma)	break innerLoop;	// Zahlenreihe nach . und wenn . und folgendes e -> Zahlenreihe bis nach e
									else comma = true;
								}else if(c2=='e') {
									if(e) break innerLoop;
									else e = true;
								}else
									break innerLoop;
						}
						objekt += s.substring(i, i2);
						
					}else if(modus == PolishNotation) {
						innerLoop: 
						for(i2--; i2>=0; i2--) {
							char c2 = s.charAt(i2);
							if(!Character.isDigit(c2)) 
								if(c2=='.') {
									if(comma)	break innerLoop;	
									else comma = true;
								}else if(c2=='e' && !comma) {
									if(e) break innerLoop;
									else e = true;
								}else
									break innerLoop;
						}
						objekt = s.substring(i2+1, i+1) + objekt;
					}
					//System.out.println(objekt+"   ");
					objekt = (negate? "-":"+") + objekt;
					stack.push(new Double(objekt));
					i = i2;
					c = s.charAt(i2);
				// Wenn Integer
				}else {
					objekt = (negate? "-":"+") + objekt;
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
				negate = false;
				if(info == MaxInfo) cr.accept(" Push -> "+stack.peek()+" | "+stack.peek().getClass().getSimpleName()+"\n");
			}
			
			// bracket stuff
			if(c=='(' && modus == ReversePolishNotation) {
				if(objekt.length()>0) throw new Exception(objekt +" nicht parsierbar");
				openbrackets++;
				while(openbrackets > 0) {
					i++;
					if(s.charAt(i)=='(') openbrackets++;
					else if(s.charAt(i)==')') if(--openbrackets==0)break;
					objekt += s.charAt(i);
				}		
				stack.push(objekt);
				objekt = "";
			}
			else if(c!=' ')	objekt = modus==PolishNotation? c+objekt:objekt+c;	
			if(set.contains(objekt)) {
				String objekt2 = objekt;
				for(int i2=i+1; i2<i+11 && i2<s.length(); i2++) {
					char c2 = s.charAt(i2);
					if(c2==' ')	break;
					if(Character.isDigit(c2)) break;
					objekt2 += c2;
					
					if(set.contains(objekt2)) {
						objekt = objekt2;
						i = i2;
					}
				}
				
				matchesSP(objekt, cr, negate);
				if(matchesOP(objekt, cr))
					cr.accept(calc(stack.pop().toString(), cr));
				
				objekt = "";
				numberFlag = false;
			}
			
//			if(matchesSP(objekt, cr, negate)) objekt = "";
//			else if(matchesOP(objekt, cr)) {
//				objekt = "";
//				numberFlag = false;
//				cr.accept(calc(stack.pop().toString(), cr));
//			}
			
			if(objekt.length()==0) negate = false;
		}
//		
//		if(modus == PolishNotation) {
//			temp = stack;
//			stack = new Stack<>();
//			while(!temp.empty()) {
//				Object o = temp.pop();
//				if(o.getClass().getSuperclass().equals(Number.class)) {
//					stack.push(o);
//					if(info == MaxInfo) {
//						cr.accept(" Push -> "+stack.peek()+" | "+stack.peek().getClass().getSimpleName()+"\n");
//					}			
//				}else {
//					if(matchesOP(o.toString(), cr)) {
//						modus = PolishNotation;
//						cr.accept(calc(stack.pop().toString()));
//						modus = ReversePolishNotation;
//					}else if(matchesSP(o.toString(), cr));
//				}
//
//			}
//			temp = null;
//			modus = PolishNotation;
//		}else
//			return;
	}
	
	private String calc(String o, Consumer<String> cr) throws Exception{
		String s="";
		String s2=null;
		Number z1 = (Number)stack.pop(),
				z2 = 0;
		
		// Konversion
		String ap = z1+" | "+z1.getClass().getSimpleName()+"\n";
		if(info == MaxInfo) s = " Pop -> "+ap;
		switch(o) {
		case "int": 	stack.push(z1.intValue()); s2= " Int("+z1+") = "; break;
		case "long": 	stack.push(z1.longValue()); s2= " Long("+z1+") = "; break;
		case "double": 	stack.push(z1.doubleValue()); s2= " Double("+z1+") = "; break;
		case "bigint": 	stack.push(new BigInteger(z1.longValue()+"")); s2= " BigInt("+z1+") = "; break;
		case "shrink":  stack.push(z1); shrinkNumber(); s2= " Shrink("+z1+") = "; break;
		case "push":	stack.push(z1); stack.push(z1); return " Push -> "+ap;
		case "pop":	return " Pop -> "+ap;
		}
		if(s2!=null) {
			if(info == MaxInfo) return s+= " Push -> "+stack.peek()+" | "+stack.peek().getClass().getSimpleName()+"\n";
			else return s2 + ap;
		}
		
		if(!(o.equals("ln") || o.equals("lg") || o.equals("loop") || o.equals("pushloop") || o.equals("popn") || o.equals("addall"))) {
			z2 = (Number)stack.pop();
			if(info == MaxInfo) s += " Pop -> "+z2+" | "+z2.getClass().getSimpleName()+"\n";
			if(modus == PolishNotation) {
				Number temp = z2;
				z2 = z1;
				z1 = temp;
			}
		}
		
		//Special
		ap = z1+" | "+z1.getClass().getSimpleName()+"\n";
		String exp;
		switch(o) {
		case "pushn": 	s2= String.format(" Push_%d(%s) -> %s (%dtimes) | %s\n", z1, z2+"", z2+"", z1, z1.getClass().getSimpleName());
						calcExp(z2+"", cr);
						for(int i=0; i<z1.intValue()-1; i++) 
							calcExp("push1", cr);
						break;
						
		case "popn": 	s2= String.format(" Pop_%d() \n", z1);
						for(int i=0; i<z1.intValue(); i++) 
							calcExp("pop1", cr); 
						break;
	
		case "loop":	exp = stack.pop().toString();
						cr.accept(String.format("Loop_%d(%s)\n", z1.intValue(), exp));
						for(int i=0; i<z1.intValue(); i++) 
							calcExp(exp, cr); 
						return String.format("End Loop_%d(%s)\n", z1.intValue(), exp);
						
		case "pushloop":	exp = stack.pop().toString();
							cr.accept(String.format("PushLoop_%d(%s)\n", z1.intValue(), exp));
							for(int i=0; i<z1.intValue(); i++) {
								calcExp(exp, cr); 
								if(i<z1.intValue()-1)stack.push(stack.peek());
							}
							return String.format("End PushLoop_%d(%s)\n", z1.intValue(), exp);
		
		case "cto":		cr.accept(String.format("CountTo_%d(%d)\n", z1.intValue(), z2.intValue()));
						calcExp(z2+" 1-", cr);
						calcExp("(1+)", cr);
						calcExp(z1+" 1+", cr);
						calcExp("phloop", cr);
						return String.format("End CountTo_%d(%d)\n", z1.intValue(), z2.intValue());
		
		case "addall":	cr.accept("AddAll()\n");
						if(z1!=null) stack.push(z1);
						calcExp("(0) 2 loop", cr);
						calcExp("(+) size 2- loop", cr);
						return "";
		}
		if(s2!=null) {
			//if(info == MaxInfo) return s+= " Push -> "+stack.peek()+" | "+stack.peek().getClass().getSimpleName()+"\n";
			return s2;
		}
			
		Class<?> cZ1 = z1.getClass(),
					cZ2 = z2.getClass();
		if(cZ1.equals(Integer.class) && cZ2.equals(Integer.class)) 						s2= calcInt(o, z1.intValue(), z2.intValue());
		else if(cZ1.equals(Long.class) || cZ2.equals(Long.class)
				&& !cZ1.equals(Double.class) && !cZ2.equals(Double.class)
				&& !cZ1.equals(BigInteger.class) && !cZ2.equals(BigInteger.class))		s2= calcLong(o, z1.longValue(), z2.longValue());
			
		else if(cZ1.equals(BigInteger.class) || cZ2.equals(BigInteger.class)			
				&& !cZ1.equals(BigDecimal.class) && !cZ2.equals(BigDecimal.class)
				&& !cZ1.equals(Double.class) && !cZ2.equals(Double.class))				s2= calcBigInt(o, new BigInteger(z1+""), new BigInteger(z2+""));
		
		else s2= calcDouble(o, z1.doubleValue(), z2.doubleValue());	
	
		shrinkNumber();	
		s2 += " | "+stack.peek().getClass().getSimpleName()+"\n";
		s+= " Push -> "+stack.peek()+" | "+stack.peek().getClass().getSimpleName()+"\n";
		if(info == MaxInfo)	return s;
		return s2;
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
		case ":":
		case "/": 	stack.push(z2/z1); s=c.apply();	break;
		case "x":
		case "*": 	stack.push(z2*z1); s=c.apply();	break;
		case "%": 	stack.push(z2%z1); break;
		case "&": 	stack.push(z2&z1); break;
		case "|": 	stack.push(z2|z1); break;
		case "^": 	stack.push(z2^z1); break;
		case "ln":		stack.push(Math.log(z1)); 
						s = " ln("+z1+") = "+stack.peek(); break;
						
		case "lg":		stack.push(Math.log10(z1));
						s = " lg("+z1+") = "+stack.peek(); break;
						
		case "log":  	stack.push(Math.log(z1)/Math.log(z2)); //log identity: log_n(b) = log_x(n)/log_x(b)
						s = " log_"+z2+"("+z1+") = "+stack.peek(); break;
						
		case "pot": 	stack.push(Math.pow(z1, z2));
						s = " pow("+z1+","+z2+") = "+stack.peek();
						String s2 = c.apply(); 
						s = s2==null ? s:s2; break;
		default:
			throw new Exception("Operator '"+o+"' nicht vorhanden für "+z1.getClass().getSimpleName());
		}
		if(s==null)
			s = String.format(" %d%s%d = %d", z2, o, z1,stack.peek());
		
		
		return s;
	}

	private String calcBigInt(String o, BigInteger z1, BigInteger z2) throws Exception {
		String s = null;
		switch(o) {
		case "+":	stack.push(z2.add(z1)); break;
		case "-": 	stack.push(z2.subtract(z1)); break;
		case ":":
		case "/": 	stack.push(z2.divide(z1)); break;
		case "x":
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
						s = " pow("+z1+","+z2+") = "+stack.peek(); break;
		default:
			throw new Exception("Operator '"+o+"' nicht vorhanden für "+z1.getClass().getSimpleName());
		}
		if(s==null)
			s = String.format(" %d%s%d = %d", z2, o, z1,stack.peek());
		
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
		case ":":
		case "/": 	stack.push(z2/z1); s=c.apply();	break;
		case "x":
		case "*": 	stack.push(z2*z1); s = c.apply(); break;
		case "%": 	stack.push(z2%z1); break;
		case "&": 	stack.push(z2&z1); break;
		case "|": 	stack.push(z2|z1); break;
		case "^": 	stack.push(z2^z1); break;
		case "ln":		stack.push(Math.log(z1)); 
						s = " ln("+z1+") = "+stack.peek(); break;
						
		case "lg":		stack.push(Math.log10(z1));
						s = " lg("+z1+") = "+stack.peek(); break;
						
		case "log":  	stack.push(Math.log(z1)/Math.log(z2)); //log identity: log_n(b) = log_x(n)/log_x(b)
						s = " log_"+z2+"("+z1+") = "+stack.peek(); break;
						
		case "pot": 	stack.push(Math.pow(z1, z2));
						s = " pow("+z1+","+z2+") = "+stack.peek();
						String s2 = c.apply(); 
						s = s2==null ? s:s2; break;
		default:
			throw new Exception("Operator '"+o+"' nicht vorhanden für "+z1.getClass().getSimpleName());
		}
		if(s==null)
			s = String.format(" %d%s%d = %d", z2, o, z1,stack.peek());
		
		return s;
	}
		
	private String calcDouble(String o, Double z1, Double z2) throws Exception {
		String s = null;
		switch(o) {
		case "+":	stack.push(z2+z1); break;
		case "-": 	stack.push(z2-z1); break;
		case ":":
		case "/": 	stack.push(z2/z1);	break;
		case "x":
		case "*": 	stack.push(z2*z1); break;
		case "%": 	stack.push(z2%z1); break;

		case "ln":		stack.push(Math.log(z1)); 
						s = " ln("+z1+") = "+stack.peek(); break;
							
		case "lg":		stack.push(Math.log10(z1));
						s = " lg("+z1+") = "+stack.peek(); break;
							
		case "log":  	stack.push(Math.log(z1)/Math.log(z2)); //log identity: log_n(b) = log_x(n)/log_x(b)
						s = " log_"+z2+"("+z1+") = "+stack.peek(); break;
							
		case "pot": 	stack.push(Math.pow(z1, z2));
						s = " pow("+z1+","+z2+") = "+stack.peek(); break;
		default:
			throw new Exception("Operator '"+o+"' nicht vorhanden für "+z1.getClass().getSimpleName());
		}
		if(s==null)
			s = String.format(" %f%s%f = %f", z2, o, z1,stack.peek());
			
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
		if(stack.peek().getClass().equals(BigInteger.class)) {
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
	
	private boolean matchesOP(String s, Consumer<String> cr) {
		for(String op:Operators)
			if(s.equals(op.toLowerCase())) {
				stack.push(s);
				if(info == MaxInfo) cr.accept(" OP -> "+stack.peek()+"\n");
				return true;
			}
		return false;
	}
	private boolean matchesSP(String s, Consumer<String> cr, boolean neg) throws Exception {
		switch(s) {
		case "e": 			stack.push(neg? -Math.E:Math.E);		break;
		case "pi": 			stack.push(neg? -Math.PI:Math.PI);		break;
		case "tau": 		stack.push(neg? -Math.PI*2:Math.PI*2);	break;
		case "maxint":		stack.push(neg? -Integer.MAX_VALUE:Integer.MAX_VALUE); 	break;
		case "minint": 		stack.push(neg? -Integer.MIN_VALUE:Integer.MIN_VALUE); 	break;
		case "maxlong": 	stack.push(neg? -Long.MAX_VALUE:Long.MAX_VALUE); 		break;
		case "minlong": 	stack.push(neg? -Long.MIN_VALUE:Long.MIN_VALUE); 		break;
		case "size": 		stack.push(neg? -stack.size():stack.size()); 			break;
		case "rpntest":		cr.accept(" OP -> "+s.toUpperCase()+"\n"); 	calcExp(RPNtest, cr);	return true;
		case "rptest": 	 	cr.accept(" OP -> "+s.toUpperCase()+"\n");	calcExp(RPtest, cr); 	return true;
		case "looptest": 	cr.accept(" OP -> "+s.toUpperCase()+"\n");	calcExp(LoopTest, cr); 	return true;
		default:
			return false;
		}
		if(info == MaxInfo) {
			cr.accept(" OP -> "+s.toUpperCase()+"\n");
			cr.accept(" Push -> "+stack.peek()+" | "+stack.peek().getClass().getSimpleName()+"\n");
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
		calcExp(item);
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


	@Override
	public void setModus(int i) {
		if(i==ReversePolishNotation) modus = i;
		else if(i==PolishNotation) modus = i;
	}

	@Override
	public void setInfo(int i) {
		if(i==MaxInfo) info = i;
		else if(i==MinInfo) info = i;
	}
}
