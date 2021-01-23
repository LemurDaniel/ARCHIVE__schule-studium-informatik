package exceptions;

@SuppressWarnings("serial")
public abstract class CustomException extends Exception {
	
	protected String title;
	protected Typ<? extends CustomException> typ;
	
	public CustomException(String title, String message, Typ<? extends CustomException>  typ) {
		super(message);
		this.title = title;
		this.typ = typ;
	}
	
	public String getTitle() {
		return title;
	}
	public boolean isTyp(Typ<? extends CustomException>  typ) {
		return this.typ.isTyp(typ);
	};

	
	
	
	
	protected static class Typ<T extends CustomException>{
		
		private Class<T> klasse;
		private int nummer;

		protected Typ(Class<T> klasse, int nummer) {
			this.klasse = klasse;
			this.nummer = nummer;
		}
		
		protected int getNummer() {
			return nummer;
		}
		
		public boolean isTyp(Typ<? extends CustomException>  typ) {
			if(!klasse.equals(typ.klasse)) return false;
			if(nummer != typ.nummer) return false;
			return true;
		}
	}
}
