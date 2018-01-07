package com.sasd13.proadmin.backend.util.adapter.itf;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.adapter.IAdapter;

public abstract class ITFAdapter<M, I> {

	private IAdapter<I, M> adapterI2M;
	private IAdapter<M, I> adapterM2I;

	protected ITFAdapter(IAdapter<I, M> adapterI2M, IAdapter<M, I> adapterM2I) {
		this.adapterI2M = adapterI2M;
		this.adapterM2I = adapterM2I;
	}

	public M adaptI2M(I item) {
		return adapterI2M.adapt(item);
	}

	public List<M> adaptI2M(List<I> items) {
		List<M> results = new ArrayList<>();

		for (I item : items) {
			results.add(adaptI2M(item));
		}

		return results;
	}

	public I adaptM2I(M item) {
		return adapterM2I.adapt(item);
	}

	public List<I> adaptM2I(List<M> items) {
		List<I> results = new ArrayList<>();

		for (M item : items) {
			results.add(adaptM2I(item));
		}

		return results;
	}
}
