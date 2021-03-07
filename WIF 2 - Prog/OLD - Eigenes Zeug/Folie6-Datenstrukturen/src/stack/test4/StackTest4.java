package stack.test4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.*;
import java.util.stream.Stream;

import javax.crypto.EncryptedPrivateKeyInfo;
import javax.swing.JFileChooser;


import java.io.PrintWriter;
import java.math.BigInteger;

public class StackTest4 implements StackR<Object> {
	
	private Stack<Object> stack;
	private Stack<Object> saveStack;
	private Map<String, Op> map;
	private int info = 0;
	private Consumer<String> cr;
	private List<String> list = new ArrayList<>();
	private boolean disableNextInfo = false;
	
	@SuppressWarnings("serial")
	public StackTest4(Consumer<String> cr) {
		this.cr = cr;
		saveStack = new Stack<>();
		stack = new Stack<Object>() {
			
			@Override
			public Object push(Object o) {
				o = super.push(o);
				if(!disableNextInfo && info==MaxInfo)	cr.accept(String.format(" Push -> %s | %s", o.toString(), o.getClass().getSimpleName()));
				return o;
			}
			@Override
			public Object pop() {
				Object o = super.pop();
				if(!disableNextInfo && info==MaxInfo)	cr.accept(String.format(" Pop  -> %s | %s", o.toString(), o.getClass().getSimpleName()));
				return o;
			}
		};
		map =new HashMap<String, Op>() {
			@Override
			public Op get(Object key) {
				Object o = super.get(key);
				if(!disableNextInfo && info==MaxInfo)	cr.accept(String.format(" OP  -> %s", key.toString()));
				return (Op)o;
			}
		};
		
		Op op = () -> { cr.accept("Operation not implemented"); };
		for(int i=0; i<Operators.length; i++)
			map.put(Operators[i], op);
		for(int i=0; i<Other.length; i++)
			map.put(Other[i], op);
		
		setStandarOP();
	}
	
