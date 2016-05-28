package de.mediapool.server.core.executor;

public interface Executor<T> {

	public void execute(T bean);
}
