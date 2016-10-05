package com.cooksys.ftd.assignments.day.three.collections.model;

public class WageSlave implements Capitalist {

    private String name;
    private int salary;
    private FatCat owner;

    public WageSlave(String name, int salary) {
        this(name, salary, null);
    }

    public WageSlave(String name, int salary, FatCat owner) {
        this.name = name;
        this.salary = salary;
        this.owner = owner;
    }

    /**
     * @return the name of the capitalist
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return the salary of the capitalist, in dollars
     */
    @Override
    public int getSalary() {
        return salary;
    }

    /**
     * @return true if this element has a parent, or false otherwise
     */
    @Override
    public boolean hasParent() {
        return owner != null;
    }

    /**
     * @return the parent of this element, or null if this represents the top of a hierarchy
     */
    @Override
    public FatCat getParent() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WageSlave wageSlave = (WageSlave) o;

        if (salary != wageSlave.salary) return false;
        if (name != null ? !name.equals(wageSlave.name) : wageSlave.name != null) return false;
        return owner != null ? owner.equals(wageSlave.owner) : wageSlave.owner == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + salary;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WageSlave{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", owner=" + owner +
                '}';
    }
}
