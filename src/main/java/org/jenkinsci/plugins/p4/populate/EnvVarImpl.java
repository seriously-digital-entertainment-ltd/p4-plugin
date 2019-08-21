package org.jenkinsci.plugins.p4.populate;

import hudson.Extension;

import org.kohsuke.stapler.DataBoundConstructor;

public class EnvVarImpl extends Populate {

	private static final long serialVersionUID = 1L;

	public final String populateTypeString;
	private String expandedPopulateTypeString;

	public final boolean have_fromData;
	public final boolean modtime_fromData;
	public final boolean quiet_fromData;
    public final String pin_fromData;
	public final boolean replace_fromData;
	public final boolean delete_fromData;
    public final boolean revert_fromData;

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
		boolean have_fromData, boolean force, boolean modtime_fromData,
		boolean quiet_fromData, String pin_fromData, ParallelSync parallel,
		boolean replace_fromData, boolean delete_fromData, boolean revert_fromData)
	{
		super(have_fromData, force, modtime_fromData, quiet_fromData, pin_fromData, parallel, replace_fromData, delete_fromData, revert_fromData);
		this.populateTypeString = populateTypeString;

		this.have_fromData = have_fromData;
		this.modtime_fromData = modtime_fromData;
		this.quiet_fromData = quiet_fromData;
		this.pin_fromData = pin_fromData;
		this.replace_fromData = replace_fromData;
		this.delete_fromData = delete_fromData;
		this.revert_fromData = revert_fromData;
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
				Set(true, false, modtime_fromData, quiet_fromData, pin_fromData, parallel, replace_fromData, delete_fromData, false);
				break;
			case CheckOnly:
				Set(false, false, false, quiet_fromData, null, parallel, false, false, false);
				break;
			case ForceClean:
				Set(have_fromData, true, modtime_fromData, quiet_fromData, pin_fromData, parallel, false, false, false);
				break;
			case SyncOnly:
				Set(have_fromData, false, modtime_fromData, quiet_fromData, pin_fromData, parallel, false, false, revert_fromData);
				break;
		}
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
