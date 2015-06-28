package rd.huma.dashboard.util;

import java.util.Optional;
import java.util.function.Supplier;

public final class IntentoBuscadorCandado<T,R> {

	private int intentos=10;
	private Supplier<T> supply;
	
	private IntentoBuscadorCandado(Supplier<T> suply) {
		this.supply = suply;
	}
	
	
	@SuppressWarnings("unchecked")
	public R get(){
		if (intentos<1){
			throw new IllegalStateException("Cantidad intentos ha sido excedido.");
		}
		
		
		intentos--;
		Optional<T> resultado = (Optional<T>) supply.get();
		if (resultado.isPresent()){
			return (R) resultado.get();
		}else{
			try {
				Thread.sleep(1000*60*3);
			} catch (InterruptedException e) {}
			return get();
		}
		
	}
	
	
	public  static <T,R>  IntentoBuscadorCandado<T,R> of(Supplier<T> supply){
		return new IntentoBuscadorCandado<T,R>(supply);
	}
 }