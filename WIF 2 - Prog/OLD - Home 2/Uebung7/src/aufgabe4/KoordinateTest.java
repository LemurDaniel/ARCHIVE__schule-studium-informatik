package aufgabe4;

public class KoordinateTest<T extends Number> {
	public T x;
	 public T y;
	 public KoordinateTest(T xp, T yp) {
		x = xp;
	 	y = yp;
	 }
	 
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public static void main(String[] args) {
//	 KoordinateTest<Double> k11, k12;
//	 KoordinateTest<Integer> k21, k22;
//	 KoordinateTest<String> k31;		-wird nicht übersetzt
//	 KoordinateTest<Number> k41, k42;
//
//	 k11 = new KoordinateTest<Float>(2.2d, 3.3d); 	-wird nicht übersetzt
//	 k12 = new KoordinateTest<Double>(2.2d, 3.3d);	
//	 k21 = new KoordinateTest<Integer>(2, 3);
//	 k31 = new Koordinate<String>("11","22");		-wird nicht übersetzt
//	 k41 = new KoordinateTest<Number>(2l, 3l);
//
//	 k41 = new KoordinateTest<Number>(4.4d, 5.5f);	
//	 k11 = new KoordinateTest<Double>(3.3f,9.9d);	
//
//	 KoordinateTest<? super Double> k99;
//	 k99 = k11;	-wird nicht übersetzt
//	 k99 = k41;	
//	 k99 = k31;	-wird nicht übersetzt
//
//	 k11 = k12;	
//	 k12 = k21;	-wird nicht übersetzt
//	 KoordinateTest k55 = new KoordinateTest<Number>(7.7f, 8.8f);
//	 KoordinateTest k66 = new KoordinateTest(7.7f, 8.8f);	-wird nicht übersetzt
//	 }

	 KoordinateTest<Double> k11, k12;
	 KoordinateTest<Integer> k21, k22;
	 //KoordinateTest<String> k31;
	 KoordinateTest<Number> k41, k42;

	 //k11 = new KoordinateTest<Float>(2.2d, 3.3d); 	-wird nicht übersetzt
	 k12 = new KoordinateTest<Double>(2.2d, 3.3d);	
	 k21 = new KoordinateTest<Integer>(2, 3);
	 //k31 = new Koordinate<String>("11","22");		-wird nicht übersetzt
	 k41 = new KoordinateTest<Number>(2l, 3l);

	 k41 = new KoordinateTest<Number>(4.4d, 5.5f);	
	 //k11 = new KoordinateTest<Double>(3.3f,9.9d);	

	 KoordinateTest<? super Double> k99;
	// k99 = k11;	-wird nicht übersetzt
	 k99 = k41;	
	 //k99 = k31;	-wird nicht übersetzt

	 k11 = k12;	
	 //k12 = k21;	-wird nicht übersetzt
	 KoordinateTest k55 = new KoordinateTest<Number>(7.7f, 8.8f);
	 KoordinateTest k66 = new KoordinateTest(7.7f, 8.8f);	//-wird nicht übersetzt
	 }
}
