package com.crio.jukebox.repositories;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T,ID> {
	public T save(T entity);
	public List<T> getAll();
	public Optional<T> getById(ID id);
	boolean existsById(ID id);
	public void delete(T entity);
	public void deleteById(ID id);
	public long count();
}
