package co.analysis.utils.functions;

import java.util.function.Function;

@FunctionalInterface

public interface ThrowException<T, R, E extends Throwable> {

	R apply(T t) throws E;

	static <T, R, E extends Throwable> Function<T, R> uncheckedThrow(ThrowException<T, R, E> f) {
		return t -> {
			try {
				return f.apply(t);
			} catch (Throwable e) {
				throw new RuntimeException(e);
			}
		};
	}

}
