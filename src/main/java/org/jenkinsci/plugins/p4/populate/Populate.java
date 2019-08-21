package org.jenkinsci.plugins.p4.populate;

import hudson.DescriptorExtensionList;
import hudson.ExtensionPoint;
import hudson.model.Describable;

import java.io.Serializable;

import jenkins.model.Jenkins;

public abstract class Populate implements ExtensionPoint,
		Describable<Populate>, Serializable {

	private static final long serialVersionUID = 2L;

	protected boolean have; // ! sync '-p'
	protected boolean force; // sync '-f'
	protected boolean modtime;
	protected boolean quiet; // task '-q'
	protected String pin;

	// auto clean only
	protected boolean replace;
	protected boolean delete;

	// sync only
	protected boolean revert;

	public Populate(boolean have, boolean force, boolean modtime,
			boolean quiet, String pin,
			boolean replace, boolean delete, boolean revert)
	{
		Set(have, force, modtime,
			quiet, pin,
			replace, delete, revert);
	}

	protected void Set(boolean have, boolean force, boolean modtime,
			boolean quiet, String pin,
			boolean replace, boolean delete, boolean revert)
	{
		this.have = have;
		this.force = force;
		this.modtime = modtime;
		this.pin = pin;
		this.quiet = quiet;

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
		return (PopulateDescriptor) Jenkins.getInstance().getDescriptor(
				getClass());
	}

	public static DescriptorExtensionList<Populate, PopulateDescriptor> all() {
		return Jenkins.getInstance()
				.<Populate, PopulateDescriptor> getDescriptorList(
						Populate.class);
	}

	public boolean implementsPopulateType(Class populateClass) {
		return getClass().isAssignableFrom(populateClass);
	}
}
