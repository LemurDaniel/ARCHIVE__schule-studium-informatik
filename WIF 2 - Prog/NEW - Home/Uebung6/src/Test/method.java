package Test;

@FunctionalInterface
interface method{
	void apply(String bez, Integer... i);
}

@FunctionalInterface
interface method2 {
	void apply() throws Exception;
}

@FunctionalInterface
interface method3 {
	void apply();
}

