package org.jenkinsci.plugins.p4.populate;

import hudson.Extension;

import org.kohsuke.stapler.DataBoundConstructor;

public class EnvVarImpl extends Populate {

	private static final long serialVersionUID = 1L;

	private final String populateTypeString;

	private String expandedPopulateTypeString;
	public enum PopulateType {
		AutoClean,
		CheckOnly,
		ForceClean,
		SyncOnly
	};
	private PopulateType populateType;
	
	/**
	 * Scriptable, direct to some internal implementation
	 * 
	 * @param populateType
	 */
	@DataBoundConstructor
	public EnvVarImpl(String populateTypeString,
		boolean have, boolean force, boolean modtime,
		boolean quiet, String pin,
		boolean replace, boolean delete, boolean revert)
	{
		super(have, force, modtime, quiet, pin, replace, delete, revert);
		this.populateTypeString = populateTypeString;
	}

	public String getPopulateTypeString() {
		return populateTypeString;
	}

	public void setExpandedPopulateTypeString(String expandedPopulateTypeString) {
		this.expandedPopulateTypeString = expandedPopulateTypeString;
		try {
			populateType = PopulateType.valueOf(expandedPopulateTypeString);
		}
		catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid env var type " + expandedPopulateTypeString + ". " + e.getMessage(), e.getCause());
		}

		switch (populateType) {
			case AutoClean:
				Set(true, false, modtime, quiet, pin, replace, delete, false);
				break;
			case CheckOnly:
				Set(false, false, false, quiet, null, false, false, false);
				break;
			case ForceClean:
				Set(have, true, modtime, quiet, pin, false, false, false);
				break;
			case SyncOnly:
				Set(have, false, modtime, quiet, pin, false, false, revert);
				break;
		}
	}
	public String getExpandedType() {
		return expandedPopulateTypeString;
	}
	public PopulateType getPopulateType() {
		return populateType;
	}

	// override
	public boolean implementsPopulateType(Class populateClass) {
		if (populateType == PopulateType.AutoClean && populateClass.isAssignableFrom(AutoCleanImpl.class))
			return true;
		if (populateType == PopulateType.CheckOnly && populateClass.isAssignableFrom(CheckOnlyImpl.class))
			return true;
		if (populateType == PopulateType.ForceClean && populateClass.isAssignableFrom(ForceCleanImpl.class))
			return true;
		if (populateType == PopulateType.SyncOnly && populateClass.isAssignableFrom(SyncOnlyImpl.class))
			return true;
		return false;
	}

	@Extension
	public static final class DescriptorImpl extends PopulateDescriptor {
		@Override
		public String getDisplayName() {
			return "Env Var";
		}
	}
}