	@Override
	public void calcExp(String s) throws Exception{
		list.add(s);
		s = " "+s+" ";
		s = s.toLowerCase();
		String objekt="";
		boolean numberFlag = false;
		int openbrackets = 0;
		boolean negate = false;
		
		for(int i=0; i>=0 && i<s.length(); i++) {
			char c = s.charAt(i);
			
			if(Character.isDigit(c) ) numberFlag = true;
			else if(c=='-'&& Character.isDigit(s.charAt(i+1))) {
				numberFlag = true;
				negate = true;
				objekt = "";
				continue;
			}else if(numberFlag) {				
				//Wenn double
				if(c=='.' || c=='e' ) {
					int i2=i;
					boolean comma = c=='.';
					boolean e = c=='e';
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
					Double d = new Double(objekt);
					stack.push(negate? -d:d);
					i = i2;
					c = s.charAt(i2);
					
				// Wenn Integer
				}else {
					try {
						Integer intz = new Integer(objekt);
						stack.push(negate? -intz:intz);
					}catch(NumberFormatException e) {
						try {
							Long longz = new Long(objekt);
							stack.push(negate? -longz:longz);
						}catch(NumberFormatException e1) {
							BigInteger big = new BigInteger(objekt);
							stack.push(negate? big.negate():big);
						}
					}
				}
				objekt = "";
				numberFlag = false;	
				negate = false;
			}
			
			// bracket stuff
			if(c=='(') {
				if(objekt.length()>0) objekt="";//throw new Exception(objekt +" nicht parsierbar");
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
			else if(c!=' ')	objekt += c;	
			
			// contains Bla
			if(map.containsKey(objekt)) {
				String objekt2 = objekt;
				for(int i2=i+1; i2<i+11 && i2<s.length(); i2++) {
					char c2 = s.charAt(i2);
					if(c2==' ')	break;
					if(Character.isDigit(c2)) break;
					objekt2 += c2;
					
					if(map.containsKey(objekt2)) {
						objekt = objekt2;
						i = i2;
					}
				}
				
				map.get(objekt).operate();			
				objekt = "";
				numberFlag = false;
			}
		}
	}
	
//		
	private Object shrinkNumber() {
		if(stack.peek().getClass().equals(BigInteger.class)) {
			try {
				Integer i = new Integer(stack.peek().toString());
				stack.pop();
				return stack.push(i);
			}catch(NumberFormatException e) {			
			}
		}
		if(stack.peek().getClass().equals(Long.class)) {
			try {
				Long i = new Long(stack.peek().toString());
				stack.pop();
				return stack.push(i);
			}catch(NumberFormatException e) {	
			}
		}
		return stack.peek();
		
	}

	private void setStandarOP() {
		//public final static String[] Operators = {"+", "-", "*", "x", "/", ":", "%", "&", "|", "^", "log", "ln", "lg", "pot", "int", "long", "double", "bigint", "shrink", "push", "pop", "pushn", "popn", "loop", "pushloop", "cto", "addall"};
		//public final static String[] Other = {"size", "e", "pi", "tau", "maxint", "minint", "maxlong", "minlong", "rpntest", "looptest", "rptest" };
		
		map.clear();
		map.put("standartop", () -> setStandarOP());
		// Verweise
		map.put(":", ()-> map.get("/").operate());
		map.put("x", ()-> map.get("*").operate());
		map.put("^", ()-> map.get("xor").operate());
		map.put("!", ()-> map.get("#").operate());
		map.put(".", ()-> map.get("push").operate());
		
		// Stuff
		map.put("size", 	()->stack.push(stack.size()) 		);
		map.put("sizes", 	()->stack.push(saveStack.size()) 	);
		map.put("false", 	()->stack.push(false) 		);
		map.put("true", 	()->stack.push(true) 	);
		map.put("e", 		()->stack.push(Math.E) 				);
		map.put("pi",		()->stack.push(Math.PI) 			);
		map.put("tau", 		()->stack.push(Math.PI*2) 			);
		map.put("maxint",	()->stack.push(Integer.MAX_VALUE) 	);
		map.put("minint",	()->stack.push(Integer.MIN_VALUE) 	);
		map.put("maxlong",	()->stack.push(Long.MAX_VALUE)		);
		map.put("minlong", 	()->stack.push(Long.MIN_VALUE) 		);
		map.put("maxdouble",	()->stack.push(Double.MAX_VALUE)		);
		map.put("mindouble", 	()->stack.push(Double.MIN_VALUE) 		);
		
		map.put("pop", 		()->stack.pop() 					);
		map.put("push", 	()->stack.push(stack.peek()) 		);
		//Konversion
		map.put("int",		()->stack.push( ((Number)stack.pop()).intValue()) 		);
		map.put("double", 	()->stack.push( ((Number)stack.pop()).doubleValue()) 	);
		map.put("long",		()->stack.push( ((Number)stack.pop()).longValue()) 		);
		map.put("bigint",	()->stack.push(  new BigInteger(((Number)stack.pop()).longValue()+"")) );
		map.put("string",	()->stack.push( stack.peek().getClass().equals(String.class) ? "("+stack.pop()+")":stack.pop().toString()) 		);
		map.put("stringn",	()->{
			Integer n1 = ((Number) stack.pop()).intValue();
			String s = "";
			for(int i=0; i<n1; i++)
				s = stack.pop().toString()+s;
			stack.push(s);
		});
		map.put("boolean", 	()-> {
			if(stack.peek().getClass().equals(Boolean.class)) return;
			Object o = stack.pop();
			if(o.getClass().getSuperclass().equals(Number.class)) {
				if(o.getClass().equals(BigInteger.class))	stack.push( ((BigInteger)o).compareTo(BigInteger.ZERO)>0 ? true:false );
				else stack.push( ((Number)o).intValue()>0 ? true:false );
			}
		});
		map.put("shrink",   ()->shrinkNumber() );	
		map.put("save",   	()->saveStack.push(stack.pop()) );	
		map.put("load",   	()->stack.push(saveStack.pop()) );	
		map.put("clear",   	()->stack.clear() );
		map.put("sclear", ()->saveStack.clear() );	
		map.put("=", ()-> calcExp(stack.pop().toString()));
		
		// Testcases
		map.put("looptest",	()->calcExp("("+LoopTest+")") );
		map.put("endiflooptest",	()->calcExp("("+EndifLoopTest+")") );
		map.put("rpntest",   ()->calcExp("("+RPNtest+")") );
		map.put("savealltest",   ()->calcExp("("+SaveAllTest+")") );
		map.put("test",   ()->calcExp( "("+Tests[((Number)stack.pop()).intValue()]+")") );

		Op logicOp=()->{
			disableNextInfo = true;
			
			while((stack.peek().getClass().equals(String.class))) map.get("=").operate(); 

			Object n1 = (Object) stack.pop();
		
			while((stack.peek().getClass().equals(String.class))) map.get("=").operate();

			if(n1.getClass().equals(Boolean.class) || stack.peek().getClass().equals(Boolean.class)) {
				if(!n1.getClass().equals(Boolean.class))	calcExp(n1+" boolean");
				else if(stack.peek().getClass().equals(Boolean.class)) map.get("boolean").operate();
				stack.push(n1);
			}else if(stack.peek().getClass().equals(Integer.class) 			&& n1.getClass().equals(Integer.class)) 
				stack.push(n1);
			else {
				if(!stack.peek().equals(Long.class) && !stack.peek().getClass().equals(Integer.class)) throw new Exception("Logische Operatoren nicht verfügbar für "+stack.peek().getClass().getSimpleName());
				if(!n1.getClass().equals(Long.class) && !n1.getClass().equals(Integer.class)) 					throw new Exception("Logische Operatoren nicht verfügbar für "+n1.getClass().getSimpleName());
				if(n1.getClass().equals(Integer.class))
					map.get("long").operate();
				stack.push(n1);
			}
			disableNextInfo = false;
		};
		//
		// Arithmetische Vorbereitung
		Op arithOp=()->{
			
			disableNextInfo = true;
			Object o = stack.pop();
			while((stack.peek().getClass().equals(String.class))) map.get("=").operate(); 
			stack.push(o);
			while((stack.peek().getClass().equals(String.class))) map.get("=").operate(); 
			Number n1 = (Number) stack.pop();
			if(stack.peek().getClass().equals(Integer.class) 			&& n1.getClass().equals(Integer.class)) 
				stack.push(n1.intValue());
					
			else if(stack.peek().getClass().equals(Double.class) 		|| n1.getClass().equals(Double.class)) {
				if(!stack.peek().getClass().equals(Double.class))
					map.get("double").operate();
				stack.push(n1.doubleValue());
			}
			else if(stack.peek().getClass().equals(BigInteger.class) 	|| n1.getClass().equals(BigInteger.class))	{
				if(!stack.peek().getClass().equals(BigInteger.class)) 
					map.get("bigint").operate();
				stack.push(new BigInteger(n1.longValue()+""));
			}
			else {
				if(!stack.peek().getClass().equals(Long.class)) 
					map.get("long").operate();
				stack.push(n1.longValue());
			}
			disableNextInfo = false;
		};
		//
		
		map.put("#", () -> {
			Object n1 = stack.pop();
			if(n1.getClass().equals(Integer.class))			 stack.push( -((Integer)n1) 						);
			else if(n1.getClass().equals(Long.class))		 stack.push( -((Long)n1) 								);
			else if(n1.getClass().equals(BigInteger.class))	 stack.push( new BigInteger(((Number)n1).longValue()+"").negate()	);
			else if(n1.getClass().equals(Double.class))		 stack.push( -((Double)n1) 							);
			else											 stack.push(!((Boolean)n1));
		});
		map.put("+", ()->{
			arithOp.operate();
			Number n1 = (Number)stack.pop();
			if(n1.getClass().equals(Integer.class))			 stack.push( (Integer)stack.pop()	+ (Integer) n1				);
			else if(n1.getClass().equals(Long.class))		 stack.push( (Long)stack.pop()	+ (Long) 	n1				);
			else if(n1.getClass().equals(BigInteger.class))	 stack.push( ((BigInteger)stack.pop()).add((BigInteger)n1)	);
			else 											 stack.push( (Double)stack.pop()	+ (Double) 	n1				);
		});
		map.put("-", ()->{
			arithOp.operate();
			Number n1 = (Number)stack.pop();
			if(n1.getClass().equals(Integer.class))			 stack.push( (Integer)stack.pop()	- (Integer) n1				);
			else if(n1.getClass().equals(Long.class))		 stack.push( (Long)stack.pop()	- (Long) 	n1				);
			else if(n1.getClass().equals(BigInteger.class))	 stack.push( ((BigInteger)stack.pop()).subtract((BigInteger)n1)	);
			else 											 stack.push( (Double)stack.pop()	- (Double) 	n1				);
		});
		map.put("/", ()->{
			arithOp.operate();
			Number n1 = (Number)stack.pop();
			if(n1.getClass().equals(Integer.class))			 stack.push( (Integer)stack.pop()	/ (Integer) n1				);
			else if(n1.getClass().equals(Long.class))		 stack.push( (Long)stack.pop()	/ (Long) 	n1				);
			else if(n1.getClass().equals(BigInteger.class))	 stack.push( ((BigInteger)stack.pop()).divide((BigInteger)n1)	);
			else 											 stack.push( (Double)stack.pop()	/ (Double) 	n1				);
		});
		map.put("*", ()->{
			arithOp.operate();
			Number n1 = (Number)stack.pop();
			if(n1.getClass().equals(Integer.class))			 stack.push( (Integer)stack.pop()	* (Integer) n1					);
			else if(n1.getClass().equals(Long.class))		 stack.push( (Long)stack.pop()	* (Long) 	n1					);
			else if(n1.getClass().equals(BigInteger.class))	 stack.push( ((BigInteger)stack.pop()).multiply((BigInteger)n1)	);
			else 											 stack.push( (Double)stack.pop()	* (Double) 	n1					);
		});
		map.put("%", ()->{
			arithOp.operate();
			Number n1 = (Number)stack.pop();
			if(n1.getClass().equals(Integer.class))			 stack.push( (Integer)stack.pop()	% (Integer) n1					);
			else if(n1.getClass().equals(Long.class))		 stack.push( (Long)stack.pop()	% (Long) 	n1					);
			else if(n1.getClass().equals(BigInteger.class))	 stack.push( ((BigInteger)stack.pop()).remainder((BigInteger)n1)	);
			else 											 stack.push( (Double)stack.pop()	% (Double) 	n1					);
		});
		map.put("ln", ()->{
			Number n1 = (Number)stack.pop();
			if(n1.getClass().equals(Integer.class))			stack.push( Math.log((Integer)n1)	);
			else if(n1.getClass().equals(Long.class))		stack.push( Math.log((Long)n1)		);
			else if(n1.getClass().equals(BigInteger.class))	throw new Exception("Operation ln nicht verfügbar für BigInteger");
			else 											stack.push( Math.log((Double)n1)		);
		});
		map.put("lg", ()->{
			Number n1 = (Number)stack.pop();
			if(n1.getClass().equals(Integer.class))			stack.push( Math.log10((Integer)n1)	);
			else if(n1.getClass().equals(Long.class))		stack.push( Math.log10((Long)n1)		);
			else if(n1.getClass().equals(BigInteger.class))	throw new Exception("Operation ln nicht verfügbar für BigInteger");
			else 											stack.push( Math.log10((Double)n1)		);
		});
		map.put("log", ()->{
			Number n1 = (Number)stack.pop();
			calcExp("ln "+n1+" ln /");
		});
		map.put("pot", ()->{
			arithOp.operate();
			Number n1 = (Number)stack.pop();
			if(n1.getClass().equals(Integer.class))			stack.push( Math.pow(((Number)stack.pop()).doubleValue(), n1.doubleValue())	);
			else if(n1.getClass().equals(Long.class))		stack.push( Math.pow(((Number)stack.pop()).doubleValue(), n1.doubleValue())  );
			else if(n1.getClass().equals(BigInteger.class))	throw new Exception("Operation ln nicht verfügbar für BigInteger");
			else 											stack.push( Math.pow(((Number)stack.pop()).doubleValue(), n1.doubleValue())  );
		});
		map.put(">", ()->{
			arithOp.operate();
			Number n1 = (Number)stack.pop();
			if(n1.getClass().equals(Integer.class))			 stack.push( (Integer)stack.pop()	> (Integer) n1				);
			else if(n1.getClass().equals(Long.class))		 stack.push( (Long)stack.pop()	> (Long) 	n1				);
			else if(n1.getClass().equals(BigInteger.class))	 stack.push( ((BigInteger)stack.pop()).compareTo(((BigInteger)n1))==1	? true:false);
			else 											 stack.push( (Double)stack.pop()	> (Double) 	n1				);
		});
		map.put("<", ()->{
			arithOp.operate();
			Number n1 = (Number)stack.pop();
			if(n1.getClass().equals(Integer.class))			 stack.push( (Integer)stack.pop()	< (Integer) n1				);
			else if(n1.getClass().equals(Long.class))		 stack.push( (Long)stack.pop()	< (Long) 	n1				);
			else if(n1.getClass().equals(BigInteger.class))	 stack.push( ((BigInteger)stack.pop()).compareTo(((BigInteger)n1))==-1	? true:false);
			else 											 stack.push( (Double)stack.pop()	< (Double) 	n1				);
		});
		map.put(">=", ()->{
			arithOp.operate();
			Number n1 = (Number)stack.pop();
			if(n1.getClass().equals(Integer.class))			 stack.push( (Integer)stack.pop()	>= (Integer) n1				);
			else if(n1.getClass().equals(Long.class))		 stack.push( (Long)stack.pop()	>= (Long) 	n1				);
			else if(n1.getClass().equals(BigInteger.class))	 stack.push( ((BigInteger)stack.pop()).compareTo(((BigInteger)n1))>=0	? true:false);
			else 											 stack.push( (Double)stack.pop()	>= (Double) 	n1				);
		});
		map.put("<=", ()->{
			arithOp.operate();
			Number n1 = (Number)stack.pop();
			if(n1.getClass().equals(Integer.class))			 stack.push( (Integer)stack.pop()	<= (Integer) n1				);
			else if(n1.getClass().equals(Long.class))		 stack.push( (Long)stack.pop()	<= (Long) 	n1				);
			else if(n1.getClass().equals(BigInteger.class))	 stack.push( ((BigInteger)stack.pop()).compareTo(((BigInteger)n1))<=0	? true:false);
			else 											 stack.push( (Double)stack.pop()	<= (Double) 	n1				);
		});
		map.put("==", ()->{
			Object o = stack.pop();
			if(stack.peek().getClass().getSuperclass().equals(Number.class) && o.getClass().getSuperclass().equals(Number.class)) {
				stack.push(o);
				arithOp.operate();
				Number n1 = (Number)stack.pop();
				if(n1.getClass().equals(Integer.class))			 stack.push( (Integer)stack.pop()	== (Integer) n1				);
				else if(n1.getClass().equals(Long.class))		 stack.push( (Long)stack.pop()	== (Long) 	n1				);
				else if(n1.getClass().equals(BigInteger.class))	 stack.push( ((BigInteger)stack.pop()).compareTo(((BigInteger)n1))==0	? true:false);
				else 											 stack.push( (Double)stack.pop()	== (Double) 	n1				);
			}else {
				stack.push(o);
				map.get("===").operate();
			}
		});
		map.put("!=", ()->calcExp("== #"));
		map.put("floor",   ()-> {
			if(stack.peek().getClass().equals(Double.class))	Math.floor((Double)stack.pop());
		});
		map.put("round",   ()-> {
			if(stack.peek().getClass().equals(Double.class))	Math.round((Double)stack.pop());
		});
		map.put("ceil",   ()-> {
			if(stack.peek().getClass().equals(Double.class))	Math.ceil((Double)stack.pop());
		});
		//Logic
		map.put("|", ()->{
			logicOp.operate();
			Object n1 = stack.pop();
			if(n1.getClass().equals(Integer.class))			stack.push( (Integer)stack.pop()	| (Integer) n1	);
			else if(n1.getClass().equals(Long.class))		stack.push( (Long)stack.pop()	| (Long) 	n1	);
			else stack.push( ((Boolean)stack.pop()).booleanValue() | ((Boolean)n1).booleanValue());
		});
		map.put("xor", ()->{
			logicOp.operate();
			Object n1 = stack.pop();
			if(n1.getClass().equals(Integer.class))			stack.push( (Integer)stack.pop()	^ (Integer) n1	);
			else if(n1.getClass().equals(Long.class))		stack.push( (Long)stack.pop()	^ (Long) 	n1	);
			//else stack.push((Boolean) stack.pop | (Boolean) n1);											stack.push( (Long)stack.pop()	^ (Long) 	n1	);
		});
		map.put("&", ()->{
			logicOp.operate();
			Object n1 = stack.pop();
			if(n1.getClass().equals(Integer.class))			stack.push( (Integer)stack.pop()	& (Integer) n1	);
			else if(n1.getClass().equals(Long.class))		stack.push( (Long)stack.pop()	& (Long) 	n1	);
			//else stack.push((Boolean) stack.pop | (Boolean) n1);	stack.push( (Long)stack.pop()	& (Long) 	n1	);
		});
		map.put("&&", ()->{	
			Object o = stack.pop();
			while(stack.peek().getClass().equals(String.class)) map.get("=").operate();
			if(!stack.peek().getClass().equals(Boolean.class)) map.get("boolean").operate();;
			if(((Boolean)stack.pop()).equals(Boolean.FALSE)) {
				stack.push(false);
				return;
			}
			
			stack.push(o);
			while(stack.peek().getClass().equals(String.class)) map.get("=").operate();
			if(!stack.peek().getClass().equals(Boolean.class)) map.get("boolean").operate();;
			if(((Boolean)stack.pop()).equals(Boolean.FALSE)) {
				stack.push(false);
				return;
			}
			stack.push(true);
		});
		map.put("||", ()->{
			Object o = stack.pop();
			while(stack.peek().getClass().equals(String.class)) map.get("=").operate();
			if(!stack.peek().getClass().equals(Boolean.class)) map.get("boolean").operate();;
			if(((Boolean)stack.pop()).equals(Boolean.TRUE)) {
				stack.push(true);
				return;
			}
			
			stack.push(o);
			while(stack.peek().getClass().equals(String.class)) map.get("=").operate();
			if(!stack.peek().getClass().equals(Boolean.class)) map.get("boolean").operate();;
			if(((Boolean)stack.pop()).equals(Boolean.TRUE)) {
				stack.push(false);
				return;
			}
			stack.push(false);
		});
		map.put("===", ()->{
			Object o = stack.pop();
			while(stack.peek().getClass().equals(String.class)) map.get("=").operate();
			if(!stack.peek().getClass().equals(Boolean.class)) map.get("boolean").operate();;
			
			stack.push(o);
			while(stack.peek().getClass().equals(String.class)) map.get("=").operate();
			if(!stack.peek().getClass().equals(Boolean.class)) map.get("boolean").operate();;

			stack.push( ((Boolean)stack.pop()).equals((Boolean)stack.pop()) );
		});

		// Loops
		map.put("pushn", ()->{
			String n1 = stack.pop().toString();
			calcExp("("+stack.pop()+") "+n1+"loop");
		});
		map.put("popn", ()->{
			String n1 = stack.pop().toString();
			calcExp("(pop)"+n1+"loop");
		});
		map.put("loop", () -> {
			Integer n1 = ((Number)stack.pop()).intValue();
			String arg = stack.pop().toString();
			cr.accept(String.format("  Start LOOP_%d(%s)", n1, arg));
			for(int i=0, count=0; i<n1; i++) {
				calcExp(arg);
				calcExp("maxloopsize");
				if(++count > Integer.parseInt(stack.pop().toString())) {
					cr.accept("MAX LOOP SIZE EXCEDED --> BREAK LOOP");
					break;
				}
			}
			cr.accept(String.format("  End LOOP_%d(%s)", n1, arg));
		});
		map.put("pushloop", () -> { // pusht Ergebnis jeder Iteration
			Integer n1 = ((Number)stack.pop()).intValue();
			String arg = stack.pop().toString();
			cr.accept(String.format("  Start PUSHLOOP_%d(%s)", n1, arg));
			for(int i=0, count=0; i<n1; i++) {
				calcExp(arg+ (i<n1-1? "push":""));
				map.get("maxloopsize").operate();
				if(++count > Integer.parseInt(stack.pop().toString())) {
					cr.accept("MAX LOOP SIZE EXCEDED --> BREAK LOOP");
					break;
				}
			}
			cr.accept(String.format("  End PUSHLOOP_%d(%s)", n1, arg));
		});
		map.put("loopall", () -> {
			if(!stack.empty()) calcExp("size 1- loop");
		});
		map.put("endifloop", () -> {
			String arg = stack.pop().toString();
			String arg2 = stack.peek().toString();
			cr.accept(String.format("  Start ENDIFLOOP (BOOL)(%s) (EXP)(%s)", arg, arg2));
			//while(stack.peek().getClass().equals(String.class)) map.get("=").operate();
			//if(!stack.peek().getClass().equals(Boolean.class)) map.get("boolean").operate();
			int count = 0;
			while(true) {			
				calcExp(arg);				
				if(!((Boolean)stack.pop()).equals(Boolean.TRUE)) break;
				calcExp(arg2);
				map.get("maxloopsize").operate();
				if(++count > Integer.parseInt(stack.pop().toString())) {
					cr.accept("MAX LOOP SIZE EXCEDED --> BREAK LOOP");
					break;
				}
			}
			cr.accept(String.format("  End ENDIFLOOP (BOOL)(%s) (EXP)(%s)", arg, arg2));
		});

		// other
		map.put("map", () -> map.put(stack.pop().toString(), ()->calcExp(stack.pop().toString())) );
		map.put("mapto", () -> 	map.put(stack.pop().toString(), map.get(stack.pop())) );
		map.put("unmap", () -> { if(!stack.peek().toString().equals("standartop")) map.remove(stack.pop().toString()); });
		map.put("exists", () -> stack.push(map.containsKey(stack.pop())) );
		map.put("maxloopsize", () -> calcExp("100000") );
		
		//More logic
		map.put("if", ()->{
			while(stack.peek().getClass().equals(String.class)) map.get("=").operate();
			if(!stack.peek().getClass().equals(Boolean.class)) map.get("boolean").operate();
			boolean b = (Boolean)stack.pop();
			Object o = stack.pop();
			
			if(!b) {
				if(stack.pop().toString().equals("elseif")) {
					map.get("if").operate();
				}
				else calcExp(stack.pop().toString());
			}else {
				while(true) {
					if(stack.peek().toString().equals("elseif")) {
						stack.pop();
						stack.pop();
						stack.pop();
					}else if(stack.peek().toString().equals("else")) {
						stack.pop();
						stack.pop();
						break;
					}
					else break;
				}
			}
			
			if(b) calcExp(o.toString());
			
		});
		map.put("else", () -> stack.push("else"));
		map.put("elseif", () -> stack.push("elseif"));
		map.put("savelog", () -> saveLog());
		map.put("list", ()-> map.forEach( (String s,Op op)-> stack.push(s) ));
		map.put("listelement", () -> {if(map.containsKey(stack.peek())) stack.push(stack.pop());});
	}

	@Override
	public int getSize() {
		return stack.size();
	}
	
	@Override
	public int getSizeS() {
		return saveStack.size();
	}
	
	@Override
	public void printStack() {
		System.out.println("Top of Stack:");
		stack.forEach(e-> System.out.println(e +"->"+ e.getClass().getSimpleName()));
	}

	@Override
	public void clearStack() {
		stack.clear();
		saveStack.clear();
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
	public Stream<Object> streamStack() {
		return stack.stream();
	}

	@Override
	public Stream<Object> streamSaveStack() {
		return saveStack.stream();
	}
	
	@Override
	public void setInfo(int i) {
		if(i==MaxInfo) info = i;
		else if(i==MinInfo) info = i;
	}
	
	@Override
	public void saveLog() {
		JFileChooser fc = new JFileChooser();
		if(fc.showSaveDialog(null) != JFileChooser.APPROVE_OPTION)
			return;
		
		try (PrintWriter pw = new PrintWriter(fc.getSelectedFile())){
			list.forEach(a -> pw.print(a+" \n"));
		}catch(Exception e) {
			
		}
	}

	@FunctionalInterface
	private interface Op{
		public void operate() throws Exception;
	}
	@FunctionalInterface
	private interface method<T> {
		public T apply() throws Exception;
	}

}
