package org.jenkinsci.plugins.p4.populate;

import java.io.Serializable;

import hudson.DescriptorExtensionList;
import hudson.ExtensionPoint;
import hudson.model.Describable;
import jenkins.model.Jenkins;

public abstract class Populate implements ExtensionPoint, Describable<Populate>, Serializable {

	private static final long serialVersionUID = 1L;

	protected boolean have; // ! sync '-p'
	protected boolean force; // sync '-f'
	protected boolean modtime;
	protected boolean quiet; // task '-q'
    protected String pin;
    protected ParallelSync parallel;

	// auto clean only
	protected boolean replace;
	protected boolean delete;
    
    // sync only
    protected boolean revert;

	public Populate(boolean have, boolean force, boolean modtime, boolean quiet, String pin, ParallelSync parallel,
			boolean replace, boolean delete, boolean revert) {
		Set(have, force, modtime, quiet, pin, parallel,
			replace, delete, revert);
	}

	protected void Set(boolean have, boolean force, boolean modtime, boolean quiet, String pin, ParallelSync parallel,
			boolean replace, boolean delete, boolean revert) {
		this.have = have;
		this.force = force;
		this.modtime = modtime;
		this.pin = pin;
		this.quiet = quiet;
		this.parallel = parallel;

		this.replace = replace;
		this.delete = delete;
		this.revert = revert;
	}

	// variables
	public boolean isHave() {
		return have;
	}

	public boolean isForce() {
		return force;
	}

	public boolean isModtime() {
		return modtime;
	}

	public boolean isQuiet() {
		return quiet;
	}

	public String getPin() {
		return pin;
	}

	public ParallelSync getParallel() {
		return parallel;
	}

	// auto clean only
	public boolean isReplace() {
		return replace;
	}

	public boolean isDelete() {
		return delete;
	}

	// sync only
	public boolean isRevert() {
		return revert;
	}

	// other
	public PopulateDescriptor getDescriptor() {
		Jenkins j = Jenkins.getInstance();
		if (j != null) {
			return (PopulateDescriptor) j.getDescriptor(getClass());
		}
		return null;
	}

	public static DescriptorExtensionList<Populate, PopulateDescriptor> all() {
		Jenkins j = Jenkins.getInstance();
		if (j != null) {
			return j.<Populate, PopulateDescriptor> getDescriptorList(Populate.class);
		}
		return null;
	}

	public boolean implementsPopulateType(Class populateClass) {
		return getClass().isAssignableFrom(populateClass);
	}
}
