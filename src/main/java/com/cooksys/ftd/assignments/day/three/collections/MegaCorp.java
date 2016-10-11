package com.cooksys.ftd.assignments.day.three.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cooksys.ftd.assignments.day.three.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.day.three.collections.model.Capitalist;
import com.cooksys.ftd.assignments.day.three.collections.model.FatCat;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {

	Map<FatCat, Set<Capitalist>> hiarch = new HashMap<>();
	Set<Capitalist> capset = new HashSet<>();

	/**
	 * Adds a given element to the hierarchy.
	 * <p>
	 * 1 If the given element is already present in the hierarchy, do not add it
	 * 1 and return false
	 * <p>
	 * 3 If the given element has a parent and the parent is not part of the 3
	 * hierarchy, add the parent and then add the given element
	 * <p>
	 * 4 If the given element has no parent but is a Parent itself, add it to
	 * the 4 hierarchy
	 * <p>
	 * 2 If the given element has no parent and is not a Parent itself, do not
	 * add 2 it and return false
	 *
	 * @param capitalist
	 *            the element to add to the hierarchy
	 * @return true if the element was added successfully, false otherwise
	 */
	@Override
	public boolean add(Capitalist capitalist) {

		if (capitalist == null)
			return false;

		if (capset.contains(capitalist) || (!capitalist.hasParent() && !(capitalist instanceof FatCat)))
			return false;

		if (capitalist.hasParent() && !hiarch.containsKey(capitalist.getParent())) {

			add(capitalist.getParent());

			if (capitalist instanceof FatCat)
				hiarch.put((FatCat) capitalist, new HashSet<Capitalist>());

			capset.add(capitalist);

			hiarch.get(capitalist.getParent()).add(capitalist);

			if (capitalist instanceof FatCat)
				hiarch.put((FatCat) capitalist, new HashSet<Capitalist>());

			return true;
		}

		if (capitalist.hasParent() && hiarch.containsKey(capitalist.getParent())) {

			if (capitalist instanceof FatCat)
				hiarch.put((FatCat) capitalist, new HashSet<Capitalist>());

			capset.add(capitalist);

			hiarch.get(capitalist.getParent()).add(capitalist);
		}

		if (!capitalist.hasParent() && capitalist instanceof FatCat) {

			hiarch.put((FatCat) capitalist, new HashSet<Capitalist>());
			capset.add(capitalist);

			return true;
		}

		return false;

	}

	/**
	 * @param capitalist
	 *            the element to search for
	 * @return true if the element has been added to the hierarchy, false
	 *         otherwise
	 */
	@Override
	public boolean has(Capitalist capitalist) {
		return capset.contains(capitalist);
	}

	/**
	 * @return all elements in the hierarchy, or an empty set if no elements
	 *         have been added to the hierarchy
	 */
	@Override
	public Set<Capitalist> getElements() {

		return new HashSet<>(capset);

	}

	/**
	 * @return all parent elements in the hierarchy, or an empty set if no
	 *         parents have been added to the hierarchy
	 */
	@Override
	public Set<FatCat> getParents() {

		return new HashSet<>(hiarch.keySet());

	}

	/**
	 * @param fatCat
	 *            the parent whose children need to be returned
	 * @return all elements in the hierarchy that have the given parent as a
	 *         direct parent, or an empty set if the parent is not present in
	 *         the hierarchy or if there are no children for the given parent
	 */
	@Override
	public Set<Capitalist> getChildren(FatCat fatCat) {

		if (hiarch.get(fatCat) == null)
			return new HashSet<>();

		return new HashSet<>(hiarch.get(fatCat));

	}

	/**
	 * @return a map in which the keys represent the parent elements in the
	 *         hierarchy, and the each value is a set of the direct children of
	 *         the associate parent, or an empty map if the hierarchy is empty.
	 */
	@Override
	public Map<FatCat, Set<Capitalist>> getHierarchy() {

		Map<FatCat, Set<Capitalist>> result = new HashMap<>();

		for (FatCat x : hiarch.keySet()) {
			result.put(x, new HashSet<>(hiarch.get(x)));
		}
		return result;

	}

	/**
	 * @param capitalist
	 * @return the parent chain of the given element, starting with its direct
	 *         parent, then its parent's parent, etc, or an empty list if the
	 *         given element has no parent or if its parent is not in the
	 *         hierarchy
	 */
	@Override
	public List<FatCat> getParentChain(Capitalist capitalist) {

		List<FatCat> list = new ArrayList<>();

		if (capitalist != null && has(capitalist)) {

			FatCat f = capitalist.getParent();

			while (f != null) {
				list.add(f);
				f = f.getParent();
			}
		}
		return list;

	}
}
