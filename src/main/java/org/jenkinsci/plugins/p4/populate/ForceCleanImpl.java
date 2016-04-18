package org.jenkinsci.plugins.p4.populate;

import hudson.Extension;

import org.kohsuke.stapler.DataBoundConstructor;

public class ForceCleanImpl extends Populate {

	private static final long serialVersionUID = 2L;
	
	/**
	 * Force sync of workspace (optional have update)
	 * 
	 * @param have
	 */
	@DataBoundConstructor
	public ForceCleanImpl(boolean have, boolean force, boolean modtime,
			boolean quiet, String pin,
			boolean replace, boolean delete, boolean revert) {
		super(have, true, modtime, quiet, pin, false, false, false);
	}

	@Extension
	public static final class DescriptorImpl extends PopulateDescriptor {

		@Override
		public String getDisplayName() {
			return "Forced clean and sync";
		}
	}
}
