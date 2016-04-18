package org.jenkinsci.plugins.p4.populate;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;

public class ForceCleanImpl extends Populate {

	private static final long serialVersionUID = 1L;

	/**
	 * Force sync of workspace (optional have update)
	 * 
	 * @param have
	 */
	@DataBoundConstructor
	public ForceCleanImpl(boolean have, boolean modtime, boolean quiet, String pin, ParallelSync parallel,
			boolean replace, boolean delete, boolean revert) {
		super(have, true, modtime, quiet, pin, parallel,
			false, false, false);
	}

	@Extension
	public static final class DescriptorImpl extends PopulateDescriptor {

		@Override
		public String getDisplayName() {
			return "Forced clean and sync";
		}
	}
}
